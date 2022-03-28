package minecraft_converter.operations.restructure;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;
import minecraft_converter.tools.JsonCreationHelper;
import minecraft_converter.tools.Rotation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static minecraft_converter.tools.ColorOperations.*;
import static minecraft_converter.tools.Messages.showException;
import static minecraft_converter.tools.RaytracedWriter.writeRayTex;
import static minecraft_converter.tools.RaytracedWriter.writeRayTexRot;

public class RestructurePiston {
    /**
     * builds the piston atlas from the four source files
     *
     * @param access file access object to use
     * @param files: 0: front of normal piston
     *               1: front of sticky piston
     *               2: side of piston
     *               3: inside of piston
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {
            // read input
            BufferedImage front = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));
            BufferedImage sticky;
            if (files[1] != null) {
                sticky = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[1], false)));
            } else {
                sticky = front;
            }
            BufferedImage side = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[2], false)));
            BufferedImage inner = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[3], false)));
            BufferedImage out_image = new BufferedImage(front.getWidth() * 8, front.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);

            BufferedImage[] rayFrontTextures = new BufferedImage[config.textures.size()];
            BufferedImage[] rayStickyTextures = new BufferedImage[config.textures.size()];
            BufferedImage[] raySideTextures = new BufferedImage[config.textures.size()];
            BufferedImage[] rayInnerTextures = new BufferedImage[config.textures.size()];

            boolean doRaytrace = config.raytraced;
            if (doRaytrace) {
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[0].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[0].substring(0, index) + map.getKey() + files[0].substring(index), true);
                    if (buffer != null)
                        rayFrontTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
                if (files[1] != null) {
                    for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                        int index = files[1].lastIndexOf(".");
                        byte[] buffer = access.getFileBytes(files[1].substring(0, index) + map.getKey() + files[1].substring(index), true);
                        if (buffer != null)
                            rayStickyTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                        else {
                            doRaytrace = false;
                            break;
                        }
                    }
                } else
                    rayStickyTextures = rayFrontTextures;
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[2].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[2].substring(0, index) + map.getKey() + files[2].substring(index), true);
                    if (buffer != null)
                        raySideTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[3].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[3].substring(0, index) + map.getKey() + files[3].substring(index), true);
                    if (buffer != null)
                        rayInnerTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
            }

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            if (doRaytrace) {
                normalTexture = new BufferedImage(front.getWidth() * 8, front.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(front.getWidth() * 8, front.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
            }

            int pixelSize = front.getWidth() / 16;
            // initialize image with color
            for (int x = 0; x < out_image.getWidth(); x++) {
                for (int y = 0; y < out_image.getHeight(); y++) {
                    if ((x < pixelSize*16 && y < pixelSize*16) || x > pixelSize * 88)
                        out_image.setRGB(x, y, getIntColor(new RGB(0, 0, 0, 0)));
                    else
                        out_image.setRGB(x, y, getIntColor(new RGB(255, 255, 255, 0)));
                }
            }

            for (int x = 0; x < front.getWidth(); x++) {
                for (int y = 0; y < front.getHeight(); y++) {
                    // place sticky front
                    out_image.setRGB(x + pixelSize*16, y, sticky.getRGB(x, y));
                    // place front
                    out_image.setRGB(x + pixelSize * 32, y, front.getRGB(x, y));
                    if (doRaytrace) {
                        writeRayTex(rayStickyTextures, normalTexture, merTexture, x + pixelSize*16, y, x, y, config);
                        writeRayTex(rayFrontTextures, normalTexture, merTexture, x + pixelSize * 32, y, x, y, config);
                    }

                    // place inner
                    if (y >= (pixelSize*5) && y < (pixelSize*11) && x >= (pixelSize*5) && x < (pixelSize*11)) {
                        // back arm front/back
                        out_image.setRGB(x + pixelSize * 65, y + pixelSize * 13, inner.getRGB(x, y));
                        out_image.setRGB(x + pixelSize * 71, y + pixelSize * 13, inner.getRGB(x, y));
                        if (doRaytrace) {
                            writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 65, y + pixelSize * 13, x, y, config);
                            writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 71, y + pixelSize * 13, x, y, config);
                        }

                        // back arm sides
                        if (x < (pixelSize * 6) || x >= (pixelSize * 10)) {
                            if (y < (pixelSize * 9)) {
                                out_image.setRGB(x + pixelSize * 59, y + pixelSize * 19, inner.getRGB(x, y));
                                out_image.setRGB(x + pixelSize * 65, y + pixelSize * 19, inner.getRGB(x, y));
                                out_image.setRGB(x + pixelSize * 71, y + pixelSize * 19, inner.getRGB(x, y));
                                out_image.setRGB(x + pixelSize * 77, y + pixelSize * 19, inner.getRGB(x, y));
                                if (doRaytrace) {
                                    writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 59, y + pixelSize * 19, x, y, config);
                                    writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 65, y + pixelSize * 19, x, y, config);
                                    writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 71, y + pixelSize * 19, x, y, config);
                                    writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 77, y + pixelSize * 19, x, y, config);
                                }
                            }
                            if (y >= (pixelSize * 7)) {
                                out_image.setRGB(x + pixelSize * 59, y + pixelSize * 21, inner.getRGB(x, y));
                                out_image.setRGB(x + pixelSize * 65, y + pixelSize * 21, inner.getRGB(x, y));
                                out_image.setRGB(x + pixelSize * 71, y + pixelSize * 21, inner.getRGB(x, y));
                                out_image.setRGB(x + pixelSize * 77, y + pixelSize * 21, inner.getRGB(x, y));
                                if (doRaytrace) {
                                    writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 59, y + pixelSize * 21, x, y, config);
                                    writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 65, y + pixelSize * 21, x, y, config);
                                    writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 71, y + pixelSize * 21, x, y, config);
                                    writeRayTex(rayInnerTextures, normalTexture, merTexture, x + pixelSize * 77, y + pixelSize * 21, x, y, config);
                                }
                            }
                        }
                    }
                    //place sides
                    if (y < pixelSize * 4) {
                        // set four times, for all edges
                        out_image.setRGB(x, y + pixelSize * 16, front.getRGB(x, y));
                        out_image.setRGB(x + pixelSize * 16, y + pixelSize * 16, front.getRGB(x, y));
                        out_image.setRGB(x + pixelSize * 32, y + pixelSize * 16, front.getRGB(x, y));
                        out_image.setRGB(x + pixelSize * 48, y + pixelSize * 16, front.getRGB(x, y));
                        if (doRaytrace) {
                            writeRayTex(rayFrontTextures, normalTexture, merTexture, x, y + pixelSize * 16, x, y, config);
                            writeRayTex(rayFrontTextures, normalTexture, merTexture, x + pixelSize * 16, y + pixelSize * 16, x, y, config);
                            writeRayTex(rayFrontTextures, normalTexture, merTexture, x + pixelSize * 32, y + pixelSize * 16, x, y, config);
                            writeRayTex(rayFrontTextures, normalTexture, merTexture, x + pixelSize * 48, y + pixelSize * 16, x, y, config);
                        }
                        // set pistonarm top
                        if (x >= pixelSize * 8) {
                            //int xRot = y + pixelSize * 64;
                            //int yRot = pixelSize * 20 - x - 1;
                            int[]rot = Rotation.rotate(new int[]{x,y}, pixelSize*16, 270);
                            int xRot = rot[0] + pixelSize * 64;
                            int yRot = rot[1] + pixelSize * 4;

                            out_image.setRGB(xRot, yRot, side.getRGB(x, y));
                            out_image.setRGB(xRot + pixelSize * 4, yRot, side.getRGB(x, y));
                            out_image.setRGB(xRot + pixelSize * 8, yRot, side.getRGB(x, y));
                            out_image.setRGB(xRot + pixelSize * 12, yRot, side.getRGB(x, y));

                            if (doRaytrace) {
                                writeRayTexRot(raySideTextures, normalTexture, merTexture, xRot, yRot, x, y, 270, config);
                                writeRayTexRot(raySideTextures, normalTexture, merTexture, xRot + pixelSize * 4, yRot, x, y, 270, config);
                                writeRayTexRot(raySideTextures, normalTexture, merTexture, xRot + pixelSize * 8, yRot, x, y, 270, config);
                                writeRayTexRot(raySideTextures, normalTexture, merTexture, xRot + pixelSize * 12, yRot, x, y, 270, config);
                            }
                        } else {
                            // set pistonarm bottom
                            //int xRot = y + pixelSize * 65;
                            //int yRot = pixelSize * 32 - x - 1;
                            int[]rot = Rotation.rotate(new int[]{x,y}, pixelSize*16, 270);
                            int xRot = rot[0] + pixelSize * 65;
                            int yRot = rot[1] + pixelSize * 16;

                            out_image.setRGB(xRot, yRot, side.getRGB(x, y));
                            out_image.setRGB(xRot + pixelSize * 6, yRot, side.getRGB(x, y));
                            out_image.setRGB(xRot + pixelSize * 12, yRot, side.getRGB(x, y));
                            out_image.setRGB(xRot + pixelSize * 18, yRot, side.getRGB(x, y));
                            if (doRaytrace) {
                                writeRayTexRot(raySideTextures, normalTexture, merTexture, xRot, yRot, x, y, 270, config);
                                writeRayTexRot(raySideTextures, normalTexture, merTexture, xRot + pixelSize * 6, yRot, x, y, 270, config);
                                writeRayTexRot(raySideTextures, normalTexture, merTexture, xRot + pixelSize * 12, yRot, x, y, 270, config);
                                writeRayTexRot(raySideTextures, normalTexture, merTexture, xRot + pixelSize * 18, yRot, x, y, 270, config);
                            }
                        }
                    }
                }
            }
            // WRITE IMAGE
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            ImageIO.write(out_image, "png", outData);
            String file = target.replace(".tga", ".png");
            access.zipAddFileByte(file, outData.toByteArray());
            if (doRaytrace) {
                String name = target.substring(0, target.lastIndexOf("."));

                outData = new ByteArrayOutputStream();
                ImageIO.write(normalTexture, "png", outData);
                file += file.isEmpty() ? name+"_normal.png" : ";" + name+"_normal.png";
                access.zipAddFileByte(name+"_normal.png", outData.toByteArray());

                outData = new ByteArrayOutputStream();
                ImageIO.write(merTexture, "png", outData);
                file += ";" + name+"_mer.png";
                access.zipAddFileByte(name+"_mer.png", outData.toByteArray());

                file += ";" + name+".texture_set.json";
                String fileName = name.substring(name.lastIndexOf('/')+1);
                access.zipAddFileByte(name+".texture_set.json", JsonCreationHelper.createTextureSet(fileName, fileName+"_mer", fileName+"_normal"));
            }
            return file;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.restructure.RestructurePiston.compute");
        }
    }
}

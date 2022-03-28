package minecraft_converter.operations.restructure;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;
import minecraft_converter.tools.JsonCreationHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static minecraft_converter.tools.Messages.showException;
import static minecraft_converter.tools.RaytracedWriter.writeRayTex;

public class RestructureFox {
    /**
     * combines Fox and fox_sleep to one texture
     *
     * @param access file access object to use
     * @param files: 0: fox texture
     *               1: sleep texture
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {
            // read input
            BufferedImage fox = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));
            BufferedImage sleep = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[1], false)));

            BufferedImage out_image = new BufferedImage(fox.getWidth() / 48 * 64, fox.getHeight(), BufferedImage.TYPE_INT_ARGB);

            BufferedImage[] rayFoxTextures = new BufferedImage[config.textures.size()];
            BufferedImage[] raySleepTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;
            if (doRaytrace) {
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[0].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[0].substring(0, index) + map.getKey() + files[0].substring(index), true);
                    if (buffer != null)
                        rayFoxTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[1].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[1].substring(0, index) + map.getKey() + files[1].substring(index), true);
                    if (buffer != null)
                        raySleepTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
            }

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            if (doRaytrace) {
                normalTexture = new BufferedImage(fox.getWidth(), fox.getHeight(), BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(fox.getWidth(), fox.getHeight(), BufferedImage.TYPE_INT_ARGB);
            }

            int pixelSize = out_image.getWidth() / 64;

            // head
            int[] currentSize = {pixelSize * 28, pixelSize * 12};
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(x, y, fox.getRGB(pixelSize + x, pixelSize * 5 + y));
                    out_image.setRGB(x,  pixelSize * 12 + y, sleep.getRGB(pixelSize + x, pixelSize * 5 + y));
                    if (doRaytrace) {
                        writeRayTex(rayFoxTextures, normalTexture, merTexture, x, y, pixelSize + x, pixelSize * 5 + y, config);
                        writeRayTex(raySleepTextures, normalTexture, merTexture, x,  pixelSize * 12 + y, pixelSize + x, pixelSize * 5 + y, config);
                    }
                }
            }
            // eras
            currentSize[0] = pixelSize * 6;
            currentSize[1] = pixelSize * 3;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    // left ear
                    out_image.setRGB(x, y, fox.getRGB(pixelSize * 8 + x, pixelSize + y));
                    // right ear
                    out_image.setRGB(pixelSize * 22 + x,  y, fox.getRGB(pixelSize * 15 + x, pixelSize + y));
                    if (doRaytrace) {
                        writeRayTex(rayFoxTextures, normalTexture, merTexture, x, y, pixelSize * 8 + x, pixelSize + y, config);
                        writeRayTex(rayFoxTextures, normalTexture, merTexture, pixelSize * 22 + x,  y, pixelSize * 15 + x, pixelSize + y, config);
                    }
                }
            }
            // tail
            currentSize[0] = pixelSize * 18;
            currentSize[1] = pixelSize * 14;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(pixelSize * 28 + x, y, fox.getRGB(pixelSize * 30 + x, pixelSize + y));
                    if (doRaytrace) {
                        writeRayTex(rayFoxTextures, normalTexture, merTexture, pixelSize * 28 + x, y, pixelSize * 30 + x, pixelSize + y, config);
                    }
                }
            }
            // body
            currentSize[0] = pixelSize * 24;
            currentSize[1] = pixelSize * 17;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    if (x >= 6 * pixelSize || y >= 6 * pixelSize) {
                        out_image.setRGB(pixelSize * 30 + x, pixelSize * 15 + y, fox.getRGB(pixelSize * 24 + x, pixelSize * 15 + y));
                        if (doRaytrace) {
                            writeRayTex(rayFoxTextures, normalTexture, merTexture, pixelSize * 30 + x, pixelSize * 15 + y, pixelSize * 24 + x, pixelSize * 15 + y, config);
                        }
                    }
                }
            }
            // nose
            currentSize[0] = pixelSize * 14;
            currentSize[1] = pixelSize * 5;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(x, pixelSize * 24 + y, fox.getRGB(pixelSize * 6 + x, pixelSize * 18 + y));
                    if (doRaytrace) {
                        writeRayTex(rayFoxTextures, normalTexture, merTexture, x, pixelSize * 24 + y, pixelSize * 6 + x, pixelSize * 18 + y, config);
                    }
                }
            }
            // legs
            currentSize[0] = currentSize[1] = pixelSize * 8;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    // right leg
                    out_image.setRGB(pixelSize * 14 + x, pixelSize * 24 + y, fox.getRGB(pixelSize * 13 + x, pixelSize * 24 + y));
                    // left leg
                    out_image.setRGB(pixelSize * 22 + x, pixelSize * 24 + y, fox.getRGB(pixelSize * 4 + x, pixelSize * 24 + y));
                    if (doRaytrace) {
                        writeRayTex(rayFoxTextures, normalTexture, merTexture, pixelSize * 14 + x, pixelSize * 24, pixelSize * 13 + x, pixelSize * 24 + y, config);
                        writeRayTex(rayFoxTextures, normalTexture, merTexture, pixelSize * 22 + x, pixelSize * 24 + y, pixelSize * 4 + x, pixelSize * 24 + y, config);
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
            throw new ExitNow("operations.restructure.RestructureFox.compute");
        }
    }
}

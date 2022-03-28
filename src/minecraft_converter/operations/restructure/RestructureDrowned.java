package minecraft_converter.operations.restructure;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.RGB;
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

import static minecraft_converter.tools.ColorOperations.getColor;
import static minecraft_converter.tools.ColorOperations.getIntColor;
import static minecraft_converter.tools.Messages.showException;
import static minecraft_converter.tools.RaytracedWriter.writeRayTex;

public class RestructureDrowned {
    /**
     * combines drowned and outer layer to one texture
     *
     * @param access file access object to use
     * @param files: 0: drowned texture
     *               1: outer layer texture
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {
            // read input
            BufferedImage drowned = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));
            BufferedImage layer = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[1], false)));

            BufferedImage out_image = new BufferedImage(drowned.getWidth(), drowned.getHeight(), BufferedImage.TYPE_INT_ARGB);

            BufferedImage[] rayDrownedTextures = new BufferedImage[config.textures.size()];
            BufferedImage[] rayLayerTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;
            if (doRaytrace) {
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[0].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[0].substring(0, index) + map.getKey() + files[0].substring(index), true);
                    if (buffer != null)
                        rayDrownedTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[1].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[1].substring(0, index) + map.getKey() + files[1].substring(index), true);
                    if (buffer != null)
                        rayLayerTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
            }

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            if (doRaytrace) {
                normalTexture = new BufferedImage(drowned.getWidth(), drowned.getHeight(), BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(drowned.getWidth(), drowned.getHeight(), BufferedImage.TYPE_INT_ARGB);
            }

            int pixelSize = out_image.getWidth() / 64;

            // drowned
            for (int x = 0; x < drowned.getWidth(); x++) {
                for (int y = 0; y < drowned.getHeight(); y++) {
                    RGB color = getColor(drowned.getRGB(x, y));
                    if (color.a > 0) {
                        if (color.b > 130) {
                            if (x >= pixelSize * 8 && x < pixelSize * 16 && y >= pixelSize * 8 && y < pixelSize * 16)
                                color.a = 191;
                            else
                                color.a = 208;
                        } else
                            color.a = 252;
                    } else {
                        color.r = color.g = color.b = 0;
                    }
                    out_image.setRGB(x, y, getIntColor(color));
                    if (doRaytrace) {
                        writeRayTex(rayDrownedTextures, normalTexture, merTexture, x, y, x, y, config);
                    }
                }
            }
            //outer layer
            // head
            int[] currentSize = {pixelSize * 32, pixelSize * 16};
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    RGB color = getColor(layer.getRGB(x, y));
                    if (color.a == 0)
                        color.r = color.g = color.b = 0;
                    out_image.setRGB(pixelSize * 32 + x, y, getIntColor(color));
                    if (doRaytrace) {
                        writeRayTex(rayLayerTextures, normalTexture, merTexture, pixelSize * 32 + x, y, x, y, config);
                    }
                }
            }
            // arms, body
            currentSize[0] = pixelSize * 56;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    RGB color = getColor(layer.getRGB(x, pixelSize * 16 + y));
                    if (color.a == 0)
                        color.r = color.g = color.b = 0;
                    out_image.setRGB(x, pixelSize * 32 + y, getIntColor(color));
                    if (doRaytrace) {
                        writeRayTex(rayLayerTextures, normalTexture, merTexture, x, pixelSize * 32 + y, x, pixelSize * 16 + y, config);
                    }
                }
            }
            // legs
            currentSize[0] = currentSize[1] = pixelSize * 16;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    RGB color = getColor(layer.getRGB(pixelSize * 16 + x, pixelSize * 48 + y));
                    if (color.a == 0)
                        color.r = color.g = color.b = 0;
                    out_image.setRGB(x, pixelSize * 48 + y, getIntColor(color));

                    color = getColor(layer.getRGB(pixelSize * 32 + x, pixelSize * 48 + y));
                    if (color.a == 0)
                        color.r = color.g = color.b = 0;
                    out_image.setRGB(pixelSize * 48 + x, pixelSize * 48 + y, getIntColor(color));

                    if (doRaytrace) {
                        writeRayTex(rayLayerTextures, normalTexture, merTexture, x, pixelSize * 48 + y, pixelSize * 16 + x, pixelSize * 48 + y, config);
                        writeRayTex(rayLayerTextures, normalTexture, merTexture, pixelSize * 48 + x, pixelSize * 48 + y, pixelSize * 32 + x, pixelSize * 48 + y, config);
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

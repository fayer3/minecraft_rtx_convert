package minecraft_converter.operations.restructure;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.operations.Copy;
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
import static minecraft_converter.tools.RaytracedWriter.writeRayTexMir;
import static minecraft_converter.tools.RaytracedWriter.writeRayTexRot;
import static minecraft_converter.tools.Rotation.rotate;

public class RestructureChestSingle {
    /**
     * restructures the single chest atlas to the old format
     *
     * @param access file access object to use
     * @param files: 0: chest texture
     * @param target output to write to
     * @param config settings object
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {
            if (config.packFormat <= 4) {
                // four and below use the same texture as bedrock
                return Copy.compute(access,null, files[0], target, config, false);
            }

            // read input
            BufferedImage chest = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));

            int width = chest.getWidth();
            int height = chest.getHeight();

            BufferedImage out_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            BufferedImage[] rayInTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;
            if (doRaytrace) {
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[0].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[0].substring(0, index) + map.getKey() + files[0].substring(index), true);
                    if (buffer != null)
                        rayInTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
            }

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            if (doRaytrace) {
                normalTexture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            }

            // top sides
            int pixelSize = width / 64;
            int[] currentSize = {pixelSize * 14, pixelSize * 5};
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    out_image.setRGB(x, pixelSize * 14 + y, chest.getRGB(source[0], pixelSize * 14 + source[1]));
                    out_image.setRGB(pixelSize * 14 + x, pixelSize * 14 + y, chest.getRGB(pixelSize * 42 + source[0], pixelSize * 14 + source[1]));
                    out_image.setRGB(pixelSize * 28 + x, pixelSize * 14 + y, chest.getRGB(pixelSize * 28 + source[0], pixelSize * 14 + source[1]));
                    out_image.setRGB(pixelSize * 42 + x, pixelSize * 14 + y, chest.getRGB(pixelSize * 14 + source[0], pixelSize * 14 + source[1]));
                    if (doRaytrace) {
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                x, pixelSize * 14 + y,
                                source[0], pixelSize * 14 + source[1],
                                180, config);
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 14 + x, pixelSize * 14 + y,
                                pixelSize * 42 + source[0], pixelSize * 14 + source[1],
                                180, config);
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 28 + x, pixelSize * 14 + y,
                                pixelSize * 28 + source[0], pixelSize * 14 + source[1],
                                180, config);
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 42 + x, pixelSize * 14 + y,
                                pixelSize * 14 + source[0], pixelSize * 14 + source[1],
                                180, config);
                    }
                }
            }

            // top, bottom, inside
            currentSize[0] = currentSize[1] = pixelSize * 14;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    // need mirror on y
                    out_image.setRGB(pixelSize * 14 + x, y, chest.getRGB(pixelSize * 28 + x, pixelSize * 14 - 1 - y));
                    out_image.setRGB(pixelSize * 28 + x, y, chest.getRGB(pixelSize * 14 + x, pixelSize * 14 - 1 - y));
                    out_image.setRGB(pixelSize * 14 + x, pixelSize * 19 + y, chest.getRGB(pixelSize * 28 + x, pixelSize * 33 - 1 - y));
                    out_image.setRGB(pixelSize * 28 + x, pixelSize * 19 + y, chest.getRGB(pixelSize * 14 + x, pixelSize * 33 - 1 - y));
                    if (doRaytrace) {
                        writeRayTexMir(rayInTextures, normalTexture, merTexture,
                                pixelSize * 14 + x, y,
                                pixelSize * 28 + x, pixelSize * 14 - 1 - y,
                                true, false, config);
                        writeRayTexMir(rayInTextures, normalTexture, merTexture,
                                pixelSize * 28 + x, y,
                                pixelSize * 14 + x, pixelSize * 14 - 1 - y,
                                true, false, config);
                        writeRayTexMir(rayInTextures, normalTexture, merTexture,
                                pixelSize * 14 + x, pixelSize * 19 + y,
                                pixelSize * 28 + x, pixelSize * 33 - 1 - y,
                                true, false, config);
                        writeRayTexMir(rayInTextures, normalTexture, merTexture,
                                pixelSize * 28 + x, pixelSize * 19 + y,
                                pixelSize * 14 + x, pixelSize * 33 - 1 - y,
                                true, false, config);
                    }
                }
            }
            // bottom sides
            currentSize[1] = pixelSize * 10;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    out_image.setRGB(x, pixelSize * 33 + y, chest.getRGB(source[0], pixelSize * 33 + source[1]));
                    out_image.setRGB(pixelSize * 28 + x, pixelSize * 33 + y, chest.getRGB(pixelSize * 28 + source[0], pixelSize * 33 + source[1]));
                    out_image.setRGB(pixelSize * 14 + x, pixelSize * 33 + y, chest.getRGB(pixelSize * 42 + source[0], pixelSize * 33 + source[1]));
                    out_image.setRGB(pixelSize * 42 + x, pixelSize * 33 + y, chest.getRGB(pixelSize * 14 + source[0], pixelSize * 33 + source[1]));
                    if (doRaytrace) {
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                x, pixelSize * 33 + y,
                                source[0], pixelSize * 33 + source[1],
                                180, config);
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 28 + x, pixelSize * 33 + y,
                                pixelSize * 28 + source[0], pixelSize * 33 + source[1],
                                180, config);
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 14 + x, pixelSize * 33 + y,
                                pixelSize * 42 + source[0], pixelSize * 33 + source[1],
                                180, config);
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 42 + x, pixelSize * 33 + y,
                                pixelSize * 14 + source[0], pixelSize * 33 + source[1],
                                180, config);
                    }
                }
            }

            // lock top/bottom
            currentSize[0] = pixelSize * 2;
            currentSize[1] = pixelSize;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    // need mirror on y
                    int[] source = {x, currentSize[1] - y - 1};
                    //top
                    out_image.setRGB(x + pixelSize, y, chest.getRGB(source[0] + pixelSize * 3, source[1]));
                    // bottom
                    out_image.setRGB(x + pixelSize * 3, y, chest.getRGB(source[0] + pixelSize, source[1]));
                    if (doRaytrace) {
                        writeRayTexMir(rayInTextures, normalTexture, merTexture,
                                x + pixelSize, y,
                                source[0] + pixelSize * 3, source[1],
                                true, false, config);
                        writeRayTexMir(rayInTextures, normalTexture, merTexture,
                                x + pixelSize * 3, y,
                                source[0] + pixelSize, source[1],
                                true, false, config);
                    }
                }
            }

            // lock front/back/right side
            currentSize[0] = pixelSize * 5;
            currentSize[1] = pixelSize * 4;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    out_image.setRGB(x + pixelSize, y + pixelSize, chest.getRGB(source[0] + pixelSize, source[1] + pixelSize));
                    if (doRaytrace) {
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                x + pixelSize, y + pixelSize,
                                source[0] + pixelSize, source[1] + pixelSize,
                                180, config);
                    }
                }
            }

            // lock left side
            currentSize[0] = pixelSize;
            currentSize[1] = pixelSize * 4;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    out_image.setRGB(x, y + pixelSize, chest.getRGB(source[0], source[1] + pixelSize));
                    if (doRaytrace) {
                        writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                x, y + pixelSize,
                                source[0], source[1] + pixelSize,
                                180, config);
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
            throw new ExitNow("operations.restructure.RestructureChestSingle.compute");
        }
    }
}

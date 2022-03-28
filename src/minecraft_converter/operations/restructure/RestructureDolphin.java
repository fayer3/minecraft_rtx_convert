package minecraft_converter.operations.restructure;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;
import minecraft_converter.tools.JsonCreationHelper;
import minecraft_converter.tools.RaytracedWriter;
import minecraft_converter.tools.Rotation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static minecraft_converter.tools.Messages.showException;

public class RestructureDolphin {
    /**
     * restructures dolphin texture
     *
     * @param access file access object to use
     * @param files: 0: dolphin texture
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {

            // read input
            BufferedImage dolphin = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));

            BufferedImage out_image = new BufferedImage(dolphin.getWidth(), dolphin.getHeight(), BufferedImage.TYPE_INT_ARGB);

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
                normalTexture = new BufferedImage(dolphin.getWidth(), dolphin.getHeight(), BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(dolphin.getWidth(), dolphin.getHeight(), BufferedImage.TYPE_INT_ARGB);
            }

            int pixelSize = dolphin.getWidth() / 64;

            // head, is same
            int[] currentSize = {pixelSize * 28, pixelSize * 13};
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(x, y, dolphin.getRGB(x, y));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                x, y,
                                x,y,
                                config);
                    }
                }
            }

            // front top/bottom
            currentSize[0] = pixelSize * 16;
            currentSize[1] = pixelSize * 13;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(pixelSize * 13 + x, pixelSize * 13 + y, dolphin.getRGB(pixelSize * 35 + x, y));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                pixelSize * 13 + x, pixelSize * 13 + y,
                                pixelSize * 35 + x, y,
                                config);
                    }
                }
            }

            // front side/back
            currentSize[0] = pixelSize * 42;
            currentSize[1] = pixelSize * 7;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(x, pixelSize * 26 + y, dolphin.getRGB(pixelSize * 22 + x, pixelSize * 13 + y));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                x, pixelSize * 26 + y,
                                pixelSize * 22 + x, pixelSize * 13 + y,
                                config);
                    }
                }
            }

            // back top/bottom
            currentSize[0] = pixelSize * 8;
            currentSize[1] = pixelSize * 11;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(pixelSize * 11 + x, pixelSize * 33 + y, dolphin.getRGB(pixelSize * 11 + x, pixelSize * 19 + y));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                pixelSize * 11 + x, pixelSize * 33 + y,
                                pixelSize * 11 + x, pixelSize * 19 + y,
                                config);
                    }
                }
            }

            // back side/back
            currentSize[0] = pixelSize * 30;
            currentSize[1] = pixelSize * 5;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(x, pixelSize * 44 + y, dolphin.getRGB(x, pixelSize * 30 + y));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                x, pixelSize * 44 + y,
                                x, pixelSize * 30 + y,
                                config);
                    }
                }
            }

            // back fin
            currentSize[0] = pixelSize * 32;
            currentSize[1] = pixelSize * 7;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(x, pixelSize * 49 + y, dolphin.getRGB(pixelSize * 19 + x, pixelSize * 20 + y));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                x, pixelSize * 49 + y,
                                pixelSize * 19 + x, pixelSize * 20 + y,
                                config);
                    }
                }
            }

            // nose, is same
            currentSize[0] = pixelSize * 12;
            currentSize[1] = pixelSize * 6;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(x, pixelSize * 13 + y, dolphin.getRGB(x, pixelSize * 13 + y));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                x, pixelSize * 13 + y,
                                x, pixelSize * 13 + y,
                                config);
                    }
                }
            }

            // top fin
            // left side
            currentSize[0] = pixelSize * 4;
            currentSize[1] = pixelSize * 5;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] offset = Rotation.rotate(new int[]{x, y}, currentSize, 270);
                    out_image.setRGB(pixelSize * 29 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 51 + offset[0], pixelSize * 5 + offset[1]));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 29 + x, pixelSize * 4 + y,
                                pixelSize * 51 + offset[0], pixelSize * 5 + offset[1],
                                270, config);
                    }
                }
            }

            // right side
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] offset = Rotation.rotate(new int[]{x, y}, currentSize, 90);
                    out_image.setRGB(pixelSize * 34 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 57 + offset[0], pixelSize * 5 + offset[1]));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 34 + x, pixelSize * 4 + y,
                                pixelSize * 57 + offset[0], pixelSize * 5 + offset[1],
                                90, config);
                    }
                }
            }

            // long side
            currentSize[0] = pixelSize;
            currentSize[1] = pixelSize * 5;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(pixelSize * 33 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 56 + x, y));
                    out_image.setRGB(pixelSize * 38 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 57 + x, y));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                pixelSize * 33 + x, pixelSize * 4 + y,
                                pixelSize * 56 + x, y,
                                config);
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                pixelSize * 38 + x, pixelSize * 4 + y,
                                pixelSize * 57 + x, y,
                                config);
                    }
                }
            }

            // short side
            currentSize[0] = pixelSize;
            currentSize[1] = pixelSize * 4;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(pixelSize * 33 + x, y, dolphin.getRGB(pixelSize * 56 + x, pixelSize * 5 + (currentSize[1] - 1 -y)));
                    out_image.setRGB(pixelSize * 34 + x, y, dolphin.getRGB(pixelSize * 57 + x, pixelSize + y));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTexMir(rayInTextures, normalTexture, merTexture,
                                pixelSize * 33 + x, y,
                                pixelSize * 56 + x, pixelSize * 5 + (currentSize[1] - 1 -y),
                                true, false, config);
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                pixelSize * 34 + x, y,
                                pixelSize * 57 + x, pixelSize + y,
                                config);
                    }
                }
            }
            //side fin
            // top / bottom
            currentSize[0] = pixelSize * 7;
            currentSize[1] = pixelSize * 4;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] offset = Rotation.rotate(new int[]{x, y}, currentSize, 180);
                    // top, right fin
                    out_image.setRGB(pixelSize * 45 + x, pixelSize * 6 + y, dolphin.getRGB(pixelSize * 56 + offset[0], pixelSize * 27 + offset[1]));
                    // top, left fin
                    out_image.setRGB(pixelSize * 44 + x, y, dolphin.getRGB(pixelSize * 56 + (currentSize[0] - 1 - offset[0]), pixelSize * 27 + offset[1]));
                    // bottom, right fin
                    out_image.setRGB(pixelSize * 53 + x, pixelSize * 6 + y, dolphin.getRGB(pixelSize * 48 + (currentSize[0] - 1 - offset[0]), pixelSize * 27 + offset[1]));
                    // bottom, left fin
                    out_image.setRGB(pixelSize * 52 + x, y, dolphin.getRGB(pixelSize * 48 + offset[0], pixelSize * 27 + offset[1]));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 45 + x, pixelSize * 6 + y,
                                pixelSize * 56 + offset[0], pixelSize * 27 + offset[1],
                                180, config);
                        RaytracedWriter.writeRayTexRotFirst(rayInTextures, normalTexture, merTexture,
                                pixelSize * 44 + x, y,
                                pixelSize * 56 + (currentSize[0] - 1 - offset[0]), pixelSize * 27 + offset[1],
                                180, false, true, config);
                        RaytracedWriter.writeRayTexRotFirst(rayInTextures, normalTexture, merTexture,
                                pixelSize * 53 + x, pixelSize * 6 + y,
                                pixelSize * 48 + (currentSize[0] - 1 - offset[0]), pixelSize * 27 + offset[1],
                                180, false, true, config);
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 52 + x, y,
                                pixelSize * 48 + offset[0], pixelSize * 27 + offset[1],
                                180, config);
                    }
                }
            }

            // top / bottom, one extra pixel, just copy last row
            currentSize[0] = pixelSize;
            currentSize[1] = pixelSize * 4;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] offset = Rotation.rotate(new int[]{x, y}, currentSize, 180);
                    // top, right fin
                    out_image.setRGB(pixelSize * 44 + x, pixelSize * 6 + y, dolphin.getRGB(pixelSize * 62 + offset[0], pixelSize * 27 + offset[1]));
                    // top, left fin
                    out_image.setRGB(pixelSize * 51 + x, y, dolphin.getRGB(pixelSize * 62 + (currentSize[0] - 1 - offset[0]), pixelSize * 27 + offset[1]));
                    // bottom, right fin
                    out_image.setRGB(pixelSize * 52 + x, pixelSize * 6 + y, dolphin.getRGB(pixelSize * 48 + (currentSize[0] - 1 - offset[0]), pixelSize * 27 + offset[1]));
                    // bottom, left fin
                    out_image.setRGB(pixelSize * 59 + x, y, dolphin.getRGB(pixelSize * 48 + offset[0], pixelSize * 27 + offset[1]));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 44 + x, pixelSize * 6 + y,
                                pixelSize * 62 + offset[0], pixelSize * 27 + offset[1],
                                180, config);
                        RaytracedWriter.writeRayTexRotFirst(rayInTextures, normalTexture, merTexture,
                                pixelSize * 51 + x, y,
                                pixelSize * 62 + (currentSize[0] - 1 - offset[0]), pixelSize * 27 + offset[1],
                                180, false, true, config);
                        RaytracedWriter.writeRayTexRotFirst(rayInTextures, normalTexture, merTexture,
                                pixelSize * 52 + x, pixelSize * 6 + y,
                                pixelSize * 48 + (currentSize[0] - 1 - offset[0]), pixelSize * 27 + offset[1],
                                180, false, true, config);
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 59 + x, y,
                                pixelSize * 48 + offset[0], pixelSize * 27 + offset[1],
                                180, config);
                    }
                }
            }
            // side short
            currentSize[0] = pixelSize * 4;
            currentSize[1] = pixelSize;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] offset = Rotation.rotate(new int[]{x, y}, currentSize, 270);
                    // outside, right fin
                    out_image.setRGB(pixelSize * 40 + x, pixelSize * 10 + y, dolphin.getRGB(pixelSize * 55 + offset[0], pixelSize * 27 + offset[1]));
                    // outside, left fin
                    out_image.setRGB(pixelSize * 52 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 55 + (offset[0]), pixelSize * 27 + (currentSize[0] - 1 - offset[1])));
                    // inside, right fin
                    out_image.setRGB(pixelSize * 52 + x, pixelSize * 10 + y, dolphin.getRGB(pixelSize * 63 + offset[0], pixelSize * 27 + (currentSize[0] - 1 - offset[1])));
                    // inside, left fin
                    out_image.setRGB(pixelSize * 40 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 63 + offset[0], pixelSize * 27 + offset[1]));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 40 + x, pixelSize * 10 + y,
                                pixelSize * 55 + offset[0], pixelSize * 27 + offset[1],
                                270, config);
                        RaytracedWriter.writeRayTexRotFirst(rayInTextures, normalTexture, merTexture,
                                pixelSize * 52 + x, pixelSize * 4 + y,
                                pixelSize * 55 + (offset[0]), pixelSize * 27 + (currentSize[0] - 1 - offset[1]),
                                270, true, false, config);
                        RaytracedWriter.writeRayTexRotFirst(rayInTextures, normalTexture, merTexture,
                                pixelSize * 52 + x, pixelSize * 10 + y,
                                pixelSize * 63 + offset[0], pixelSize * 27 + (currentSize[0] - 1 - offset[1]),
                                270, true, false, config);
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 40 + x, pixelSize * 4 + y,
                                pixelSize * 63 + offset[0], pixelSize * 27 + offset[1],
                                270, config);
                    }
                }
            }

            // side long
            currentSize[0] = pixelSize * 7;
            currentSize[1] = pixelSize;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] offset = Rotation.rotate(new int[]{x, y}, currentSize, 90);
                    // front, right fin
                    out_image.setRGB(pixelSize * 45 + x, pixelSize * 10 + y, dolphin.getRGB(pixelSize * 55 + offset[0], pixelSize * 20 + offset[1]));
                    // front, left fin
                    out_image.setRGB(pixelSize * 44 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 55 + (offset[0]), pixelSize * 20 + (currentSize[0] - 1 - offset[1])));
                    // back, right fin
                    out_image.setRGB(pixelSize * 56 + x, pixelSize * 10 + y, dolphin.getRGB(pixelSize * 56 + offset[0], pixelSize * 20 + (currentSize[0] - 1 - offset[1])));
                    // back, left fin
                    out_image.setRGB(pixelSize * 57 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 56 + offset[0], pixelSize * 20 + offset[1]));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 45 + x, pixelSize * 10 + y,
                                pixelSize * 55 + offset[0], pixelSize * 20 + offset[1],
                                90, config);
                        RaytracedWriter.writeRayTexRotFirst(rayInTextures, normalTexture, merTexture,
                                pixelSize * 44 + x, pixelSize * 4 + y,
                                pixelSize * 55 + (offset[0]), pixelSize * 20 + (currentSize[0] - 1 - offset[1]),
                                90, true, false, config);
                        RaytracedWriter.writeRayTexRotFirst(rayInTextures, normalTexture, merTexture,
                                pixelSize * 56 + x, pixelSize * 10 + y,
                                pixelSize * 56 + offset[0], pixelSize * 20 + (currentSize[0] - 1 - offset[1]),
                                90, true, false, config);
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 57 + x, pixelSize * 4 + y,
                                pixelSize * 56 + offset[0], pixelSize * 20 + offset[1],
                                90, config);
                    }
                }
            }

            // long side 1 extra pixel
            currentSize[0] = pixelSize;
            currentSize[1] = pixelSize;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] offset = Rotation.rotate(new int[]{x, y}, currentSize, 90);
                    // front, right fin
                    out_image.setRGB(pixelSize * 44 + x, pixelSize * 10 + y, dolphin.getRGB(pixelSize * 55 + offset[0], pixelSize * 20 + offset[1]));
                    // front, left fin
                    out_image.setRGB(pixelSize * 51 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 55 + (offset[0]), pixelSize * 20 + offset[1]));
                    // back, right fin
                    out_image.setRGB(pixelSize * 63 + x, pixelSize * 10 + y, dolphin.getRGB(pixelSize * 56 + offset[0], pixelSize * 20 + offset[1]));
                    // back, left fin
                    out_image.setRGB(pixelSize * 56 + x, pixelSize * 4 + y, dolphin.getRGB(pixelSize * 56 + offset[0], pixelSize * 20 + offset[1]));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 44 + x, pixelSize * 10 + y,
                                pixelSize * 55 + offset[0], pixelSize * 20 + offset[1],
                                90, config);
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 51 + x, pixelSize * 4 + y,
                                pixelSize * 55 + (offset[0]), pixelSize * 20 + offset[1],
                                90, config);
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 63 + x, pixelSize * 10 + y,
                                pixelSize * 56 + offset[0], pixelSize * 20 + offset[1],
                                90, config);
                        RaytracedWriter.writeRayTexRot(rayInTextures, normalTexture, merTexture,
                                pixelSize * 56 + x, pixelSize * 4 + y,
                                pixelSize * 56 + offset[0], pixelSize * 20 + offset[1],
                                90, config);
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
            throw new ExitNow("operations.restructure.RestructureTropicalB.compute");
        }
    }
}

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
import static minecraft_converter.tools.RaytracedWriter.writeRayTexMir;
import static minecraft_converter.tools.RaytracedWriter.writeRayTexRot;
import static minecraft_converter.tools.Rotation.rotate;

public class RestructureChestDouble {
    /**
     * builds the double chest atlas from the two split textures source files
     *
     * @param access file access object to use
     * @param files: 0: left chest texture
     *               1: right chest texture
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {

            // read input
            BufferedImage left = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));
            BufferedImage right = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[1], false)));

            int width = left.getWidth() * 2;
            int height = left.getHeight();

            BufferedImage out_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            BufferedImage[] rayLeftTextures = new BufferedImage[config.textures.size()];
            BufferedImage[] rayRightTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;
            if (doRaytrace) {
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[0].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[0].substring(0, index) + map.getKey() + files[0].substring(index), true);
                    if (buffer != null)
                        rayLeftTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[1].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[1].substring(0, index) + map.getKey() + files[1].substring(index), true);
                    if (buffer != null)
                        rayRightTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
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

            // top sides, left & right
            int pixelSize = width / 128;
            int[] currentSize = {pixelSize * 14, pixelSize * 5};
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    // left side
                    out_image.setRGB(x, pixelSize * 14 + y, right.getRGB(source[0], pixelSize * 14 + source[1]));
                    // right side
                    out_image.setRGB(pixelSize * 44 + x, pixelSize * 14 + y, left.getRGB(pixelSize * 29 + source[0], pixelSize * 14 + source[1]));

                    if (doRaytrace) {
                        writeRayTexRot(rayRightTextures, normalTexture, merTexture,
                                x, pixelSize * 14 + y,
                                source[0], pixelSize * 14 + source[1],
                                180, config);
                        writeRayTexRot(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 44 + x, pixelSize * 14 + y,
                                pixelSize * 29 + source[0], pixelSize * 14 + source[1],
                                180, config);
                    }
                }
            }

            // top sides, front & back
            currentSize[0] = pixelSize * 15;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    // front left
                    out_image.setRGB(pixelSize * 14 + x, pixelSize * 14 + y, right.getRGB(pixelSize * 43 + source[0], pixelSize * 14 + source[1]));
                    // front right
                    out_image.setRGB(pixelSize * 29 + x, pixelSize * 14 + y, left.getRGB(pixelSize * 43 + source[0], pixelSize * 14 + source[1]));
                    // back left
                    out_image.setRGB(pixelSize * 58 + x, pixelSize * 14 + y, left.getRGB(pixelSize * 14 + source[0], pixelSize * 14 + source[1]));
                    // back right
                    out_image.setRGB(pixelSize * 73 + x, pixelSize * 14 + y, right.getRGB(pixelSize * 14 + source[0], pixelSize * 14 + source[1]));
                    if (doRaytrace) {
                        writeRayTexRot(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 14 + x, pixelSize * 14 + y,
                                pixelSize * 43 + source[0], pixelSize * 14 + source[1],
                                180, config);
                        writeRayTexRot(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 29 + x, pixelSize * 14 + y,
                                pixelSize * 43 + source[0], pixelSize * 14 + source[1],
                                180, config);
                        writeRayTexRot(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 58 + x, pixelSize * 14 + y,
                                pixelSize * 14 + source[0], pixelSize * 14 + source[1],
                                180, config);
                        writeRayTexRot(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 73 + x, pixelSize * 14 + y,
                                pixelSize * 14 + source[0], pixelSize * 14 + source[1],
                                180, config);
                    }
                }
            }

            // top, bottom, inside
            currentSize[1] = pixelSize * 14;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    // need mirror y
                    int[] source = {x, currentSize[1] - y - 1};
                    // left
                    // inside top
                    out_image.setRGB(pixelSize * 44 + x, y, right.getRGB(pixelSize * 14 + source[0], source[1]));
                    // outside top
                    out_image.setRGB(pixelSize * 14 + x, y, right.getRGB(pixelSize * 29 + source[0], source[1]));
                    // inside bottom
                    out_image.setRGB(pixelSize * 14 + x, pixelSize * 19 + y, right.getRGB(pixelSize * 29 + source[0],pixelSize * 19 + source[1]));
                    // outside bottom
                    out_image.setRGB(pixelSize * 44 + x, pixelSize * 19 + y, right.getRGB(pixelSize * 14 + source[0], pixelSize * 19 + source[1]));
                    if (doRaytrace) {
                        writeRayTexMir(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 44 + x, y,
                                pixelSize * 14 + source[0], source[1],
                                true, false, config);
                        writeRayTexMir(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 14 + x, y,
                                pixelSize * 29 + source[0], source[1],
                                true, false, config);
                        writeRayTexMir(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 14 + x, pixelSize * 19 + y,
                                pixelSize * 29 + source[0], pixelSize * 19 + source[1],
                                true, false, config);
                        writeRayTexMir(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 44 + x, pixelSize * 19 + y,
                                pixelSize * 14 + source[0], pixelSize * 19 + source[1],
                                true, false, config);
                    }

                    // right
                    // inside top
                    out_image.setRGB(pixelSize * 59 + x, y, left.getRGB(pixelSize * 14 + source[0], source[1]));
                    // outside top
                    out_image.setRGB(pixelSize * 29 + x, y, left.getRGB(pixelSize * 29 + source[0], source[1]));
                    // inside bottom
                    out_image.setRGB(pixelSize * 29 + x, pixelSize * 19 + y, left.getRGB(pixelSize * 29 + source[0],pixelSize * 19 + source[1]));
                    // outside bottom
                    out_image.setRGB(pixelSize * 59 + x, pixelSize * 19 + y, left.getRGB(pixelSize * 14 + source[0], pixelSize * 19 + source[1]));
                    if (doRaytrace) {
                        writeRayTexMir(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 59 + x, y,
                                pixelSize * 14 + source[0], source[1],
                                true, false, config);
                        writeRayTexMir(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 29 + x, y,
                                pixelSize * 29 + source[0], source[1],
                                true, false, config);
                        writeRayTexMir(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 29 + x, pixelSize * 19 + y,
                                pixelSize * 29 + source[0], pixelSize * 19 + source[1],
                                true, false, config);
                        writeRayTexMir(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 59 + x, pixelSize * 19 + y,
                                pixelSize * 14 + source[0], pixelSize * 19 + source[1],
                                true, false, config);
                    }
                }
            }

            // bottom sides, left & right
            currentSize[0] = pixelSize * 14;
            currentSize[1] = pixelSize * 10;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    // left side
                    out_image.setRGB(x, pixelSize * 33 + y, right.getRGB(source[0], pixelSize * 33 + source[1]));
                    // right side
                    out_image.setRGB(pixelSize * 44 + x, pixelSize * 33 + y, left.getRGB(pixelSize * 29 + source[0], pixelSize * 33 + source[1]));
                    if (doRaytrace) {
                        writeRayTexRot(rayRightTextures, normalTexture, merTexture,
                                x, pixelSize * 33 + y,
                                source[0], pixelSize * 33 + source[1],
                                180, config);
                        writeRayTexRot(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 44 + x, pixelSize * 33 + y,
                                pixelSize * 29 + source[0], pixelSize * 33 + source[1],
                                180, config);
                    }
                }
            }

            // bottom sides, front & back
            currentSize[0] = pixelSize * 15;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    // front left
                    out_image.setRGB(pixelSize * 14 + x, pixelSize * 33 + y, right.getRGB(pixelSize * 43 + source[0], pixelSize * 33 + source[1]));
                    // front right
                    out_image.setRGB(pixelSize * 29 + x, pixelSize * 33 + y, left.getRGB(pixelSize * 43 + source[0], pixelSize * 33 + source[1]));
                    // back right
                    out_image.setRGB(pixelSize * 73 + x, pixelSize * 33 + y, right.getRGB(pixelSize * 14 + source[0], pixelSize * 33 + source[1]));
                    // back left
                    out_image.setRGB(pixelSize * 58 + x, pixelSize * 33 + y, left.getRGB(pixelSize * 14 + source[0], pixelSize * 33 + source[1]));
                    if (doRaytrace) {
                        writeRayTexRot(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 14 + x, pixelSize * 33 + y,
                                pixelSize * 43 + source[0], pixelSize * 33 + source[1],
                                180, config);
                        writeRayTexRot(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 29 + x, pixelSize * 33 + y,
                                pixelSize * 43 + source[0], pixelSize * 33 + source[1],
                                180, config);
                        writeRayTexRot(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 73 + x, pixelSize * 33 + y,
                                pixelSize * 14 + source[0], pixelSize * 33 + source[1],
                                180, config);
                        writeRayTexRot(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 58 + x, pixelSize * 33 + y,
                                pixelSize * 14 + source[0], pixelSize * 33 + source[1],
                                180, config);
                    }
                }
            }

            // lock top/bottom
            currentSize[0] = currentSize[1] = pixelSize;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    // mirror on y
                    int[] source = {x, currentSize[1] - y - 1};
                    //top
                    //left
                    out_image.setRGB(x + pixelSize, y, right.getRGB(source[0] + pixelSize * 2, source[1]));
                    //right
                    out_image.setRGB(x + pixelSize * 2, y, left.getRGB(source[0] + pixelSize * 2, source[1]));
                    // bottom
                    //left
                    out_image.setRGB(x + pixelSize * 3, y, right.getRGB(source[0] + pixelSize, source[1]));
                    //right
                    out_image.setRGB(x + pixelSize * 4, y, left.getRGB(source[0] + pixelSize, source[1]));
                    if (doRaytrace) {
                        writeRayTexMir(rayRightTextures, normalTexture, merTexture,
                                pixelSize + x, y,
                                pixelSize * 2 + source[0], source[1],
                                true, false, config);
                        writeRayTexMir(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 2 + x, y,
                                pixelSize * 2 + source[0], source[1],
                                true, false, config);
                        writeRayTexMir(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 3 + x, y,
                                pixelSize + source[0], source[1],
                                true, false, config);
                        writeRayTexMir(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 4 + x, y,
                                pixelSize + source[0], source[1],
                                true, false, config);
                    }
                }
            }

            // lock front/back/right side
            currentSize[0] = pixelSize * 3;
            currentSize[1] = pixelSize * 4;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    out_image.setRGB(x + pixelSize * 2, y + pixelSize, left.getRGB(source[0] + pixelSize, source[1] + pixelSize));
                    if (doRaytrace) {
                        writeRayTexRot(rayLeftTextures, normalTexture, merTexture,
                                pixelSize * 2 + x, pixelSize + y,
                                pixelSize + source[0], pixelSize + source[1],
                                180, config);
                    }
                }
            }

            // lock front/back/left side
            currentSize[0] = pixelSize;
            currentSize[1] = pixelSize * 4;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    int[] source = rotate(new int[]{x, y}, currentSize, 180);
                    // side
                    out_image.setRGB(x, y + pixelSize, right.getRGB(source[0], source[1] + pixelSize));
                    // front
                    out_image.setRGB(x + pixelSize, y + pixelSize, right.getRGB(source[0] + pixelSize * 3, source[1] + pixelSize));
                    // back
                    out_image.setRGB(x+ pixelSize * 5, y + pixelSize, right.getRGB(source[0] + pixelSize, source[1] + pixelSize));
                    if (doRaytrace) {
                        writeRayTexRot(rayRightTextures, normalTexture, merTexture,
                                x, y + pixelSize,
                                source[0], source[1] + pixelSize,
                                180, config);
                        writeRayTexRot(rayRightTextures, normalTexture, merTexture,
                                pixelSize + x, y + pixelSize,
                                pixelSize * 3 + source[0], source[1] + pixelSize,
                                180, config);
                        writeRayTexRot(rayRightTextures, normalTexture, merTexture,
                                pixelSize * 5 + x, y + pixelSize,
                                pixelSize + source[0], source[1] + pixelSize,
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
            throw new ExitNow("operations.restructure.RestructureChestDouble.compute");
        }
    }
}

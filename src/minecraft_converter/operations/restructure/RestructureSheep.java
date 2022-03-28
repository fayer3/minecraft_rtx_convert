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

public class RestructureSheep {
    /**
     * combines sheep and sheep_fur to one texture
     *
     * @param access file access object to use
     * @param files: 0: sheep texture
     *               1: sheep_fur texture
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {
            // read input
            BufferedImage sheep = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));
            BufferedImage fur = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[1], false)));

            BufferedImage out_image = new BufferedImage(sheep.getWidth() , sheep.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);

            BufferedImage[] raySheepTextures = new BufferedImage[config.textures.size()];
            BufferedImage[] rayFurTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;
            if (doRaytrace) {
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[0].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[0].substring(0, index) + map.getKey() + files[0].substring(index), true);
                    if (buffer != null)
                        raySheepTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[1].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[1].substring(0, index) + map.getKey() + files[1].substring(index), true);
                    if (buffer != null)
                        rayFurTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
            }

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            if (doRaytrace) {
                normalTexture = new BufferedImage(sheep.getWidth() , sheep.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(sheep.getWidth() , sheep.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
            }

            int pixelSize = out_image.getWidth() / 64;

            // fur
            int[] currentSize = {pixelSize * 56, pixelSize * 30};
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    out_image.setRGB(x, pixelSize * 32 + y, fur.getRGB(x, y));
                    if (doRaytrace) {
                        writeRayTex(rayFurTextures, normalTexture, merTexture, x, pixelSize * 32 + y, x, y, config);
                    }
                }
            }
            // sheep
            currentSize[1] = pixelSize * 32;
            for (int x = 0; x < currentSize[0]; x++) {
                for (int y = 0; y < currentSize[1]; y++) {
                    RGB color = getColor(sheep.getRGB(x, y));
                    float average = (color.r + color.g + color.b) / 3f;
                    float low = average - 5;
                    float high = average + 5;
                    if (!(color.r > low && color.r < high && color.g > low && color.g < high && color.b > low && color.b < high) || (x >= 8 * pixelSize && x < 14 * pixelSize && y >= 9 * pixelSize && y < 12 * pixelSize && (average == 0 || average == 255))) {
                        color.a = 3;
                    }
                    out_image.setRGB(x, y, getIntColor(color));
                    if (doRaytrace) {
                        writeRayTex(raySheepTextures, normalTexture, merTexture, x, y, x, y, config);
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
            throw new ExitNow("operations.restructure.RestructureSheep.compute");
        }
    }
}

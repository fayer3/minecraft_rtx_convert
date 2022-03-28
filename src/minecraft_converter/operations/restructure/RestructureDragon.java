package minecraft_converter.operations.restructure;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.operations.Copy;
import minecraft_converter.tools.FileAccess;
import minecraft_converter.tools.JsonCreationHelper;
import minecraft_converter.tools.RaytracedWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static minecraft_converter.tools.Messages.showException;
import static minecraft_converter.tools.Rotation.rotate;

public class RestructureDragon {
    /**
     * re adds the bottom wing texture for packFormat 5 and up
     *
     * @param access file access object to use
     * @param files: 0: dragon texture
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
            BufferedImage dragon = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));

            BufferedImage out_image = new BufferedImage(dragon.getWidth(), dragon.getHeight(), BufferedImage.TYPE_INT_ARGB);

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
                normalTexture = new BufferedImage(dragon.getWidth(), dragon.getHeight(), BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(dragon.getWidth(), dragon.getHeight(), BufferedImage.TYPE_INT_ARGB);
            }

            int pixelSize = dragon.getWidth() / 256;

            for (int x = 0; x < out_image.getWidth(); x++) {
                for (int y = 0; y < out_image.getHeight(); y++) {
                    int[] source = {x, y};
                    if (x >= pixelSize * 56 && x < pixelSize * 112 && y >= pixelSize * 88 && y < pixelSize * 200) {
                        source[0] -= pixelSize * 56;
                    }
                    out_image.setRGB(x, y, dragon.getRGB(source[0], source[1]));
                    if (doRaytrace) {
                        RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture, x, y, source[0], source[1], config);
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
            throw new ExitNow("operations.restructure.RestructureDragon.compute");
        }
    }
}

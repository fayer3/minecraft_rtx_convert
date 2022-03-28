package minecraft_converter.operations.restructure;

import minecraft_converter.datatypes.Settings;
import minecraft_converter.exceptions.ExitNow;
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
import static minecraft_converter.tools.Messages.showException;
import static minecraft_converter.tools.RaytracedWriter.writeRayTex;

public class RestructureBannerBase {
    /**
     * adds pole to banner base
     *
     * @param access file access object to use
     * @param files: banner_base
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {

            // read input
            BufferedImage base = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));
            //load textures for normal/mer
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

            if (doRaytrace) {
                normalTexture = new BufferedImage(base.getWidth(), base.getHeight() * files.length, BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(base.getWidth(), base.getHeight() * files.length, BufferedImage.TYPE_INT_ARGB);
            }

            // generate longer pole
            for (int x = base.getWidth() / 64 * 52; x < base.getWidth() / 64 * 60; x++) {
                for (int y = 0; y < base.getHeight(); y++) {
                    if (y < base.getHeight() / 64 * 44) {
                        base.setRGB(x, y, base.getRGB(x - base.getWidth() / 8, y));
                    }
                    else {
                        base.setRGB(x, y, base.getRGB(x - base.getWidth() / 8, y - base.getHeight() / 64 * 20));
                    }
                }
            }
            if (doRaytrace) {
                for (int x = 0; x < base.getWidth(); x++) {
                    for (int y = 0; y < base.getHeight(); y++) {
                        if (x >= base.getWidth() / 64 * 52 && x < base.getWidth() / 64 * 60) {
                            if (y < base.getHeight() / 64 * 44) {
                                writeRayTex(rayInTextures, normalTexture, merTexture, x, y, x - base.getWidth() / 8, y, config);
                            } else {
                                writeRayTex(rayInTextures, normalTexture, merTexture, x, y, x - base.getWidth() / 8, y - base.getHeight() / 64 * 20, config);
                            }
                        } else {
                            writeRayTex(rayInTextures, normalTexture, merTexture, x, y, x, y, config);
                        }
                    }
                }
            }

            // WRITE IMAGE
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            String file = "";
            // only use tga if transparent pixels are present, because tga is not compressed currently
            if (config.raytraced && config.useTga && ((target.contains("/blocks/") || !config.limitTransparencyToBlocks))) {
                ImageIO.write(base, "tga", outData);
                file = target.replace(".png", ".tga");
            }
            else {
                ImageIO.write(base, "png", outData);
                file = target.replace(".tga", ".png");
            }
            access.zipAddFileByte(file, outData.toByteArray());
            if (doRaytrace) {
                String name = target.substring(0, target.lastIndexOf("."));

                outData = new ByteArrayOutputStream();
                ImageIO.write(normalTexture, "png", outData);
                file += ";" + name+"_normal.png";
                access.zipAddFileByte(name+"_normal.png", outData.toByteArray());

                outData = new ByteArrayOutputStream();
                ImageIO.write(merTexture, "png", outData);
                file += ";" + name+"_mer.png";
                access.zipAddFileByte(name+"_mer.png", outData.toByteArray());
            }
            return file;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.restructure.RestructureAnimation.compute");
        }
    }
    /**
     * adds pole to banner base, only Raytrace textures
     *
     * @param access file access object to use
     * @param files: banner_base
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String computeRay(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {

            String file = "";
            // read input
            BufferedImage base = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));
            //load textures for normal/mer
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

            BufferedImage out_image = new BufferedImage(base.getWidth(), base.getHeight() * files.length, BufferedImage.TYPE_INT_ARGB);

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

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
            if (doRaytrace) {
                normalTexture = new BufferedImage(base.getWidth(), base.getHeight() * files.length, BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(base.getWidth(), base.getHeight() * files.length, BufferedImage.TYPE_INT_ARGB);

                // generate longer pole
                for (int x = 0; x < base.getWidth(); x++) {
                    for (int y = 0; y < base.getHeight(); y++) {
                        if (x >= base.getWidth() / 64 * 52 && x < base.getWidth() / 64 * 60) {
                            if (y < base.getHeight() / 64 * 44) {
                                writeRayTex(rayInTextures, normalTexture, merTexture, x, y, x - base.getWidth() / 8, y, config);
                            } else {
                                writeRayTex(rayInTextures, normalTexture, merTexture, x, y, x - base.getWidth() / 8, y - base.getHeight() / 64 * 20, config);
                            }
                        } else {
                            writeRayTex(rayInTextures, normalTexture, merTexture, x, y, x, y, config);
                        }
                    }
                }

                // WRITE IMAGE
                String name = target.substring(0, target.lastIndexOf("."));

                ByteArrayOutputStream outData = new ByteArrayOutputStream();
                ImageIO.write(normalTexture, "png", outData);
                file += ";" + name + "_normal.png";
                access.zipAddFileByte(name + "_normal.png", outData.toByteArray());

                outData = new ByteArrayOutputStream();
                ImageIO.write(merTexture, "png", outData);
                file += ";" + name + "_mer.png";
                access.zipAddFileByte(name + "_mer.png", outData.toByteArray());

                file += ";" + name+".texture_set.json";
                String fileName = name.substring(name.lastIndexOf('/')+1);
                access.zipAddFileByte(name+".texture_set.json", JsonCreationHelper.createTextureSet(fileName, fileName+"_mer", fileName+"_normal"));
            }
            return file;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.restructure.RestructureAnimation.compute");
        }
    }
}

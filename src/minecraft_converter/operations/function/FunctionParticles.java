package minecraft_converter.operations.function;

import javafx.scene.control.Label;
import minecraft_converter.Data;
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

public class FunctionParticles {
    /**
     * builds the particles atlas from the source files
     *
     * @param access file access object to use
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(Label fileProgress, FileAccess access, Settings config) throws ExitNow {
        try {
            String files = "";
            BufferedImage particle_atlas = null;

            BufferedImage[] rayInTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            for (Map.Entry<String, String> entry : Data.particlesOffset.entrySet()) {

                byte[] buffer = access.getFileBytes(entry.getKey(), true);
                if (buffer == null) {
                    if (config.partialParticles)
                        continue;
                    return "";
                }

                BufferedImage current = ImageIO.read(new ByteArrayInputStream(buffer));

                if (doRaytrace) {
                    for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                        int index = entry.getKey().lastIndexOf(".");
                        buffer = access.getFileBytes(entry.getKey().substring(0, index) + map.getKey() + entry.getKey().substring(index), true);
                        if (buffer != null) {
                            rayInTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                            if (entry.getKey().contains("explosion")) {
                                rayInTextures[map.getValue()] = scaleDown(rayInTextures[map.getValue()], 4);
                            }
                        }
                        else {
                            doRaytrace = false;
                            break;
                        }
                    }
                }

                if (entry.getKey().contains("explosion")) {
                    current = scaleDown(current, 4);
                }

                if (particle_atlas == null) {
                    int multiplier = 16;
                    if (entry.getKey().contains("flash"))
                        multiplier = 4;
                    particle_atlas = new BufferedImage(current.getWidth() * multiplier, current.getHeight() * multiplier, BufferedImage.TYPE_INT_ARGB);
                    if (doRaytrace) {
                        normalTexture = new BufferedImage(current.getWidth() * multiplier, current.getHeight() * multiplier, BufferedImage.TYPE_INT_ARGB);
                        merTexture = new BufferedImage(current.getWidth() * multiplier, current.getHeight() * multiplier, BufferedImage.TYPE_INT_ARGB);
                    }
                }
                for (String offset: entry.getValue().split(";")) {
                    String[] offsetString = offset.split(",");
                    int xOffset = Math.round(Float.parseFloat(offsetString[0]) * (particle_atlas.getHeight() / 16f));
                    int yOffset = Math.round(Float.parseFloat(offsetString[1]) * (particle_atlas.getWidth() / 16f));

                    for (int x = 0; x < current.getWidth(); x++) {
                        for (int y = 0; y < current.getHeight(); y++) {
                            particle_atlas.setRGB(x + xOffset, y + yOffset, current.getRGB(x, y));
                        }
                    }
                }

            }

            // WRITE IMAGE
            if (particle_atlas != null) {
                ByteArrayOutputStream outData = new ByteArrayOutputStream();
                ImageIO.write(particle_atlas, "png", outData);
                access.zipAddFileByte("textures/particle/particles.png", outData.toByteArray());
                Data.optionDone.put("particles", true);
                files = "textures/particle/particles.png";

                if (doRaytrace) {
                    String name = "textures/painting/particles";

                    outData = new ByteArrayOutputStream();
                    ImageIO.write(normalTexture, "png", outData);
                    files += ";" + name+"_normal.png";
                    access.zipAddFileByte(name+"_normal.png", outData.toByteArray());

                    outData = new ByteArrayOutputStream();
                    ImageIO.write(merTexture, "png", outData);
                    files += ";" + name+"_mer.png";
                    access.zipAddFileByte(name+"_mer.png", outData.toByteArray());

                    files += ";" + name+".texture_set.json";
                    String fileName = name.substring(name.lastIndexOf('/')+1);
                    access.zipAddFileByte(name+".texture_set.json", JsonCreationHelper.createTextureSet(fileName, fileName+"_mer", fileName+"_normal"));
                }
            }
            return files;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionParticles.compute");
        }
    }

    private static BufferedImage scaleDown(BufferedImage source, int scale) {
        BufferedImage out = new BufferedImage(source.getWidth()/scale, source.getHeight()/scale, source.getType());
        for (int x = 0; x < out.getWidth(); x++) {
            for (int y = 0; y < out.getHeight(); y++) {
                RGB color = new RGB();
                for (int i = 0; i < scale; i++) {
                    for (int j = 0; j < scale; j++) {
                        color.add(getColor(source.getRGB(x*scale+i, y*scale+j)));
                    }
                }
                color.div(scale*scale);
                out.setRGB(x, y, getIntColor(color));
            }
        }
        return out;
    }
}

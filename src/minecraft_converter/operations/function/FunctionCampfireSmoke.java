package minecraft_converter.operations.function;

import minecraft_converter.Data;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;

import javax.imageio.ImageIO;
import javafx.scene.control.Label;
import minecraft_converter.tools.JsonCreationHelper;
import minecraft_converter.tools.RaytracedWriter;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static minecraft_converter.tools.Messages.showException;

public class FunctionCampfireSmoke {
    /**
     * builds the particles atlas from the source files
     *
     * @param access file access object to use
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(Label fileProgress, FileAccess access, Settings config) throws ExitNow {
        try {
            BufferedImage smoke_atlas = null;
            BufferedImage[] rayInTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            for (Map.Entry<String, String> entry : Data.texturesMode.entrySet()) {
                if (!entry.getKey().startsWith("assets/minecraft/textures/particle/big_smoke_"))
                    continue;

                byte[] buffer = access.getFileBytes(entry.getKey(), true);
                if (buffer == null)
                    continue;

                BufferedImage current = ImageIO.read(new ByteArrayInputStream(buffer));

                if (doRaytrace) {
                    for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                        int index = entry.getKey().lastIndexOf(".");
                        buffer = access.getFileBytes(entry.getKey().substring(0, index) + map.getKey() + entry.getKey().substring(index), true);
                        if (buffer != null)
                            rayInTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                        else {
                            doRaytrace = false;
                            break;
                        }
                    }
                }

                //Platform.runLater(()->fileProgress.setText(entry.getKey().replace("assets/minecraft/textures/", "")));
                if (smoke_atlas == null) {
                    smoke_atlas = new BufferedImage(current.getWidth(), current.getHeight() * 12, BufferedImage.TYPE_INT_ARGB);
                    if (doRaytrace) {
                        normalTexture = new BufferedImage(current.getWidth(), current.getHeight() * 12, BufferedImage.TYPE_INT_ARGB);
                        merTexture = new BufferedImage(current.getWidth(), current.getHeight() * 12, BufferedImage.TYPE_INT_ARGB);
                    }
                }
                String offset = entry.getKey().replace("assets/minecraft/textures/particle/big_smoke_", "");
                int yOffset = Integer.parseInt(offset.substring(0, offset.lastIndexOf("."))) * smoke_atlas.getWidth();

                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        smoke_atlas.setRGB(x, y + yOffset, current.getRGB(x, y));
                        if (doRaytrace) {
                            RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                    x, y + yOffset,
                                    x, y,
                                    config);
                        }
                    }
                }

            }

            // WRITE IMAGE
            String file = "";
            if (smoke_atlas != null) {
                ByteArrayOutputStream outData = new ByteArrayOutputStream();
                ImageIO.write(smoke_atlas, "png", outData);
                access.zipAddFileByte("textures/particle/campfire_smoke.png", outData.toByteArray());
                Data.optionDone.put("campfire_smoke", true);
                file = "textures/particle/campfire_smoke.png";
                if (doRaytrace) {
                    String name = "textures/particle/campfire_smoke";

                    outData = new ByteArrayOutputStream();
                    ImageIO.write(normalTexture, "png", outData);
                    file += ";" + name+"_normal.png";
                    access.zipAddFileByte(name+"_normal.png", outData.toByteArray());

                    outData = new ByteArrayOutputStream();
                    ImageIO.write(merTexture, "png", outData);
                    file += ";" + name+"_mer.png";
                    access.zipAddFileByte(name+"_mer.png", outData.toByteArray());

                    file += ";" + name+".texture_set.json";
                    String fileName = name.substring(name.lastIndexOf('/')+1);
                    access.zipAddFileByte(name+".texture_set.json", JsonCreationHelper.createTextureSet(fileName, fileName+"_mer", fileName+"_normal"));
                }
            }
            return file;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionCampfireSmoke.compute");
        }
    }


}

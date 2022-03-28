package minecraft_converter.operations.function;

import minecraft_converter.Data;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;

import javafx.scene.control.Label;
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

public class FunctionPainting {
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
            BufferedImage painting_atlas = null;
            int paintings = Data.paintingOffset.size();;
            //Platform.runLater(()->fileProgress.setText("writing painting atlas: 0/"+paintings));
            int currentPainting = 0;

            BufferedImage[] rayInTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            for (Map.Entry<String, String> entry : Data.paintingOffset.entrySet()) {
                currentPainting++;
                int finalCurrentPainting = currentPainting;
                //Platform.runLater(()->fileProgress.setText("writing painting atlas: "+ finalCurrentPainting +"/"+paintings));

                byte[] buffer = access.getFileBytes(entry.getKey(), true);
                if (buffer == null) {
                    if (config.partialPainting)
                        continue;
                    return "";
                }

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

                String[] values = entry.getValue().split(";");

                if (painting_atlas == null) {
                    String[] size = values[1].split(",");
                    int sizeX = Integer.parseInt(size[0]);
                    int sizeY = Integer.parseInt(size[1]);
                    painting_atlas = new BufferedImage(current.getWidth() / sizeX * 16, current.getHeight() / sizeY * 16, BufferedImage.TYPE_INT_ARGB);
                    if (doRaytrace) {
                        normalTexture = new BufferedImage(current.getWidth() / sizeX * 16, current.getHeight() / sizeY * 16, BufferedImage.TYPE_INT_ARGB);
                        merTexture = new BufferedImage(current.getWidth() / sizeX * 16, current.getHeight() / sizeY * 16, BufferedImage.TYPE_INT_ARGB);
                    }
                }

                String[] offsetString = values[0].split(",");
                int xOffset = Math.round(Float.parseFloat(offsetString[0]) * (painting_atlas.getHeight() / 16f));
                int yOffset = Math.round(Float.parseFloat(offsetString[1]) * (painting_atlas.getWidth() / 16f));

                int tileX = 1;
                int tileY = 1;
                if (values.length > 2) {
                    String[] tiles = values[2].split(",");
                    tileX = Integer.parseInt(tiles[0]);
                    tileY = Integer.parseInt(tiles[1]);
                }

                for (int tx = 0; tx < tileX; tx++) {
                    for (int ty = 0; ty < tileY; ty++) {
                        for (int x = 0; x < current.getWidth(); x++) {
                            for (int y = 0; y < current.getHeight(); y++) {
                                painting_atlas.setRGB(x + xOffset + tx*current.getWidth(), y + yOffset + ty*current.getHeight(), current.getRGB(x, y));
                                if (doRaytrace) {
                                    RaytracedWriter.writeRayTex(rayInTextures, normalTexture, merTexture,
                                            x + xOffset + tx*current.getWidth(), y + yOffset + ty*current.getHeight(),
                                            x, y,
                                            config);
                                }
                            }
                        }
                    }
                }

            }

            // WRITE IMAGE
            if (painting_atlas != null) {
                ByteArrayOutputStream outData = new ByteArrayOutputStream();
                ImageIO.write(painting_atlas, "png", outData);
                access.zipAddFileByte("textures/painting/kz.png", outData.toByteArray());
                Data.optionDone.put("painting", true);
                files = "textures/painting/kz.png";
                if (doRaytrace) {
                    String name = "textures/painting/kz";

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
            throw new ExitNow("operations.function.FunctionPainting.compute");
        }
    }
}

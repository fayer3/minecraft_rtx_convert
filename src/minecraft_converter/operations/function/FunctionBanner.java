package minecraft_converter.operations.function;

import minecraft_converter.Data;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;

import javax.imageio.ImageIO;
import javafx.scene.control.Label;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static minecraft_converter.tools.ColorOperations.getColor;
import static minecraft_converter.tools.ColorOperations.getIntColor;
import static minecraft_converter.tools.Messages.showException;

public class FunctionBanner {
    /**
     * builds the banner atlas from the source files
     *
     * @param access file access object to use
     * @param config settings object
     * @return path to files saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(Label fileProgress, FileAccess access, Settings config) throws ExitNow {
        try {
            String files = "";
            BufferedImage banner_atlas = null;
            // patterns for pillager banner
            BufferedImage base = null;
            BufferedImage rhombus = null;
            BufferedImage stripe_bottom = null;
            BufferedImage stripe_center = null;
            BufferedImage border = null;
            BufferedImage stripe_middle = null;
            BufferedImage half_horizontal = null;
            BufferedImage circle = null;

            int totalBanners = Data.bannerOffset.size();
            int currentBanner = 0;
            //Platform.runLater(()->fileProgress.setText("writing banner atlas: 0/" + totalBanners));

            int pixelSize = 0;

            for (Map.Entry<String, String> entry : Data.bannerOffset.entrySet()) {
                currentBanner ++;
                int finalCurrentBanner = currentBanner;
                //Platform.runLater(()->fileProgress.setText("writing banner atlas: " + finalCurrentBanner + "/" + totalBanners));

                byte[] buffer = access.getFileBytes(entry.getKey(), true);
                if (buffer == null) {
                    if (config.partialBanner)
                        continue;
                    return "";
                }

                String[] offsetString = entry.getValue().split(",");
                int yOffset = Integer.parseInt(offsetString[0]);
                int xOffset = Integer.parseInt(offsetString[1]);

                BufferedImage current = ImageIO.read(new ByteArrayInputStream(buffer));

                if (entry.getKey().contains("banner_base"))
                    base = current;
                else if (entry.getKey().contains("rhombus"))
                    rhombus = current;
                else if (entry.getKey().contains("stripe_bottom"))
                    stripe_bottom = current;
                else if (entry.getKey().contains("stripe_center"))
                    stripe_center = current;
                else if (entry.getKey().contains("/border"))
                    border = current;
                else if (entry.getKey().contains("stripe_middle"))
                    stripe_middle = current;
                else if (entry.getKey().contains("half_horizontal"))
                    half_horizontal = current;
                else if (entry.getKey().contains("circle"))
                    circle = current;

                if (banner_atlas == null) {
                    banner_atlas = new BufferedImage(current.getWidth() * 8, current.getHeight() * 8, BufferedImage.TYPE_INT_ARGB);
                    pixelSize = current.getWidth() / 64;
                    /*// initialize image with color
                    for (int x = 0; x < banner_atlas.getWidth(); x++) {
                        for (int y = 0; y < banner_atlas.getHeight(); y++) {
                            banner_atlas.setRGB(x, y, getIntColor(new RGB(255, 255, 255, 0)));
                        }
                    }*/
                }
                if (entry.getKey().contains("banner_base")) {
                    for (int x = 0; x < current.getWidth(); x++) {
                        for (int y = 0; y < current.getHeight(); y++) {
                            banner_atlas.setRGB(x + xOffset * current.getWidth(), y + yOffset * current.getHeight(), current.getRGB(x, y));
                        }
                    }
                } else {
                    // dont copy border of other banners, unnecessary
                    for (int x = 0; x < pixelSize*42; x++) {
                        for (int y = 0; y < pixelSize*41; y++) {
                            banner_atlas.setRGB(x + xOffset * current.getWidth(), y + yOffset * current.getHeight(), current.getRGB(x, y));
                        }
                    }
                }

            }

            // WRITE IMAGE
            if (banner_atlas != null) {
                //Platform.runLater(()->fileProgress.setText("saving banner atlas"));
                ByteArrayOutputStream outData = new ByteArrayOutputStream();
                ImageIO.write(banner_atlas, "png", outData);
                access.zipAddFileByte("textures/entity/banner/banner.png", outData.toByteArray());
                files = "textures/entity/banner/banner.png";
                Data.optionDone.put("banner", true);
            }

            // create pillager banner from patterns
            // only create if all patterns are available
            if (base != null &&
                    rhombus != null &&
                    stripe_bottom != null &&
                    stripe_center != null &&
                    border != null &&
                    stripe_middle != null &&
                    half_horizontal != null &&
                    circle != null) {

                //Platform.runLater(()->fileProgress.setText("generating illager banner: 0/8"));

                // generate longer pole
                for (int x = base.getWidth() / 64 * 52; x < base.getWidth() / 64 * 60; x++) {
                    for (int y = 0; y < base.getHeight(); y++) {
                        if (y < base.getHeight() / 64 * 44)
                            base.setRGB(x, y, base.getRGB(x - base.getWidth() / 8, y));
                        else
                            base.setRGB(x, y, base.getRGB(x - base.getWidth() / 8, y - base.getHeight() / 64 * 20));
                    }
                }

                for (int i = 0; i < 8; i++) {
                    int finalI = i+1;
                    //Platform.runLater(()->fileProgress.setText("generating illager banner: " + finalI + "/8"));
                    BufferedImage current = null;
                    RGB color = null;
                    switch (i) {
                        case 0:
                            current = rhombus;
                            color = new RGB(22,156,156,255); //cyan
                            break;
                        case 1:
                            current = stripe_bottom;
                            color = new RGB(157,157,151,255); //light gray
                            break;
                        case 2:
                            current = stripe_center;
                            color = new RGB(71, 79, 82, 255); //gray
                            break;
                        case 3:
                            current = border;
                            color = new RGB(157,157,151,255); //light gray
                            break;
                        case 4:
                            current = stripe_middle;
                            color = new RGB(29, 29, 33, 255); //black
                            break;
                        case 5:
                            current = half_horizontal;
                            color = new RGB(157,157,151,255); //light gray
                            break;
                        case 6:
                            current = circle;
                            color = new RGB(157,157,151,255); //light gray
                            break;
                        case 7:
                            current = border;
                            color = new RGB(29, 29, 33, 255); //black
                            break;
                    }

                    for (int x = 0; x < pixelSize * 42; x++) {
                        for (int y = 0; y < pixelSize * 41; y++) {
                            RGB finalColor = getColor(base.getRGB(x,y));
                            RGB patternColor = getColor(current.getRGB(x,y));
                            int average = (patternColor.r + patternColor.g + patternColor.b) / 3;
                            patternColor.r = patternColor.g = patternColor.b = 255;
                            if (config.packFormat <= 4) // packages before 5 used lightness for blending
                                patternColor.a = average;

                            finalColor = finalColor.mix(patternColor.mult(color));
                            base.setRGB(x, y, getIntColor(finalColor));
                        }
                    }
                }
                ByteArrayOutputStream outData = new ByteArrayOutputStream();
                ImageIO.write(base, "png", outData);
                access.zipAddFileByte("textures/entity/banner/banner_pattern_illager.png", outData.toByteArray());
                if (files.isEmpty()) {
                    files = "textures/entity/banner/banner_pattern_illager.png";
                } else {
                    files += ";textures/entity/banner/banner_pattern_illager.png";
                }
                Data.optionDone.put("banner", true);
            }
            return files;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionBanner.compute");
        }
    }
}

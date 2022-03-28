package minecraft_converter.operations.function;

import javafx.scene.control.Label;
import minecraft_converter.Data;
import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.operations.restructure.RestructureBannerBase;
import minecraft_converter.tools.FileAccess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static minecraft_converter.tools.ColorOperations.getColor;
import static minecraft_converter.tools.ColorOperations.getIntColor;
import static minecraft_converter.tools.Messages.showException;

public class FunctionBannerIllager {
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

            // patterns for pillager banner
            try {
                BufferedImage base = ImageIO.read(new ByteArrayInputStream(access.getFileBytes("assets/minecraft/textures/entity/banner_base.png", false)));
                BufferedImage rhombus = ImageIO.read(new ByteArrayInputStream(access.getFileBytes("assets/minecraft/textures/entity/banner/rhombus.png", false)));
                BufferedImage stripe_bottom = ImageIO.read(new ByteArrayInputStream(access.getFileBytes("assets/minecraft/textures/entity/banner/stripe_bottom.png", false)));
                BufferedImage stripe_center = ImageIO.read(new ByteArrayInputStream(access.getFileBytes("assets/minecraft/textures/entity/banner/stripe_center.png", false)));
                BufferedImage border = ImageIO.read(new ByteArrayInputStream(access.getFileBytes("assets/minecraft/textures/entity/banner/border.png", false)));
                BufferedImage stripe_middle = ImageIO.read(new ByteArrayInputStream(access.getFileBytes("assets/minecraft/textures/entity/banner/stripe_middle.png", false)));
                BufferedImage half_horizontal = ImageIO.read(new ByteArrayInputStream(access.getFileBytes("assets/minecraft/textures/entity/banner/half_horizontal.png", false)));
                BufferedImage circle = ImageIO.read(new ByteArrayInputStream(access.getFileBytes("assets/minecraft/textures/entity/banner/circle.png", false)));

                int pixelSize = base.getWidth() / 64;

                // create pillager banner from patterns

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
                    int finalI = i + 1;
                    //Platform.runLater(()->fileProgress.setText("generating illager banner: " + finalI + "/8"));
                    BufferedImage current = null;
                    RGB color = null;
                    switch (i) {
                        case 0:
                            current = rhombus;
                            color = new RGB(22, 156, 156, 255); //cyan
                            break;
                        case 1:
                            current = stripe_bottom;
                            color = new RGB(157, 157, 151, 255); //light gray
                            break;
                        case 2:
                            current = stripe_center;
                            color = new RGB(71, 79, 82, 255); //gray
                            break;
                        case 3:
                            current = border;
                            color = new RGB(157, 157, 151, 255); //light gray
                            break;
                        case 4:
                            current = stripe_middle;
                            color = new RGB(29, 29, 33, 255); //black
                            break;
                        case 5:
                            current = half_horizontal;
                            color = new RGB(157, 157, 151, 255); //light gray
                            break;
                        case 6:
                            current = circle;
                            color = new RGB(157, 157, 151, 255); //light gray
                            break;
                        case 7:
                            current = border;
                            color = new RGB(29, 29, 33, 255); //black
                            break;
                    }

                    for (int x = 0; x < pixelSize * 42; x++) {
                        for (int y = 0; y < pixelSize * 41; y++) {
                            RGB finalColor = getColor(base.getRGB(x, y));
                            RGB patternColor = getColor(current.getRGB(x, y));
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
                access.zipAddFileByte("textures/entity/banner/banner_illager.png", outData.toByteArray());
                return "textures/entity/banner/banner_illager.png"+ RestructureBannerBase.computeRay(access, new String[]{"assets/minecraft/textures/entity/banner_base.png"}, "textures/entity/banner/banner_illager.png", config);

            } catch (ExitNow e) {
                System.out.println("FunctionBannerPillager aborted, not all needed banners available");
                return "";
            }
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionBannerPillager.compute");
        }
    }
}

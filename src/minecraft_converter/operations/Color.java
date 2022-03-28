package minecraft_converter.operations;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static minecraft_converter.tools.ColorOperations.getColor;
import static minecraft_converter.tools.ColorOperations.getIntColor;
import static minecraft_converter.tools.Messages.showException;

public class Color {
    /**
     * processes the given parameters and calls the appropriate copy Method
     *
     * @param access file access object to use
     * @param parameters array of parameters if null a raw file copy is done
     * @param source input path of the source image
     * @param target output to write to
     * @param config settings Object
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] parameters, String source, String target, Settings config) throws ExitNow {
        if (parameters.length > 1) {
            String setting = parameters[0];
            if (setting.startsWith("option."))
                setting = config.options.get(setting.replace("option.", ""));
            String[] fromColors = setting.split(",");
            RGB from = new RGB(Integer.parseInt(fromColors[0]), Integer.parseInt(fromColors[1]), Integer.parseInt(fromColors[2]),255);

            setting = parameters[1];
            if (setting.startsWith("option."))
                setting = config.options.get(setting.replace("option.", ""));
            String[] toColors = setting.split(",");
            RGB to = new RGB(Integer.parseInt(toColors[0]), Integer.parseInt(toColors[1]), Integer.parseInt(toColors[2]),255);
            return color(access, source, target, from, to, false, config);
        } else {
            throw new ExitNow("Color operation needs two arguments on: '" + target + "'");
        }
    }
    /**
     * colors the the image in a specific color from one color to an other
     *
     * @param source input path of the source image
     * @param target output to write to
     * @param from source reference color for colorization
     * @param to final color the to colorize with
     * @param allColors if true all colors are colorized, if false only grayish colors are colorized
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String color(FileAccess access, String source, String target, RGB from, RGB to, boolean allColors, Settings config)  throws ExitNow {
        try {
            // read input
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(source, false)));

            // output
            BufferedImage out_image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            boolean transparentPresent = false;

            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    RGB color = getColor(image.getRGB(x,y));

                    boolean colorize = allColors;

                    float average = (color.r + color.g + color.b) / 3f;
                    float low = average - 5;
                    float high = average + 5;
                    if (allColors || color.r > low && color.r < high && color.g > low && color.g < high && color.b > low && color.b < high) {
                        colorize = true;
                    }
                    if (colorize) {
                        color.r = Math.round((float) color.r / from.r * to.r);
                        color.g = Math.round((float) color.g / from.g * to.g);
                        color.b = Math.round((float) color.b / from.b * to.b);
                        if (color.r == 0 && color.b == 0 && color.g == 0 && color.a == 0) {
                            color.r = to.r;
                            color.g = to.g;
                            color.b = to.b;
                        }
                    }
                    out_image.setRGB(x, y, getIntColor(color));
                    transparentPresent = transparentPresent || color.a < 255;
                }
            }

            // WRITE IMAGE
            // Writing to file taking type and path as
            //ImageIO.write(out_image, "png", new File(out.getAbsolutePath().replace(".tga",".png")));
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            String file = "";
            // only use tga if transparent pixels are present, because tga is not compressed currently
            if (config.raytraced && config.useTga && (transparentPresent && (target.contains("/blocks/") || !config.limitTransparencyToBlocks))) {
                ImageIO.write(out_image, "tga", outData);
                file = target.replace(".png", ".tga");
            }
            else {
                ImageIO.write(out_image, "png", outData);
                file = target.replace(".tga", ".png");
            }
            access.zipAddFileByte(file, outData.toByteArray());
            return file;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.Color.color");
        }
    }
}
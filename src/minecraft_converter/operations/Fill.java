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

public class Fill {

    /**
     * processes the given parameters and calls the fill Method
     *
     * @param access file access object to use
     * @param parameters array of parameters if null a raw file copy is done
     * @param source input path of the source image
     * @param target output to write to
     * @param config settings object
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] parameters, String source, String target, Settings config) throws ExitNow {
        if (parameters.length > 0) {
            boolean onlyTransparent = true;
            RGB rgb = new RGB();
            for (String parameter : parameters) {
                String[] settings = parameter.split("=");
                String setting = settings[1];
                if (setting.startsWith("option."))
                    setting = config.options.get(setting.replace("option.", ""));
                if (config.raytraced && settings[0].contains("(!rtx)"))
                    continue;
                switch(settings[0].replace("(!rtx)", "")) {
                    case "onlyTransparent":
                        onlyTransparent = "true".equals(setting);
                        break;
                    case "color":
                        String[] color = setting.split(",");
                        if (color.length == 4)
                            rgb.a = Integer.parseInt(color[3]);
                        rgb.r = Integer.parseInt(color[0]);
                        rgb.g = Integer.parseInt(color[1]);
                        rgb.b = Integer.parseInt(color[2]);
                        break;

                }
                /*if (parameter.startsWith("onlyTransparent="))
                    onlyTransparent = parameter.replace("onlyTransparent=", "").equals("true");
                else {
                    String[] color = parameter.split(",");
                    if (color.length == 4)
                        rgb.a = Integer.parseInt(color[3]);
                    rgb.r = Integer.parseInt(color[0]);
                    rgb.g = Integer.parseInt(color[1]);
                    rgb.b = Integer.parseInt(color[2]);
                }*/
            }
            return fill(access, source, target, rgb, onlyTransparent, config);
        } else {
            throw new ExitNow("fill operation needs one argument on: '" + target + "'");
        }
    }


    /**
     * fills the image with the specified color
     * @param access file access object to use
     * @param source input path of the source image
     * @param target output to write to
     * @param fill the color to fill the image with
     * @param onlyTransparent only fills transparent pixels with the specified color
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    private static String fill(FileAccess access, String source, String target, RGB fill, boolean onlyTransparent, Settings config)  throws ExitNow {
        try {
            // read input
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(source, false)));

            // output
            BufferedImage out_image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            boolean transparentPresent = false;
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    RGB color = getColor(image.getRGB(x, y));
                    if (!onlyTransparent || color.a == 0) {
                        color = fill;
                    }
                    out_image.setRGB(x, y, getIntColor(color));
                    transparentPresent = transparentPresent || color.a < 255;
                }
            }

            // WRITE IMAGE
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
            throw new ExitNow("Fill.fill");
        }
    }
}
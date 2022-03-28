package minecraft_converter.operations;

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

public class Combine {

    /**
     * processes the given parameters and calls the combine Method
     *
     * @param access file access object to use
     * @param parameters array of parameters if null a raw file copy is done
     * @param source input path of the source image
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] parameters, String source, String target, Settings config) throws ExitNow {
        if (parameters.length > 0) {
            String[] inputs = new String[parameters.length / 2];
            CombineOptions[] options = new CombineOptions[parameters.length / 2];
            for (int p = 0; p < parameters.length; p += 2) {
                options[p / 2] = new CombineOptions();
                if (parameters[p].equals("this"))
                    inputs[p / 2] = source;
                else
                    inputs[p / 2] = parameters[p];
                for (String par : parameters[p + 1].split(",")) {
                    if ("copy".equals(par))
                        continue;
                    String[] settings = par.split("=");
                    String setting = settings[1];
                    if (setting.startsWith("option."))
                        setting = config.options.get(setting.replace("option.", ""));

                    if (config.raytraced && settings[0].contains("(!rtx)"))
                        continue;
                    switch(settings[0].replace("(!rtx)", "")) {
                        case "transparent":
                            options[p / 2].transparent = "true".equals(setting);
                            break;
                        case "minAlphaToCopy":
                            options[p / 2].minAlphaToCopy = Integer.parseInt(setting);
                            break;
                        case "only_difference":
                            options[p / 2].only_difference = "true".equals(setting);;
                            break;
                        case "rotate":
                            options[p / 2].rotate = Integer.parseInt(setting);
                            break;
                        case "maxAlpha":
                            options[p / 2].maxAlpha = Integer.parseInt(setting);
                            break;
                        case "alphaColor": {
                            String[] colors = setting.split("-");
                            options[p / 2].alphaColor = new RGB(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]), 0);
                            break;
                        }
                        case "blend":
                            options[p / 2].blend = "true".equals(setting);;
                            break;
                        case "colorMultInt":
                            options[p / 2].colorMultInt = Integer.parseInt(setting);
                            break;
                        case "color": {
                            String[] colors = setting.split("_");
                            String[] from = colors[0].split("-");
                            String[] to = colors[1].split("-");
                            options[p/2].from = new RGB(Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]), 255);
                            options[p/2].to = new RGB(Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]), 255);
                            break;
                        }
                        case "colorOnlyGray":
                            options[p/2].colorOnlyGray = "true".equals(setting);
                            break;
                    }


                    /*if (par.equals("transparent"))
                        options[p / 2].transparent = true;
                    else if (par.startsWith("minAlphaToCopy="))
                        options[p / 2].minAlphaToCopy = Integer.parseInt(par.split("=")[1]);
                    else if (par.equals("only_difference"))
                        options[p / 2].only_difference = true;
                    else if (par.startsWith("rotate="))
                        options[p / 2].rotate = Integer.parseInt(par.split("=")[1]);
                    else if (par.startsWith("maxAlpha="))
                        options[p / 2].maxAlpha = Integer.parseInt(par.split("=")[1]);
                    else if (par.startsWith("alphaColor=")) {
                        String[] colors = par.substring(par.indexOf('=')+1).split("-");
                        options[p / 2].alphaColor = new RGB(Integer.parseInt(colors[0]),Integer.parseInt(colors[1]),Integer.parseInt(colors[2]),0);
                    }else if (par.equals("blend"))
                        options[p / 2].blend = true;
                    else if (par.startsWith("colorMultInt="))
                        options[p / 2].colorMultInt = Integer.parseInt(par.replace("colorMultInt=", ""));*/
                }
            }
            return combine(access, inputs, options, target, config);
        } else {
            throw new ExitNow("Not enough parameters for combine on: '" + target + "'");
        }
    }

    /**
     * options for combining images
     * transparent:     sets the alpha value to 0
     * minAlphaToCopy:  only writes a color if the alpha is >= this value
     * only_difference: only writes pixels that are not equal to the previous color
     * rotate:          <0: rotation 90° anticlockwise
     *                  >0: rotation 90° clockwise
     *                  ==0: no rotation
     * alphaColor:      color for alpha, if null the source color is used
     * maxAlpha:        limits the alpha value to the speciefed value
     * blend:           mixes the new color with the old, by the new alpha
     */
    private static class CombineOptions {
        public boolean transparent = false;
        public int minAlphaToCopy = 0;
        public boolean only_difference = false;
        public int rotate = 0;
        public RGB alphaColor = null;
        public int maxAlpha = 255;
        public int colorMultInt = -1;
        public RGB from = null;
        public RGB to = null;
        public boolean colorOnlyGray = true;
        public boolean blend;
    }

    /**
     * combines multiple images to one
     *
     * @param access file access object to use
     * @param in input paths of the source images
     * @param options options for each source image
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    private static String combine(FileAccess access, String[] in, CombineOptions[] options, String target, Settings config)  throws ExitNow {
        try {
            // read input
            BufferedImage[] images = new BufferedImage[in.length];

            BufferedImage[][] rayInTextures = new BufferedImage[in.length][config.textures.size()];
            boolean doRaytrace = config.raytraced;

            for (int i = 0; i < in.length; i++) {
                images[i] = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(in[i], false)));
                //load textures for normal/mer
                if (doRaytrace) {
                    for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                        int index = in[i].lastIndexOf(".");
                        byte[] buffer = access.getFileBytes(in[i].substring(0, index) + map.getKey() + in[i].substring(index), true);
                        if (buffer != null)
                            rayInTextures[i][map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                        else {
                            doRaytrace = false;
                            break;
                        }
                    }
                }
            }

            // output
            BufferedImage out_image = new BufferedImage(images[0].getWidth(), images[0].getHeight(), BufferedImage.TYPE_INT_ARGB);

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            if (doRaytrace) {
                normalTexture = new BufferedImage(images[0].getWidth(), images[0].getHeight(), BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(images[0].getWidth(), images[0].getHeight(), BufferedImage.TYPE_INT_ARGB);
            }
            boolean transparentPresent = false;

            for (int i = 0; i < images.length; i++) {
                for (int x = 0; x < images[i].getWidth(); x++) {
                    for (int y = 0; y < images[i].getHeight(); y++) {
                        int xOut = x;
                        int yOut = y;
                        if (options[i].rotate < 0) {
                            xOut = y;
                            yOut = images[i].getWidth() - x - 1;
                        } else if (options[i].rotate > 0) {
                            xOut = images[i].getWidth() - y - 1;
                            yOut = x;
                        }
                        RGB color = getColor(images[i].getRGB(x, y));
                        if (options[i].only_difference && out_image.getRGB(xOut, yOut) == images[i].getRGB(x, y)) {
                            continue;
                        }
                        if (color.a < options[i].minAlphaToCopy) {
                            continue;
                        }
                        if (options[0].alphaColor != null && color.a == 0) {
                            color = options[i].alphaColor;
                        }
                        if (color.a > options[i].maxAlpha) {
                            color.a = options[i].maxAlpha;
                        }
                        if (options[i].transparent) {
                            color.a = 0;
                        }
                        if (options[i].colorMultInt >=0) {
                            RGB mult = getColor(options[i].colorMultInt);
                            mult.a = 255;
                            color.mult(mult);
                        }
                        if (options[i].from != null && options[i].to != null) {
                            boolean colorize = !options[i].colorOnlyGray;
                            float average = (color.r + color.g + color.b) / 3f;
                            float low = average - 5;
                            float high = average + 5;
                            if (color.r > low && color.r < high && color.g > low && color.g < high && color.b > low && color.b < high) {
                                colorize = true;
                            }
                            if (colorize) {
                                color.r = Math.round((float) color.r / options[i].from.r * options[i].to.r);
                                color.g = Math.round((float) color.g / options[i].from.g * options[i].to.g);
                                color.b = Math.round((float) color.b / options[i].from.b * options[i].to.b);
                                if (color.r == 0 && color.b == 0 && color.g == 0 && color.a == 0) {
                                    color.r = options[i].to.r;
                                    color.g = options[i].to.g;
                                    color.b = options[i].to.b;
                                }
                            }
                        }
                        if (options[i].blend) {
                            RGB source = getColor(out_image.getRGB(xOut, yOut));
                            int alpha = source.a;
                            color = source.mix(color);
                            color.a = alpha;
                        }
                        out_image.setRGB(xOut, yOut, getIntColor(color));
                        transparentPresent = transparentPresent || color.a < 255;

                        if (doRaytrace) {
                            if (x < normalTexture.getWidth() && y < normalTexture.getHeight()) {
                                RGB normal = getColor(rayInTextures[i][config.textures.get(config.converter.getNormalSuffix())].getRGB(x, y));
                                normal = config.converter.getNormal(normal);
                                // alter normal when rotation / mirroring
                                // guide here: https://robonobodojo.wordpress.com/2015/11/22/transforming-normal-maps/
                                if (options[i].rotate < 0) {
                                    RGB temp = new RGB(normal);
                                    normal.r = temp.g;
                                    normal.g = 255-temp.r;
                                } else if (options[i].rotate > 0) {
                                    RGB temp = new RGB(normal);
                                    normal.r = 255-temp.g;
                                    normal.g = temp.r;
                                }
                                normalTexture.setRGB(xOut, yOut, getIntColor(normal));

                                RGB mer = new RGB(0, 0, 0, 255);
                                mer.r = config.converter.getMetallic(getColor(rayInTextures[i][config.textures.get(config.converter.getMetallicSuffix())].getRGB(x, y)));
                                mer.g = config.converter.getEmissive(getColor(rayInTextures[i][config.textures.get(config.converter.getEmissiveSuffix())].getRGB(x, y)));
                                mer.b = config.converter.getRoughness(getColor(rayInTextures[i][config.textures.get(config.converter.getRoughnessSuffix())].getRGB(x, y)));
                                merTexture.setRGB(xOut, yOut, getIntColor(mer));
                            }
                        }
                    }
                }
            }

            // WRITE IMAGE
            // Output file path
            String out = "";
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
            out = file;
            if (doRaytrace) {
                String name = target.substring(0, target.lastIndexOf("."));

                outData = new ByteArrayOutputStream();
                ImageIO.write(normalTexture, "png", outData);
                out += out.isEmpty() ? name+"_normal.png" : ";" + name+"_normal.png";
                access.zipAddFileByte(name+"_normal.png", outData.toByteArray());

                outData = new ByteArrayOutputStream();
                ImageIO.write(merTexture, "png", outData);
                out += ";" + name+"_mer.png";
                access.zipAddFileByte(name+"_mer.png", outData.toByteArray());

                out += ";" + name+".texture_set.json";
                String fileName = name.substring(name.lastIndexOf('/')+1);
                access.zipAddFileByte(name+".texture_set.json", JsonCreationHelper.createTextureSet(fileName, fileName+"_mer", fileName+"_normal"));
            }
            return out;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.Combine.combine");
        }
    }
}
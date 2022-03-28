package minecraft_converter.operations;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;
import minecraft_converter.tools.JsonCreationHelper;
import minecraft_converter.tools.RaytracedWriter;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static minecraft_converter.tools.ColorOperations.*;
import static minecraft_converter.tools.Messages.showException;

public class Copy {

    /**
     * Options for copy operations
     * rotate <0: rotation 90° anticlockwise
     *        >0: rotation 90° clockwise
     *        ==0: no rotation
     * mirror_v : vertical mirror
     * mirror_h : horizontal mirror
     * alpha : alpha to set, only used if >= 0
     * setAlphaGreyOnly : only set alpha on grayish pixels
     * setAlphaColorOnly: only set alpha on non grayish pixels;
     * minAlphaToSet: minimum source alpha to set new alpha
     * alphaGrayThreshold: if positive every color is only treated as gray if every channel is above this;
     * grayscale: convert the image to grayscale
     * alphaColor: color for alpha, if null the source color is used
     * maxAlpha: limits the alpha value to the specified value
     * height: sets the image height to imageheight*height
     * width: sets the image width to imagewidth*width
     * heightFrames: sets the image height in frames, one frame is width tall
     *
     * adjust colors
     * old Min/Max are the values of the source image
     * these get remapped to the range new Min/Max
     * values over oldMax are clamped to oldMax, same for oldMin
     *
     * single: saves only the 'first' or 'last' frame
     */
    private static class CopyOptions {
        // mirror and rotate not at the same time
        // mirror has priority
        public int rotate = 0;
        public boolean mirror_x = false;
        public boolean mirror_y = false;
        public int alpha = -1;
        public boolean setAlphaGreyOnly = false;
        public boolean setAlphaColorOnly = false;
        public int minAlphaToSet = 0;
        public int alphaGrayThreshold = -1;
        public boolean grayscale = false;
        public RGB alphaColor = null;
        public int maxAlpha = 255;
        public float height = 1;
        public float width = 1;
        public int heightFrames = -1;

        public int oldMin = -1;
        public int oldMax = -1;
        public int newMin = -1;
        public int newMax = -1;

        public RGB from = null;
        public RGB to = null;
        public boolean colorOnlyGray = true;

        public String single = "";
    }

    /**
     * processes the given parameters and calls the appropriate copy Method
     *
     * @param access file access object to use
     * @param parameters array of parameters if null a raw file copy is done
     * @param source input path of the source image
     * @param target output to write to
     * @param onlyRaytrace only writes raytrace textures and skips normal texture
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] parameters, String source, String target, Settings config, boolean onlyRaytrace) throws ExitNow {
        if (parameters != null) {
            CopyOptions options = new CopyOptions();
            if (!onlyRaytrace) {
                for (String parameter : parameters) {
                    String[] settings = parameter.split("=");
                    String setting = settings[1];
                    if (setting.startsWith("option."))
                        setting = config.options.get(setting.replace("option.", ""));
                    if (config.raytraced && settings[0].contains("(!rtx)"))
                        continue;
                    switch(settings[0].replace("(!rtx)", "")) {
                        case "rotate":
                            options.rotate = Integer.parseInt(setting);
                            break;
                        case "mirror_x":
                            options.mirror_x = "true".equals(setting);
                            break;
                        case "mirror_y":
                            options.mirror_y = "true".equals(setting);
                            break;
                        case "setAlphaGreyOnly":
                            options.setAlphaGreyOnly = "true".equals(setting);
                            break;
                        case "setAlphaColorOnly":
                            options.setAlphaColorOnly = "true".equals(setting);
                            break;
                        case "alphaGrayThreshold":
                            options.alphaGrayThreshold = Integer.parseInt(setting);
                            break;
                        case "grayscale":
                            options.grayscale = "true".equals(setting);
                            break;
                        case "alpha":
                            options.alpha = Integer.parseInt(setting);
                            break;
                        case "maxAlpha":
                            options.maxAlpha = Integer.parseInt(setting);
                            break;
                        case "minAlphaToSet":
                            options.minAlphaToSet = Integer.parseInt(setting);
                            break;
                        case "alphaColor":
                            String[] rgb = setting.split(",");
                            options.alphaColor = new RGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]), 0);
                            break;
                        case "height":
                            options.height = Float.parseFloat(setting);
                            break;
                        case "heightFrames":
                            options.heightFrames = Integer.parseInt(setting);
                            break;
                        case "width":
                            options.width = Float.parseFloat(setting);
                            break;
                        case "adjustColor":
                            String[] values = setting.split(",");
                            if (values.length != 4)
                                throw new ExitNow("option adjustColor of copy needs four arguments");
                            options.oldMin = Integer.parseInt(values[0]);
                            options.oldMax = Integer.parseInt(values[1]);
                            options.newMin = Integer.parseInt(values[2]);
                            options.newMax = Integer.parseInt(values[3]);
                            break;
                        case "single":
                            options.single = setting;
                            break;
                        case "color": {
                            String[] colors = setting.split("_");
                            String[] from = colors[0].split("-");
                            String[] to = colors[1].split("-");
                            options.from = new RGB(Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]), 255);
                            options.to = new RGB(Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]), 255);
                            break;
                        }
                        case "colorOnlyGray":
                            options.colorOnlyGray = "true".equals(setting);
                            break;
                    }

                    /*if (parameter.startsWith("rotate")) {
                        options.rotate = Integer.parseInt(settings[1]);
                    } else if (parameter.startsWith("mirror_v"))
                        options.mirror_v = true;
                    else if (parameter.startsWith("mirror_h"))
                        options.mirror_h = true;
                    else if (parameter.startsWith("setAlphaGreyOnly")) {
                        options.setAlphaGreyOnly = true;
                    } else if (parameter.startsWith("setAlphaColorOnly")) {
                        options.setAlphaColorOnly = true;
                    } else if (parameter.startsWith("alphaGrayThreshold=")) {
                        options.alphaGrayThreshold = Integer.parseInt(settings[1]);
                    } else if (parameter.startsWith("grayscale"))
                        options.grayscale = true;
                    else if (parameter.startsWith("alpha="))
                        options.alpha = Integer.parseInt(settings[1]);
                    else if (parameter.startsWith("alphaColor=")) {
                        String[] rgb = settings[1].split(",");
                        options.alphaColor = new RGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]), 0);
                    } else if (parameter.startsWith("height="))
                        options.height = Float.parseFloat(settings[1]);
                    else if (parameter.startsWith("heightFrames="))
                        options.heightFrames = Integer.parseInt(settings[1]);
                    else if (parameter.startsWith("width="))
                        options.width = Float.parseFloat(settings[1]);
                    else if (parameter.startsWith("adjustColor=")) {
                        String[] values = settings[1].split(",");
                        if (values.length != 4)
                            throw new ExitNow("option adjustColor of copy needs four arguments");
                        options.oldMin = Integer.parseInt(values[0]);
                        options.oldMax = Integer.parseInt(values[1]);
                        options.newMin = Integer.parseInt(values[2]);
                        options.newMax = Integer.parseInt(values[3]);
                    } else if (parameter.startsWith("single=")) {
                        options.single = parameter.replace("single=", "");
                    }*/
                }
            }
            return copy(access, source, target, options, config, onlyRaytrace);
        } else {
            String file = "";
            if (config.raytraced) {
                return copy(access, source, target, new CopyOptions(), config, onlyRaytrace);
            }
            else {
                if (!onlyRaytrace) {
                    file = copyRaw(access, source, target, config);
                    if (!file.isEmpty())
                        file += ";";
                }
                return file+copy(access, source, target, new CopyOptions(), config, true);
            }
        }
    }

    /**
     * simple file copy without alterations
     * @param access file access object to use
     * @param source input path of the source image
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    private static String copyRaw(FileAccess access, String source, String target, Settings config) throws ExitNow {

        // check if image is no greyscale, else convert
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(source, false)));
            if (image.getColorModel().getColorSpace().getType() != ColorSpace.TYPE_GRAY) {
                String format = source.substring(source.lastIndexOf("."));
                String file = target.replace(".tga", format).replace(".png", format);
                access.zipAddFilePath(source, file);
                return file;
            } else {
                return copy(access, source, target, new CopyOptions(), config, false);
            }
        } catch (IOException e) {
            showException(e, "error reading image: "+source);
            throw new ExitNow("operations.Copy.copy");
        }
    }

    /**
     *  copies a image with the provided options
     *
     * @param access file access object to use
     * @param source input path of the source image
     * @param target output to write to
     * @param options options for the copy operation, @see #CopyOptions
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    private static String copy(FileAccess access, String source, String target, CopyOptions options, Settings config, boolean onlyRaytrace) throws ExitNow {
        try {
            // read input
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(source, false)));

            int height = Math.round(image.getHeight() * options.height);
            int width = Math.round(image.getWidth() * options.width);

            if (!options.single.isEmpty()) {
                height = width;
            } else if (options.heightFrames > 0) {
                height = width * options.heightFrames;
            }

            // output
            BufferedImage out_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            //load textures for normal/mer
            BufferedImage[] rayInTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;
            if (doRaytrace) {
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = source.lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(source.substring(0, index) + map.getKey() + source.substring(index), true);
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
                int minWidth = Math.min(width, rayInTextures[0].getWidth());
                int minHeight = Math.min(height, rayInTextures[0].getHeight());
                normalTexture = new BufferedImage(minWidth, minHeight, BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(minWidth, minHeight, BufferedImage.TYPE_INT_ARGB);
            }
            boolean transparentPresent = false;

            for (int x = 0; x < Math.min(image.getWidth(), out_image.getWidth()); x++) {
                for (int y = 0; y < Math.min(image.getHeight(), out_image.getHeight()); y++) {
                    int xOut = x;
                    int yOut = y;
                    int xIn = x;
                    int yIn = y;

                    if (options.single.equals("last")) {
                        xIn = x;
                        yIn = image.getHeight() - image.getWidth() + y;
                    }

                    if (options.rotate < 0) {
                        xOut = yIn;
                        yOut = image.getWidth() - xIn - 1;
                    } else if (options.rotate > 0) {
                        xOut = image.getWidth() - yIn - 1;
                        yOut = xIn;
                    }
                    if (options.mirror_x) {
                        xOut = image.getWidth() - 1 - xIn;
                    }
                    if (options.mirror_y) {
                        yOut = image.getHeight() - 1 - yIn;
                    }
                    if (!onlyRaytrace) {
                        RGB color = getColor(image.getRGB(xIn, yIn));
                        if (options.alpha >= 0 && color.a >= options.minAlphaToSet) {
                            if (!options.setAlphaGreyOnly && !options.setAlphaColorOnly) {
                                color.a = options.alpha;
                            } else if (options.setAlphaGreyOnly) {
                                float average = (color.r + color.g + color.b) / 3f;
                                float low = average - 5;
                                float high = average + 5;
                                if (color.a >= options.alpha && (options.alphaGrayThreshold < 0 ||
                                        (color.r > options.alphaGrayThreshold && color.g > options.alphaGrayThreshold && color.b > options.alphaGrayThreshold))) {
                                    if (color.r > low && color.r < high && color.g > low && color.g < high && color.b > low && color.b < high) {
                                        color.a = options.alpha;
                                    }
                                }
                            } else { //  if (options.setAlphaColorOnly)
                                float average = (color.r + color.g + color.b) / 3f;
                                float low = average - 5;
                                float high = average + 5;
                                if (color.a >= options.alpha && (options.alphaGrayThreshold < 0 ||
                                        (color.r <= options.alphaGrayThreshold && color.g <= options.alphaGrayThreshold && color.b <= options.alphaGrayThreshold))) {
                                    if (options.alphaGrayThreshold >= 0 || !(color.r > low && color.r < high && color.g > low && color.g < high && color.b > low && color.b < high)) {
                                        color.a = options.alpha;
                                    }
                                }
                            }
                        }
                        if (options.from != null && options.to != null) {
                            boolean colorize = !options.colorOnlyGray;
                            float average = (color.r + color.g + color.b) / 3f;
                            float low = average - 5;
                            float high = average + 5;
                            if (color.r > low && color.r < high && color.g > low && color.g < high && color.b > low && color.b < high) {
                                colorize = true;
                            }
                            if (colorize) {
                                color.r = Math.round((float) color.r / options.from.r * options.to.r);
                                color.g = Math.round((float) color.g / options.from.g * options.to.g);
                                color.b = Math.round((float) color.b / options.from.b * options.to.b);
                                if (color.r == 0 && color.b == 0 && color.g == 0 && color.a == 0) {
                                    color.r = options.to.r;
                                    color.g = options.to.g;
                                    color.b = options.to.b;
                                }
                            }
                        }
                        if (options.grayscale) {
                            color = getGrey(color);
                        }
                        if (options.alphaColor != null && color.a == 0)
                            color = options.alphaColor;

                        if (options.oldMin + options.oldMax + options.newMin + options.newMax != -4) {
                            color.r = ((options.newMax - options.newMin) * (Math.min(Math.max(color.r, options.oldMin), options.oldMax) - options.oldMin)) / (options.oldMax - options.oldMin) + options.newMin;
                            color.g = ((options.newMax - options.newMin) * (Math.min(Math.max(color.g, options.oldMin), options.oldMax) - options.oldMin)) / (options.oldMax - options.oldMin) + options.newMin;
                            color.b = ((options.newMax - options.newMin) * (Math.min(Math.max(color.b, options.oldMin), options.oldMax) - options.oldMin)) / (options.oldMax - options.oldMin) + options.newMin;
                        }
                        color.a = Math.min(options.maxAlpha, color.a);
                        out_image.setRGB(xOut, yOut, getIntColor(color));
                        transparentPresent = transparentPresent || color.a < 255;
                    }
                    if (doRaytrace) {
                        if (xIn < normalTexture.getWidth() && yIn < normalTexture.getHeight()) {
                            RaytracedWriter.writeRayTexRotFirst(rayInTextures, normalTexture, merTexture, xOut, yOut, xIn, yIn, options.rotate, options.mirror_y, options.mirror_x, config);
                        }
                    }
                }
            }

            String out = "";
            if (!onlyRaytrace) {
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
                out += file;
            }
            if (doRaytrace) {
                String name = target.substring(0, target.lastIndexOf("."));

                ByteArrayOutputStream outData = new ByteArrayOutputStream();
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
            throw new ExitNow("operations.Copy.copy");
        }
    }
}

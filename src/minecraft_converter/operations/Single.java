package minecraft_converter.operations;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;
import minecraft_converter.tools.JsonCreationHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

import static minecraft_converter.tools.ColorOperations.getColor;
import static minecraft_converter.tools.ColorOperations.getIntColor;
import static minecraft_converter.tools.Messages.showException;

public class Single {
    /**
     * processes the given parameters and calls the single Method
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
        boolean last = false;
        RGB colorFrom = null;
        RGB colorTo = null;
        int alpha = -1;
        if (parameters != null) {
            for (int p = 0; p < parameters.length; p++) {
                String[] settings = parameters[p].split("=");
                String setting = settings[1];
                if (setting.startsWith("option."))
                    setting = config.options.get(setting.replace("option.", ""));
                if (config.raytraced && settings[0].contains("(!rtx)"))
                    continue;
                switch(settings[0].replace("(!rtx)", "")) {
                    case "last":
                        last = "true".equals(setting);
                        break;
                    case "color":
                        String[] colors = setting.split("-");
                        String[] rgb = colors[0].split(",");
                        colorFrom = new RGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]), 0);
                        rgb = colors[1].split(",");
                        colorTo = new RGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]), 0);
                        break;
                    case "alpha":
                        alpha = Integer.parseInt(setting);
                        break;
                }

                /*if (parameters[p].equals("last"))
                    last = true;
                else if (parameters[p].equals("color")) {
                    p++;
                    String[] rgb = parameters[p].split(",");
                    colorFrom = new RGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]), 0);
                    p++;
                    rgb = parameters[p].split(",");
                    colorTo = new RGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]), 0);
                } else if (parameters[p].startsWith("alpha="))
                    alpha = Integer.parseInt(parameters[p].split("=")[1]);*/
            }
        }
        return single(access, source, target, last, colorFrom, colorTo, alpha, config);
    }

    /**
     * saves a single frame of an animation sheet
     *
     * @param in input path of the source image
     * @param target output to write to
     * @param last should the last frame be saved or the first
     * @param colorFrom source reference color for colorization
     * @param colorTo final color the to colorize with
     * @param alpha value for the final pixel alpha, if < 0 it's ignored
     * @param config settings object
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String single(FileAccess access, String in, String target, boolean last, RGB colorFrom, RGB colorTo, int alpha, Settings config) throws ExitNow {
        try {
        // read input
        BufferedImage image = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(access.getFileBytes(in, false))));
        BufferedImage[] rayInTextures = new BufferedImage[config.textures.size()];
        boolean doRaytrace = config.raytraced;
        if (doRaytrace) {
            for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                int index = in.lastIndexOf(".");
                byte[] buffer = access.getFileBytes(in.substring(0, index) + map.getKey() + in.substring(index), true);
                if (buffer != null)
                    rayInTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                else {
                    doRaytrace = false;
                    break;
                }
            }
        }

        // output
        // only copy the first tile
        BufferedImage out_image = new BufferedImage(image.getWidth(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);

        BufferedImage normalTexture = null;
        BufferedImage merTexture = null;

        if (doRaytrace) {
            normalTexture = new BufferedImage(image.getWidth(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
            merTexture = new BufferedImage(image.getWidth(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
        }
        boolean transparentPresent = false;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getWidth(); y++) {
                int xIn = x;
                int yIn = y;
                if (last) {
                    xIn = x;
                    yIn = image.getHeight()-image.getWidth()+y;
                }
                RGB color = getColor(image.getRGB(xIn,yIn));
                if (alpha >= 0) {
                    color.a = alpha;
                }
                if (colorFrom != null && colorTo != null) {
                    color.r = Math.round((float)color.r/colorFrom.r*colorTo.r);
                    color.g = Math.round((float)color.g/colorFrom.g*colorTo.g);
                    color.b = Math.round((float)color.b/colorFrom.b*colorTo.b);
                    if(color.r == 0 && color.b == 0 && color.g == 0 && color.a == 0) {
                        color.r = colorTo.r;
                        color.g = colorTo.g;
                        color.b = colorTo.b;
                    }
                }
                out_image.setRGB(x, y, getIntColor(color));
                transparentPresent = transparentPresent || color.a < 255;
                if (doRaytrace) {
                    RGB normal = getColor(rayInTextures[config.textures.get(config.converter.getNormalSuffix())].getRGB(xIn, yIn));
                    normal = config.converter.getNormal(normal);
                    normalTexture.setRGB(x, y, getIntColor(normal));

                    RGB mer = new RGB(0,0,0,255);
                    mer.r = config.converter.getMetallic(getColor(rayInTextures[config.textures.get(config.converter.getMetallicSuffix())].getRGB(xIn, yIn)));
                    mer.g = config.converter.getEmissive(getColor(rayInTextures[config.textures.get(config.converter.getEmissiveSuffix())].getRGB(xIn, yIn)));
                    mer.b = config.converter.getRoughness(getColor(rayInTextures[config.textures.get(config.converter.getRoughnessSuffix())].getRGB(xIn, yIn)));
                    merTexture.setRGB(x, y, getIntColor(mer));
                }
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

            file += ";" + name+".texture_set.json";
            String fileName = name.substring(name.lastIndexOf('/')+1);
            access.zipAddFileByte(name+".texture_set.json", JsonCreationHelper.createTextureSet(fileName, fileName+"_mer", fileName+"_normal"));
        }

        return file;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.Single.single");
        }
    }
}
package minecraft_converter.tools;

import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;

import java.awt.image.BufferedImage;

import static minecraft_converter.tools.ColorOperations.getColor;
import static minecraft_converter.tools.ColorOperations.getIntColor;

public class RaytracedWriter {
    public static void writeRayTex(BufferedImage[] inTex, BufferedImage normalTex, BufferedImage merTex, int x, int y, int sourceX, int sourceY, Settings config) {
        if (checkBound(normalTex, sourceX, sourceY)) {
            RGB normal = getColor(inTex[config.textures.get(config.converter.getNormalSuffix())].getRGB(sourceX, sourceY));
            normal = config.converter.getNormal(normal);
            normalTex.setRGB(x, y, getIntColor(normal));

            RGB mer = new RGB(0, 0, 0, 255);
            mer.r = config.converter.getMetallic(getColor(inTex[config.textures.get(config.converter.getMetallicSuffix())].getRGB(sourceX, sourceY)));
            mer.g = config.converter.getEmissive(getColor(inTex[config.textures.get(config.converter.getEmissiveSuffix())].getRGB(sourceX, sourceY)));
            mer.b = config.converter.getRoughness(getColor(inTex[config.textures.get(config.converter.getRoughnessSuffix())].getRGB(sourceX, sourceY)));
            merTex.setRGB(x, y, getIntColor(mer));
        }
    }

    public static void writeRayTexRotFirst(BufferedImage[] inTex, BufferedImage normalTex, BufferedImage merTex, int x, int y, int sourceX, int sourceY, int rotate, boolean mirror_y, boolean mirror_x, Settings config) {
        if (checkBound(normalTex, sourceX, sourceY)) {
            RGB normal = getColor(inTex[config.textures.get(config.converter.getNormalSuffix())].getRGB(sourceX, sourceY));
            normal = config.converter.getNormal(normal);
            // alter normal when rotation / mirroring
            doRotation(normal, rotate);
            doMirror(normal, mirror_y, mirror_x);
            normalTex.setRGB(x, y, getIntColor(normal));

            if (sourceX < inTex[config.textures.get(config.converter.getMetallicSuffix())].getWidth() && sourceY < inTex[config.textures.get(config.converter.getMetallicSuffix())].getHeight()) {
                RGB mer = new RGB(0, 0, 0, 255);
                mer.r = config.converter.getMetallic(getColor(inTex[config.textures.get(config.converter.getMetallicSuffix())].getRGB(sourceX, sourceY)));
                mer.g = config.converter.getEmissive(getColor(inTex[config.textures.get(config.converter.getEmissiveSuffix())].getRGB(sourceX, sourceY)));
                mer.b = config.converter.getRoughness(getColor(inTex[config.textures.get(config.converter.getRoughnessSuffix())].getRGB(sourceX, sourceY)));
                merTex.setRGB(x, y, getIntColor(mer));
            }
        }
    }

    public static void writeRayTexMirFirst(BufferedImage[] inTex, BufferedImage normalTex, BufferedImage merTex, int x, int y, int sourceX, int sourceY, int rotate, boolean mirror_y, boolean mirror_x, Settings config) {
        if (checkBound(normalTex, sourceX, sourceY)) {
            RGB normal = getColor(inTex[config.textures.get(config.converter.getNormalSuffix())].getRGB(sourceX, sourceY));
            normal = config.converter.getNormal(normal);
            // alter normal when rotation / mirroring
            doMirror(normal, mirror_y, mirror_x);
            doRotation(normal, rotate);
            normalTex.setRGB(x, y, getIntColor(normal));

            RGB mer = new RGB(0, 0, 0, 255);
            mer.r = config.converter.getMetallic(getColor(inTex[config.textures.get(config.converter.getMetallicSuffix())].getRGB(sourceX, sourceY)));
            mer.g = config.converter.getEmissive(getColor(inTex[config.textures.get(config.converter.getEmissiveSuffix())].getRGB(sourceX, sourceY)));
            mer.b = config.converter.getRoughness(getColor(inTex[config.textures.get(config.converter.getRoughnessSuffix())].getRGB(sourceX, sourceY)));
            merTex.setRGB(x, y, getIntColor(mer));
        }
    }

    public static void writeRayTexRot(BufferedImage[] inTex, BufferedImage normalTex, BufferedImage merTex, int x, int y, int sourceX, int sourceY, int rotate, Settings config) {
        if (checkBound(normalTex, sourceX, sourceY)) {
            RGB normal = getColor(inTex[config.textures.get(config.converter.getNormalSuffix())].getRGB(sourceX, sourceY));
            normal = config.converter.getNormal(normal);
            // alter normal when rotation / mirroring
            doRotation(normal, rotate);
            normalTex.setRGB(x, y, getIntColor(normal));

            RGB mer = new RGB(0, 0, 0, 255);
            mer.r = config.converter.getMetallic(getColor(inTex[config.textures.get(config.converter.getMetallicSuffix())].getRGB(sourceX, sourceY)));
            mer.g = config.converter.getEmissive(getColor(inTex[config.textures.get(config.converter.getEmissiveSuffix())].getRGB(sourceX, sourceY)));
            mer.b = config.converter.getRoughness(getColor(inTex[config.textures.get(config.converter.getRoughnessSuffix())].getRGB(sourceX, sourceY)));
            merTex.setRGB(x, y, getIntColor(mer));
        }
    }

    public static void writeRayTexMir(BufferedImage[] inTex, BufferedImage normalTex, BufferedImage merTex, int x, int y, int sourceX, int sourceY, boolean mirror_y, boolean mirror_x, Settings config) {
        if (checkBound(normalTex, sourceX, sourceY)) {
            RGB normal = getColor(inTex[config.textures.get(config.converter.getNormalSuffix())].getRGB(sourceX, sourceY));
            normal = config.converter.getNormal(normal);
            // alter normal when rotation / mirroring
            doMirror(normal, mirror_y, mirror_x);
            normalTex.setRGB(x, y, getIntColor(normal));

            RGB mer = new RGB(0, 0, 0, 255);
            mer.r = config.converter.getMetallic(getColor(inTex[config.textures.get(config.converter.getMetallicSuffix())].getRGB(sourceX, sourceY)));
            mer.g = config.converter.getEmissive(getColor(inTex[config.textures.get(config.converter.getEmissiveSuffix())].getRGB(sourceX, sourceY)));
            mer.b = config.converter.getRoughness(getColor(inTex[config.textures.get(config.converter.getRoughnessSuffix())].getRGB(sourceX, sourceY)));
            merTex.setRGB(x, y, getIntColor(mer));
        }
    }

    private static boolean checkBound(BufferedImage normalTex, int sourceX, int sourceY) {
        return sourceX < normalTex.getWidth() && sourceY < normalTex.getHeight();
    }

    private static void doRotation(RGB normal, int rotate){
        // guide here: https://robonobodojo.wordpress.com/2015/11/22/transforming-normal-maps/
        if ((rotate+360) % 360 == 270) {
            RGB temp = new RGB(normal);
            normal.r = temp.g;
            normal.g = 255-temp.r;
        } else if ((rotate+360) % 360 == 90) {
            RGB temp = new RGB(normal);
            normal.r = 255-temp.g;
            normal.g = temp.r;
        } else if ((rotate+360) % 360 == 180) {
            RGB temp = new RGB(normal);
            normal.r = 255-temp.r;
            normal.g = 255-temp.g;
        }
    }
    private static void doMirror(RGB normal, boolean mirror_y, boolean mirror_x) {
        // guide here: https://robonobodojo.wordpress.com/2015/11/22/transforming-normal-maps/
        if (mirror_y) {
            normal.g = 255-normal.g;
        }
        if (mirror_x) {
            normal.r = 255-normal.r;
        }
    }
}

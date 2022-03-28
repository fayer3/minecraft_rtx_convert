package minecraft_converter.tools;

import minecraft_converter.datatypes.RGB;

public final class ColorOperations {

    public static RGB getColor(int color) {
        RGB colors = new RGB();
        colors.r = (color>>16) & 0xff; // red
        colors.g = (color>>8) & 0xff; // green
        colors.b = color & 0xff; // blue
        colors.a = (color>>24) & 0xff; // alpha

        return colors;
    }

    public static int getIntColor(RGB color) {
        return (color.a<<24)+(color.r<<16)+(color.g<<8)+color.b;
    }

    public static RGB getGrey(RGB color) {
        RGB colors = new RGB(color);
        colors.r = colors.g = colors.b = Math.round(0.299f * colors.r + 0.587f * colors.g + 0.114f * colors.b);
        return colors;
    }

    public static RGB getGrey(int color) {
        RGB colors = new RGB();
        colors.r = (color>>16) & 0xff; // red
        colors.g = (color>>8) & 0xff; // green
        colors.b = color & 0xff; // blue
        colors.a = (color>>24) & 0xff; // alpha

        colors.r = colors.g = colors.b = Math.round(0.299f * colors.r + 0.587f * colors.g + 0.114f * colors.b);

        return colors;
    }

    public static RGB getHSVGray(int color) {
        RGB colors = new RGB();
        colors.r = (color>>16) & 0xff; // red
        colors.g = (color>>8) & 0xff; // green
        colors.b = color & 0xff; // blue
        colors.a = (color>>24) & 0xff; // alpha

        colors.r = colors.g = colors.b = Math.max(colors.r,Math.max(colors.g, colors.b));

        return colors;
    }
}

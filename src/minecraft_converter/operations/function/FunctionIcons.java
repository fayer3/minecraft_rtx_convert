package minecraft_converter.operations.function;

import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.tools.FileAccess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static minecraft_converter.tools.ColorOperations.*;
import static minecraft_converter.tools.Messages.showException;

public class FunctionIcons {

    private static final String experiencebar_json = "{\n" +
            "  \"nineslice_size\": [\n" +
            "    182\n" +
            "  ],\n" +
            "  \"base_size\": [\n" +
            "    182,\n" +
            "    5\n" +
            "  ]\n" +
            "}";

    /**
     * splits the icons into its textures
     *
     * @param access file access object to use
     * @param config settings object
     * @return path to files saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String source, Settings config) throws ExitNow {
        try {

            byte[] buffer = access.getFileBytes(source, true);
            if (buffer == null) {
                return "";
            }

            BufferedImage icons = ImageIO.read(new ByteArrayInputStream(buffer));

            int pixelSize = icons.getWidth() / 256;

            // cross_hair
            BufferedImage current = new BufferedImage(pixelSize * 16, pixelSize * 16, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    RGB color = getColor(icons.getRGB(x, y));
                    if (color.a == 0)
                        color.r = color.g = color.b = 0;
                    current.setRGB(x, y, getIntColor(color));
                }
            }
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/cross_hair.png", outData.toByteArray());
            String files = "textures/ui/cross_hair.png";
            // hearts
            for (int i = 0; i < 18; i++) {
                String heart = "";
                switch (i) {
                    case 0:
                        heart = "heart_background";
                        break;
                    case 1:
                        heart = "heart_blink";
                        break;
                    case 4:
                        heart = "heart";
                        break;
                    case 5:
                        heart = "heart_half";
                        break;
                    case 6:
                        heart = "heart_flash";
                        break;
                    case 7:
                        heart = "heart_flash_half";
                        break;
                    case 8:
                        heart = "poison_heart";
                        break;
                    case 9:
                        heart = "poison_heart_half";
                        break;
                    case 10:
                        heart = "poison_heart_flash";
                        break;
                    case 11:
                        heart = "poison_heart_flash_half";
                        break;
                    case 12:
                        heart = "wither_heart";
                        break;
                    case 13:
                        heart = "wither_heart_half";
                        break;
                    case 14:
                        heart = "wither_heart_flash";
                        break;
                    case 15:
                        heart = "wither_heart_flash_half";
                        break;
                    case 16:
                        heart = "absorption_heart";
                        break;
                    case 17:
                        heart = "absorption_heart_half";
                        break;
                    default:
                        continue;
                }
                current = new BufferedImage(pixelSize * 9, pixelSize * 9, BufferedImage.TYPE_INT_ARGB);
                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        current.setRGB(x, y, icons.getRGB(x + pixelSize * (16 + 9 * i), y));
                    }
                }
                outData = new ByteArrayOutputStream();
                ImageIO.write(current, "png", outData);
                access.zipAddFileByte("textures/ui/" + heart + ".png", outData.toByteArray());
                files += ";textures/ui/" + heart + ".png";
            }

            // armor, horse hearts
            for (int i = 0; i < 12; i++) {
                String icon = "";
                switch (i) {
                    case 0:
                        icon = "armor_empty";
                        break;
                    case 1:
                        icon = "armor_half";
                        break;
                    case 2:
                        icon = "armor_full";
                        break;
                    case 8:
                        icon = "horse_heart";
                        break;
                    case 9:
                        icon = "horse_heart_half";
                        break;
                    case 10:
                        icon = "horse_heart_flash";
                        break;
                    case 11:
                        icon = "horse_heart_flash_half";
                        break;
                    default:
                        continue;
                }
                current = new BufferedImage(pixelSize * 9, pixelSize * 9, BufferedImage.TYPE_INT_ARGB);
                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        current.setRGB(x, y, icons.getRGB(x + pixelSize * (16 + 9 * i), y + pixelSize * 9));
                    }
                }
                outData = new ByteArrayOutputStream();
                ImageIO.write(current, "png", outData);
                access.zipAddFileByte("textures/ui/" + icon + ".png", outData.toByteArray());
                files += ";textures/ui/" + icon + ".png";
            }

            // bubbles
            for (int i = 0; i < 2; i++) {
                String icon = "";
                switch (i) {
                    case 0:
                        icon = "bubble";
                        break;
                    case 1:
                        icon = "bubble_pop";
                        break;
                    default:
                        continue;
                }
                current = new BufferedImage(pixelSize * 9, pixelSize * 9, BufferedImage.TYPE_INT_ARGB);
                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        current.setRGB(x, y, icons.getRGB(x + pixelSize * (16 + 9 * i), y + pixelSize * 18));
                    }
                }
                outData = new ByteArrayOutputStream();
                ImageIO.write(current, "png", outData);
                access.zipAddFileByte("textures/ui/" + icon + ".png", outData.toByteArray());
                files += ";textures/ui/" + icon + ".png";
            }

            // hunger
            for (int i = 0; i < 14; i++) {
                String icon = "";
                switch (i) {
                    case 0:
                        icon = "hunger_background";
                        break;
                    case 1:
                        icon = "hunger_blink";
                        break;
                    case 4:
                        icon = "hunger_full";
                        break;
                    case 5:
                        icon = "hunger_half";
                        break;
                    case 6:
                        icon = "hunger_flash_full";
                        break;
                    case 7:
                        icon = "hunger_flash_half";
                        break;
                    case 8:
                        icon = "hunger_effect_full";
                        break;
                    case 9:
                        icon = "hunger_effect_half";
                        break;
                    case 10:
                        icon = "hunger_effect_flash_full";
                        break;
                    case 11:
                        icon = "hunger_effect_flash_half";
                        break;
                    case 13:
                        icon = "hunger_effect_background";
                        break;
                    default:
                        continue;
                }
                current = new BufferedImage(pixelSize * 9, pixelSize * 9, BufferedImage.TYPE_INT_ARGB);
                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        current.setRGB(x, y, icons.getRGB(x + pixelSize * (16 + 9 * i), y + pixelSize * 27));
                    }
                }
                outData = new ByteArrayOutputStream();
                ImageIO.write(current, "png", outData);
                access.zipAddFileByte("textures/ui/" + icon + ".png", outData.toByteArray());
                files += ";textures/ui/" + icon + ".png";
            }

            // experience bar
            for (int i = 0; i < 6; i++) {
                current = new BufferedImage(pixelSize * 182, pixelSize * 5, BufferedImage.TYPE_INT_ARGB);
                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        if (i != 2 && i!=3)
                            current.setRGB(x, y, icons.getRGB(x, y + pixelSize * (64+i*5)));
                        else {
                            current.setRGB(x, y, getIntColor(getHSVGray(icons.getRGB(x, y + pixelSize * (64+i*5)))));
                        }
                    }
                }
                String bar = "";
                switch (i) {
                    case 0:
                        bar = "experiencebarempty_full";
                        break;
                    case 1:
                        bar = "experiencebarfull_full";
                        break;
                    case 2:
                        bar = "empty_progress_bar";
                        break;
                    case 3:
                        bar = "filled_progress_bar";
                        break;
                    case 4:
                        bar = "horse_jump_empty";
                        break;
                    case 5:
                        bar = "horse_jump_full";
                        break;
                    default:
                        continue;
                }
                outData = new ByteArrayOutputStream();
                ImageIO.write(current, "png", outData);
                access.zipAddFileByte("textures/ui/"+bar+".png", outData.toByteArray());
                files += ";textures/ui/"+bar+".png";
                if (i < 4) {
                    access.zipAddFileByte("textures/ui/" + bar + ".json", experiencebar_json.getBytes());
                    files += ";textures/ui/" + bar + ".json";
                }
            }
            access.zipAddFileStream("ui/hud_screen.json", FunctionIcons.class.getClassLoader().getResourceAsStream("bedrock/hud_screen.json"));
            files += ";ui/hud_screen.json";


            return files;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionIcons.compute");
        }
    }
}

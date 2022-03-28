package minecraft_converter.operations.function;

import minecraft_converter.datatypes.Settings;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.tools.FileAccess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static minecraft_converter.tools.Messages.showException;

public class FunctionEnchanting {

    /**
     * cuts out the fire and progress arrow
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

            BufferedImage furnace = ImageIO.read(new ByteArrayInputStream(buffer));

            int pixelSize = furnace.getWidth() / 256;

            String files = "";
            // text background
            for (int i = 0; i < 3; i++) {
                BufferedImage current = new BufferedImage(pixelSize * 108, pixelSize * 19, BufferedImage.TYPE_INT_ARGB);
                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        current.setRGB(x, y, furnace.getRGB(x, y + pixelSize * (166+19*i)));
                    }
                }
                String name = "";
                switch (i) {
                    case 0: name = "enchanting_active_background";
                        break;
                    case 1: name = "enchanting_dark_background";
                        break;
                    case 2: name = "enchanting_active_background_with_hover_text";
                        break;
                    default: continue;
                }
                ByteArrayOutputStream outData = new ByteArrayOutputStream();
                ImageIO.write(current, "png", outData);
                access.zipAddFileByte("textures/ui/"+name+".png", outData.toByteArray());
                if (!files.isEmpty())
                    files += ";";
                files += "textures/ui/"+name+".png";
            }

            // text background
            for (int i = 0; i < 6; i++) {
                BufferedImage current = new BufferedImage(pixelSize * 13, pixelSize * 11, BufferedImage.TYPE_INT_ARGB);
                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        current.setRGB(x, y, furnace.getRGB(x + pixelSize * (3+16*(i%3)-(i%3 == 2 ? 1:0)), y + pixelSize * (225+(i < 3 ? 0 : 16))));
                    }
                }
                String name = "";
                switch (i) {
                    case 0:
                    case 1:
                    case 2: name = "dust_selectable_";
                        break;
                    case 3:
                    case 4:
                    case 5: name = "dust_unselectable_";
                        break;
                    default: continue;
                }
                ByteArrayOutputStream outData = new ByteArrayOutputStream();
                ImageIO.write(current, "png", outData);
                access.zipAddFileByte("textures/ui/"+name+(i%3+1)+".png", outData.toByteArray());
                files += ";textures/ui/"+name+(i%3+1)+".png";
            }
            // lapis
            BufferedImage current = new BufferedImage(pixelSize * 16, pixelSize * 16, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x, y, furnace.getRGB(x + pixelSize *35, y + pixelSize * 47));
                }
            }
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/lapis_image.png", outData.toByteArray());
            files += ";textures/ui/lapis_image.png";

            return files;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionEnchanting.compute");
        }
    }
}

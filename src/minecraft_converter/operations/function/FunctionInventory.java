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

public class FunctionInventory {

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

            // effect_background
            BufferedImage current = new BufferedImage(pixelSize * 120, pixelSize * 32, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x, y, icons.getRGB(x, y + pixelSize * 166));
                }
            }
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/effect_background.png", outData.toByteArray());
            String files = "textures/ui/effect_background.png";

            // hud_mob_effect_background
            current = new BufferedImage(pixelSize * 24, pixelSize * 24, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x, y, icons.getRGB(x + pixelSize * 141, y + pixelSize * 166));
                }
            }
            outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/hud_mob_effect_background.png", outData.toByteArray());
            files += ";textures/ui/hud_mob_effect_background.png";

            // small crafting arrow
            current = new BufferedImage(pixelSize * 16, pixelSize * 13, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x, y, icons.getRGB(x + pixelSize * 135, y + pixelSize * 29));
                }
            }
            outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/arrow.png", outData.toByteArray());
            files += ";textures/ui/arrow.png";

            return files;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionInventory.compute");
        }
    }
}

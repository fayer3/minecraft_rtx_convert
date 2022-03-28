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

public class FunctionFurnace {

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

            // fire off
            BufferedImage current = new BufferedImage(pixelSize * 13, pixelSize * 13, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x, y, furnace.getRGB(x+ pixelSize*57, y + pixelSize * 37));
                }
            }
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/flame_empty_image.png", outData.toByteArray());
            String files = "textures/ui/flame_empty_image.png";

            // fire on
            current = new BufferedImage(pixelSize * 13, pixelSize * 13, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x, y, furnace.getRGB(x+ pixelSize*176, y));
                }
            }
            outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/flame_full_image.png", outData.toByteArray());
            files += ";textures/ui/flame_full_image.png";

            // arrow off
            current = new BufferedImage(pixelSize * 22, pixelSize * 15, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x, y, furnace.getRGB(x + pixelSize*80, y + pixelSize*35));
                }
            }
            outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/arrow_inactive.png", outData.toByteArray());
            files += ";textures/ui/arrow_inactive.png";
            // arrow on
            current = new BufferedImage(pixelSize * 22, pixelSize * 15, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x, y, furnace.getRGB(x + pixelSize*177, y + pixelSize*15));
                }
            }
            outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/arrow_active.png", outData.toByteArray());
            files += ";textures/ui/arrow_active.png";

            //access.zipAddFileStream("ui/furnace_screen.json", FunctionFurnace.class.getClassLoader().getResourceAsStream("bedrock/furnace_screen.json"));
            //files += ";ui/furnace_screen.json";

            return files;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionFurnace.compute");
        }
    }
}

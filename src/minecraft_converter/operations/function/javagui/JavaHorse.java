package minecraft_converter.operations.function.javagui;

import minecraft_converter.datatypes.Settings;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.tools.FileAccess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static minecraft_converter.tools.Messages.showException;

public class JavaHorse {

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

            String files = "";

            for (int i = 0; i < 3; i++){
                BufferedImage current = new BufferedImage(pixelSize * 16, pixelSize * 16, BufferedImage.TYPE_INT_ARGB);
                String name = "";
                switch (i) {
                    case 0: name = "empty_horse_slot_armor";
                        break;
                    case 1: name = "empty_horse_slot_saddle";
                        break;
                    case 2: name = "empty_llama_slot_carpet";
                        break;
                }
                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        current.setRGB(x, y, icons.getRGB(x+ pixelSize*(1+18*i), y + pixelSize * 221));
                    }
                }
                ByteArrayOutputStream outData = new ByteArrayOutputStream();
                ImageIO.write(current, "png", outData);
                access.zipAddFileByte("textures/ui/"+name+".png", outData.toByteArray());
                files = files.isEmpty() ? "textures/ui/"+name+".png" : files+"textures/ui/"+name+".png";
            }

            access.zipAddFileStream("ui/horse_screen.json", JavaHorse.class.getClassLoader().getResourceAsStream("bedrock/horse_screen.json"));
            files += ";ui/horse_screen.json";

            return files;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionHorse.compute");
        }
    }
}

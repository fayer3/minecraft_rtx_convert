package minecraft_converter.operations.function;

import javafx.scene.control.Label;
import minecraft_converter.Data;
import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.tools.FileAccess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static minecraft_converter.tools.ColorOperations.getColor;
import static minecraft_converter.tools.ColorOperations.getIntColor;
import static minecraft_converter.tools.Messages.showException;

public class FunctionWidgets {
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

            BufferedImage widgets = ImageIO.read(new ByteArrayInputStream(buffer));

            int pixelSize = widgets.getWidth()/256;

            // hotbar start
            BufferedImage current = new BufferedImage(pixelSize, pixelSize*22, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x,y,widgets.getRGB(x,y));
                }
            }
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/hotbar_start_cap.png", outData.toByteArray());
            String files = "textures/ui/hotbar_start_cap.png";

            //hotbar end
            current = new BufferedImage(pixelSize, pixelSize*22, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x,y,widgets.getRGB(x+pixelSize*181,y));
                }
            }
            outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/hotbar_end_cap.png", outData.toByteArray());
            files += ";textures/ui/hotbar_end_cap.png";

            // hotbar
            for (int i = 0; i < 9 ; i++) {
                current = new BufferedImage(pixelSize*20, pixelSize*22, BufferedImage.TYPE_INT_ARGB);
                for (int x = 0; x < current.getWidth(); x++) {
                    for (int y = 0; y < current.getHeight(); y++) {
                        current.setRGB(x,y,widgets.getRGB(x+pixelSize+pixelSize*20*i,y));
                    }
                }
                outData = new ByteArrayOutputStream();
                ImageIO.write(current, "png", outData);
                access.zipAddFileByte("textures/ui/hotbar_"+i+".png", outData.toByteArray());
                files += ";textures/ui/hotbar_"+i+".png";
            }
            //hotbar selected
            current = new BufferedImage(pixelSize*24, pixelSize*24, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x,y,widgets.getRGB(x,y+pixelSize*22));
                }
            }
            outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/selected_hotbar_slot.png", outData.toByteArray());
            files += ";textures/ui/selected_hotbar_slot.png";

            //invite base
            current = new BufferedImage(pixelSize*16, pixelSize*16, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x,y,widgets.getRGB(x+pixelSize*182,y+pixelSize*24));
                }
            }
            outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/invite_base.png", outData.toByteArray());
            files += ";textures/ui/invite_base.png";

            //invite pressed
            current = new BufferedImage(pixelSize*16, pixelSize*16, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < current.getWidth(); x++) {
                for (int y = 0; y < current.getHeight(); y++) {
                    current.setRGB(x,y,widgets.getRGB(x+pixelSize*166,y+pixelSize*24));
                }
            }
            outData = new ByteArrayOutputStream();
            ImageIO.write(current, "png", outData);
            access.zipAddFileByte("textures/ui/invite_pressed.png", outData.toByteArray());
            files += ";textures/ui/invite_pressed.png";
            access.zipAddFileByte("textures/ui/invite_hover.png", outData.toByteArray());
            files += ";textures/ui/invite_hover.png";


            return files;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.function.FunctionWidgets.compute");
        }
    }
}

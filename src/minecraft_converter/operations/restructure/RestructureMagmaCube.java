package minecraft_converter.operations.restructure;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.operations.Copy;
import minecraft_converter.tools.FileAccess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static minecraft_converter.tools.ColorOperations.getColor;
import static minecraft_converter.tools.ColorOperations.getIntColor;
import static minecraft_converter.tools.Messages.showException;

public class RestructureMagmaCube {
    /**
     * sets the alpha values for the magmacube
     *
     * @param access file access object to use
     * @param files: 0: magmacube texture
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {
            // read input
            BufferedImage magmacube = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));

            BufferedImage out_image = new BufferedImage(magmacube.getWidth() , magmacube.getHeight(), BufferedImage.TYPE_INT_ARGB);

            int pixelSize = out_image.getWidth() / 64;

            for (int x = 0; x < out_image.getWidth(); x++) {
                for (int y = 0; y < out_image.getHeight(); y++) {
                    RGB color = getColor(magmacube.getRGB(x, y));
                    if (x < 24 * pixelSize && y >= 16*pixelSize)
                        color.a = Math.min(color.a, 29);
                    else
                        color.a = Math.min(color.a, 126);
                    out_image.setRGB(x, y, getIntColor(color));
                }
            }

            // WRITE IMAGE
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            ImageIO.write(out_image, "png", outData);
            String file = target.replace(".tga", ".png");
            access.zipAddFileByte(file, outData.toByteArray());
            return file+";"+ Copy.compute(access,null, files[0], target, config, true);
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.restructure.RestructureSheep.compute");
        }
    }
}

package minecraft_converter.operations.restructure;

import minecraft_converter.exceptions.ExitNow;
import minecraft_converter.datatypes.RGB;
import minecraft_converter.datatypes.Settings;
import minecraft_converter.tools.FileAccess;
import minecraft_converter.tools.JsonCreationHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import static minecraft_converter.tools.ColorOperations.getIntColor;
import static minecraft_converter.tools.Messages.showException;
import static minecraft_converter.tools.RaytracedWriter.writeRayTexMirFirst;
import static minecraft_converter.tools.Rotation.rotate;

public class RestructureBed {
    /**
     * restructures the bed atlas
     *
     * @param access file access object to use
     * @param files: 0: bed atlas
     * @param target output to write to
     * @return path to file saved
     * @throws ExitNow if an error occurs
     */
    public static String compute(FileAccess access, String[] files, String target, Settings config) throws ExitNow {
        try {
            // read input
            BufferedImage bed = ImageIO.read(new ByteArrayInputStream(access.getFileBytes(files[0], false)));

            int width = bed.getWidth();
            int height = bed.getHeight();

            BufferedImage out_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            BufferedImage[] rayInTextures = new BufferedImage[config.textures.size()];
            boolean doRaytrace = config.raytraced;
            if (doRaytrace) {
                for (Map.Entry<String, Integer> map : config.textures.entrySet()) {
                    int index = files[0].lastIndexOf(".");
                    byte[] buffer = access.getFileBytes(files[0].substring(0, index) + map.getKey() + files[0].substring(index), true);
                    if (buffer != null)
                        rayInTextures[map.getValue()] = ImageIO.read(new BufferedInputStream(new ByteArrayInputStream(buffer)));
                    else {
                        doRaytrace = false;
                        break;
                    }
                }
            }

            BufferedImage normalTexture = null;
            BufferedImage merTexture = null;

            if (doRaytrace) {
                normalTexture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                merTexture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            }

            // initialize image with color
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    out_image.setRGB(x, y, getIntColor(new RGB(255, 255, 255, 0)));
                }
            }

            int pixelSize = width / 64;

            for (int x = 0; x < pixelSize * 44; x++) {
                for (int y = 0; y < pixelSize * 50; y++) {
                    int sourceX = x;
                    int sourceY = y;
                    int rotate = 0;
                    boolean mirror_y = false;
                    boolean mirror_x = false;
                    if (x >= pixelSize * 22 && x < pixelSize * 38 && y < pixelSize * 6) {
                        sourceY += 22*pixelSize;
                    } else if (x < pixelSize * 44 && y >= pixelSize * 22 && y < pixelSize * 38) {
                        sourceY += 6*pixelSize;
                    } else if (x < pixelSize * 12 && y >= pixelSize * 38 && y < pixelSize * 44) {
                        int[] offset = getOffsetLeg(pixelSize, x, y - pixelSize * 38, "topLeft");
                        sourceX = 50 * pixelSize + offset[0];
                        sourceY = 6 * pixelSize + offset[1];
                        rotate = offset[2];
                        mirror_x = offset[3] > 0;
                        mirror_y = offset[4] > 0;
                    } else if (x < pixelSize * 12 && y >= pixelSize * 44 && y < pixelSize * 50) {
                        int[] offset = getOffsetLeg(pixelSize, x, y - pixelSize * 44, "bottomLeft");
                        sourceX = 50 * pixelSize + offset[0];
                        sourceY = offset[1];
                        rotate = offset[2];
                        mirror_x = offset[3] > 0;
                        mirror_y = offset[4] > 0;
                    } else if (x < pixelSize * 24 && x >= pixelSize * 12 && y >= pixelSize * 38 && y < pixelSize * 44) {
                        int[] offset = getOffsetLeg(pixelSize, x - pixelSize * 12, y - pixelSize * 38, "topRight");
                        sourceX = 50 * pixelSize + offset[0];
                        sourceY = 18 * pixelSize + offset[1];
                        rotate = offset[2];
                        mirror_x = offset[3] > 0;
                        mirror_y = offset[4] > 0;
                    } else if (x < pixelSize * 24 && x >= pixelSize * 12 && y >= pixelSize * 44 && y < pixelSize * 50) {
                        int[] offset = getOffsetLeg(pixelSize, x - pixelSize * 12, y - pixelSize * 44, "bottomRight");
                        sourceX = 50 * pixelSize + offset[0];
                        sourceY = 12 * pixelSize + offset[1];
                        rotate = offset[2];
                        mirror_x = offset[3] > 0;
                        mirror_y = offset[4] > 0;
                    }
                    if (x < pixelSize * 44 && y < pixelSize * 38 || (x < pixelSize * 24 && y < pixelSize * 50)) {
                        out_image.setRGB(x, y, bed.getRGB(sourceX, sourceY));
                        if (doRaytrace) {
                            writeRayTexMirFirst(rayInTextures, normalTexture, merTexture, x, y, sourceX, sourceY, rotate, mirror_y, mirror_x, config);
                        }
                    }

                }
            }
            // WRITE IMAGE
            String out = "";
            ByteArrayOutputStream outData = new ByteArrayOutputStream();
            ImageIO.write(out_image, "png", outData);
            out = target.replace(".tga", ".png");
            access.zipAddFileByte(out, outData.toByteArray());

            if (doRaytrace) {
                String name = target.substring(0, target.lastIndexOf("."));

                outData = new ByteArrayOutputStream();
                ImageIO.write(normalTexture, "png", outData);
                out += out.isEmpty() ? name+"_normal.png" : ";" + name+"_normal.png";
                access.zipAddFileByte(name+"_normal.png", outData.toByteArray());

                outData = new ByteArrayOutputStream();
                ImageIO.write(merTexture, "png", outData);
                out += ";" + name+"_mer.png";
                access.zipAddFileByte(name+"_mer.png", outData.toByteArray());

                out += ";" + name+".texture_set.json";
                String fileName = name.substring(name.lastIndexOf('/')+1);
                access.zipAddFileByte(name+".texture_set.json", JsonCreationHelper.createTextureSet(fileName, fileName+"_mer", fileName+"_normal"));
            }
            return out;
        } catch (IOException e) {
            showException(e, "error reading/writing image");
            throw new ExitNow("operations.restructure.RestructureBed.compute");
        }
    }

    private static int[] getOffsetLeg(int pixelSize, int x, int y, String leg) {
        int[] offset = {x, y, 0, 0, 0}; // x y rotation mirror_x mirror_y
        // top
        if (x >= pixelSize * 3 && y >= pixelSize * 3 && x < pixelSize * 6 && y < pixelSize * 6) {
            offset[0] -= pixelSize * 3;
            offset[1] -= pixelSize * 3;
            int[] rotOffset = {offset[0], offset[1]};
            switch (leg) {
                case "topLeft":
                    // rotate 90°
                    rotOffset = rotate(offset, 3*pixelSize, 90);
                    offset[2] = 90;
                    break;
                case "topRight":
                    // no rotation needed
                    break;
                case "bottomLeft":
                    // rotate 180°
                    rotOffset = rotate(offset, 3*pixelSize, 180);
                    offset[2] = 180;
                    break;
                case "bottomRight":
                    // rotate 270°
                    rotOffset = rotate(offset, 3*pixelSize, 270);
                    offset[2] = 270;
                    break;
            }
            offset[0] = rotOffset[0];
            offset[1] = rotOffset[1];
            offset[0] += pixelSize * 3;
        }
        // bottom
        else if (x >= pixelSize * 9 && y >= pixelSize * 3 && x < pixelSize * 12 && y < pixelSize * 6) {
            offset[0] -= pixelSize * 9;
            offset[1] -= pixelSize * 3;
            // mirror x
            offset[0] = pixelSize * 3 - offset[0] - 1;
            offset[3]=1;
            int[] rotOffset = {offset[0], offset[1]};
            switch (leg) {
                case "topLeft":
                    // rotate 270°
                    rotOffset = rotate(offset, 3*pixelSize, 270);
                    offset[2] = 270;
                    break;
                case "topRight":
                    // rotate 180°
                    rotOffset = rotate(offset, 3*pixelSize, 180);
                    offset[2] = 180;
                    break;
                case "bottomLeft":
                    // no rotation needed
                    break;
                case "bottomRight":
                    // rotate 90°
                    rotOffset = rotate(offset, 3*pixelSize, 90);
                    offset[2] = 90;
                    break;
            }
            offset[0] = rotOffset[0];
            offset[1] = rotOffset[1];
            offset[0] += pixelSize * 6;
        }
        // -x
        else if (x >= 0 && y >= pixelSize * 3 && x < pixelSize * 3 && y < pixelSize * 6) {
            offset[1] -= pixelSize * 3;
            // rotate left
            int[] rotOffset = rotate(offset, 3*pixelSize, 270);
            offset[2] = 270;
            offset[0] = rotOffset[0];
            offset[1] = rotOffset[1];

            offset[1] += pixelSize * 3;

            switch (leg) {
                case "topLeft":
                    // move 1
                    offset[0] += pixelSize * 3;
                    break;
                case "topRight":
                    // move 2
                    offset[0] += pixelSize * 6;
                    break;
                case "bottomLeft":
                    // no move
                    break;
                case "bottomRight":
                    // move 3
                    offset[0] += pixelSize * 9;
                    break;
            }
        }
        // +x
        else if (x >= pixelSize * 6 && y >= pixelSize * 3 && x < pixelSize * 9 && y < pixelSize * 6) {
            offset[0] -= pixelSize * 6;
            offset[1] -= pixelSize * 3;
            // rotate right
            int[] rotOffset = rotate(offset, 3*pixelSize, 90);
            offset[2] = 90;
            offset[0] = rotOffset[0];
            offset[1] = rotOffset[1];

            offset[1] += pixelSize * 3;

            switch (leg) {
                case "topLeft":
                    // move 3
                    offset[0] += pixelSize * 9;
                    break;
                case "topRight":
                    // no move
                    break;
                case "bottomLeft":
                    // move 2
                    offset[0] += pixelSize * 6;
                    break;
                case "bottomRight":
                    // move 1
                    offset[0] += pixelSize * 3;
                    break;
            }
        }
        // -y
        else if (x >= pixelSize * 3 && y >= 0 && x < pixelSize * 6 && y < pixelSize * 3) {
            offset[0] -= pixelSize * 3;
            // rotate right
            int[] rotOffset = rotate(offset, 3*pixelSize, 180);
            offset[2] = 180;
            offset[0] = rotOffset[0];
            offset[1] = rotOffset[1];

            offset[1] += pixelSize * 3;

            switch (leg) {
                case "topLeft":
                    // no move
                    break;
                case "topRight":
                    // move 1
                    offset[0] += pixelSize * 3;
                    break;
                case "bottomLeft":
                    // move 3
                    offset[0] += pixelSize * 9;
                    break;
                case "bottomRight":
                    // move 2
                    offset[0] += pixelSize * 6;
                    break;
            }
        }
        // +y
        else if (x >= pixelSize * 6 && y >= 0 && x < pixelSize * 9 && y < pixelSize * 3) {
            offset[0] -= pixelSize * 6;
            // mirror x
            offset[3]=1;
            offset[0] = pixelSize * 3 - offset[0] - 1;
            // rotate right
            int[] rotOffset = rotate(offset, 3*pixelSize, 180);
            offset[2] = 180;
            offset[0] = rotOffset[0];
            offset[1] = rotOffset[1];

            offset[1] += pixelSize * 3;

            switch (leg) {
                case "topLeft":
                    // move 2
                    offset[0] += pixelSize * 6;
                    break;
                case "topRight":
                    // move 3
                    offset[0] += pixelSize * 9;
                    break;
                case "bottomLeft":
                    // move 1
                    offset[0] += pixelSize * 3;
                    break;
                case "bottomRight":
                    // no move
                    break;
            }
        }
        return offset;
    }

}

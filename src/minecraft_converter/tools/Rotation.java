package minecraft_converter.tools;

public class Rotation {

    public static int[] rotate(int[] location, int size, int degrees) {
        return rotate(location, new int[]{size, size}, degrees);
    }

    public static int[] rotate(int[] location, int[] size, int degrees) {
        int[] offset = {location[0], location[1]};
        if (degrees == 270) {
            offset[0] = location[1];
            offset[1] = size[0] - location[0] - 1;
        } else if (degrees == 90) {
            offset[0] = size[1] - location[1] - 1;
            offset[1] = location[0];
        } else if (degrees == 180) {
            offset[0] = size[0] - location[0] - 1;
            offset[1] = size[1] - location[1] - 1;
        }
        return offset;
    }
}

package minecraft_converter.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class JsonCreationHelper {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static byte[] createTextureSet(String color, String mer, String normal) {
        JsonObject textureSet = new JsonObject();
        textureSet.add("format_version", new JsonPrimitive("1.16.100"));
        JsonObject minecraftTextureSet = new JsonObject();
        if (color != null) {
            minecraftTextureSet.add("color", new JsonPrimitive(color));
        }
        if (mer != null) {
            minecraftTextureSet.add("metalness_emissive_roughness", new JsonPrimitive(mer));
        }
        if (normal != null) {
            minecraftTextureSet.add("normal", new JsonPrimitive(normal));
        }
        textureSet.add("minecraft:texture_set", minecraftTextureSet);
        return gson.toJson(textureSet).getBytes();
    }
}

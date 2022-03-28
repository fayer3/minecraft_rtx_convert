package minecraft_converter.datatypes;

import minecraft_converter.Data;
import minecraft_converter.SettingsController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Settings {
    public boolean raytraced = false;
    public TextureConverter converter;
    public Map<String, Integer> textures = new HashMap<>();;
    public Map<String, String> options = new HashMap<>(Data.getOptions());
    public Set<String> excludedTextures = new HashSet<>();
    public int packFormat = 5; // 4 for 1.12/1.14, 5 for 1.15/1.16
    public int numberOfThreads = Runtime.getRuntime().availableProcessors();
    public SettingsController.banner bannerPattern = SettingsController.banner.ANY;
    public boolean limitTransparencyToBlocks = true;
    public boolean useTga = true;
    public int mipmaps = 4;

    public boolean partialPainting = true;
    public boolean partialParticles = true;
    public boolean partialBanner = true;

    public UiType ui = UiType.Bedrock;

    public enum UiType{Bedrock, Java}

    public Settings(Settings other) {
        raytraced = other.raytraced;
        converter = other.converter;
        textures.putAll(other.textures);
        options.putAll(other.options);
        excludedTextures.addAll(other.excludedTextures);
        packFormat = other.packFormat;
        numberOfThreads = other.numberOfThreads;
        bannerPattern = other.bannerPattern;
        limitTransparencyToBlocks = other.limitTransparencyToBlocks;
        partialPainting = other.partialPainting;
        partialParticles = other.partialParticles;
        partialBanner = other.partialBanner;
        mipmaps = other.mipmaps;

    }
    public Settings(){};

    public String resetOption(String key) {
        if (options.containsKey(key)) {
            String value = Data.getDefaultOption(key);
            options.put(key, value);
            return value;
        }
        return "";
    }

    public void resetAllOptions() {
        options.putAll(Data.getOptions());
    }

    public void createTextureMap(){
        if (converter != null) {
            int count = 0;
            textures.put(converter.getNormalSuffix(), count++);
            if (!textures.containsKey(converter.getMetallicSuffix()))
                textures.put(converter.getMetallicSuffix(), count++);
            if (!textures.containsKey(converter.getEmissiveSuffix()))
                textures.put(converter.getEmissiveSuffix(), count++);
            if (!textures.containsKey(converter.getRoughnessSuffix()))
                textures.put(converter.getRoughnessSuffix(), count);

        }
    }

    public boolean excluded(String s) {
        return excludedTextures.contains(s);
    }
}

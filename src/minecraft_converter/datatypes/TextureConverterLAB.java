package minecraft_converter.datatypes;

public final class TextureConverterLAB implements TextureConverter{
    private static final String normalSuffix = "_n";
    private static final String metallicSuffix = "_s";
    private static final String emissiveSuffix = "_s";
    private static final String roughnessSuffix = "_s";

    public final static String name = "LAB-PBR";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNormalSuffix() {
        return normalSuffix;
    }

    @Override
    public String getMetallicSuffix() {
        return metallicSuffix;
    }

    @Override
    public String getEmissiveSuffix() {
        return emissiveSuffix;
    }

    @Override
    public String getRoughnessSuffix() {
        return roughnessSuffix;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public NormalFormat getNormalFormat() {
        return NormalFormat.DIRECTX;
    }

    @Override
    public NormalChannels getNormalChannels() {
        return NormalChannels.RG;
    }

    @Override
    public Channel getMetallicChannel() {
        return Channel.Green;
    }

    @Override
    public Operation getMetallicOperation() {
        return Operation.THRESHOLD_UP;
    }

    @Override
    public String getMetallicOperationExtra() {
        return "230";
    }

    @Override
    public Channel getEmissiveChannel() {
        return Channel.Alpha;
    }

    @Override
    public Operation getEmissiveOperation() {
        return Operation.COPY_RANGE;
    }

    @Override
    public String getEmissiveOperationExtra() {
        return "0,254,0,0";
    }

    @Override
    public Channel getRoughnessChannel() {
        return Channel.Red;
    }

    @Override
    public Operation getRoughnessOperation() {
        return Operation.INVERSE;
    }
}

package minecraft_converter.datatypes;

public final class TextureConverterCustom implements TextureConverter{
    private String normalSuffix = "_n";
    private NormalFormat normalFormat = NormalFormat.DIRECTX;
    private NormalChannels normalChannels = NormalChannels.RGB;

    private String metallicSuffix = "_s";
    private Channel metallicChannel = Channel.Red;
    private Operation metallicOperation = Operation.COPY;
    private String metallicOperationExtra = "0";

    private String emissiveSuffix = "_s";
    private Channel emissiveChannel = Channel.Red;
    private Operation emissiveOperation = Operation.COPY;
    private String emissiveOperationExtra = "0";

    private String roughnessSuffix = "_s";
    private Channel roughnessChannel = Channel.Red;
    private Operation roughnessOperation = Operation.COPY;
    private String roughnessOperationExtra = "0";

    public final static String name = "Custom";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNormalSuffix() {
        return normalSuffix;
    }

    @Override
    public NormalFormat getNormalFormat() {
        return normalFormat;
    }

    @Override
    public NormalChannels getNormalChannels() {
        return normalChannels;
    }

    @Override
    public String getMetallicSuffix() {
        return metallicSuffix;
    }

    @Override
    public Channel getMetallicChannel() {
        return metallicChannel;
    }

    @Override
    public Operation getMetallicOperation() {
        return metallicOperation;
    }

    @Override
    public String getMetallicOperationExtra() {
        return metallicOperationExtra;
    }

    @Override
    public String getEmissiveSuffix() {
        return emissiveSuffix;
    }

    @Override
    public Channel getEmissiveChannel() {
        return emissiveChannel;
    }

    @Override
    public Operation getEmissiveOperation() {
        return emissiveOperation;
    }

    @Override
    public String getEmissiveOperationExtra() {
        return emissiveOperationExtra;
    }

    @Override
    public String getRoughnessSuffix() {
        return roughnessSuffix;
    }

    @Override
    public Channel getRoughnessChannel() {
        return roughnessChannel;
    }

    @Override
    public Operation getRoughnessOperation() {
        return roughnessOperation;
    }

    @Override
    public String getRoughnessOperationExtra() {
        return roughnessOperationExtra;
    }

    @Override
    public void setNormalSuffix(String normalSuffix) {
        this.normalSuffix = normalSuffix;
    }

    @Override
    public void setNormalFormat(NormalFormat normalFormat) {
        this.normalFormat = normalFormat;
    }

    @Override
    public void setNormalChannels(NormalChannels normalChannels) {
        this.normalChannels = normalChannels;
    }

    @Override
    public void setMetallicSuffix(String metallicSuffix) {
        this.metallicSuffix = metallicSuffix;
    }

    @Override
    public void setMetallicChannel(Channel metallicChannel) {
        this.metallicChannel = metallicChannel;
    }

    @Override
    public void setMetallicOperation(Operation metallicOperation) {
        this.metallicOperation = metallicOperation;
    }

    @Override
    public void setMetallicOperationExtra(String metallicOperationExtra) {
        this.metallicOperationExtra = metallicOperationExtra;
    }

    @Override
    public void setEmissiveSuffix(String emissiveSuffix) {
        this.emissiveSuffix = emissiveSuffix;
    }

    @Override
    public void setEmissiveChannel(Channel emissiveChannel) {
        this.emissiveChannel = emissiveChannel;
    }

    @Override
    public void setEmissiveOperation(Operation emissiveOperation) {
        this.emissiveOperation = emissiveOperation;
    }

    @Override
    public void setEmissiveOperationExtra(String emissiveOperationExtra) {
        this.emissiveOperationExtra = emissiveOperationExtra;
    }

    @Override
    public void setRoughnessSuffix(String roughnessSuffix) {
        this.roughnessSuffix = roughnessSuffix;
    }

    @Override
    public void setRoughnessChannel(Channel roughnessChannel) {
        this.roughnessChannel = roughnessChannel;
    }

    @Override
    public void setRoughnessOperation(Operation roughnessOperation) {
        this.roughnessOperation = roughnessOperation;
    }

    @Override
    public void setRoughnessOperationExtra(String roughnessOperationExtra) {
        this.roughnessOperationExtra = roughnessOperationExtra;
    }

    @Override
    public String toString() {
        return name;
    }
}

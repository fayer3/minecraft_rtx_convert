package minecraft_converter.datatypes;

public interface TextureConverter {

    enum NormalFormat {OPENGL, DIRECTX}
    enum NormalChannels {RGB, RG;
        @Override
        public String toString() {
            switch (this) {
                case RG:
                    return "Red, Green";
                case RGB:
                    return "Reed, Green, Blue";
            }
            return "";
        }
    }

    //enum Operation {COPY, COPY_RANGE, INVERSE, INVERSE_SQUARE, THRESHOLD_UP, THRESHOLD_DOWN}
    public enum Operation {
        COPY            ("copy", "copies the value as is"),
        COPY_RANGE      ("range", "takes the values inside the specified range and expands the mto the range 0-255\n" +
                "extra data format: \"lowerBound,UpperBound,below,above\"\n" +
                "lowerBound: lower bound of the range\n" +
                "upperBound: upper bound of the range\n" +
                "below: value to assign, if the source is below lowerBound\n" +
                "above: value to assign, if the source is above upperBound"),
        INVERSE         ("inverse", "inverse the value: (255-x)"),
        INVERSE_SQUARE  ("inverse square", "squared of the inverted value: 255-(pow)"),
        THRESHOLD_UP    (">=", "Values greater than the specified threshold are set to 255, other to 0"),
        THRESHOLD_DOWN  ("<=", "Values less than the specified threshold are set to 255, other to 0");
        public final String presentation;
        public final String explanation;

        Operation(String presentation, String explanation) {
            this.presentation = presentation;
            this.explanation = explanation;
        }
        @Override
        public String toString() {
            return presentation;
        }

    }
    enum Channel {Red, Green, Blue, Alpha}

    String getName();

    String getNormalSuffix();
    NormalFormat getNormalFormat();
    NormalChannels getNormalChannels();

    String getMetallicSuffix();
    Channel getMetallicChannel();
    Operation getMetallicOperation();
    default String getMetallicOperationExtra(){
        return "0";
    }

    String getEmissiveSuffix();
    Channel getEmissiveChannel();
    Operation getEmissiveOperation();
    default String getEmissiveOperationExtra(){
        return "0";
    }

    String getRoughnessSuffix();
    Channel getRoughnessChannel();
    Operation getRoughnessOperation();
    default String getRoughnessOperationExtra(){
        return "0";
    }

    default void setNormalSuffix(String normalSuffix){}
    default void setNormalFormat(NormalFormat normalFormat){}
    default void setNormalChannels(NormalChannels normalChannels){}

    default void setMetallicSuffix(String metallicSuffix){}
    default void setMetallicChannel(Channel metallicChannel){}
    default void setMetallicOperation(Operation metallicOperation){}
    default void setMetallicOperationExtra(String metallicOperationExtra){}

    default void setEmissiveSuffix(String emissiveSuffix){}
    default void setEmissiveChannel(Channel emissiveChannel){}
    default void setEmissiveOperation(Operation emissiveOperation){}
    default void setEmissiveOperationExtra(String emissiveOperationExtra){}

    default void setRoughnessSuffix(String roughnessSuffix){}
    default void setRoughnessChannel(Channel roughnessChannel){}
    default void setRoughnessOperation(Operation roughnessOperation){}
    default void setRoughnessOperationExtra(String roughnessOperationExtra){}

    static boolean equals(TextureConverter first, TextureConverter other){
        if (first == other)
            return true;
        if (first == null || other == null)
            return false;
        return first.getNormalSuffix().equals(other.getNormalSuffix()) &&
                first.getNormalChannels().equals(other.getNormalChannels()) &&
                first.getNormalFormat().equals(other.getNormalFormat()) &&
                first.getMetallicSuffix().equals(other.getMetallicSuffix()) &&
                first.getMetallicChannel().equals(other.getMetallicChannel()) &&
                first.getMetallicOperation().equals(other.getMetallicOperation()) &&
                first.getMetallicOperationExtra().equals(other.getMetallicOperationExtra()) &&
                first.getEmissiveSuffix().equals(other.getEmissiveSuffix()) &&
                first.getEmissiveChannel().equals(other.getEmissiveChannel()) &&
                first.getEmissiveOperation().equals(other.getEmissiveOperation()) &&
                first.getEmissiveOperationExtra().equals(other.getEmissiveOperationExtra()) &&
                first.getRoughnessSuffix().equals(other.getRoughnessSuffix()) &&
                first.getRoughnessChannel().equals(other.getRoughnessChannel()) &&
                first.getRoughnessOperation().equals(other.getRoughnessOperation()) &&
                first.getRoughnessOperationExtra().equals(other.getRoughnessOperationExtra());
    }

    default void setValues(TextureConverter other) {
        setNormalSuffix(other.getNormalSuffix());
        setNormalFormat(other.getNormalFormat());
        setNormalChannels(other.getNormalChannels());

        setMetallicSuffix(other.getMetallicSuffix());
        setMetallicChannel(other.getMetallicChannel());
        setMetallicOperation(other.getMetallicOperation());
        setMetallicOperationExtra(other.getMetallicOperationExtra());

        setEmissiveSuffix(other.getEmissiveSuffix());
        setEmissiveChannel(other.getEmissiveChannel());
        setEmissiveOperation(other.getEmissiveOperation());
        setEmissiveOperationExtra(other.getEmissiveOperationExtra());

        setRoughnessSuffix(other.getRoughnessSuffix());
        setRoughnessChannel(other.getRoughnessChannel());
        setRoughnessOperation(other.getRoughnessOperation());
        setRoughnessOperationExtra(other.getRoughnessOperationExtra());
    }

    default RGB getNormal(RGB color) {
        switch (getNormalChannels()) {
            case RGB: {
                double r = (color.r - 128.) / 255. * 2;
                double g = (color.g - 128.) / 255. * 2;
                double b = (color.b - 128.) / 255. * 2;
                if (getNormalFormat() == NormalFormat.OPENGL)
                    g = 1 - g;
                return new RGB(Math.min(Math.round(Math.round(r * 255. * 0.5 + 128.)), 255), Math.min(Math.round(Math.round(g * 255. * 0.5 + 128.)), 255), Math.min(Math.round(Math.round(b * 255. * 0.5 + 128.)), 255), 255);
            }
            case RG:
                double r = (color.r - 128.)/255.*2;
                double g = (color.g - 128.)/255.*2;
                double length = r*r+g*g;
                double b = 0;
                if (length > 1) {
                    // wrong normals, clamp to 0 and normalize
                    r /= length;
                    g /= length;
                } else {
                    b = Math.sqrt(1-length);
                }

                if (getNormalFormat() == NormalFormat.OPENGL)
                    g = 1-g;
                return new RGB(Math.min(Math.round(Math.round(r*255.*0.5+128.)),255), Math.min(Math.round(Math.round(g*255.*0.5+128.)),255), Math.min(Math.round(Math.round(b*255.*0.5+128.)),255), 255);
        }
        return new RGB(128, 128, 255, 255);
    }

    default int getEmissive(RGB color) {
        return doOperation(color, getEmissiveChannel(), getEmissiveOperation(), getEmissiveOperationExtra());
    }

    default int getMetallic(RGB color) {
        return doOperation(color, getMetallicChannel(), getMetallicOperation(), getMetallicOperationExtra());
    }

    default int getRoughness(RGB color) {
        return doOperation(color, getRoughnessChannel(), getRoughnessOperation(), getRoughnessOperationExtra());
    }

    default int doOperation(RGB color, Channel channel, Operation operation, String extra) {
        int value = switchChannel(color, channel);
        return switchOperation(value, operation, extra);
    }

    default int switchChannel(RGB color, Channel c) {
        switch (c) {
            case Red:
                return color.r;
            case Green:
                return color.g;
            case Blue:
                return color.b;
            case Alpha:
                return color.a;
        }
        return 0;
    }

    default int switchOperation(int value, Operation o, String extra) {
        switch (o) {
            case COPY:
                return value;
            case THRESHOLD_DOWN:
                int thresholdDown = Integer.parseInt(extra.trim());
                return value <= thresholdDown ? 255 : 0;
            case THRESHOLD_UP:
                int thresholdUp = Integer.parseInt(extra.trim());
                return value >= thresholdUp ? 255 : 0;
            case COPY_RANGE:
                String[] bound = extra.trim().split(",");
                double rangeLow = Integer.parseInt(bound[0]);
                double rangeHigh = Integer.parseInt(bound[1]);
                int caseHigh = Integer.parseInt(bound[2]);
                int caseLow = Integer.parseInt(bound[3]);
                if (value > rangeHigh)
                    return caseHigh;
                else if (value < rangeLow)
                    return caseLow;
                // scale range to 0-255
                double newValue = (255.*(value-rangeLow)) / (rangeHigh-rangeLow);
                return Math.round(Math.round(newValue));
            case INVERSE:
                return 255-value;
            case INVERSE_SQUARE:
                double valueOne = value / 255.;
                return Math.round(Math.round(1-valueOne*valueOne));
        }
        return 0;
    }
}

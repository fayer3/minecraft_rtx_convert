package minecraft_converter.tools;

import java.io.InputStream;

public class ZipOperation {
    public boolean isPath = false;
    public boolean isStream = false;
    public String source = "";
    public String target = "";
    public byte[] data = null;
    public InputStream stream = null;

    public ZipOperation(String source, String target) {
        isPath = true;
        this.source = source;
        this.target = target;
    }

    public ZipOperation(String target, InputStream stream) {
        isStream = true;
        this.target = target;
        this.stream = stream;
    }

    public ZipOperation(String target, byte[] data) {
        this.target = target;
        this.data = data;
    }
}

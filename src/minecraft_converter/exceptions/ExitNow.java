package minecraft_converter.exceptions;

public class ExitNow extends Exception{
    public ExitNow() {
        super();
    }
    public ExitNow(String message) {
        super(message);
    }
    public ExitNow(String message, Exception other) {
        super(message, other);
    }
    public ExitNow(Exception other) {
        super(other);
    }
}

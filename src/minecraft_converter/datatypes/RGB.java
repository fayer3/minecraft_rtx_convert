package minecraft_converter.datatypes;

public class RGB {
    public int r,g,b,a;
    public RGB(){}
    public RGB(int r, int g, int b, int a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public RGB(RGB in) {
        this.r = in.r;
        this.g = in.g;
        this.b = in.b;
        this.a = in.a;
    }

    public RGB mult(RGB other) {
        this.r = Math.round(this.r * (other.r/255f));
        this.g = Math.round(this.g * (other.g/255f));
        this.b = Math.round(this.b * (other.b/255f));
        this.a = Math.round(this.a * (other.a/255f));
        return this;
    }

    public RGB mix(RGB other) {
        float factor = other.a/255f;
        this.r = Math.round(this.r * (1-factor) + other.r * factor);
        this.g = Math.round(this.g * (1-factor) + other.g * factor);
        this.b = Math.round(this.b * (1-factor) + other.b * factor);
        //this.a = Math.round(this.a * (1-factor) + other.a * factor);
        return this;
    }

    public RGB add(RGB other) {
        this.r += other.r;
        this.g += other.g;
        this.b += other.b;
        this.a += other.a;
        return this;
    }

    public RGB div(float f) {
        this.r = Math.round(this.r/f);
        this.g = Math.round(this.g/f);
        this.b = Math.round(this.b/f);
        this.a = Math.round(this.a/f);
        return this;
    }
}

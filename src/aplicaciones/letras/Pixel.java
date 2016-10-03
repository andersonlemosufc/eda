package aplicaciones.letras;


public class Pixel {
    public static final int BASE = 255;
    private int r, g, b; 
    private int hashCode;
    
    public Pixel() { 
        r = 0; g = 0; b = 0;
        hashCode = 0;
    }
    
    public Pixel(int red, int green, int blue) { 
        this.r = red; this.g = green; this.b = blue; 
        hashCode = r * BASE * BASE + g * BASE + b; 
    }
    
    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
    
    public boolean equals(Object o) {
        if (o instanceof Pixel) {
            Pixel p = (Pixel) o;
            return (p.r == r) && (p.g == g) && (p.b == b);
        }
        return false;
    }
    public String toString() {
        return r + " " + g + " " + b;
    }
    
    public int hashCode() {
        return hashCode;
    }
}
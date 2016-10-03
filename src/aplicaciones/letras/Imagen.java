package aplicaciones.letras;

import java.io.DataInputStream;
import java.io.IOException;


public class Imagen {
    public static final int SUMA_TODOS           = 0;  
    public static final int SUMA_PONDERADA_TODOS = 1;  
    public static final int SUMA_TRES            = 2;  
    public static final int SUMA_PONDERADA_TRES  = 3;  
    public static final String[] NOMFDIS = {"SUMA_TODOS", 
        "SUMA_PONDERADA_TODOS", "SUMA_TRES", "SUMA_PONDERADA_TRES" };
        
    public static final String NOM_URL = "https://poliformat.upv.es/x/j5uDOn";
   
    protected Pixel[][] pixels;
    protected int alto, ancho;
    protected int valorHash; 
    
    protected Imagen(int numF, int numC) { 
        this.alto = numF; 
        this.ancho = numC;
        pixels = new Pixel[alto][ancho];
        valorHash = 0;
    }
    
    public Imagen(DataInputStream in, int numF, int numC, int fhash) {
        this(numF, numC);
        try {
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    pixels[i][j] = new Pixel(in.readUnsignedByte(),
                             in.readUnsignedByte(), in.readUnsignedByte());
                }
            }
            valorHash = obtieneValorHash(fhash);
        } catch (IOException e) { 
            System.err.println("Problemas con la lectura de fichero");
            e.printStackTrace();
        }
    }
  
    public Imagen(int numF, int numC, int fhash) {
        this(numF, numC);
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                pixels[i][j] = new Pixel((int) (Math.random() * Pixel.BASE), 
                    (int) (Math.random() * Pixel.BASE), 
                    (int) (Math.random() * Pixel.BASE));
            }
        }
        valorHash = obtieneValorHash(fhash);
    }
  
    public final int getAlto() { return alto; }
  
    public final int getAncho() { return ancho; }
  
    public Pixel getPixel(int x, int y) { return pixels[x][y]; }
  
    public boolean equals(Object img) {
        if (img instanceof Imagen) {
            Imagen der = (Imagen) img;
            if (ancho != der.ancho || alto != der.alto) {
                return false; 
            }
            if (this.hashCode() != der.hashCode()) {
                return false;
            }
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    if (!pixels[i][j].equals(der.pixels[i][j])) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
  
    public int hashCode() { return this.valorHash; }
    
    protected int obtieneValorHash(int fHash) {
        int res = 0;
        switch (fHash) {
            case SUMA_TODOS:
                res = this.valorHashSumaTodos(); break;                   
            case SUMA_PONDERADA_TODOS:
                res = this.valorHashSumaPonderadaTodos(); 
                break;                         
            case SUMA_TRES: 
                res = this.valorHashSuma3(); break;                        
            case SUMA_PONDERADA_TRES:
                res = this.valorHashSumaPonderada3(); break;
            default:
                res = 0; break;
        }
        return res;   
    }
  
    private int valorHashSumaTodos() {
        int res = 0;
        for(int i=0;i<pixels.length;i++){
            for(int j=0;j<pixels[i].length;j++){
                res  += pixels[i][j].hashCode();
            }
        }
        return res;
    }
  
    private int valorHashSumaPonderadaTodos() {
        int res = 0;
        for(int i=0;i<pixels.length;i++){
            for(int j=0;j<pixels[i].length;j++){
                res  = res * Pixel.BASE + pixels[i][j].hashCode();
            }
        }
        return res;
        
    }
  
    private int valorHashSuma3() { 
        int res = 0;
        int it=pixels.length/2-1;
        int jt=pixels[0].length/2-1;
        for(int i=it;i<(it+3);i++){
            for(int j=jt;j<(jt+3);j++){
                res  += pixels[i][j].hashCode();
            }
        }
        return res;
        
    }
    
    private int valorHashSumaPonderada3() {
        int res = 0;
        int it=pixels.length/2-1;
        int jt=pixels[0].length/2-1;
        for(int i=it;i<(it+3);i++){
            for(int j=jt;j<(jt+3);j++){
                res  = res * Pixel.BASE + pixels[i][j].hashCode();
            }
        }
        return res;
        
    }
}
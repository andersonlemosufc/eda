package aplicaciones.municipios;


public class Municipio implements Comparable<Municipio> {
    
    private String nombre;    
    private int poblacion;    
    private double extension;   
    private int posX, posY;
    
       
    public Municipio(String nombreM) { this(nombreM, 0, 0.0, 0, 0); }
   
    public Municipio(String nom, int pobl, double ext, int pX, int pY) {
        this.nombre = nom;
        this.poblacion = pobl;
        this.extension = ext;
        this.posX = pX; this.posY = pY;
    }
    
    public String getNombre() { return nombre; }
    
    public int getPoblacion() { return poblacion; }
    
    public double getExtension() { return extension; }
    
    public int getPosX() { return posX; }
    
    public int getPosY() { return posY; }
    
    public int compareTo(Municipio x) { return nombre.compareTo(x.nombre); }
    
    public String toString() {
        String res = nombre + ";" + poblacion + ";" 
            + extension + ";" + posX + ";" + posY;
        return res;
    }
    
    public boolean equals(Object x) { 
        return ((Municipio) x).nombre.equals(nombre); 
    }

    public int hashCode() { return nombre.hashCode(); }
}

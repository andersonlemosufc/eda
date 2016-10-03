package aplicaciones.hospital;

public class Paciente implements Comparable<Paciente>  {

 
    public static final int SANO      = 6;
    public static final int MUY_LEVE  = 5;
    public static final int LEVE      = 4;
    public static final int MODERADO  = 3;    
    public static final int GRAVE     = 2;
    public static final int CRITICO   = 1;
    public static final int FALLECIDO = 0;    
    private static final String[] NOMBRE_ESTADO = {"fallecido", 
        "critico", "grave", "moderado", "leve", "muy leve", "sano"};
    
    private String nombre;
    private int edad, estado;
    private long tpoLlegadaUrgencias;    
  
    public Paciente(String nombre, int edad, int estado) {        
        this.nombre = nombre;
        this.edad = edad;
        this.estado = estado;
        tpoLlegadaUrgencias = 0;
    }
    
    public Paciente(String n) {
        this(n, (int) (Math.random() * 101), (int) (Math.random() * 5 + 1));
    }
    
    public int getEstado() { 
        return estado; 
    }    
    
    public String getDescripcionEstado() { 
        return NOMBRE_ESTADO[estado];
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public int getEdad() { 
        return edad; 
    }
    
    public long getTpoLlegadaUrgencias() {
        return tpoLlegadaUrgencias;
    }
    
    public void setTpoLlegadaUrgencias(long tpo) {        
        tpoLlegadaUrgencias = tpo;
    }
    
    public boolean aplicarCura() {
        if (Math.random() < 0.05) { estado = 0; } // fallece
        else                      { estado++; }   // mejora
        return estado > 0;
    }
    
    public int compareTo(Paciente otro) { 
        int res = 0; 
        if(this.getEstado()!=otro.getEstado()){
            res = this.getEstado() - otro.getEstado();
        } else {
            if(this.getEdad()<15 || otro.getEdad()<15){
                res = (this.getEdad() - otro.getEdad()) ;
            } else if(this.getEdad()>65 || otro.getEdad()>65){
                res = (otro.getEdad() - this.getEdad());
            }
        }
        return res;
    }
     
    public String toString() { 
        return "Paciente: " + nombre + ", " + edad 
               + " anyos, estado:" + estado + "\n";
    }
    
    public boolean equals(Object otro) { 
        return otro instanceof Paciente 
               && ((Paciente) otro).nombre.equals(this.nombre);
    }             
}

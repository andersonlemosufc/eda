package librerias.estructurasDeDatos.grafos;

public class Adyacente { 
	
    protected int destino;
    protected double peso;
    
    public Adyacente(int codAdy, double pesoArista) { 
        destino = codAdy;   peso = pesoArista; 
    }
    
    public int getDestino() { return destino; }
     
    public double getPeso() { return peso; }
    
    public String toString() { 
        return destino + "(" + peso + ") ";
    }
}

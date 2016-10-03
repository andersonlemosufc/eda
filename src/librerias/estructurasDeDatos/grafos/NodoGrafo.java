package librerias.estructurasDeDatos.grafos;

import java.lang.Comparable;

public class NodoGrafo implements Comparable<NodoGrafo>{
   
    private int vertice;
    private double peso;
    
    public NodoGrafo(int vertice, double peso) {
        this.peso = peso;
        this.vertice = vertice;
    }

    public int getVertice(){
        return this.vertice;
    }
    
    public void setVertice(int vertice){
        this.vertice = vertice;
    }
    
    public double getPeso(){
        return this.peso;
    }
    
    public void setPeso(double peso){
        this.peso = peso;
    }
    
    public int compareTo(NodoGrafo otro){
        double t = this.peso-otro.getPeso();
        return t==0 ? 0 : t<0 ?  -1 : 1;
    }
}

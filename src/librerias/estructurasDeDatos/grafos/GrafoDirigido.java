package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

public class GrafoDirigido extends Grafo { 
    
    protected int numV, numA;
    protected ListaConPI<Adyacente>[] elArray;
    
    @SuppressWarnings("unchecked")
    public GrafoDirigido(int numVertices) {
        numV = numVertices; numA = 0;
        elArray = new ListaConPI[numVertices]; 
        for (int i = 0; i < numV; i++) {
            elArray[i] = new LEGListaConPI<Adyacente>();
        }
    }
    
    public int numVertices() { return numV; }

    public int numAristas() { return numA; }
       
    public boolean existeArista(int i, int j) {
        ListaConPI<Adyacente> l = elArray[i]; 
        boolean esta = false;
        for (l.inicio(); !l.esFin() && !esta; l.siguiente()) {
            if (l.recuperar().destino == j) { esta = true; }
        }
        return esta;   
    }
    
    public double pesoArista(int i, int j) {
        ListaConPI<Adyacente> l = elArray[i];
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            if (l.recuperar().destino == j) {
                return l.recuperar().peso;
            }
        }
        return 0.0;
    }
    
    public void insertarArista(int i, int j) {
        if (!existeArista(i, j)) { 
            elArray[i].insertar(new Adyacente(j, 1)); 
            numA++; 
        }
    }
 
    public void insertarArista(int i, int j, double p) {
        if (!existeArista(i, j)) { 
            elArray[i].insertar(new Adyacente(j, p)); 
            numA++; 
        }
    }
    
    public ListaConPI<Adyacente> adyacentesDe(int i) {
        return elArray[i];
    }
    
}


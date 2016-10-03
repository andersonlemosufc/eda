package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.jerarquicos.PriorityQColaPrioridad;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public abstract class Grafo {
    protected static final double INFINITO = Double.POSITIVE_INFINITY;
    
    protected int[] visitados; 
    protected int ordenVisita; 
    protected Cola<Integer> q; 
    
    protected double[] distanciaMin; 
    protected int[] caminoMin;       
    
    public abstract int numVertices();
    
    public abstract int numAristas();
    
    public abstract boolean existeArista(int i, int j);
    
    public abstract double pesoArista(int i, int j);
    
    public abstract void insertarArista(int i, int j);
    
    public abstract void insertarArista(int i, int j, double p);

    public abstract ListaConPI<Adyacente> adyacentesDe(int i);
                  
    public String toString() {
        String res = "";  
        for (int  i = 0; i < numVertices(); i++) {
            res += "Vertice: " + i;
            ListaConPI<Adyacente> l = adyacentesDe(i);
            if (l.esVacia()) { res += " sin Adyacentes "; }
            else { res += " con Adyacentes "; } 
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                res +=  l.recuperar() + " ";  
            }
            res += "\n";  
        }
        return res;      
    }  
    
    protected void dijkstra(int origen) {
        distanciaMin = new double[numVertices()];
        caminoMin = new int[numVertices()];     
        visitados = new int[numVertices()];     
        for(int k=0;k<numVertices();k++){   
            distanciaMin[k] = INFINITO;
            caminoMin[k] = -1;
            visitados[k] = 0;
        }
        PriorityQColaPrioridad<NodoGrafo> cola = new PriorityQColaPrioridad<NodoGrafo>();
        distanciaMin[origen]=0;
        cola.insertar(new NodoGrafo(origen, 0));
        while(!cola.esVacia()){
            NodoGrafo n = cola.eliminarMin();
            if(visitados[n.getVertice()]==0){
                visitados[n.getVertice()] = 1;
                ListaConPI<Adyacente> l = adyacentesDe(n.getVertice());
                for(l.inicio();!l.esFin();l.siguiente()){
                    Adyacente a = l.recuperar();
                    int w = a.getDestino();
                    double pesoW = a.getPeso();
                    if(distanciaMin[w] > distanciaMin[n.getVertice()]+pesoW){
                        distanciaMin[w] = distanciaMin[n.getVertice()]+pesoW;
                        caminoMin[w] = n.getVertice();
                        cola.insertar(new NodoGrafo(w, distanciaMin[w]));
                    }
                }
            }
        }
    }
    
    public double distanciaMinima(int vOrigen, int vDestino) {
        dijkstra(vOrigen);
        return distanciaMin[vDestino];
    }
    
    public ListaConPI<Integer> caminoMinimo(int origen, int destino) {
        ListaConPI<Integer> l = new LEGListaConPI<Integer>();
        if(existeVertice(origen) && existeVertice(destino)){
            if(distanciaMinima(origen, destino) < INFINITO){
                while(destino!=origen){
                    l.insertar(destino);
                    destino = caminoMin[destino];
                    l.inicio();
                }
                l.insertar(origen);
            }
        }
        return l;
    }   
    
    private boolean existeVertice(int v){
        return (v>=0 && v<numVertices());
    }
    
}

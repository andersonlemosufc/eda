package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ListaConPI;

public class LEGListaConPI<E> implements ListaConPI<E> {

    protected NodoLEG<E> pri, ant, ult; 
    
    protected int talla;
    
    public LEGListaConPI() {
        pri = ult = ant = new NodoLEG<E>(null);
        talla = 0;
    }
    
    public void insertar(E e) { 
        NodoLEG<E> nuevo = new NodoLEG<E>(e); talla++;
        
        if(ant==ult){
            ant.siguiente = nuevo;
            ant = ult = nuevo;
        } else if(ant==pri){
            nuevo.siguiente = pri.siguiente;
            pri.siguiente = nuevo;
            ant = nuevo;
        } else {        
            nuevo.siguiente = ant.siguiente;
            ant.siguiente = nuevo;
            ant = nuevo;
        }     
    } 
    
    public void eliminar() {
        talla--;
        if(!esFin()){
            if(ant.siguiente==ult){
                ult = ant;
            }
            ant.siguiente=ant.siguiente.siguiente;
        }
    }
    
    public void inicio() { 
        if(!esVacia()){
            ant = pri;
        }
    }
    
    public void siguiente() { 
        if(!esFin()){
            ant = ant.siguiente;
        }
    }
   
    public void fin() {
       ant = ult;
    }

    public E recuperar() { 
        E res = null;
        if(!esFin()){
           res = ant.siguiente.dato;
        }
        return res;
    }
    public boolean esFin() { 
        return ant == ult;
    }
    
    public boolean esVacia() { 
        return pri==ult;
    }
    
    public int talla() { return talla; }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        NodoLEG<E> aux = pri.siguiente;         
        for (int i = 1; i < talla; i++, aux = aux.siguiente) {            
            s.append(aux.dato.toString() + ", ");
        }
        if (talla != 0) {
            s.append(aux.dato.toString() + "]"); 
        } else { s.append("]"); }
        return s.toString();
    }
}

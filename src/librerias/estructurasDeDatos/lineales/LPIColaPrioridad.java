package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;

public class LPIColaPrioridad<E extends Comparable<E>> 
    extends LEGListaConPI<E>
    implements ColaPrioridad<E> {
    
    public LPIColaPrioridad() { super(); }
    
    public void insertar(E e) { 
        this.inicio();
        while(!this.esFin()){
            E otro = this.recuperar();
            if(e.compareTo(otro)<0){
                break;
            }
            this.siguiente();
        }
        super.insertar(e);
    }
    
    public E recuperarMin() { 
        inicio();
        return recuperar();
    }
    
    public E eliminarMin() { 
        E res = null;
        if(!this.esVacia()) {
            this.inicio();
            res = this.recuperarMin();
            this.eliminar();
        }
        return res;
    }
    
    public boolean esVacia() { return ( super.esVacia() ); }  
}

package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import java.util.PriorityQueue;

public class PriorityQColaPrioridad<E extends Comparable<E>> extends PriorityQueue<E> implements ColaPrioridad<E> 
{
	private static final long serialVersionUID = 1L;

    public PriorityQColaPrioridad() { 
       super();
    }
    
    public void insertar(E e) {
        this.add(e);
    }
    
    public E recuperarMin() { 
        return this.peek();
    }
    
    public E eliminarMin() { 
        return this.poll();
    }
    
    public boolean esVacia() { 
        return this.isEmpty();
    }
    
}
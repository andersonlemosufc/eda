package librerias.estructurasDeDatos.modelos;

public interface ColaPrioridad<E extends Comparable<E>> {

    void  insertar(E e);
    
    E  eliminarMin();
    
    E  recuperarMin();
    
    boolean esVacia();
}

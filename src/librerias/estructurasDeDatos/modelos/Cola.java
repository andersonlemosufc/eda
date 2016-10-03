package librerias.estructurasDeDatos.modelos;

public interface Cola<E> {
	
    void encolar(E e);
    
    E desencolar();
    
    E primero();
    
    boolean esVacia();
}
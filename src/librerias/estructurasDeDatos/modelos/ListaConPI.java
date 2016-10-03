package librerias.estructurasDeDatos.modelos;

public interface ListaConPI<E> {
    
	void insertar(E e);
	
    void eliminar();
    
    void inicio();
    
    void siguiente();
    
    void fin();
    
    E recuperar();
    
    boolean esFin();
    
    boolean esVacia();
    
    int talla();
}
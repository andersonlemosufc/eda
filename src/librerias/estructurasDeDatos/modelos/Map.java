package librerias.estructurasDeDatos.modelos;

public interface Map<C, V> {

    V insertar(C c, V v);
    
    V eliminar(C c);
    
    V recuperar(C c);
    
    boolean esVacio();
    
    int talla();
    
    ListaConPI<C> claves();
}


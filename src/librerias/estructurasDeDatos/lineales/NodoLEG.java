package librerias.estructurasDeDatos.lineales;

class NodoLEG<E> {

    E dato;
    NodoLEG<E> siguiente;
    
    NodoLEG(E e, NodoLEG<E> s) {
        this.dato = e;
        this.siguiente = s;
    }
    
    NodoLEG(E dato) { this(dato, null); } 
} 
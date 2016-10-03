package librerias.estructurasDeDatos.jerarquicos;

class NodoABB<E> {

	E dato;
    int talla;
    NodoABB<E> izq;
    NodoABB<E> der;
	 
    public NodoABB(E valor) {
	   this(valor, null, null);
    }
	 
    public NodoABB(E valor, NodoABB<E> hizq, NodoABB<E> hder) {
        this.dato = valor;
        this.izq = hizq;
        this.der = hder;
        this.talla = 1;
        if (hizq != null) { this.talla += hizq.talla; }
        if (hder != null) { this.talla += hder.talla; }
    }
}

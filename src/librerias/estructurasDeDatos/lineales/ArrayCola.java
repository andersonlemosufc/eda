package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.Cola;

public class ArrayCola<E> implements Cola<E> {
    protected static final int CAPACIDAD_POR_DEFECTO = 30000;
    protected E[] elArray;
    
    protected int finalC, principioC, talla;
   
    @SuppressWarnings("unchecked")
    public ArrayCola() {
        elArray = (E[]) new Object[CAPACIDAD_POR_DEFECTO];
        talla = 0; principioC = 0; finalC = 0;
    }
    
    public void encolar(E e) {
        if (talla == elArray.length) {
            duplicarArrayCircular();
        }
        elArray[finalC] = e;
        finalC = incrementar(finalC); talla++;
    }
    
    @SuppressWarnings("unchecked")
    protected void duplicarArrayCircular() {
        E[] nuevoArray = (E[]) new Object[elArray.length * 2];
        for (int i = 0; i < talla; i++, principioC = incrementar(principioC)) {
            nuevoArray[i] = elArray[principioC];
        }
        elArray = nuevoArray; principioC = 0; finalC = talla;
    }
    
    protected int incrementar(int indice) {
        if (++indice == elArray.length) { indice = 0; }
        return indice;
    }
    
    public E desencolar() {
        E elPrimero = elArray[principioC];
        principioC = incrementar(principioC); talla--;
        return elPrimero;
    }
    
    public E primero() { return elArray[principioC]; }
    
    public boolean esVacia() { return talla == 0; }
    
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        int aux = principioC;
        for (int i = 0, j = talla - 1; i < j; i++, aux = incrementar(aux)) {
            res.append(elArray[aux].toString() + ", ");
        }
        if (talla != 0) {
            res.append(elArray[aux].toString() + "]");
        }
        else { res.append("]"); }
        return res.toString();
    }
}

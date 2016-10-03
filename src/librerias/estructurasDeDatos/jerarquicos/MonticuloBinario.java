package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;

public class MonticuloBinario<E extends Comparable<E>> 
    implements ColaPrioridad<E> {  
	
    protected static final int CAPACIDAD_POR_DEFECTO = 10;
    protected E[] elArray;
    protected int talla;
    
    @SuppressWarnings("unchecked")
    public MonticuloBinario() {
        elArray = (E[]) new Comparable[CAPACIDAD_POR_DEFECTO];
        talla = 0;
    }
    
    public boolean esVacia() { return talla == 0; }
      
    public E recuperarMin() { return elArray[1]; }
    
    public void insertar(E e) {
        if (talla == elArray.length - 1) { duplicarArray(); }
        int  posActual = ++talla;
        while (posActual > 1 && e.compareTo(elArray[posActual / 2]) < 0) {
            elArray[posActual] = elArray[posActual / 2];
            posActual = posActual / 2;
        }
        elArray[posActual] = e;
    }
    
    @SuppressWarnings("unchecked")
    protected void duplicarArray() {
        E[] nuevo = (E[]) new Comparable[elArray.length * 2];
        System.arraycopy(elArray, 1, nuevo, 1, talla);
        elArray = nuevo;
    }  
    
    public E eliminarMin() {
        E elMinimo = recuperarMin();
        elArray[1] = elArray[talla--];
        hundir(1);
        return elMinimo;
    }
    
    protected void hundir(int posActual) {
        E aux = elArray[posActual]; 
        int hijo = posActual * 2; 
        boolean esHeap = false;
        while (hijo <= talla && !esHeap) {
            if (hijo != talla 
                && elArray[hijo + 1].compareTo(elArray[hijo]) < 0) { hijo++; }
            if (elArray[hijo].compareTo(aux) < 0) {
                elArray[posActual] = elArray[hijo];
                posActual = hijo; 
                hijo = posActual * 2;
            } else { esHeap = true; }
        }
        elArray[posActual] = aux;
    }
}

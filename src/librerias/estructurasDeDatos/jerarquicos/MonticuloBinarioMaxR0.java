package librerias.estructurasDeDatos.jerarquicos;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;

public class MonticuloBinarioMaxR0<E extends Comparable<E>> implements ColaPrioridad<E> {
	
    protected static final int CAPACIDAD_POR_DEFECTO = 10;
    protected E[] elArray;
    protected int talla;
    

    @SuppressWarnings("unchecked")
    public MonticuloBinarioMaxR0() {
        elArray = (E[]) new Comparable[CAPACIDAD_POR_DEFECTO];
        talla = 0;
    }
   
    public boolean esVacia() { return talla == 0; }
        
    public E recuperarMin() { return elArray[0]; }
    

    public void insertar(E e) {
        if (talla == elArray.length) { duplicarArray(); }
        int  posActual = talla++;
        while (posActual > 0 && e.compareTo(elArray[(posActual-1) / 2]) > 0) {
            elArray[posActual] = elArray[(posActual-1) / 2];
            posActual = (posActual-1) / 2;
        }
        elArray[posActual] = e;
    }
    
    @SuppressWarnings("unchecked")
    protected void duplicarArray() {
        E[] nuevo = (E[]) new Comparable[elArray.length * 2];
        System.arraycopy(elArray, 0, nuevo, 0, talla);
        elArray = nuevo;
    }  
    
    public E eliminarMin() {
        E elMinimo = recuperarMin();
        elArray[0] = elArray[--talla];
        hundir(0);
        return elMinimo;
    }
    
    protected void hundir(int posActual) {
        E aux = elArray[posActual]; 
        int hijo = posActual * 2+1; 
        boolean esHeap = false;
        while (hijo <= talla && !esHeap) {
            if (hijo != talla 
                && elArray[hijo + 1].compareTo(elArray[hijo]) > 0) { hijo++; }
            if (elArray[hijo].compareTo(aux) > 0) {
                elArray[posActual] = elArray[hijo];
                posActual = hijo; 
                hijo = posActual * 2+1;
            } else { esHeap = true; }
        }
        elArray[posActual] = aux;
    }
}

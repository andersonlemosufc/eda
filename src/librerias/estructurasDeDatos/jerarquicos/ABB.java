package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.util.Ordenacion;

public class ABB<E extends Comparable<E>> {
    protected NodoABB<E> raiz;

    public ABB() {
        raiz = null;
    }
    
    public ABB(E[] v) {
        Ordenacion.quickSort(v);
        this.raiz = construirEquilibrado(v, 0, v.length-1);
    }
    
    protected NodoABB<E> construirEquilibrado(E[] v, int ini, int fin) {
        if(fin<ini) return null;
        int m = (fin+ini)/2;
        NodoABB<E> n = new NodoABB<E>(v[m], construirEquilibrado(v, ini, m-1), construirEquilibrado(v, m+1, fin));
        return n;
    }
    
    public void reconstruirEquilibrado() {
        E v[] = toArrayInOrden();
        this.raiz = construirEquilibrado(v, 0, v.length-1);
    }
   
    public E recuperar(E x) {
        return recuperar(x, raiz);
    }
    
    protected E recuperar(E x, NodoABB<E> actual) {
        if (actual == null) { return null; }
        int cmp = x.compareTo(actual.dato);
        if (cmp < 0) { 
            return recuperar(x, actual.izq); 
        } else if (cmp > 0) { 
            return recuperar(x, actual.der); 
        } else { return actual.dato; }
    }
    
    public void insertar(E x) {
        raiz = insertar(x, raiz);
    }
    
    protected NodoABB<E> insertar(E x, NodoABB<E> actual) {
        if (actual == null) { return new NodoABB<E>(x); }
        int cmp = x.compareTo(actual.dato);
        if (cmp < 0) { 
            actual.izq = insertar(x, actual.izq); 
        } else if (cmp > 0) { 
            actual.der = insertar(x, actual.der); 
        } else { actual.dato = x; }
        actual.talla = 1 + talla(actual.izq) + talla(actual.der);
        return actual;
    }
    
    public int talla() {
        return talla(raiz);
    }

    protected int talla(NodoABB<E> actual) {
        if (actual == null) { 
            return 0;
        } else { return actual.talla; }
    }
  
    public E recuperarMin() {
        return recuperarMin(raiz).dato;
    }
    
    protected NodoABB<E> recuperarMin(NodoABB<E> actual) {
        if (actual.izq == null) { 
            return actual;
        } else { return recuperarMin(actual.izq); }
    }

    public E eliminarMin() {
        E min = recuperarMin();
        raiz = eliminarMin(raiz);
        return min;
    }
 
    protected NodoABB<E> eliminarMin(NodoABB<E> actual) {
        if (actual.izq == null) { return actual.der; }
        actual.izq = eliminarMin(actual.izq);
        actual.talla--;
        return actual;
    }
  
    public void eliminar(E x) {
        raiz = eliminar(x, raiz);
    }
    
    protected NodoABB<E> eliminar(E x, NodoABB<E> actual) {
        if (actual == null) { return actual; }
        int cmp = x.compareTo(actual.dato);
        if (cmp < 0) {
            actual.izq  = eliminar(x, actual.izq);
        } else if (cmp > 0) {
            actual.der = eliminar(x, actual.der);
        } else {
            if (actual.der == null) { return actual.izq; }
            if (actual.izq  == null) { return actual.der; }
            actual.dato = recuperarMin(actual.der).dato;
            actual.der = eliminarMin(actual.der);
        }
        actual.talla = 1 + talla(actual.izq) + talla(actual.der);
        return actual;
    }
  
    public boolean esVacio() {
        return raiz == null;
    }
  
    public String toStringInOrden() {
        StringBuilder sb = new StringBuilder().append("[");
        if (raiz != null) { toStringInOrden(sb, raiz); }
        return sb.append("]").toString();
    }
    
    protected void toStringInOrden(StringBuilder sb, NodoABB<E> actual) {
        if (actual.izq != null) {
            toStringInOrden(sb, actual.izq);
            sb.append(",");
        }
        sb.append(actual.dato.toString());
        if (actual.der != null) { 
            sb.append(",");
            toStringInOrden(sb, actual.der); 
        }
    }

    public String toStringPreOrden() {
        StringBuilder sb = new StringBuilder().append("[");
        if (raiz != null) { toStringPreOrden(sb, raiz); }
        return sb.append("]").toString();
    }
    
    protected void toStringPreOrden(StringBuilder sb, NodoABB<E> actual) {
        sb.append(actual.dato.toString());
        if (actual.izq != null) {
            sb.append(",");
            toStringPreOrden(sb, actual.izq);
        }
        if (actual.der != null) {
            sb.append(",");
            toStringPreOrden(sb, actual.der);
        }
    }

    public String toStringPostOrden() {
        StringBuilder sb = new StringBuilder().append("[");
        if (raiz != null) { toStringPostOrden(sb, raiz); }
        return sb.append("]").toString();
    }
    
    protected void toStringPostOrden(StringBuilder sb, NodoABB<E> actual) {
        if (actual.izq != null) {
            toStringPostOrden(sb, actual.izq);
            sb.append(",");
        }
        if (actual.der != null) {
            toStringPostOrden(sb, actual.der);
            sb.append(",");
        }
        sb.append(actual.dato.toString());
    }
    
    public String toStringPorNiveles() {
       if (this.raiz == null) { return "[]"; }
       StringBuilder res = new StringBuilder().append("[");
       Cola<NodoABB<E>> q = new ArrayCola<NodoABB<E>>();
       q.encolar(this.raiz);
       while (!q.esVacia()) {
           NodoABB<E> actual = q.desencolar();
           res.append(actual.dato.toString());
           res.append(", ");
           if (actual.izq != null) { q.encolar(actual.izq); }
           if (actual.der != null) { q.encolar(actual.der); }
       }
       res.setLength(res.length() - 2);
       return res.append("]").toString();
    }
    
    @SuppressWarnings("unchecked")
    public E[] toArrayInOrden() {
        E[] v = (E[]) new Comparable[talla()];
        toArrayInOrden(v, raiz, 0);
        return v;
    }
    
    protected void toArrayInOrden(E[] v, NodoABB<E> actual, int pos) {
       if (actual != null) {
           toArrayInOrden(v, actual.izq, pos);
           pos += talla(actual.izq);
           v[pos++] = actual.dato;
           toArrayInOrden(v, actual.der, pos);    
       }
    }

    public E sucesor(E e) {
        NodoABB<E> res = sucesor(e, this.raiz);
        if (res == null) { 
            return null; 
        } else { 
            return res.dato; 
        }
    }
   
    protected NodoABB<E> sucesor(E e, NodoABB<E> actual) {
        NodoABB<E> res = null;
        if (actual != null) {
            int resC = actual.dato.compareTo(e);
            if (resC > 0) {
                res = sucesor(e, actual.izq);
                if (res == null) { res = actual; }
            } else {
                res = sucesor(e, actual.der);
            }
        }
        return res;
    }
}
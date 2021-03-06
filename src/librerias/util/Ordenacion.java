package librerias.util;

public class Ordenacion {    
    
    // QUICK SORT ------------------------------------------------------------
    public static <T extends Comparable<T>> void quickSort(T[]  a) {
        quickSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] a, int izq,
                                                            int der) {
        if (izq < der) {
            T pivot = mediana3(a, izq, der);
            int i = izq;
            int j = der - 1;
            for ( ; i < j;) {    
                while (pivot.compareTo(a[++i]) > 0) { ; }
                while (pivot.compareTo(a[--j]) < 0) { ; }
                intercambiar(a, i, j);
            }
            intercambiar(a, i, j);
            intercambiar(a, i, der - 1);
            quickSort(a, izq, i - 1);
            quickSort(a, i + 1, der);
        }
    }

 
    private static <T> void intercambiar(T[] a, int ind1, int ind2) {
        T tmp = a[ind1];    
        a[ind1] = a[ind2];
        a[ind2] = tmp;   
    }
 
    private static <T extends Comparable<T>> T mediana3(T[] a, int izq,
                                                        int der) {    
        int c = (izq + der) / 2;   
        if (a[c].compareTo(a[izq]) < 0)   { intercambiar(a, izq, c);   }
        if (a[der].compareTo(a[izq]) < 0) { intercambiar(a, izq, der); }
        if (a[der].compareTo(a[c]) < 0)   { intercambiar(a, c, der);   }
        intercambiar(a, c, der - 1);
        return a[der - 1];
    }


    // MERGE SORT --------------------------------------------      
    public static <T extends Comparable<T>> void mergeSort1(T[] v) {
        mergeSort1(v, 0, v.length - 1);
    }
    
    private static <T extends Comparable<T>> void mergeSort1(T[] v, 
                                                  int i, int f) {
        if (i < f) {
            int m = (i + f) / 2;
            mergeSort1(v, i, m);
            mergeSort1(v, m + 1, f);
            merge1(v, i, f, m);
        }
    }        
    
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge1(T[] v, int i, 
                                                         int f, int m) {
        int a = i, b = m + 1, k = 0;
        T[] res = (T[]) new Comparable[f - i + 1];
        while (a <= m && b <= f) {
            if (v[a].compareTo(v[b]) < 0) { res[k++] = v[a++]; }
            else { res[k++] = v[b++]; }
        }
        while (a <= m) { res[k++] = v[a++]; }
        while (b <= f) { res[k++] = v[b++]; }
        
        for (a = i, k = 0; a <= f; a++, k++) { v[a] = res[k]; }
    }   
    
    // VERSION 2
    public static <T extends Comparable<T>> void mergeSort2(T[] v) {
        T aux[] = mergeSort2(v, 0, v.length - 1);
        for(int k=0;k<aux.length;k++){
            v[k]=aux[k];
        }
    }
    
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] mergeSort2(T[] v, 
                                                  int i, int f) {
        if (i < f) {
            int m = (i + f) / 2;
            T a[] = mergeSort2(v, i, m);
            T b[] = mergeSort2(v, m + 1, f);
            return merge2(a, b);
        } 
        T res[] = ((T[]) new Comparable[1]);
        res[0]=v[i];
        return res;
    }
   
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] merge2(T[] v1, T[] v2) {
        int a = 0, b = 0, k = 0;
        T[] res = (T[]) new Comparable[v1.length+v2.length];
        while (a < v1.length && b < v2.length) {
            if (v1[a].compareTo(v2[b]) < 0) { res[k++] = v1[a++]; }
            else { res[k++] = v2[b++]; }
        }
        while (a < v1.length) { res[k++] = v1[a++]; }
        while (b < v2.length) { res[k++] = v2[b++]; }
        
        return res;
    }  
    
    public static <T extends Comparable<T>> boolean sonIguales(T[] a, T[] b) {
        boolean iguales = true;
        if (a.length != b.length) { iguales = false; }
        else {
            for (int i = 0; i < a.length && iguales; i++) {
                iguales = (a[i].compareTo(b[i]) == 0);
            }
        }    
        return iguales;
    }    
}

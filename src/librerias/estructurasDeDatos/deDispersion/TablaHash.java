package librerias.estructurasDeDatos.deDispersion;

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

public class TablaHash<C, V> implements Map<C, V> {

    public static final double FACTOR_CARGA = 0.75;
    
    protected ListaConPI<EntradaHash<C, V>>[] elArray;
    
    protected int talla; 
    
    @SuppressWarnings("unchecked") 
    public TablaHash(int inicial) {
        int capacidad = siguientePrimo((int) (inicial / FACTOR_CARGA));
        elArray = new LEGListaConPI[capacidad];
        for (int i = 0; i < elArray.length; i++) { 
            elArray[i] = new LEGListaConPI<EntradaHash<C, V>>();
        }
        talla = 0;
    }
    
    public static final int siguientePrimo(int n) {
        int nn = n;
        if (nn % 2 == 0) { nn++; }
        for ( ; !esPrimo(nn); nn += 2) { ; } 
        return nn;
    } 
    
    protected static final boolean esPrimo(int n) {
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) { return false; }
        }
        return true;
    } 
    
    protected int indiceHash(C c) {
        int indiceHash = c.hashCode() % this.elArray.length;
        if (indiceHash < 0) { indiceHash += this.elArray.length; }
        return indiceHash;
    }
    
    public V recuperar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = elArray[pos];
        V valor = null;        
        l.inicio();
        while (!l.esFin() && !l.recuperar().clave.equals(c)) { 
            l.siguiente(); 
        }
        if (!l.esFin()) {
            valor = l.recuperar().valor;
        }
        return valor;
    }
    
    public V eliminar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = elArray[pos];
        V valor = null;      
        l.inicio();
        while (!l.esFin() && !l.recuperar().clave.equals(c)) { 
            l.siguiente(); 
        }
        if (!l.esFin()) {
            valor = l.recuperar().valor;
            l.eliminar();
            talla--;
        }
        return valor;
    }
      
    public V insertar(C c, V v) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = elArray[pos];
        V antiguoValor = null;
        l.inicio();
        while (!l.esFin() && !l.recuperar().clave.equals(c)) { 
            l.siguiente(); 
        }
        if (l.esFin()) { 
            l.insertar(new EntradaHash<C, V>(c, v));
            talla++;
            if (factorCarga() > FACTOR_CARGA) {
                rehashing();
            }
        }
        else { 
            antiguoValor = l.recuperar().valor; l.recuperar().valor = v;
        }
        return antiguoValor;
    }
    public boolean esVacio() { return talla == 0; }
    
    public int talla() { return talla; } 
        
    public final double factorCarga() {
        return (double) talla/elArray.length;
    }
    
    @SuppressWarnings("unchecked")
    protected final void rehashing() {
        int capacidad = siguientePrimo(elArray.length*2);
        ListaConPI<EntradaHash<C, V>>[] aux = elArray;
        elArray = new LEGListaConPI[capacidad];
        talla = 0;
        for (int i = 0; i < elArray.length; i++) { 
            elArray[i] = new LEGListaConPI<EntradaHash<C, V>>();
        }
        for(int i=0;i<aux.length;i++){
            ListaConPI<EntradaHash<C, V>> l = aux[i];
            l.inicio();
            while(!l.esFin()){
                EntradaHash<C, V> e = (EntradaHash<C, V>) l.recuperar();
                this.insertar(e.clave, e.valor);
                l.siguiente();
            }
        }
    } 

    public ListaConPI<C> claves() {
        ListaConPI<C> res = new LEGListaConPI<C>();
        for(int k=0;k<elArray.length;k++){
            ListaConPI<EntradaHash<C, V>> lista = elArray[k];
            lista.inicio();
            while(!lista.esFin()){
                EntradaHash<C,V> e = (EntradaHash<C,V>) lista.recuperar();
                res.insertar(e.clave);
                lista.siguiente();
            }
        }
        return res;
        
    }
   
    public final double desviacionTipica() {
        double fc = this.factorCarga();
        double aux = 0;
        for(int k=0;k<elArray.length;k++){
            aux += Math.pow(elArray[k].talla()-fc, 2);
        }
        return Math.sqrt(aux/elArray.length);
        
    }
  
    public String histograma() {
        String res = "";
        int aux[] = new int[10];
        for(int k=0;k<10;k++) aux[k]=0;
        for(int k=0;k<elArray.length;k++){
            int i = elArray[k].talla();
            if(i>9) i=9;
            aux[i]++;
        }
        for(int k=0;k<10;k++) res += k+"\t"+aux[k]+"\n";
        return res;
        
    }
    
    public ListaConPI<C> clavesMaxColisiones(){
        ListaConPI<C> res = new LEGListaConPI<C>();
        if(this.elArray.length>0){
            int i=0, max=elArray[0].talla();
            for(int k=1;k<elArray.length;k++){
                int t = elArray[k].talla();
                if(t>max){
                    max=t;
                    i=k;
                }
            }
            ListaConPI<EntradaHash<C,V>> l = elArray[i];
            for(l.inicio();!l.esFin();l.siguiente()){
                EntradaHash<C,V> e = l.recuperar();
                res.insertar(e.clave);
            }
        }
        return res;
    }
}
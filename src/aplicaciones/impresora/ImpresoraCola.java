package aplicaciones.impresora;

import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.lineales.ArrayCola;

public class ImpresoraCola implements Impresora {
    private Cola<Documento> cola;
    
    public ImpresoraCola() {
        cola = new ArrayCola<Documento>();
    }
    
    public void guardarTrabajo(Documento doc) {
        cola.encolar(doc);
    }
    
    public boolean hayTrabajos() {
        return !cola.esVacia();
    }
   
    public Documento siguienteTrabajo() {
        return cola.primero();
    }
    
    public int imprimirSiguiente() {
        Documento doc = cola.desencolar();
        int tiempoImpresion = 60 * doc.getNumPaginas() / PAGINAS_POR_MINUTO;
        return tiempoImpresion;
    }
}

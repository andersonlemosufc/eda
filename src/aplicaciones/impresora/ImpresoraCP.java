package aplicaciones.impresora;

import librerias.estructurasDeDatos.jerarquicos.MonticuloBinarioMaxR0;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;

public class ImpresoraCP implements Impresora {
    private ColaPrioridad<Documento> cp;
    
    public ImpresoraCP() {
        this.cp = new MonticuloBinarioMaxR0<Documento>();
    }
    
    public void guardarTrabajo(Documento doc) {
        this.cp.insertar(doc);
    }
    
    public boolean hayTrabajos() {
        return !this.cp.esVacia();
    }
    
    public Documento siguienteTrabajo() {
        return this.cp.recuperarMin();
    }
    
    public int imprimirSiguiente() {
        Documento doc = this.cp.eliminarMin();
        int tiempoImpresion = 60 * doc.getNumPaginas() / PAGINAS_POR_MINUTO;
        return tiempoImpresion;
    }
}

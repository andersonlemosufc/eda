package aplicaciones.impresora;

public interface Impresora {

	int PAGINAS_POR_MINUTO = 30;
    
    void guardarTrabajo(Documento doc);
    
    boolean hayTrabajos();
    
    Documento siguienteTrabajo();
    
    int imprimirSiguiente();
}

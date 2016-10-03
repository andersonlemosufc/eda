package aplicaciones.impresora;


public class Documento implements Comparable<Documento>{
	
    private String titulo;
    private int numPaginas;
    private int envio;

    public Documento(String titulo, int numPaginas, int envio) {
        this.titulo = titulo;
        this.numPaginas = numPaginas;
        this.envio = envio;
    }

    public String getTitulo() { return titulo; }

    public int getNumPaginas() { return numPaginas; }

    public int getEnvio() { return envio; }

    public String toString() {
        return titulo + " (" + numPaginas + " pag.) Envio: " + envio;
    }
    
    @Override
    public int compareTo(Documento otro){
        if(otro==null) return 1;
        return otro.getNumPaginas()-this.numPaginas;
    }
}

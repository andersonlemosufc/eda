package aplicaciones.hospital;

public class Hospital {

    private Paciente[] camas;
    private int ingresados;
    private int altas;
    private int fallecidos;
    private int ocupacionMaxima;
    private int ocupadas;

    public Hospital(int capacidad) {
        camas = new Paciente[capacidad];
        ingresados = altas = fallecidos = ocupacionMaxima = ocupadas = 0;
    }
    
    public boolean hayCamasLibres() {        
        return (camas.length - ocupadas) > 0;
    }
    
    public void ingresarPaciente(Paciente p) {
        ingresados++;
        ocupadas++;
        if (ocupadas > ocupacionMaxima) { ocupacionMaxima = ocupadas; }
        for (int i = 0; i < camas.length; i++) {
            if (camas[i] == null || camas[i].getEstado() == Paciente.FALLECIDO)
            { camas[i] = p; break; }
        }
    }
    
    public void aplicarTratamiento(Paciente p) {   
        p.aplicarCura();
        if (p.getEstado() == Paciente.FALLECIDO 
            || p.getEstado() == Paciente.SANO) {
            if (p.getEstado() == Paciente.SANO) {
                altas++;
                for (int i = 0; i < camas.length; i++) {
                    if (camas[i] == p) { 
                        camas[i] = null; 
                        break; 
                    }
                }
            } 
	        else { fallecidos++; }
            ocupadas--;            
        }
    }

    public int pacientesIngresados() {
        return ingresados;
    }

    public int pacientesCurados() {
        return altas;
    }
    
    public int pacientesFallecidos() {
        return fallecidos;
    }
    
    public double saturacion() {
        return ocupacionMaxima / (double) camas.length;
    }
    
    public Paciente[] toArray() {        
        return camas;
    }
}

package aplicaciones.hospital;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.lineales.LPIColaPrioridad;

public class Urgencias {
    private ColaPrioridad<Paciente> cp;
    private int llegados;
    private int atendidos;
    private int tpoEspera;
    private Paciente[] sillas;
    
    public Urgencias(int capacidad) {
        cp = new LPIColaPrioridad<Paciente>(); 
        sillas = new Paciente[capacidad];
        llegados = atendidos = tpoEspera = 0;
    }
    
    public boolean hayPacientesEnEspera() {
        return !cp.esVacia();
    }
    
    public Paciente llamarAlPacienteMasGrave(long tiempo) {
        Paciente p = cp.eliminarMin();
        for (int i = 0; i < sillas.length; i++) {
            if (sillas[i] == p) { sillas[i] = null; break; }
        }
        atendidos++;
        tpoEspera += tiempo - p.getTpoLlegadaUrgencias();
        return p;
    }
    
    public Paciente pacienteMasGrave() {
        return cp.recuperarMin();
    }
    
    public void llegadaDePaciente(Paciente p) {
        for (int i = 0; i < sillas.length; i++) {
            if (sillas[i] == null) { sillas[i] = p; break; }
        }
        cp.insertar(p);
        llegados++;
    }
    
    public int pacientesLlegados() {
        return llegados;
    }
    
    public int pacientesAtendidos() {
        return atendidos;
    }
    
    public double tiempoMedioEspera() {
        return tpoEspera / (double) atendidos;
    }
    
    public Paciente[] toArray() {        
        return sillas;
    }
}
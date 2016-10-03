package aplicaciones.municipios;

import librerias.estructurasDeDatos.grafos.GrafoDirigido;
import librerias.estructurasDeDatos.grafos.Adyacente;
import java.util.ArrayList;
import java.util.Scanner;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.deDispersion.TablaHash;
import java.io.File;
import java.io.FileNotFoundException;


public class GestorGrafoMunicipios {
    
    public final static String FICH_MUNICIPIOS = "municipios.txt";
        
    private static final int NUM_MUNICIPIOS = 5000;
    
    private static final String FICH_DISTS = "distancias.txt";
    
    private static final String NO_ACC_MSG = "El fichero de municipios no es "
        + "accesible para lectura, compruebe su correcta ubicaci\u00f3n";
    private static final String NO_FOR_MSG = "Formato no v\u00e1lido en "
        + "la l\u00ednea: ";
    private static final String NO_FDIS_MSG = "Fichero de distancias "
        + "no encontrado";    
        
    private GrafoDirigido grafo;
   
    private Municipio[] munis;
    private Map<Municipio, Integer> dicMunis;

    public GestorGrafoMunicipios() {
        ArrayList<Municipio> munisAL = new ArrayList<Municipio>(NUM_MUNICIPIOS);
        try {         
            Scanner fent = new Scanner(new File(FICH_MUNICIPIOS), "UTF-8"); 
            while (fent.hasNext()) {
                String linea = fent.nextLine();
                String[] lA = linea.split(";");
                Municipio m = new Municipio(lA[0], Integer.parseInt(lA[1]),
                    Double.parseDouble(lA[2]), Integer.parseInt(lA[3]),
                    Integer.parseInt(lA[4]));
                munisAL.add(m);
            }
            fent.close();
            munis = munisAL.toArray(new Municipio[munisAL.size()]);
            dicMunis = new TablaHash<Municipio, Integer>(munis.length);
            for (int i = 0; i < munis.length; i++) {
                dicMunis.insertar(munis[i], i);
            }
            grafo = new GrafoDirigido(munis.length);
            cargarAristas();
        } catch (java.io.IOException eChecked) {
        	eChecked.printStackTrace();
            System.out.println(NO_ACC_MSG);
        }
    }

    @SuppressWarnings("resource")
	private void cargarAristas() {        
        try {
            Scanner f = new Scanner(new File(FICH_DISTS), "UTF-8");
            while (f.hasNext()) {
                String linea = f.nextLine();
                String[] datosCarretera = linea.split(";");
                if (datosCarretera.length != 3) {
                    System.out.println(NO_FOR_MSG  + linea);
                    break;
                }
                String orig = datosCarretera[0].trim().toLowerCase();
                String dst = datosCarretera[1].trim().toLowerCase();
                double distancia = Double.parseDouble(datosCarretera[2]);
                int origen = obtenerCodigo(new Municipio(orig)),
                    destino = obtenerCodigo(new Municipio(dst));
                grafo.insertarArista(origen, destino, distancia);
                grafo.insertarArista(destino, origen, distancia);
            }
        } catch (FileNotFoundException e) {
            System.err.println(NO_FDIS_MSG);
        }
    }
    
    public int obtenerCodigo(Municipio etiqueta) {
        Integer codigo = dicMunis.recuperar(etiqueta); 
        if (codigo == null) { return -1; }
        return codigo.intValue();
    }

    public int numMunicipios() { return grafo.numVertices(); }
    
    public Municipio getMunicipio(int indice) { return munis[indice]; }
    
    public int numAristas() { return grafo.numAristas(); }

    public ListaConPI<Adyacente> adyacentesDe(int indice) {
        return grafo.adyacentesDe(indice);
    }
    
    public boolean existeMunicipio(Municipio m) { 
        return obtenerCodigo(m) != -1; 
    }

    public double distancia(Municipio m1, Municipio m2) {
        return grafo.pesoArista(obtenerCodigo(m1), obtenerCodigo(m2));
    }

    public ListaConPI<Municipio> caminoMinimo(Municipio mOrig, Municipio mDst) {
        ListaConPI<Municipio> l = new LEGListaConPI<Municipio>();
        ListaConPI<Integer> i = grafo.caminoMinimo(obtenerCodigo(mOrig), obtenerCodigo(mDst));
        for(i.inicio();!i.esFin();i.siguiente())l.insertar(munis[i.recuperar()]);
        return l;
    }
}

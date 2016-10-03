package aplicaciones.letras;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import librerias.estructurasDeDatos.deDispersion.TablaHash;

public class EvaluaFuncionDispersion {
    public final static int NUM_IMGS = 22000;
    public final static String NOM_DIR = "res";
    public final static String NOM_FICH = NOM_DIR + File.separatorChar + "histo"; 
    public final static String EXT = ".txt";
  
    public static void main(String[] args) {
        int fdis = 0;
        try {
            File dir = new File(NOM_DIR); dir.mkdir();
            TablaHash<Imagen,String> t;
            PrintWriter pw;
            while(fdis<4){
                t = cargarImagenes(fdis);
                System.out.println("Funcion de dispersion "+Imagen.NOMFDIS[fdis]);
                System.out.println("\tFactor de carga: "+t.factorCarga());
                System.out.println("\tDesviacion Tipica: "+t.desviacionTipica());
                pw = new PrintWriter(new File(NOM_FICH + Imagen.NOMFDIS[fdis] + EXT));
                pw.write(t.histograma());
                pw.close();
                fdis++;
            }
        } catch (FileNotFoundException e) { 
            System.err.println("Problemas con el fichero..." 
                + NOM_FICH + fdis + EXT); 
        } catch (IOException e) {
            System.err.println("No se encontro el fichero: " + Imagen.NOM_URL);
        }
    }
   
    private static TablaHash<Imagen, String> cargarImagenes(int fdis) 
        throws IOException {
        TablaHash<Imagen, String> tab;
        tab = new TablaHash<Imagen, String>(NUM_IMGS);
        URL url = new URL(Imagen.NOM_URL);
        URLConnection con = url.openConnection();
        DataInputStream is = new DataInputStream(con.getInputStream());
        for (int i = 0; i < NUM_IMGS; i++) {
            Imagen img = new Imagen(is, 11, 13, fdis);
            tab.insertar(img, "");
        }
        return tab;
    }
   
}
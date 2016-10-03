package aplicaciones.editorPredictivo;

import librerias.estructurasDeDatos.jerarquicos.ABB;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;

public class EditorPredictivo extends ABB<String> {

    public EditorPredictivo() {
        super();
    }

    public EditorPredictivo(String nombreFichero) {   
        super();
        try {         
            Scanner fPalabras = new Scanner(new File(nombreFichero), "UTF-8"); 
            int talla = fPalabras.nextInt(); fPalabras.nextLine();
            String[] palabras = new String[talla];
            int nLinea = 0;
            while (fPalabras.hasNext() && nLinea < talla) {
                palabras[nLinea] = fPalabras.nextLine().toLowerCase().trim(); 
                nLinea++;
            }
            fPalabras.close();
            
            this.raiz = construirEquilibrado(palabras, 0, talla-1);
            
        } catch (FileNotFoundException eChecked) {
            System.out.println("El fichero " + nombreFichero 
                + " no es accesible para lectura, comprueba "
                + "su correcta ubicaci\u00f3n");
        }
    }

    public void incluir(String nueva) { 
        this.insertar(nueva.toLowerCase().trim()); 
    }

    public void guardar(String nombreFichero) {
        try { 
            PrintWriter fPalabras = new PrintWriter(nombreFichero, "UTF-8");
            String[] palabras = this.toArrayInOrden();
            fPalabras.println(palabras.length);
            for (int i = 0; i < palabras.length; i++) {
                fPalabras.println((String) palabras[i]);
            }
            fPalabras.close();
        } catch (IOException eChecked) {
            System.out.println("Error guardando el fichero " + nombreFichero 
                + ": " + eChecked);
        }
    }

    public ListaConPI<String> recuperarSucesores(String prefijo, int n) {
        
        ListaConPI<String> res = new LEGListaConPI<String>();
        res.inicio();
        
        String s = this.recuperar(prefijo);
        int k=0;
        if(s!=null) {
            res.insertar(s);
            k++;
        }
        else s = prefijo;
        
        while(k<n && s!=null){
            s = sucesor(s);
            if(s!=null && (s.indexOf(prefijo)==0)) {
                res.insertar(s);
            }
            k++;
        }
        
        return res;
        
    }
}

package prueba1;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import librerias.estructurasDeDatos.deDispersion.TablaHash;
import librerias.estructurasDeDatos.modelos.ListaConPI;

@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class T2Ejercicio
extends CorrectorEx {

	private static final long serialVersionUID = 1L;
	
	private static String enunciado = "<head><meta charset=\"UTF-8\"></head><body><font size=\"5\"><b><u>EJERCICIO</u></b><br>Dise&ntilde;a un m&eacute;todo en la clase <b><i>TablaHash</i></b> que  devuelva una ListaConPI con las claves de la (primera) cubeta de longitud m&aacute;xima de una Tabla Hash.<br><br><code>public ListaConPI&lt;C&gt; clavesMaxColisiones() { // Completar }</code><br></font></body>";
    private static final double MAX_NOTA = 7.0;
    private static final double MIN_NOTA = 0.0;
    private static final double REG_NOTA = 3.5;

    public T2Ejercicio() throws IOException {
        super("eda", "prueba1", 2, 1, enunciado, "clavesMaxColisiones", "TablaHash", 7.0, false, "Primer parcial de laboratorio", CAS);
        this.upFile = System.getProperty("user.home") + File.separator + this.idAsig + File.separator + "librerias" + File.separator + "estructurasDeDatos" + File.separator + "deDispersion" + File.separator + this.clase + "." + this.extFile;
        this.clase = "librerias.estructurasDeDatos.deDispersion.TablaHash";
        if (this.verb) {
            System.out.println("... abriendo fichero..." + this.upFile);
        }
    }

    public static void main() throws IOException {
        T2Ejercicio examen = new T2Ejercicio();
    }

    @Override
    public double compruebaCasos(Method m) throws Exception {
        double nota;
        block16 : {
            String[] nombres = new String[]{"Oscar", "Pepe", "Luis", "Rodolfo", "Ataulfo", "Raul", "Manu", "Samuel", "Jordi", "Marcos"};
            String[] notas = new String[]{new String("ocho"), "siete", "tres", "seis", new String("ocho"), "tres", "nueve", "cinco", new String("ocho"), "seis"};
            Integer[] numeros = new Integer[]{8, 7, 65, 70, 47, 24, 5, 69, 46, 23};
            String mensaje = "";
            nota = 0.0;
            try {
                TablaHash m1 = new TablaHash(3);
                TablaHash m2 = new TablaHash(5);
                for (int i = 0; i < nombres.length; ++i) {
                    m1.insertar((Object)numeros[i], (Object)notas[i]);
                    m2.insertar((Object)nombres[i], (Object)notas[i]);
                }
                ListaConPI res1 = (ListaConPI)m.invoke((Object)m1, new Object[0]);
                ListaConPI res2 = (ListaConPI)m.invoke((Object)m2, new Object[0]);
                int correctos1 = 0;
                int correctos2 = 0;
                if (T2Ejercicio.contiene(res1, numeros[9])) {
                    ++correctos1;
                }
                if (T2Ejercicio.contiene(res1, numeros[8])) {
                    ++correctos1;
                }
                if (T2Ejercicio.contiene(res1, numeros[7])) {
                    ++correctos1;
                }
                if (T2Ejercicio.contiene(res2, nombres[2])) {
                    ++correctos2;
                }
                if (T2Ejercicio.contiene(res2, nombres[4])) {
                    ++correctos2;
                }
                if (correctos1 != 3 || res1.talla() != 3 || correctos2 != 2 || res2.talla() != 2) {
                    if (correctos2 > 0 && correctos1 > 0) {
                        nota = 3.5;
                        if (this.verb) {
                            mensaje = "No incluyes en la lista todas las claves de la cubeta de longitud m\u00e1xima";
                            System.out.println(mensaje + " " + nota);
                        }
                    } else {
                        nota = 0.0;
                        if (this.verb) {
                            mensaje = "La cubeta que usas para obtener la lista de claves resultado NO es la (primera) de longitud m\u00e1xima";
                            System.out.println(mensaje + " " + nota);
                        }
                    }
                } else {
                    nota = 7.0;
                }
            }
            catch (InvocationTargetException e) {
                if (this.verb) {
                    mensaje = "** Excepci\u00f3n en el m\u00e9todo: " + e.getCause();
                    System.out.println(mensaje);
                }
            }
            catch (Exception e) {
                nota = 0.0;
                if (!this.verb) break block16;
                mensaje = "** Excepci\u00f3n en el m\u00e9todo: " + e.getCause();
                System.out.println(mensaje);
            }
        }
        return nota;
    }

    private static <E> boolean contiene(ListaConPI<E> lpi, E nombre) {
        lpi.inicio();
        while (!lpi.esFin()) {
            if (lpi.recuperar().equals(nombre)) {
                return true;
            }
            lpi.siguiente();
        }
        return false;
    }
}
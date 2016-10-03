package librerias.estructurasDeDatos.lineales;

import java.lang.reflect.Field;

@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class PruebaLEGLPI {
    Field priPub;
    Field antPub;
    Field ultPub;
    Field tallaPub;
    int errores = 0;
    int tests = 0;

    public static void main(String[] args) {
        new PruebaLEGLPI();
    }

    private void assertNotNull(String tit, String msg, Object y) {
        ++this.tests;
        if (y == null) {
            System.out.printf("%15s ERRONEO:  %s\n", tit, msg);
            ++this.errores;
        }
    }

    private void assertEquals(String tit, String msg, Object x, Object y) {
        ++this.tests;
        if (x == null && x != y || x != null && !x.equals(y)) {
            System.out.printf("%15s ERRONEO:  %s\n", tit, msg);
            ++this.errores;
        }
    }

    private void assertEquals(String tit, String msg, int x, int y) {
        ++this.tests;
        if (x != y) {
            System.out.printf("%15s ERRONEO:  %s\n", tit, msg);
            ++this.errores;
        }
    }

    private PruebaLEGLPI() {
        try {
            this.priPub = LEGListaConPI.class.getDeclaredField("pri");
            this.antPub = LEGListaConPI.class.getDeclaredField("ant");
            this.ultPub = LEGListaConPI.class.getDeclaredField("ult");
            this.tallaPub = LEGListaConPI.class.getDeclaredField("talla");
            this.priPub.setAccessible(true);
            this.antPub.setAccessible(true);
            this.ultPub.setAccessible(true);
            this.tallaPub.setAccessible(true);
            System.out.println("TEST DE LA CLASE LEGListaConPI<E>");
            this.testConstructor();
            this.testInicio();
            this.testSiguiente();
            this.testEsVacia();
            this.tesEsFin();
            this.testInsertar();
            this.testEliminar();
            this.testRecuperar();
            System.out.println("Realizados " + this.tests + " tests de los cuales han fallado " + this.errores + ".");
        }
        catch (NoSuchFieldException e) {
            System.out.println(e.getMessage());
        }
    }

	private void testConstructor() {
        int erroresAux = this.errores;
        String tit = "constructor";
        String at1 = "Comprueba que el atributo";
        String at2 = "est\u00e1 inicializado al nodo ficticio.";
        String m1 = "Comprueba que el objeto que creas no es null";
        String m2 = at1 + " pri " + at2;
        String m3 = at1 + " ant " + at2;
        String m4 = at1 + " ult " + at2;
        String m5 = at1 + "talla est\u00e1 inicializado a 0";
        try {
            LEGListaConPI l = new LEGListaConPI();
            NodoLEG pri = (NodoLEG)this.priPub.get((Object)l);
            NodoLEG ant = (NodoLEG)this.antPub.get((Object)l);
            NodoLEG ult = (NodoLEG)this.ultPub.get((Object)l);
            int talla = (Integer)this.tallaPub.get((Object)l);
            this.assertNotNull(tit, m1, (Object)l);
            this.assertNotNull(tit, m2, (Object)pri);
            this.assertEquals(tit, m3, (Object)pri, (Object)ant);
            this.assertEquals(tit, m4, (Object)pri, (Object)ult);
            this.assertEquals(tit, m5, 0, talla);
            this.assertEquals(tit, "Dato del Nodo Ficticio debe ser null: ", null, pri.dato);
            this.assertEquals(tit, "Siguiente del Nodo Ficticio debe ser null: ", null, (Object)pri.siguiente);
            if (this.errores == erroresAux) {
                System.out.printf("%15s CORRECTO.\n", tit);
            }
        }
        catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    
	private void testInicio() {
        NodoLEG pri;
        int erroresAux = this.errores;
        String tit = "inicio()";
        String n0 = "num0";
        String n1 = "num1";
        String n2 = "num2";
        String n3 = "num3";
        NodoLEG ant = pri = new NodoLEG((Object)null);
        pri.siguiente = new NodoLEG((Object)n0);
        pri.siguiente.siguiente = new NodoLEG((Object)n1);
        NodoLEG ult = pri.siguiente.siguiente.siguiente = new NodoLEG((Object)n2);
        int talla = 3;
        try {
            LEGListaConPI l = new LEGListaConPI();
            this.priPub.set((Object)l, (Object)pri);
            this.antPub.set((Object)l, (Object)ant);
            this.ultPub.set((Object)l, (Object)ult);
            this.tallaPub.set((Object)l, 3);
            l.inicio();
            ant = (NodoLEG)this.antPub.get((Object)l);
            ult = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "pri debe ser igual que ant", (Object)pri, (Object)ant);
            l.inicio();
            ant = (NodoLEG)this.antPub.get((Object)l);
            ult = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "pri debe ser igual que ant", (Object)pri, (Object)ant);
            if (this.errores == erroresAux) {
                System.out.printf("%15s CORRECTO.\n", tit);
            }
        }
        catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private void testSiguiente() {
        NodoLEG pri;
        int erroresAux = this.errores;
        String tit = "siguiente()";
        String n0 = "num0";
        String n1 = "num1";
        String n2 = "num2";
        NodoLEG ant = pri = new NodoLEG((Object)null);
        pri.siguiente = new NodoLEG((Object)n0);
        pri.siguiente.siguiente = new NodoLEG((Object)n1);
        NodoLEG ult = pri.siguiente.siguiente.siguiente = new NodoLEG((Object)n2);
        int talla = 3;
        try {
            LEGListaConPI l = new LEGListaConPI();
            this.priPub.set((Object)l, (Object)pri);
            this.antPub.set((Object)l, (Object)ant);
            this.ultPub.set((Object)l, (Object)ult);
            this.tallaPub.set((Object)l, 3);
            l.siguiente();
            ant = (NodoLEG)this.antPub.get((Object)l);
            ult = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "falla cuando esta al inicio", (Object)pri.siguiente, (Object)ant);
            l.siguiente();
            ant = (NodoLEG)this.antPub.get((Object)l);
            ult = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "falla cuando esta al medio", (Object)pri.siguiente.siguiente, (Object)ant);
            l.siguiente();
            ant = (NodoLEG)this.antPub.get((Object)l);
            ult = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "falla cuando esta al final", (Object)ult, (Object)ant);
            if (this.errores == erroresAux) {
                System.out.printf("%15s CORRECTO.\n", tit);
            }
        }
        catch (IllegalAccessException e) {
            // empty catch block
        }
    }

    private void testEsVacia() {
        NodoLEG pri;
        int erroresAux = this.errores;
        String tit = "esVacia()";
        String n0 = "num0";
        String n1 = "num1";
        String n2 = "num2";
        String n3 = "num3";
        NodoLEG ant = pri = new NodoLEG((Object)null);
        pri.siguiente = new NodoLEG((Object)n0);
        pri.siguiente.siguiente = new NodoLEG((Object)n1);
        NodoLEG ult = pri.siguiente.siguiente.siguiente = new NodoLEG((Object)n2);
        int talla = 3;
        try {
            LEGListaConPI l = new LEGListaConPI();
            this.priPub.set((Object)l, (Object)pri);
            this.antPub.set((Object)l, (Object)ant);
            this.ultPub.set((Object)l, (Object)ult);
            this.tallaPub.set((Object)l, 3);
            this.assertEquals(tit, "devuelve true con 3 elementos y el PI al principio", false, l.esVacia());
            this.antPub.set((Object)l, (Object)ant.siguiente);
            this.assertEquals(tit, "devuelve true con 3 elementos y el PI en medio", false, l.esVacia());
            this.antPub.set((Object)l, (Object)ant.siguiente.siguiente);
            this.assertEquals(tit, "devuelve true con 3 elementos y el PI al final", false, l.esVacia());
            ant = pri = new NodoLEG((Object)null);
            ult = pri;
            this.priPub.set((Object)l, (Object)pri);
            this.antPub.set((Object)l, (Object)ant);
            this.ultPub.set((Object)l, (Object)ult);
            this.tallaPub.set((Object)l, 0);
            this.assertEquals(tit, "devuelve false con 0 elementos y solamente el nodo ficticio", true, l.esVacia());
            if (this.errores == erroresAux) {
                System.out.printf("%15s CORRECTO.\n", tit);
            }
        }
        catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private void tesEsFin() {
        NodoLEG pri;
        int erroresAux = this.errores;
        String tit = "esFin()";
        String n0 = "num0";
        String n1 = "num1";
        String n2 = "num2";
        NodoLEG ant = pri = new NodoLEG((Object)null);
        pri.siguiente = new NodoLEG((Object)n0);
        pri.siguiente.siguiente = new NodoLEG((Object)n1);
        NodoLEG ult = pri.siguiente.siguiente.siguiente = new NodoLEG((Object)n2);
        int talla = 3;
        try {
            LEGListaConPI l = new LEGListaConPI();
            this.priPub.set((Object)l, (Object)pri);
            this.antPub.set((Object)l, (Object)ant);
            this.ultPub.set((Object)l, (Object)ult);
            this.tallaPub.set((Object)l, 3);
            this.assertEquals(tit, "devuelve true con 3 elementos y el PI al principio", false, l.esFin());
            this.antPub.set((Object)l, (Object)ant.siguiente);
            this.assertEquals(tit, "devuelve true con 3 elementos y el PI en medio", false, l.esFin());
            this.antPub.set((Object)l, (Object)ant.siguiente.siguiente.siguiente);
            this.assertEquals(tit, "devuelve true con 3 elementos y el PI al final", true, l.esFin());
            ant = pri = new NodoLEG((Object)null);
            ult = pri;
            this.priPub.set((Object)l, (Object)pri);
            this.antPub.set((Object)l, (Object)ant);
            this.ultPub.set((Object)l, (Object)ult);
            this.tallaPub.set((Object)l, 0);
            this.assertEquals(tit, "esFin() evuelve false con 0 elementos y solamente el nodo ficticio", true, l.esFin());
            if (this.errores == erroresAux) {
                System.out.printf("%15s CORRECTO.\n", tit);
            }
        }
        catch (IllegalAccessException e) {
            // empty catch block
        }
    }

    private void testInsertar() {
        int erroresAux = this.errores;
        String tit = "insertar()";
        String n0 = "num0";
        String n1 = "num1";
        String n2 = "num2";
        String n3 = "num3";
        try {
            LEGListaConPI l = new LEGListaConPI();
            l.insertar((Object)"num1");
            NodoLEG pri = (NodoLEG)this.priPub.get((Object)l);
            NodoLEG ant = (NodoLEG)this.antPub.get((Object)l);
            NodoLEG ult = (NodoLEG)this.ultPub.get((Object)l);
            int talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "El nodo ficticio contiene un dato", null, pri.dato);
            this.assertNotNull(tit, "Lista vac\u00eda: No se ha insertado el primer elemento", (Object)pri.siguiente);
            this.assertEquals(tit, "El PI ha cambiado al insertar en cabeza", (Object)pri.siguiente, (Object)ant);
            this.assertEquals(tit, "El valor insertado no es el esperado", "num1", ant.dato);
            this.assertEquals(tit, "ult no se ha actualizado correctamente", (Object)pri.siguiente, (Object)ult);
            this.assertEquals(tit, "talla no se ha actualizado correctamente", 1, talla);
            l.insertar((Object)"num3");
            pri = (NodoLEG)this.priPub.get((Object)l);
            ant = (NodoLEG)this.antPub.get((Object)l);
            ult = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "El nodo ficticio contiene un dato", null, pri.dato);
            this.assertEquals(tit, "El PI ha cambiado al insertar al final", (Object)pri.siguiente.siguiente, (Object)ant);
            this.assertEquals(tit, "ult no se ha actualizado correctamente", (Object)pri.siguiente.siguiente, (Object)ult);
            this.assertEquals(tit, "talla no se ha actualizado correctamente", 2, talla);
            this.assertEquals(tit, "El valor insertado no es el esperado", "num3", ant.dato);
            this.antPub.set((Object)l, (Object)pri);
            l.insertar((Object)"num0");
            pri = (NodoLEG)this.priPub.get((Object)l);
            ant = (NodoLEG)this.antPub.get((Object)l);
            ult = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "El nodo ficticio contiene un dato", null, pri.dato);
            this.assertEquals(tit, "El PI ha cambiado al insertar en cabeza", (Object)pri.siguiente, (Object)ant);
            this.assertEquals(tit, "El valor insertado no es el esperado", "num0", ant.dato);
            this.assertEquals(tit, "ult ha cambiado insertando en cabeza", (Object)pri.siguiente.siguiente.siguiente, (Object)ult);
            this.assertEquals(tit, "talla no se ha actualizado correctamente", 3, talla);
            this.antPub.set((Object)l, (Object)pri.siguiente);
            l.insertar((Object)"num2");
            pri = (NodoLEG)this.priPub.get((Object)l);
            ant = (NodoLEG)this.antPub.get((Object)l);
            ult = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "El nodo ficticio contiene un dato", null, pri.dato);
            this.assertEquals(tit, "El punto de inter\u00e9s ha cambiado al insertar en medio", (Object)pri.siguiente.siguiente, (Object)ant);
            this.assertEquals(tit, "El valor insertado no es el esperado", "num2", ant.dato);
            this.assertEquals(tit, "ult ha cambiado insertando en cabeza", (Object)pri.siguiente.siguiente.siguiente.siguiente, (Object)ult);
            this.assertEquals(tit, "talla no se ha actualizado correctamente", 4, talla);
            if (this.errores == erroresAux) {
                System.out.printf("%15s CORRECTO.\n", tit);
            }
        }
        catch (IllegalAccessException e) {
            // empty catch block
        }
    }

    private void testEliminar() {
        NodoLEG pri;
        int erroresAux = this.errores;
        String tit = "eliminar()";
        String n0 = "num0";
        String n1 = "num1";
        String n2 = "num2";
        String n3 = "num3";
        NodoLEG ant = pri = new NodoLEG((Object)null);
        pri.siguiente = new NodoLEG((Object)n0);
        pri.siguiente.siguiente = new NodoLEG((Object)n1);
        pri.siguiente.siguiente.siguiente = new NodoLEG((Object)n2);
        NodoLEG ult = pri.siguiente.siguiente.siguiente.siguiente = new NodoLEG((Object)n3);
        int talla = 4;
        try {
            LEGListaConPI l = new LEGListaConPI();
            this.priPub.set((Object)l, (Object)pri);
            this.antPub.set((Object)l, (Object)ant);
            this.ultPub.set((Object)l, (Object)ult);
            this.tallaPub.set((Object)l, 4);
            l.eliminar();
            NodoLEG auxp = (NodoLEG)this.priPub.get((Object)l);
            NodoLEG auxa = (NodoLEG)this.antPub.get((Object)l);
            NodoLEG auxu = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del nodo ficticio al eliminar el primero", (Object)pri, (Object)auxp);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del PI al eliminar el primero", (Object)ant, (Object)auxa);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del ultimo al eliminar el primero", (Object)ult, (Object)auxu);
            this.assertEquals(tit, "eliminar() no modifica el atributo talla", 3, talla);
            ant = ant.siguiente;
            this.antPub.set((Object)l, (Object)ant);
            l.eliminar();
            auxp = (NodoLEG)this.priPub.get((Object)l);
            auxa = (NodoLEG)this.antPub.get((Object)l);
            auxu = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del nodo ficticio al eliminar un intermedio", (Object)pri, (Object)auxp);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del PI al eliminar un intermedio", (Object)ant, (Object)auxa);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del ultimo al eliminar un intermedio", (Object)ult, (Object)auxu);
            this.assertEquals(tit, "eliminar() no modifica el atributo talla", 2, talla);
            ult = ant;
            l.eliminar();
            auxp = (NodoLEG)this.priPub.get((Object)l);
            auxa = (NodoLEG)this.antPub.get((Object)l);
            auxu = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del nodo ficticio al eliminar el ultimo", (Object)pri, (Object)auxp);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del PI al eliminar el ultimo", (Object)ant, (Object)auxa);
            this.assertEquals(tit, "eliminar() no modifica la posici\u00f3n del ultimo al eliminar el ultimo", (Object)ult, (Object)auxu);
            this.assertEquals(tit, "eliminar() no modifica el atributo talla", 1, talla);
            ant = ult = pri;
            this.antPub.set((Object)l, (Object)pri);
            l.eliminar();
            auxp = (NodoLEG)this.priPub.get((Object)l);
            auxa = (NodoLEG)this.antPub.get((Object)l);
            auxu = (NodoLEG)this.ultPub.get((Object)l);
            talla = (Integer)this.tallaPub.get((Object)l);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del nodo ficticio al eliminar el unico", (Object)pri, (Object)auxp);
            this.assertEquals(tit, "eliminar() modifica la posici\u00f3n del PI al eliminar el unico", (Object)pri, (Object)auxa);
            this.assertEquals(tit, "eliminar() no modifica la posici\u00f3n del ultimo al eliminar el unico", (Object)pri, (Object)auxu);
            this.assertEquals(tit, "eliminar() no modifica el atributo talla", 0, talla);
            if (this.errores == erroresAux) {
                System.out.printf("%15s CORRECTO.\n", tit);
            }
        }
        catch (IllegalAccessException e) {
            // empty catch block
        }
    }

    private void testRecuperar() {
        NodoLEG pri;
        int erroresAux = this.errores;
        String tit = "recuperar()";
        String n0 = "num0";
        String n1 = "num1";
        String n2 = "num2";
        NodoLEG ant = pri = new NodoLEG((Object)null);
        pri.siguiente = new NodoLEG((Object)n0);
        pri.siguiente.siguiente = new NodoLEG((Object)n1);
        NodoLEG ult = pri.siguiente.siguiente.siguiente = new NodoLEG((Object)n2);
        int talla = 3;
        try {
            LEGListaConPI l = new LEGListaConPI();
            this.priPub.set((Object)l, (Object)pri);
            this.antPub.set((Object)l, (Object)ant);
            this.ultPub.set((Object)l, (Object)ult);
            this.tallaPub.set((Object)l, 3);
            this.assertEquals(tit, "recuperar() no funciona con el PI al principio", "num0", l.recuperar());
            this.antPub.set((Object)l, (Object)ant.siguiente);
            this.assertEquals(tit, "recuperar() no funciona con el PI en medio", "num1", l.recuperar());
            this.antPub.set((Object)l, (Object)ant.siguiente.siguiente);
            this.assertEquals(tit, "recuperar() no funciona con el PI en el ultimo", "num2", l.recuperar());
            if (this.errores == erroresAux) {
                System.out.printf("%15s CORRECTO.\n", tit);
            }
        }
        catch (IllegalAccessException e) {
            // empty catch block
        }
    }
}
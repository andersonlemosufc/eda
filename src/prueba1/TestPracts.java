package prueba1;

import aplicaciones.hospital.Paciente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import librerias.estructurasDeDatos.jerarquicos.ABB;
import librerias.util.Ordenacion;

@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class TestPracts {
    private static String boundary;
    private static HttpURLConnection httpConn;
    private static OutputStream outputStream;
    private static PrintWriter writer;
    private static final String LINE_FEED = "\r\n";
    private static String charset;
    private static final String CAP = "Primera prueba de laboratorio de EDA. Curso 2015-16.";
    private static final String LIN = "========================================================";
    private static final String NOTA1 = "NOTA: De existir una ejecuci\u00f3n infinita en tu c\u00f3digo el ";
    private static final String NOTA2 = "test de esa pr\u00e1ctica no acabar\u00e1. En ese caso ejecuta los";
    private static final String NOTA3 = "tests individualmente.";
    private static final String ENTREGA = "Test entregado.";
    private static final int turno = 0;
    private static final int[] EJER_PRACT;
    private static String server;
    private static String alumno;
    private static String pc;

    private TestPracts() {
    }

    private static void datosAlum() {
        alumno = System.getProperty("user.name");
        try {
            InetAddress localMachine = InetAddress.getLocalHost();
            pc = localMachine.getHostName();
        }
        catch (Exception e) {
            pc = "";
        }
    }

    public static void main(String[] args) throws IOException {
        TestPracts.datosAlum();
        System.out.println("========================================================");
        System.out.println("Primera prueba de laboratorio de EDA. Curso 2015-16.");
        System.out.println("ALUMNO: " + alumno + "\t" + "en el PC: " + pc);
        System.out.println("========================================================");
        System.out.println("NOTA: De existir una ejecuci\u00f3n infinita en tu c\u00f3digo el \ntest de esa pr\u00e1ctica no acabar\u00e1. En ese caso ejecuta los\ntests individualmente.");
        System.out.println("========================================================");
        int numEjer = EJER_PRACT.length;
        for (int i = 1; i <= numEjer; ++i) {
            TestPracts.ejer(alumno, pc, i);
        }
    }

    public static void test1() {
        TestPracts.datosAlum();
        TestPracts.ejer(alumno, pc, 1);
    }

    public static void test2() {
        TestPracts.datosAlum();
        TestPracts.ejer(alumno, pc, 2);
    }

    public static void test3() {
        TestPracts.datosAlum();
        TestPracts.ejer(alumno, pc, 3);
    }

    public static void test4() {
        TestPracts.datosAlum();
        TestPracts.ejer(alumno, pc, 4);
    }

    private static void ejer(String alumno, String pc, int ejer) {
        System.out.print("---> Prueba " + ejer + " (pract " + EJER_PRACT[ejer - 1] + "). ");
        double nota = 0.0;
        try {
            switch (ejer) {
                case 1: {
                    nota = TestPracts.testCompareToPaciente();
                    break;
                }
                case 2: {
                    nota = TestPracts.testMerge2();
                    break;
                }
                case 3: {
                    nota = TestPracts.testMergeSort2();
                    break;
                }
                case 4: {
                    nota = TestPracts.testRecEquilibrado();
                    break;
                }
            }
            TestPracts.doPost(nota, alumno, pc, ejer);
            System.out.println("Test entregado.");
        }
        catch (NoClassDefFoundError e) {
            System.out.println("EXCEPCION: " + e);
        }
        catch (Exception e) {
            System.out.println("EXCEPCION: " + e);
        }
        catch (Error e) {
            System.out.println("ERROR: " + e);
        }
    }

    private static double testCompareToPaciente() throws Exception {
        double MAX_NOTA = 1.0;
        double MIN_NOTA = 0.0;
        Paciente a = new Paciente("A", 35, 2);
        Paciente b = new Paciente("B", 35, 2);
        Paciente c = new Paciente("C", 35, 1);
        Paciente d = new Paciente("D", 34, 2);
        Paciente e = new Paciente("E", 34, 1);
        Paciente f = new Paciente("F", 36, 3);
        boolean res = a.compareTo(b) == 0 && a.compareTo(c) > 0 && c.compareTo(a) < 0 && a.compareTo(d) == 0 && a.compareTo(e) > 0 && a.compareTo(f) < 0 && d.compareTo(a) == 0 && f.compareTo(a) > 0 && e.compareTo(a) < 0;
        a = new Paciente("A", 5, 2);
        b = new Paciente("B", 5, 2);
        c = new Paciente("C", 5, 1);
        d = new Paciente("D", 4, 2);
        e = new Paciente("E", 4, 1);
        f = new Paciente("F", 6, 3);
        res &= a.compareTo(b) == 0 && a.compareTo(c) > 0 && c.compareTo(a) < 0 && a.compareTo(d) > 0 && a.compareTo(e) > 0 && a.compareTo(f) < 0 && d.compareTo(a) < 0 && f.compareTo(a) > 0 && e.compareTo(a) < 0;
        a = new Paciente("A", 85, 2);
        b = new Paciente("B", 85, 2);
        c = new Paciente("C", 85, 1);
        d = new Paciente("D", 84, 2);
        e = new Paciente("E", 84, 1);
        f = new Paciente("F", 86, 3);
        res &= a.compareTo(b) == 0 && a.compareTo(c) > 0 && c.compareTo(a) < 0 && a.compareTo(d) < 0 && a.compareTo(e) > 0 && a.compareTo(f) < 0 && d.compareTo(a) > 0 && f.compareTo(a) > 0 && e.compareTo(a) < 0;
        a = new Paciente("A", 10, 2);
        b = new Paciente("B", 10, 1);
        c = new Paciente("C", 85, 1);
        d = new Paciente("D", 85, 2);
        e = new Paciente("E", 30, 1);
        f = new Paciente("F", 30, 2);
        if (res &= a.compareTo(c) > 0 && c.compareTo(a) < 0 && b.compareTo(c) < 0 && c.compareTo(b) > 0 && a.compareTo(e) > 0 && e.compareTo(a) < 0 && b.compareTo(e) < 0 && e.compareTo(b) > 0 && a.compareTo(d) < 0 && d.compareTo(a) > 0 && b.compareTo(d) < 0 && e.compareTo(b) > 0 && a.compareTo(f) < 0 && f.compareTo(a) > 0 && b.compareTo(f) < 0 && f.compareTo(b) > 0 && c.compareTo(e) < 0 && e.compareTo(c) > 0 && c.compareTo(f) < 0 && f.compareTo(c) > 0 && d.compareTo(e) > 0 && e.compareTo(d) < 0 && d.compareTo(f) < 0 && f.compareTo(d) > 0) {
            return 1.0;
        }
        return 0.0;
    }

    private static double testMerge2() throws Exception {
        String metodo = "merge2";
        String clase = "librerias.util.Ordenacion";
        double nota = 0.0;
        Method m = TestPracts.buscarMetodo(Class.forName(clase), metodo);
        if (m == null) {
            System.out.println("No existe el metodo que se quiere probar.");
        } else {
            nota = TestPracts.compruebaMerge2(m);
        }
        return nota;
    }

    private static double compruebaMerge2(Method m) throws Exception {
        double MAX_NOTA = 0.5;
        double MIN_NOTA = 0.0;
        int TALLA = 66666;
        Object[] a1 = TestPracts.crearAleatorioInteger(66666);
        Arrays.sort(a1);
        Object[] a2 = TestPracts.crearAleatorioInteger(66666);
        Arrays.sort(a2);
        Comparable[] c = (Comparable[])m.invoke(null, a1, a2);
        boolean res = true;
        for (int i = 1; i < c.length && res; ++i) {
            res = c[i - 1].compareTo(c[i]) <= 0;
        }
        boolean bl = res = res && c.length == a1.length + a2.length;
        if (res) {
            return 0.5;
        }
        return 0.0;
    }

    private static double testMergeSort2() {
        double MAX_NOTA = 0.5;
        double MIN_NOTA = 0.0;
        int TALLA = 666666;
        Object[] a1 = TestPracts.crearAleatorioInteger(666666);
        Comparable[] a2 = (Comparable[]) Arrays.copyOf(a1, a1.length);
        Arrays.sort(a1);
        Ordenacion.mergeSort2((Comparable[])a2);
        boolean res = TestPracts.sonIguales((Comparable[])a1, (Comparable[])a2);
        if (res) {
            return 0.5;
        }
        return 0.0;
    }

    private static double testRecEquilibrado() {
        double MAX_NOTA = 1.0;
        double MIN_NOTA = 0.0;
        int TALLA = 511;
        int FACTOR1 = (int)(Math.random() * 511.0);
        int FACTOR2 = (int)(Math.random() * 511.0 / 2.0);
        Integer[] a = new Integer[511];
        for (int i = 0; i < a.length; ++i) {
            a[i] = FACTOR2 + FACTOR1 * a.length - i;
        }
        ABB<Integer> aBB = TestPracts.generarABBPrueba(a);
        ABB<Integer> aEq = TestPracts.crearABBEquil(a);
        aBB.reconstruirEquilibrado();
        String porNiv1 = aBB.toStringPorNiveles();
        String porNiv2 = aEq.toStringPorNiveles();
        boolean res = porNiv1.equals(porNiv2);
        if (res) {
            return 1.0;
        }
        return 0.0;
    }

    private static Integer[] crearAleatorioInteger(int talla) {
        Integer[] aux = new Integer[talla];
        for (int i = 0; i < aux.length; ++i) {
            aux[i] = (int)(Math.random() * (double)(10 * talla));
        }
        return aux;
    }

    private static <T extends Comparable<T>> boolean sonIguales(T[] a, T[] b) {
        boolean iguales = true;
        if (a.length != b.length) {
            iguales = false;
        } else {
            for (int i = 0; i < a.length && iguales; ++i) {
                iguales = a[i].compareTo(b[i]) == 0;
            }
        }
        return iguales;
    }

    private static Method buscarMetodo(Class clase, String nombre) {
        AccessibleObject m = null;
        Method[] methods = clase.getDeclaredMethods();
        for (int i = 0; i < methods.length && m == null; ++i) {
            if (!methods[i].getName().equalsIgnoreCase(nombre)) continue;
            m = methods[i];
        }
        if (m != null) {
            m.setAccessible(true);
        }
        return (Method) m;
    }

    private static ABB<Integer> generarABBPrueba(Integer[] a) {
        ABB arbre = new ABB();
        for (int i = 0; i < a.length; ++i) {
            arbre.insertar((Comparable)a[i]);
        }
        return arbre;
    }

    private static ABB<Integer> crearABBEquil(Integer[] a) {
        Arrays.sort((Object[])a);
        ABB aEq = new ABB();
        TestPracts.constEquil(aEq, a, 0, a.length - 1);
        return aEq;
    }

    private static void constEquil(ABB<Integer> aEq, Integer[] a, int ini, int fin) {
        if (ini <= fin) {
            int mitad = (ini + fin) / 2;
            aEq.insertar(a[mitad]);
            TestPracts.constEquil(aEq, a, ini, mitad - 1);
            TestPracts.constEquil(aEq, a, mitad + 1, fin);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void doPost(double nota, String alumno, String pc, int noPregunta) {
        charset = "UTF-8";
        String requestURL = server;
        String reply = "";
        boolean ok = false;
        try {
            TestPracts.multipartUtility(requestURL, "EUC-JP");
            TestPracts.addHeaderField("User-Agent", "CodeJava");
            TestPracts.addHeaderField("Test-Header", "Header-Value");
            TestPracts.addFormField("user", alumno);
            String notaD = String.format("T0Ej" + noPregunta + " %.2f", nota);
            TestPracts.addFormField("nota", notaD);
            TestPracts.addFormField("pc", pc);
            TestPracts.addFormField("pregunta", "" + noPregunta);
            List<String> response = TestPracts.finish();
            for (String line : response) {
                reply = reply + line;
            }
            ok = reply.trim().equalsIgnoreCase("OK");
        }
        catch (Exception ex) {
            ok = false;
            reply = ex.getMessage();
        }
    }

    private static void multipartUtility(String requestURL, String chars) throws IOException {
        charset = chars;
        boundary = "===" + System.currentTimeMillis() + "===";
        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection)url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
        httpConn.setRequestProperty("Test", "Bonjour");
        outputStream = httpConn.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
    }

    private static void addHeaderField(String name, String value) {
        writer.append(name + ": " + value).append("\r\n");
        writer.flush();
    }

    private static void addFormField(String name, String value) {
        writer.append("--" + boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append("\r\n");
        writer.append("Content-Type: text/plain; charset=" + charset).append("\r\n");
        writer.append("\r\n");
        writer.append(value).append("\r\n");
        writer.flush();
    }

    private static List<String> finish() throws IOException {
        BufferedReader reader;
        writer.append("\r\n").flush();
        writer.append("--" + boundary + "--").append("\r\n");
        writer.close();
        int status = httpConn.getResponseCode();
        ArrayList<String> response = new ArrayList<String>();
        if (status == 200) {
            reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }
        reader.close();
        httpConn.disconnect();
        return response;
    }

    static {
        EJER_PRACT = new int[]{1, 2, 2, 4};
        server = "http://users.dsic.upv.es/asignaturas/etsinf/eda/cap/docs/pruebaLab/upload.php";
    }
}
package librerias.estructurasDeDatos.deDispersion;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class TestTablaHash2 {
	
    public static void main(String[] args) {
        boolean histo;
        boolean fc = TestTablaHash1.testFactorCarga();
        boolean rehash = TestTablaHash1.testRehashing();
        boolean claves = TestTablaHash1.testClaves();
        if (!(fc && rehash && claves)) {
            System.out.println("-( Vuelve a pasar el test 1!");
            return;
        }
        boolean desv = TestTablaHash2.testDesvTipica();
        if (!desv) {
            System.out.println("-( desviacionTipica no es correcto.");
        }
        if (!(histo = TestTablaHash2.testHisto())) {
            System.out.println("-( histograma no es correcto.");
        }
        if (desv && histo) {
            System.out.println(" -)-) Codigo de TablaHash correcto!");
        }
    }

    protected static boolean testHisto() {
        System.out.print("... Test histograma: ");
        TablaHash th = new TablaHash(40);
        for (int i = 0; i < 40; ++i) {
            th.insertar((Object)("N" + i), (Object)i);
        }
        String histogr = th.histograma();
        String miHistogr = TestTablaHash2.miHistograma(th);
        String[] histo = histogr.split("\n");
        String[] miHisto = miHistogr.split("\n");
        boolean ok = true;
        if (histo.length != miHisto.length) {
            System.out.println("Debe tener 10 lineas (una para cada longitud)");
            ok = false;
        } else {
            ok = true;
            for (int i2 = 0; i2 < histo.length && ok; ++i2) {
                if (histo[i2].trim().equals(miHisto[i2].trim())) continue;
                ok = false;
                System.out.print("Error en la longitud " + i2);
                System.out.println("... deber\u00eda ser " + miHisto[i2] + " y es " + histo[i2]);
            }
        }
        if (ok) {
            System.out.println("ok!");
        }
        return ok;
    }

    public static String miHistograma(TablaHash t) {
        int i;
        String res = "";
        int[] histo = new int[10];
        for (i = 0; i < t.elArray.length; ++i) {
            int cont = t.elArray[i].talla();
            if (cont >= histo.length) {
                cont = histo.length - 1;
            }
            int[] arrn = histo;
            int n = cont;
            arrn[n] = arrn[n] + 1;
        }
        for (i = 0; i < histo.length; ++i) {
            res = res + i + "\t" + histo[i] + "\n";
        }
        return res;
    }

    protected static boolean testDesvTipica() {
        double miDT;
        boolean res;
        System.out.print("... Test desviacionTipica: ");
        TablaHash th = new TablaHash(40);
        for (int i = 0; i < 40; ++i) {
            th.insertar((Object)("N" + i), (Object)i);
        }
        double tuDT = th.desviacionTipica();
        boolean bl = res = Math.abs(tuDT - (miDT = TestTablaHash2.miDesviacionTipica(th))) < 1.0E-8;
        if (res) {
            System.out.println("ok!");
        }
        return res;
    }

    private static final double miDesviacionTipica(TablaHash t) {
        double lMedia = (double)t.talla / (double)t.elArray.length;
        double suma = 0.0;
        for (int i = 0; i < t.elArray.length; ++i) {
            double di = (double)t.elArray[i].talla() - lMedia;
            suma += di * di;
        }
        return Math.sqrt(suma / (double)t.elArray.length);
    }
}
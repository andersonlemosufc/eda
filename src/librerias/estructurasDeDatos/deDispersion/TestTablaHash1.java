package librerias.estructurasDeDatos.deDispersion;

import librerias.estructurasDeDatos.modelos.ListaConPI;


@SuppressWarnings({"rawtypes", "unchecked"})
public class TestTablaHash1 {
	
    public static void main(String[] args) {
        String corr = "\n\tCorr\u00edgelo antes de seguir comprobando.";
        boolean rehash = false;
        boolean claves = false;
        boolean fc = TestTablaHash1.testFactorCarga();
        if (!fc) {
            System.out.println("\t-( factorCarga no es correcto." + corr);
        } else {
            rehash = TestTablaHash1.testRehashing();
            if (!rehash) {
                System.out.println("\t-( rehashing no es correcto." + corr);
            } else {
                claves = TestTablaHash1.testClaves();
                if (!claves) {
                    System.out.println("\t-( claves no es correcto." + corr);
                }
            }
        }
    }

	protected static boolean testFactorCarga() {
        System.out.print("... Test factor de carga: ");
        boolean ok = true;
        String res = "";
        TablaHash t = new TablaHash(10);
        for (int i = 0; i < 5; ++i) {
            t.insertar((Object)new Integer(i), (Object)0);
        }
        int longArray = TablaHash.siguientePrimo((int)13);
        if (t.factorCarga() != (double)t.talla() / (double)longArray) {
            ok = false;
            res = res + "Factor de Carga Incorrecto";
        } else {
            res = res + "ok!";
        }
        System.out.println(res);
        return ok;
    }

    protected static boolean testRehashing() {
        System.out.print("... Test rehashing: ");
        boolean ok = true;
        String res = "";
        TablaHash t = new TablaHash(10);
        int t1 = t.elArray.length;
        int nrH1 = 1 + (int)((double)t1 * 0.75);
        for (int i = 0; i < nrH1; ++i) {
            t.insertar((Object)new Integer(i), (Object)0);
        }
        if (t.talla() != nrH1) {
            ok = false;
            res = res + "\n\tTalla incorrecta";
        } else {
            int t2 = t.elArray.length;
            int t2Ref = TestTablaHash1.nuevaCapacidad(t1);
            if (t2 != t2Ref) {
                ok = false;
                res = res + "\n\tTama\u00f1o incorrecto del nuevo array";
            } else {
                for (int i2 = 0; i2 < nrH1 && ok; ++i2) {
                    Integer oi = (Integer)t.recuperar((Object)new Integer(i2));
                    if (oi != null) continue;
                    ok = false;
                    res = res + "\n\tNo se han copiado todos los datos";
                }
            }
        }
        if (res.equals("")) {
            res = "ok!";
        }
        System.out.println(res);
        return ok;
    }

    protected static boolean testClaves() {
        System.out.print("... Test claves: ");
        boolean ok = true;
        TablaHash th = new TablaHash(100);
        for (int i = 0; i < 40; ++i) {
            th.insertar((Object)("N" + i), (Object)i);
        }
        ListaConPI lista = th.claves();
        int[] res = new int[40];
        lista.inicio();
        while (!lista.esFin()) {
            block11 : {
                String s = (String)lista.recuperar();
                if (s.startsWith("N")) {
                    try {
                        int n = Integer.parseInt(s.substring(1));
                        if (n < 0 || n >= 40) {
                            ok = false;
                            break block11;
                        }
                        res[n] = 1;
                    }
                    catch (NumberFormatException e) {
                        ok = false;
                    }
                } else {
                    ok = false;
                }
            }
            lista.siguiente();
        }
        if (ok) {
            for (int i2 = 0; i2 < res.length; ++i2) {
                if (res[i2] == 1) continue;
                ok = false;
            }
        }
        if (ok) {
            System.out.println("ok!");
        } else {
            System.out.println("incorrecto.");
        }
        return ok;
    }

    private static int nuevaCapacidad(int old) {
        return TestTablaHash1.siguientePrimo(old * 2);
    }

    public static final int siguientePrimo(int n) {
        int nn = n;
        if (nn % 2 == 0) {
            ++nn;
        }
        while (!TestTablaHash1.esPrimo(nn)) {
            nn += 2;
        }
        return nn;
    }

    protected static final boolean esPrimo(int n) {
        int i = 3;
        while (i * i <= n) {
            if (n % i == 0) {
                return false;
            }
            i += 2;
        }
        return true;
    }
}
package librerias.estructurasDeDatos.jerarquicos;

import java.lang.reflect.Field;

@SuppressWarnings("rawtypes")
public class TestMonticuloBinarioMaxR0 {
    
	private static void checkTalla(MonticuloBinarioMaxR0<Integer> m, int talla) throws Exception {
        Class c = m.getClass();
        Field f = c.getDeclaredField("talla");
        if (f == null) {
            throw new Exception("Atributo 'talla' no encontrado");
        }
        f.setAccessible(true);
        Integer mTalla = (Integer)f.get(m);
        if (mTalla != talla) {
            throw new Exception("La talla no se actualiza correctamente");
        }
    }

    private static void checkArray(MonticuloBinarioMaxR0<Integer> m, Integer[] v, String error) throws Exception {
        Class c = m.getClass();
        Field f = c.getDeclaredField("elArray");
        if (f == null) {
            throw new Exception("Atributo 'elArray' no encontrado");
        }
        f.setAccessible(true);
        Object[] a = (Object[])f.get(m);
        if (a.length < v.length) {
            throw new Exception(error);
        }
        for (int i = 0; i < v.length; ++i) {
            if (a[i].equals(v[i])) continue;
            throw new Exception(error);
        }
    }

    private static void testInsertar(MonticuloBinarioMaxR0<Integer> m) throws Exception {
        Integer[] v = new Integer[]{2, 10, 5, 8, 1, 4, 3, 0, 9, 7, 6};
        Integer[] res = new Integer[]{10, 9, 5, 8, 7, 4, 3, 0, 2, 1, 6};
        try {
            for (int i = 0; i < v.length; ++i) {
                m.insertar(v[i]);
            }
        }
        catch (Exception e) {
            throw new Exception("Error al insertar");
        }
        TestMonticuloBinarioMaxR0.checkTalla(m, v.length);
        TestMonticuloBinarioMaxR0.checkArray(m, res, "Error al insertar");
    }

    private static void testEliminar(MonticuloBinarioMaxR0<Integer> m) throws Exception {
        Integer[] res = new Integer[]{5, 2, 4, 0, 1, 3};
        try {
            for (int i = 10; i >= 6; --i) {
                if ((Integer)m.recuperarMin() != i) {
                    throw new Exception();
                }
                if ((Integer)m.eliminarMin() == i) continue;
                throw new Exception();
            }
        }
        catch (Exception e) {
            throw new Exception("Error al eliminarMin");
        }
        TestMonticuloBinarioMaxR0.checkTalla(m, 6);
        TestMonticuloBinarioMaxR0.checkArray(m, res, "Error al eliminarMin");
    }

    @SuppressWarnings("unchecked")
	private static boolean testMonticulo() {
        try {
            MonticuloBinarioMaxR0 m = new MonticuloBinarioMaxR0();
            TestMonticuloBinarioMaxR0.testInsertar(m);
            TestMonticuloBinarioMaxR0.testEliminar(m);
            if (m.esVacia()) {
                throw new Exception("Error en esVacia");
            }
            for (int i = 0; i < 6; ++i) {
                m.eliminarMin();
            }
            if (!m.esVacia()) {
                throw new Exception("Error en esVacia");
            }
        }
        catch (Exception e) {
            System.out.println("Error en la clase MonticuloBinarioMaxR0: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        if (TestMonticuloBinarioMaxR0.testMonticulo()) {
            System.out.println("Test correcto!");
        }
    }
}
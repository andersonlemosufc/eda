package aplicaciones.letras;

public class TestImagen {
    public static void main(String[] args) {
        Imagen[] imgAl1 = new Imagen[2];
        Imagen[] imgAl2 = new Imagen[2];
        Imagen[] imgAl3 = new Imagen[2];
        Imagen[] imgAl4 = new Imagen[2];
        int nfilas = (int)Math.random() * 20 + 6;
        int ncols = (int)Math.random() * 20 + 6;
        imgAl1[0] = new Imagen(nfilas, ncols, 0);
        imgAl2[0] = new Imagen(nfilas, ncols, 1);
        imgAl3[0] = new Imagen(nfilas, ncols, 2);
        imgAl4[0] = new Imagen(nfilas, ncols, 3);
        nfilas = 11;
        ncols = 13;
        imgAl1[1] = new Imagen(nfilas, ncols, 0);
        imgAl2[1] = new Imagen(nfilas, ncols, 1);
        imgAl3[1] = new Imagen(nfilas, ncols, 2);
        imgAl4[1] = new Imagen(nfilas, ncols, 3);
        System.out.println("** Comprobando hashCode...");
        boolean[] error = new boolean[]{false, false, false, false};
        error[0] = imgAl1[0].hashCode() != TestImagen.hashCodeRef(imgAl1[0], 0);
        error[0] = imgAl1[1].hashCode() != TestImagen.hashCodeRef(imgAl1[1], 0);
        error[1] = imgAl2[0].hashCode() != TestImagen.hashCodeRef(imgAl2[0], 1);
        error[1] = imgAl2[1].hashCode() != TestImagen.hashCodeRef(imgAl2[1], 1);
        error[2] = imgAl3[0].hashCode() != TestImagen.hashCodeRef(imgAl3[0], 2);
        error[2] = imgAl3[1].hashCode() != TestImagen.hashCodeRef(imgAl3[1], 2);
        error[3] = imgAl4[0].hashCode() != TestImagen.hashCodeRef(imgAl4[0], 3);
        error[3] = imgAl4[1].hashCode() != TestImagen.hashCodeRef(imgAl4[1], 3);
        for (int i = 0; i < error.length; ++i) {
            if (error[i]) {
                System.out.println("\t -- Error en el c\u00e1lculo de hashCode(): " + Imagen.NOMFDIS[i]);
                if (i != 1 && i != 3) continue;
                System.out.println("\t -- o el recorrido para el c\u00e1lculo no es por filas-columnas ascendente o la ponderaci\u00f3n no es la prevista");
                continue;
            }
            System.out.println("\t ++ Correcto el c\u00e1lculo de hashCode(): " + Imagen.NOMFDIS[i]);
        }
        System.out.println("** Comprobando equals...");
        boolean errorEquals = false;
        if (!imgAl1[0].equals((Object)imgAl1[0]) || !imgAl1[1].equals((Object)imgAl1[1])) {
            errorEquals = true;
        }
        if (!imgAl2[0].equals((Object)imgAl2[0]) || !imgAl2[1].equals((Object)imgAl2[1])) {
            errorEquals = true;
        }
        if (!imgAl3[0].equals((Object)imgAl3[0]) || !imgAl3[1].equals((Object)imgAl3[1])) {
            errorEquals = true;
        }
        if (!imgAl4[0].equals((Object)imgAl4[0]) || !imgAl4[1].equals((Object)imgAl4[1])) {
            errorEquals = true;
        }
        if (imgAl1[0].equals((Object)imgAl1[1]) != TestImagen.sonEquals(imgAl1[0], imgAl1[1])) {
            errorEquals = true;
        }
        if (imgAl2[0].equals((Object)imgAl2[1]) != TestImagen.sonEquals(imgAl2[0], imgAl2[1])) {
            errorEquals = true;
        }
        if (imgAl3[0].equals((Object)imgAl3[1]) != TestImagen.sonEquals(imgAl3[0], imgAl3[1])) {
            errorEquals = true;
        }
        if (imgAl4[0].equals((Object)imgAl4[1]) != TestImagen.sonEquals(imgAl4[0], imgAl4[1])) {
            errorEquals = true;
        }
        try {
            imgAl4[0].equals((Object)new Integer(5));
        }
        catch (Exception e) {
            errorEquals = true;
        }
        if (errorEquals) {
            System.out.println("\t -- Error en equals");
        } else {
            System.out.println("\t ++ Correcto el codigo para metodo equals");
        }
    }

    private static boolean sonEquals(Imagen i1, Imagen i2) {
        if (i1.getAncho() != i2.getAncho() || i1.getAlto() != i2.getAlto()) {
            return false;
        }
        for (int i = 0; i < i1.getAlto(); ++i) {
            for (int j = 0; j < i1.getAncho(); ++j) {
                if (i1.getPixel(i, j).equals((Object)i2.getPixel(i, j))) continue;
                return false;
            }
        }
        return true;
    }

    private static int hashCodeRef(Imagen img, int fHash) {
        int res = 0;
        switch (fHash) {
            case 0: {
                res = TestImagen.valorHashSumaTodos(img);
                break;
            }
            case 1: {
                res = TestImagen.valorHashSumaPonderadaTodos(img);
                break;
            }
            case 2: {
                res = TestImagen.valorHashSuma3(img);
                break;
            }
            case 3: {
                res = TestImagen.valorHashSumaPonderada3(img);
            }
        }
        return res;
    }

    private static int valorHashSumaPonderadaTodos(Imagen img) {
        int res = 0;
        for (int i = 0; i < img.getAlto(); ++i) {
            for (int j = 0; j < img.getAncho(); ++j) {
                res = res * 255 + img.getPixel(i, j).hashCode();
            }
        }
        return res;
    }

    private static int valorHashSumaTodos(Imagen img) {
        int res = 0;
        for (int i = 0; i < img.getAlto(); ++i) {
            for (int j = 0; j < img.getAncho(); ++j) {
                res += img.getPixel(i, j).hashCode();
            }
        }
        return res;
    }

    private static int valorHashSumaPonderada3(Imagen img) {
        int res = 0;
        int y = img.getAlto() / 2 - 1;
        int x = img.getAncho() / 2 - 1;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                res = res * 255 + img.getPixel(i + y, j + x).hashCode();
            }
        }
        return res;
    }

    private static int valorHashSuma3(Imagen img) {
        int res = 0;
        int y = img.getAlto() / 2 - 1;
        int x = img.getAncho() / 2 - 1;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                res += img.getPixel(i + y, j + x).hashCode();
            }
        }
        return res;
    }
}
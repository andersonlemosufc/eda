package aplicaciones.pruebasOrdenacion;

public class GeneradorDeString {
    
    private int numIg; 
    private String base;
    
    public GeneradorDeString(int n) {
        this.numIg = n;
        String s = "";
        int desAlea = (int) (Math.random() * 10);
        for (int i = 0; i < numIg; i++) {
            s += (char) ((i + desAlea) % 256 + (int) '0'); 
        }
        this.base = s;
   
    }

    public String generar() {
        String s = "";   
        for (int i = 1; i <= (int) (Math.random() * 2 * numIg); i++) {
            s += (char) ((int) (Math.random() * 128) + (int) '0');
        }
        return this.base + s;
    }
}

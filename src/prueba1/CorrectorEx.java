package prueba1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TooManyListenersException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

@SuppressWarnings({"rawtypes", "unused"})
public abstract class CorrectorEx
extends JFrame
implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	protected static int NUM_CORR = 0;
    protected static int CAS = 0;
    protected static int VAL = 1;
    protected static int ENG = 2;
    protected static String[] msgNoDosEjOpen = new String[]{"No puede haber dos ejercicios abiertos a la vez", "No hi pot haver dos exercicis oberts alhora", "There can't be simultaneously two open exercises"};
    protected static String[] msgOK = new String[]{"* Correcto\nEl examen podr\u00e1 ser evaluado (y recalificado de ser necesario) por el profesor\n", "* Correcte\nL'examen podr\u00e0 ser avaluat (i requalificat si cal) pel professor\n", "* Correct\nYour solution can be re-evaluated (and re-qualified when necessary) by the teacher\n"};
    protected static String[] msgNoOK = new String[]{"* No correcto. A corregir por el profesor\n", "* No correcte. A corregir pel professor\n", "* No correct. To be evaluated by the teacher\n"};
    protected static String[] msgAlum = new String[]{"Alumno:", "Alumne:", "Student:"};
    protected static String[] msgNoUsuario = new String[]{"Si este no es tu usuario deber\u00e1s reiniciar tu sesi\u00f3n.", "Si aquest no \u00e9s el teu usuari has de reiniciar la sessi\u00f3.", "If this is not your user, restart session."};
    protected static String[] msgEnun = new String[]{"Enunciado:", "Enunciat:", "Statement:"};
    protected static String[] msgEnvSol = new String[]{"Enviar soluci\u00f3n", "Enviar soluci\u00f3", "Send solution"};
    protected static String[] msgResPrueba = new String[]{"Resultado de la prueba:", "Resultat de la prova:", "Test result:"};
    protected static String[] msgEEFichNoEx = new String[]{"\n\nERROR: tu ejercicio no se ha guardado en el servidor porque el fichero a entregar no existe en el lugar indicado.", "\n\nERROR: el teu exercici no s'ha guardat en el servidor perqu\u00e8 el fitxer que s'ha d'entregar no existeix en el lloc indicat.", "\n\nERROR: your exercise was not saved on the server because the file that is to be delivered is not in the right place."};
    protected static String[] msgEE = new String[]{"\n\nERROR: tu ejercicio no se ha guardado en el servidor. Comenta el problema con el profesor del aula.\n", "\n\nERROR: el teu exercici no s'ha guardat en el servidor. Comenta el problema amb el professor de l'aula.\n", "\n\nERROR: your exercise was not saved on the server. Ask the teacher in your classroom.\n"};
    protected static String[] msgDetalle = new String[]{"\n* Detalle: ", "\n* Detall: ", "\n* Detail: "};
    protected static String[] msgExito = new String[]{"\n\nEjercicio entregado con \u00e9xito.", "\n\nExercici entregat amb \u00e8xit.", "\n\nExercise successfully delivered."};
    protected static String[] msgCompEj = new String[]{"\nComprobando el ejercicio:\n", "\nComprovant l'exercici:\n", "\nChecking the exercise:\n"};
    protected static String[] msgNoMetodo = new String[]{"No se ha encontrado el m\u00e9todo en la clase", "No s'ha trobat el m\u00e8tode en la classe", "No method found in class"};
    protected static String[] msgErrorMetodo = new String[]{"Excepci\u00f3n en el m\u00e9todo: ", "Excepci\u00f3 en el m\u00e8tode: ", "Exception in the method: "};
    protected static String[] msgNoClase = new String[]{"No se ha encontrado la clase ", "No s'ha trobat la classe ", "No class found "};
    protected static String[] msgNoTodoOK = new String[]{"* No es completamente correcto\n", "No \u00e9s completament correcte\n", "Not fully correct\n"};
    protected String idAsig;
    protected String idExamen;
    protected int turno;
    protected int noPregunta;
    protected String enunciado = "";
    protected int lang;
    protected String metodo = "";
    protected String clase = "";
    protected double maxNota;
    protected boolean verb;
    protected String server;
    protected String extFile;
    protected String upFile;
    protected String tituloEx;
    private String alumno;
    private String pc;
    private JPanel jTop;
    private JPanel jCenter;
    private JPanel jBottom;
    private JButton jTest;
    private JTextPane resPane;
    private String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;

    public CorrectorEx(String idA, String idEx, int t, int p, String nomFEnun, String m, String cl, double nota, boolean v, String titEx, int idioma) throws IOException {
        super(titEx);
        try {
            if (NUM_CORR > 0) {
                throw new TooManyListenersException(msgNoDosEjOpen[this.lang]);
            }
            ++NUM_CORR;
            this.idAsig = idA;
            this.idExamen = idEx;
            this.turno = t;
            this.noPregunta = p;
            this.enunciado = CorrectorEx.leerEnunciado(nomFEnun);
            this.lang = idioma;
            this.metodo = m;
            this.clase = cl;
            this.maxNota = nota;
            this.verb = v;
            this.server = "http://users.dsic.upv.es/asignaturas/etsinf/eda/cap/docs/pruebaLab/upload.php";
            this.extFile = "java";
            this.upFile = System.getProperty("user.home") + File.separator + this.idAsig + File.separator + idEx + File.separator + this.clase + "." + this.extFile;
            this.tituloEx = titEx;
            this.setDefaultCloseOperation(3);
            this.getContentPane().setLayout(new BorderLayout());
            this.initTopPanel();
            this.initCenterPanel();
            this.initBottomPanel();
            this.setMinimumSize(new Dimension(800, 560));
            this.setPreferredSize(new Dimension(800, 560));
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
        catch (TooManyListenersException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("resource")
	private static String leerEnunciado(String nom) {
        try {
            Scanner s = new Scanner(new File(nom));
            String lin = "";
            while (s.hasNext()) {
                lin = lin + s.nextLine();
            }
            return lin;
        }
        catch (IOException e) {
            return nom;
        }
    }

    private void initTopPanel() {
        this.jTop = new JPanel();
        JLabel jLabelAl = new JLabel(msgAlum[this.lang]);
        JTextField jTextFieldAl = new JTextField();
        jTextFieldAl.setEditable(false);
        JLabel jLabelPC = new JLabel("PC:");
        JTextField jTextFieldPC = new JTextField();
        jTextFieldPC.setEditable(false);
        JLabel jLabelWarn = new JLabel(msgNoUsuario[this.lang]);
        GroupLayout jTopLayout = new GroupLayout(this.jTop);
        this.jTop.setLayout(jTopLayout);
        jTopLayout.setHorizontalGroup(jTopLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jTopLayout.createSequentialGroup().addGap(23, 23, 23).addGroup(jTopLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jLabelPC).addComponent(jLabelAl)).addGap(22, 22, 22).addGroup(jTopLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jTopLayout.createSequentialGroup().addComponent(jTextFieldAl, -2, 134, -2).addGap(18, 18, 18).addComponent(jLabelWarn)).addComponent(jTextFieldPC, -2, 134, -2)).addContainerGap(228, 32767)));
        jTopLayout.setVerticalGroup(jTopLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jTopLayout.createSequentialGroup().addContainerGap().addGroup(jTopLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabelAl).addComponent(jTextFieldAl, -2, -1, -2).addComponent(jLabelWarn)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jTopLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(jLabelPC).addComponent(jTextFieldPC, -2, -1, -2)).addContainerGap(8, 32767)));
        this.getContentPane().add((Component)this.jTop, "North");
        this.alumno = System.getProperty("user.name");
        jTextFieldAl.setText(this.alumno);
        try {
            InetAddress localMachine = InetAddress.getLocalHost();
            this.pc = localMachine.getHostName();
        }
        catch (Exception e) {
            this.pc = "";
        }
        jTextFieldPC.setText(this.pc);
    }

    private void initCenterPanel() {
        this.jCenter = new JPanel();
        JLabel labelEnunciado = new JLabel(msgEnun[this.lang]);
        JTextPane jenunciado = new JTextPane();
        GroupLayout layout = new GroupLayout(this.jCenter);
        this.jCenter.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(21, 21, 21).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(jenunciado, GroupLayout.Alignment.LEADING).addComponent(labelEnunciado, GroupLayout.Alignment.LEADING)).addGap(21, 21, 21)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(labelEnunciado).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jenunciado, -2, 307, 32767).addGap(18, 18, 18)));
        this.jCenter.setBorder(BorderFactory.createEtchedBorder(1));
        jenunciado.setEditable(false);
        jenunciado.setContentType("text/html; charset=EUC-JP");
        jenunciado.setText(this.enunciado);
        this.getContentPane().add((Component)this.jCenter, "Center");
    }

    private void initBottomPanel() {
        this.jBottom = new JPanel();
        this.jTest = new JButton(msgEnvSol[this.lang]);
        GroupLayout layout = new GroupLayout(this.jBottom);
        this.jBottom.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(484, 32767).addComponent(this.jTest, -2, 180, -2).addGap(21, 21, 21)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jTest, -1, 44, 32767).addContainerGap()));
        this.getContentPane().add((Component)this.jBottom, "South");
        this.jTest.addActionListener(this);
    }

    private void appendRes(String txt, Color color, boolean bold, boolean underline, boolean italic) {
        StyledDocument doc = this.resPane.getStyledDocument();
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setForeground(style, color);
        StyleConstants.setBold(style, bold);
        StyleConstants.setUnderline(style, underline);
        StyleConstants.setItalic(style, italic);
        try {
            doc.insertString(doc.getLength(), txt, style);
        }
        catch (Exception var8_8) {
            // empty catch block
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.corregirEjercicio(this.maxNota, this.verb);
    }

    private void corregirEjercicio(double max, boolean verb) {
        this.getContentPane().remove(this.jCenter);
        this.getContentPane().remove(this.jBottom);
        this.jCenter = new JPanel();
        JLabel jLabel = new JLabel(msgResPrueba[this.lang]);
        JScrollPane jScrollPane = new JScrollPane();
        this.resPane = new JTextPane();
        jScrollPane.setViewportView(this.resPane);
        this.resPane.setEditable(false);
        this.resPane.setContentType("text/html");
        GroupLayout layout = new GroupLayout(this.jCenter);
        this.jCenter.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel).addGap(0, 657, 32767)).addComponent(jScrollPane)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane, -1, 443, 32767).addContainerGap()));
        this.getContentPane().add((Component)this.jCenter, "Center");
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
        double notaEjercicio = this.comprobarEjercicio(max, verb);
        String nota = String.format("T" + this.turno + "Ej" + this.noPregunta + " %.2f", notaEjercicio);
        this.doPost(nota);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void doPost(String nota) {
        String charset = "UTF-8";
        File uploadFile = new File(this.upFile);
        String requestURL = this.server;
        String reply = "";
        boolean ok = false;
        try {
            this.multipartUtility(requestURL, charset);
            this.addHeaderField("User-Agent", "CodeJava");
            this.addHeaderField("Test-Header", "Header-Value");
            this.addFormField("user", this.alumno);
            this.addFormField("nota", nota);
            this.addFormField("pc", this.pc);
            this.addFormField("pregunta", "" + this.noPregunta);
            this.addFormField("extension", this.extFile);
            this.addFilePart("fichero", uploadFile);
            List<String> response = this.finish();
            for (String line : response) {
                reply = reply + line;
            }
            ok = reply.trim().equalsIgnoreCase("OK");
        }
        catch (Exception ex) {
            ok = false;
            reply = ex.getMessage();
        }
        finally {
            if (!ok) {
                if (!uploadFile.exists()) {
                    this.appendRes(msgEEFichNoEx[this.lang], Color.RED, true, false, false);
                    this.appendRes(msgDetalle[this.lang] + reply + "\n", Color.BLUE, false, false, true);
                } else {
                    this.appendRes(msgEE[this.lang], Color.RED, true, false, false);
                    this.appendRes(msgDetalle[this.lang] + reply + "\n", Color.BLUE, false, false, true);
                }
            } else {
                this.appendRes(msgExito[this.lang], Color.BLACK, true, false, false);
            }
        }
    }

    private void multipartUtility(String requestURL, String charset) throws IOException {
        this.charset = charset;
        this.boundary = "===" + System.currentTimeMillis() + "===";
        URL url = new URL(requestURL);
        this.httpConn = (HttpURLConnection)url.openConnection();
        this.httpConn.setUseCaches(false);
        this.httpConn.setDoOutput(true);
        this.httpConn.setDoInput(true);
        this.httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.boundary);
        this.httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
        this.httpConn.setRequestProperty("Test", "Bonjour");
        this.outputStream = this.httpConn.getOutputStream();
        this.writer = new PrintWriter(new OutputStreamWriter(this.outputStream, charset), true);
    }

    private void addFormField(String name, String value) {
        this.writer.append("--" + this.boundary).append("\r\n");
        this.writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append("\r\n");
        this.writer.append("Content-Type: text/plain; charset=" + this.charset).append("\r\n");
        this.writer.append("\r\n");
        this.writer.append(value).append("\r\n");
        this.writer.flush();
    }

    private void addFilePart(String fieldName, File uploadFile) throws IOException {
        String fileName = uploadFile.getName();
        this.writer.append("--" + this.boundary).append("\r\n");
        this.writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"").append("\r\n");
        this.writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append("\r\n");
        this.writer.append("Content-Transfer-Encoding: binary").append("\r\n");
        this.writer.append("\r\n");
        this.writer.flush();
        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            this.outputStream.write(buffer, 0, bytesRead);
        }
        this.outputStream.flush();
        inputStream.close();
        this.writer.append("\r\n");
        this.writer.flush();
    }

    private void addHeaderField(String name, String value) {
        this.writer.append(name + ": " + value).append("\r\n");
        this.writer.flush();
    }

    private List<String> finish() throws IOException {
        BufferedReader reader;
        this.writer.append("\r\n").flush();
        this.writer.append("--" + this.boundary + "--").append("\r\n");
        this.writer.close();
        int status = this.httpConn.getResponseCode();
        ArrayList<String> response = new ArrayList<String>();
        if (status == 200) {
            reader = new BufferedReader(new InputStreamReader(this.httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }
        reader.close();
        this.httpConn.disconnect();
        return response;
    }

    public double comprobarEjercicio(double max, boolean verb) {
        this.appendRes(msgCompEj[this.lang], Color.BLACK, true, true, false);
        String mensaje = "";
        double nota = 0.0;
        try {
            Method m = CorrectorEx.buscarMetodo(Class.forName(this.clase), this.metodo);
            if (m == null) {
                mensaje = msgNoMetodo[this.lang] + this.metodo + " -- " + this.clase;
            } else {
                try {
                    nota = this.compruebaCasos(m);
                }
                catch (Exception exc) {
                    mensaje = msgErrorMetodo[this.lang] + exc.toString();
                }
            }
        }
        catch (ClassNotFoundException e) {
            nota = 0.0;
            mensaje = msgNoClase[this.lang] + this.clase;
        }
        if (nota == max) {
            this.appendRes(msgOK[this.lang], Color.GREEN, false, false, false);
        } else if (nota > 0.0 && verb) {
            this.appendRes(msgNoTodoOK[this.lang] + nota, Color.ORANGE, false, false, false);
        } else {
            this.appendRes(msgNoOK[this.lang] + mensaje, Color.RED, false, false, false);
        }
        return nota;
    }

    protected static Method buscarMetodo(Class clase, String nombre) {
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

    public abstract double compruebaCasos(Method var1) throws Exception;

    public void pruebaTest(int n) throws Exception {
        for (int i = 1; i <= n; ++i) {
            Method m = CorrectorEx.buscarMetodo(Class.forName(this.clase), this.metodo + i);
            double nota = this.compruebaCasos(m);
            System.out.printf("Caso %2d: %3.2f\n\n", i, nota);
        }
    }
}
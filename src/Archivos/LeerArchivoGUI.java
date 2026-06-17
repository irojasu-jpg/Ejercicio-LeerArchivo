package Archivos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Esta clase denominada LeerArchivoGUI presenta una interfaz gráfica que
 * permite seleccionar un archivo de texto con extensión .txt y mostrar su
 * contenido en pantalla, reutilizando la lógica de lectura definida en la
 * clase LeerArchivo.
 * @version 1.0/2020
 */
public class LeerArchivoGUI extends JFrame {

    private final JTextArea areaTexto;
    private final JButton botonSeleccionar;
    private final JButton botonLeer;
    private File archivoSeleccionado;

    /**
     * Constructor que inicializa los componentes de la interfaz gráfica.
     */
    public LeerArchivoGUI() {
        super("Lectura de archivos - LeerArchivo");

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);

        botonSeleccionar = new JButton("Seleccionar archivo...");
        botonLeer = new JButton("Leer archivo");
        botonLeer.setEnabled(false);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(botonSeleccionar);
        panelBotones.add(botonLeer);

        botonSeleccionar.addActionListener(e -> seleccionarArchivo());
        botonLeer.addActionListener(e -> leerArchivo());

        setLayout(new BorderLayout());
        add(panelBotones, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
    }

    /**
     * Abre un JFileChooser para que el usuario seleccione el archivo de
     * texto que desea leer.
     */
    private void seleccionarArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccionar archivo de texto");
        FileNameExtensionFilter filtroTxt = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        selector.setFileFilter(filtroTxt);
        selector.setAcceptAllFileFilterUsed(true);

        int resultado = selector.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = selector.getSelectedFile();
            botonLeer.setEnabled(true);
            areaTexto.setText("");
            setTitle("Lectura de archivos - " + archivoSeleccionado.getName());
        }
    }

    /**
     * Lee el archivo seleccionado utilizando LeerArchivo.leerContenido y
     * muestra su contenido en pantalla. En caso de que no se pueda leer
     * el archivo se muestra un mensaje de error.
     * @throws IOException Excepción que indica que no se pudo leer
     * el archivo
     */
    private void leerArchivo() {
        if (archivoSeleccionado == null) {
            return;
        }
        try {
            String contenido = LeerArchivo.leerContenido(archivoSeleccionado.getAbsolutePath());
            areaTexto.setText(contenido);
        } catch (IOException e) { // En caso de que se genere una excepción
            JOptionPane.showMessageDialog(this,
                    "No se pudo leer el archivo.",
                    "Error de lectura",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método main que lanza la interfaz gráfica de lectura de archivos.
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LeerArchivoGUI ventana = new LeerArchivoGUI();
            ventana.setVisible(true);
        });
    }
}

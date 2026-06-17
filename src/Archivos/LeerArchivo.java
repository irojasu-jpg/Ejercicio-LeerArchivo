package Archivos;
import java.io.*;

/**
 * Esta clase denominada LeerArchivo tiene como objetivo leer los datos
 * presentes en un archivo de texto con extensión .txt
 * @version 1.2/2020
 */
public class LeerArchivo {

    /**
     * Método que lee un archivo de texto línea por línea y devuelve su
     * contenido como una sola cadena de texto. Reutiliza la misma lógica
     * de lectura basada en FileInputStream, InputStreamReader y
     * BufferedReader empleada en el método main de esta clase.
     * @param nombreArchivo Ruta del archivo de texto a leer
     * @return El contenido completo del archivo, con cada línea separada
     * por un salto de línea
     * @throws IOException Excepción que indica que no se pudo leer
     * el archivo
     */
    public static String leerContenido(String nombreArchivo) throws IOException {
        FileInputStream archivo; // Definición del flujo de datos
        InputStreamReader conversor; // Definición del flujo de lectura
        BufferedReader filtro; // Definición del buffer
        String linea;
        StringBuilder contenido = new StringBuilder();
        /* Crea los objetos FileInputStream, InputStreamReader y
            BufferedReader */
        archivo = new FileInputStream(nombreArchivo);
        conversor = new InputStreamReader(archivo);
        filtro = new BufferedReader(conversor);
        linea = filtro.readLine();
        while (linea != null) {
            contenido.append(linea).append(System.lineSeparator()); /* Agrega
                en el resultado una línea del archivo */
            linea = filtro.readLine(); // Lee la siguiente línea
        }
        filtro.close(); // Cierra el archivo
        return contenido.toString();
    }

    /**
     * Método main que lee una archivo de texto y muestra su contenido
     * en pantalla
     * @throws IOException Excepción que indica que no se pudo leer
     * el archivo
     */
    public static void main(String[] args) throws Exception {
        String nombreArchivo = "C:/prueba.txt"; /* Definición del
            archivo de texto a leer */
        FileInputStream archivo; // Definición del flujo de datos
        InputStreamReader conversor; // Definición del flujo de lectura
        BufferedReader filtro; // Definición del buffer
        String linea;
        try {
            /* Crea los objetos FileInputStream, InputStreamReader y
                BufferedReader */
            archivo = new FileInputStream(nombreArchivo);
            conversor = new InputStreamReader(archivo);
            filtro = new BufferedReader(conversor);
            linea = filtro.readLine();
            while (linea != null) {
                System.out.println(linea); /* Imprime en pantalla una
                    línea del archivo */
                linea = filtro.readLine(); // Lee la siguiente línea
            }
            filtro.close(); // Cierra el archivo
        } catch (IOException e) { // En caso de que se genere una excepción
            System.out.println("No se pudo leer el archivo.");
        }
    }
}

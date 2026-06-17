# Ejercicio 6.8 — Lectura de archivos: clase LeerArchivo

## Enunciado

Se tiene un archivo de texto guardado en el disco duro, ubicado en una ruta
específica (en el libro: `C:/prueba.txt`). Se debe implementar una clase
llamada `LeerArchivo` que se encargue de leer dicho archivo línea por línea
y mostrar su contenido en pantalla, utilizando para ello las clases
`FileInputStream`, `InputStreamReader` y `BufferedReader` del paquete
`java.io`.

Para leer el archivo se combinan estas tres clases de la siguiente manera:

```java
FileInputStream archivo = new FileInputStream(nombreArchivo);
InputStreamReader conversor = new InputStreamReader(archivo);
BufferedReader filtro = new BufferedReader(conversor);
String linea = filtro.readLine();
```

`FileInputStream` abre el flujo de bytes hacia el archivo, `InputStreamReader`
convierte ese flujo de bytes a caracteres (Unicode) y `BufferedReader` añade
un búfer que permite leer el contenido línea por línea mediante el método
`readLine()`, hasta que este devuelve `null` (fin de archivo).

## Estructura del proyecto

```
Ejercicio-LeerArchivo/
├── src/
│   └── Archivos/
│       ├── LeerArchivo.java       # Versión de consola (fiel al libro)
│       └── LeerArchivoGUI.java    # Versión con interfaz gráfica (Swing)
├── sample/
│   └── prueba.txt                 # Archivo de ejemplo para probar la lectura
├── diagrams/
│   ├── class_diagram.png
│   ├── use_case_diagram.png
│   └── Diagramas_LeerArchivo.docx
├── bin/                            # Clases compiladas (generadas)
└── README.md
```

La clase `LeerArchivo` contiene el método `main` original del libro, además
de un método estático auxiliar `leerContenido(String nombreArchivo)` que
encapsula la misma lógica de lectura (FileInputStream + InputStreamReader +
BufferedReader) y devuelve el contenido del archivo como una cadena de
texto. Este método es reutilizado por `LeerArchivoGUI` para evitar duplicar
el ciclo de lectura.

## Compilación

Desde la raíz del proyecto:

```bash
javac -d bin src/Archivos/*.java
```

## Ejecución — versión de consola

```bash
java -cp bin Archivos.LeerArchivo
```

Por defecto la clase intenta leer la ruta `C:/prueba.txt` (tal como en el
libro). Para probarla con el archivo de ejemplo incluido, edita temporalmente
la variable `nombreArchivo` en `LeerArchivo.java` o copia `sample/prueba.txt`
a esa ruta. Si el archivo no existe, el programa captura la excepción
`IOException` y muestra el mensaje `"No se pudo leer el archivo."`.

## Ejecución — versión gráfica (GUI)

```bash
java -cp bin Archivos.LeerArchivoGUI
```

La interfaz gráfica permite:

1. Pulsar **"Seleccionar archivo..."** para elegir un archivo de texto
   mediante un `JFileChooser` (filtrado por `.txt`, aunque se pueden
   seleccionar otros tipos de archivo).
2. Pulsar **"Leer archivo"** para leer el archivo seleccionado y mostrar su
   contenido completo en un área de texto desplazable (`JTextArea` dentro de
   un `JScrollPane`).
3. Si ocurre un error de lectura, se muestra un cuadro de diálogo
   (`JOptionPane`) con el mensaje `"No se pudo leer el archivo."` en lugar de
   que el programa termine abruptamente.

### Archivo de prueba

Se incluye el archivo `sample/prueba.txt` con diez líneas de texto
(`Linea 1` a `Linea 10`) para poder probar la GUI inmediatamente al
seleccionar y leer un archivo.

## Diagramas

En la carpeta `diagrams/` se incluyen:

- `class_diagram.png`: diagrama de clases (paquete `Archivos`, clase
  `LeerArchivo` y su dependencia de `FileInputStream`, `InputStreamReader`
  y `BufferedReader`), recreando el diagrama de la página 428 del libro.
- `use_case_diagram.png`: diagrama de casos de uso del actor "Usuario"
  frente a las acciones de seleccionar, leer y visualizar el contenido del
  archivo.
- `Diagramas_LeerArchivo.docx`: documento Word con el enunciado, ambos
  diagramas y su explicación.

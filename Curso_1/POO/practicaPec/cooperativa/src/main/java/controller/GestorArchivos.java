package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class GestorArchivos {

    public static void crearArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }

    // Escribe borrando lo ya guardado. Sirve para usar en un inicio
    public static void escribirArchivo(String nombreArchivo, String contenido) {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(contenido);
            salida.close();
            System.out.println("Se ha escrito en el archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void agregarContenidoArchivo(String nombreArchivo, String contenido) {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.print(contenido);
            salida.close();
            System.out.println("Se ha agregado información en el archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void agregarSaltoDeLinea(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.print("\n");
            salida.close();
            System.out.println("Se ha agregado un salto de línea en el archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void leerArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            while(lectura != null) {
                //System.out.println("lectura = " + lectura);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    // Extrae los datos hasta un máximo indicado como parámetro de todas las líneas que contengan una palabra dada
    public static ArrayList<String> extraerDatosTodasLineasArchivoConPalabra(String nombreArchivo, String palabra, int numDatos) {
        File archivo = new File(nombreArchivo);
        ArrayList<String> datosArray = new ArrayList<>();
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            while(lectura != null) {
                //System.out.println("lectura = " + lectura);
                lectura = entrada.readLine();
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals(palabra)) {
                        for(int i=0; i<numDatos; i++) {
                            datosArray.add(datosFraccionados[i]);
                        }
                    }
                }
                
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        return datosArray;
    }
    
    public static boolean detectarNombreArchivo(String nombreArchivo, String nombre) {
        File archivo = new File(nombreArchivo);
        boolean deteccion = false;
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            boolean nombreExiste = false;
            while(lectura != null && nombreExiste == false) {
                //System.out.println("lectura = " + lectura);
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals(nombre)) {
                        nombreExiste = true;
                        deteccion = true;
                    }
                }
                lectura = entrada.readLine();
            }
            entrada.close();
            return nombreExiste;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        return deteccion;
        
    }
    
    // para detectar un nombre en el archivo y que me devuelva el valor del índice indicado de esa línea detectado en el archivo
    // devuelve null o valor real
    public static String detectarNombreArchivoYDevolverValor(String nombreArchivo, String nombre, int indice) {
        File archivo = new File(nombreArchivo);
        boolean deteccion = false;
        String valorBuscado = "100";
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            boolean nombreExiste = false;
            while(lectura != null && nombreExiste == false) {
                //System.out.println("lectura = " + lectura);
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals(nombre)) {
                        nombreExiste = true;
                        deteccion = true;
                        valorBuscado = datosFraccionados[indice];
                    }
                }
                lectura = entrada.readLine();
            }
            entrada.close();
            return valorBuscado;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        if(deteccion) {
            return valorBuscado;
        } else {
            return "Error!! No existe el valor que se busca";
        }
        
    }
    
    // detecta linea que contiene un nombre pasado por parámetro y devuelve la línea donde se encuentra dicho nombre
    public static String detectarNombreArchivoYDevolverLineaLectura(String nombreArchivo, String nombre) {
        File archivo = new File(nombreArchivo);
        boolean deteccion = false;
        String lineaBuscada = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            boolean nombreExiste = false;
            while(lectura != null && nombreExiste == false) {
                //System.out.println("lectura = " + lectura);
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals(nombre)) {
                        nombreExiste = true;
                        deteccion = true;
                        lineaBuscada = lectura;
                    }
                }
                lectura = entrada.readLine();
            }
            entrada.close();
            return lineaBuscada;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        if(deteccion) {
            return lineaBuscada;
        } else {
            return "Error!! No existe el valor que se busca";
        }
        
    }
    
    public static ArrayList<String> devolverEmailsEnLineaLectura(String lineaLectura) {
        
        ArrayList<String> emailsArray = new ArrayList<>();
        
        //System.out.println("lectura = " + lineaLectura);
        String[] datosFraccionados = lineaLectura.split(",");
        for (String dato : datosFraccionados) {
            if(dato.contains("@")) {
                emailsArray.add(dato);
            }
            
        }

        return emailsArray;
        
    }
    
    public static String devolverNombreArchivoConEmail(String nombreArchivo, String email) {
        File archivo = new File(nombreArchivo);
        boolean deteccion = false;
        String nombreExtraido = "";
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            boolean emailExiste = false;
            while(lectura != null && emailExiste == false) {
                //System.out.println("lectura = " + lectura);
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals(email)) {
                        emailExiste = true;
                        deteccion = true;
                        nombreExtraido = datosFraccionados[1];
                    }
                }
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        return nombreExtraido;
        
    }
    
    public static String devolverFuncionArchivoConEmail(String nombreArchivo, String email) {
        File archivo = new File(nombreArchivo);
        boolean deteccion = false;
        String funcionExtraida = "";
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            boolean emailExiste = false;
            while(lectura != null && emailExiste == false) {
                //System.out.println("lectura = " + lectura);
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals(email)) {
                        emailExiste = true;
                        deteccion = true;
                        funcionExtraida = datosFraccionados[3];
                    }
                }
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        return funcionExtraida;
        
    }
    
    public static String devolverGrupoEspecificoConsumidorConEmail(String nombreArchivo, String email) {
        File archivo = new File(nombreArchivo);
        boolean deteccion = false;
        String grupoEspecificoExtraido = "";
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            boolean emailExiste = false;
            while(lectura != null && emailExiste == false) {
                //System.out.println("lectura = " + lectura);
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals(email)) {
                        emailExiste = true;
                        deteccion = true;
                        grupoEspecificoExtraido = datosFraccionados[4];
                    }
                }
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        return grupoEspecificoExtraido;
        
    }
    
    public static String[] fraccionarLineaLectura(String linea) {
        String[] lineaFraccionada = linea.split(",");
        return lineaFraccionada;
    }
    
    // devuelve array con la lista de palabras posicionadas en el índice indicado
    public static ArrayList<String> devolverArrayArchivoConIndice(String nombreArchivo, int indice) {
        File archivo = new File(nombreArchivo);
        boolean deteccion = false;
        ArrayList<String> productos = new ArrayList();
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            while(lectura != null) {
                //System.out.println("lectura = " + lectura);
                String[] datosFraccionados = lectura.split(",");
                productos.add(datosFraccionados[indice]);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        return productos;
        
    }
    
    public static boolean detectarExistenciaArchivo(String nombreArchivo) {
        
        boolean existe = false;
        //String rutaArchivo = "usuarios.txt";

        // Crea un objeto File para el archivo especificado
        File archivo = new File(nombreArchivo);

        // Comprueba si el archivo existe o no
        if (archivo.exists()) {
            existe = true;
            return existe;
        } else {
            existe = false;
            return existe;
        }
        
    }
    
    // modifica linea del archivo indicado buscando una palabra concreta, eliminando la línea y añadiendo contenido indicado
    public static void modificarLineaArchivo(String nombreArchivo, String nombreABuscar, String contenidoAgregar) {
        crearArchivo("temp.txt");
        
        File archivo = new File(nombreArchivo);
        boolean deteccion = false;
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            boolean nombreExiste = false;
            while(lectura != null && nombreExiste == false) {
                //System.out.println("lectura = " + lectura);
                String[] datosFraccionados = lectura.split(",");
                if(datosFraccionados[0].equals(nombreABuscar)) {
                    agregarContenidoArchivo("temp.txt", contenidoAgregar);
                    agregarSaltoDeLinea("temp.txt");
                } else {
                    agregarContenidoArchivo("temp.txt", lectura);
                    agregarSaltoDeLinea("temp.txt");
                }
                //for (String dato : datosFraccionados) {
                   // if(dato.equals(nombreABuscar)) {
                     //   agregarContenidoArchivo("temp.txt", contenidoAgregar);
                       // agregarSaltoDeLinea("temp.txt");
                    //} else {
                      //  agregarContenidoArchivo("temp.txt", lectura);
                       // agregarSaltoDeLinea("temp.txt");
                    //}
                //}
                lectura = entrada.readLine();
            }
            entrada.close();
            
            archivo.delete();
            File dump = new File(nombreArchivo);
            File nuevoArchivo = new File("temp.txt");
            nuevoArchivo.renameTo(dump);
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    public static String[] extraerDatosTablaCorrespondenciaProductos(String nombreProducto) {
    
        String lineaProducto = detectarNombreArchivoYDevolverLineaLectura("tablaCorrespondenciaProductos.txt", nombreProducto);
        String[] datosTabla = fraccionarLineaLectura(lineaProducto);
        
        return datosTabla;
    }

}

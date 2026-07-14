package ec.edu.ups.bibliotecainterfaz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Utilidad generica para persistir listas de objetos en archivos
 * binarios (serializacion nativa de Java), reutilizable por cualquier
 * DAO que necesite guardar su informacion en disco con extension .ups.
 *
 * Cada DAO que use esta clase gestiona su propio archivo (un archivo
 * por entidad: libros.ups, usuarios.ups, prestamos.ups), tal como lo
 * exige la practica.
 *
 * @author Grupo Biblioteca
 */
public class ArchivoUtil {

    private ArchivoUtil() {
        // clase utilitaria, no se instancia
    }

    /**
     * Escribe el objeto (normalmente una lista) de forma binaria en la
     * ruta indicada, sobrescribiendo el contenido anterior.
     */
    public static void guardar(String rutaArchivo, Serializable datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(datos);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo binario '" + rutaArchivo + "': " + e.getMessage());
        }
    }

    /**
     * Lee y reconstruye el objeto guardado en la ruta indicada. Si el
     * archivo todavia no existe (primera ejecucion del programa) o hay
     * algun problema al leerlo, devuelve null en lugar de lanzar una
     * excepcion, para que el DAO que lo use pueda simplemente arrancar
     * con una lista vacia.
     */
    @SuppressWarnings("unchecked")
    public static <T> T cargar(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo binario '" + rutaArchivo + "': " + e.getMessage());
            return null;
        }
    }
}

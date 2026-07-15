package ec.edu.ups.bibliotecainterfaz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class ArchivoUtil {

    private ArchivoUtil() {
        
    }

    
    public static void guardar(String rutaArchivo, Serializable datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(datos);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo binario '" + rutaArchivo + "': " + e.getMessage());
        }
    }

    
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

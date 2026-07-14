/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package ec.edu.ups.bibliotecainterfaz;
import ec.edu.ups.bibliotecainterfaz.controlador.BibliotecaControlador;
import ec.edu.ups.bibliotecainterfaz.dao.libroDAO;
import ec.edu.ups.bibliotecainterfaz.dao.prestamoDAO;
import ec.edu.ups.bibliotecainterfaz.dao.usuarioDAO;
import ec.edu.ups.bibliotecainterfaz.idao.LibroDaoArchivo;
import ec.edu.ups.bibliotecainterfaz.idao.LibroDaoImpl;
import ec.edu.ups.bibliotecainterfaz.idao.PrestamoDaoArchivo;
import ec.edu.ups.bibliotecainterfaz.idao.PrestamoDaoImpl;
import ec.edu.ups.bibliotecainterfaz.idao.UsuarioDaoArchivo;
import ec.edu.ups.bibliotecainterfaz.idao.UsuarioDaoImpl;
import ec.edu.ups.bibliotecainterfaz.vista.MenuInicio;

public class BibliotecaInterfaz {

    public static void main(String[] args) {

        // Le preguntamos al usuario que mecanismo de persistencia desea usar
        // ANTES de crear el controlador, ya que el controlador necesita
        // recibir un conjunto especifico de DAO (en memoria o en archivo).
        Object[] opciones = {"Memoria / Memory", "Archivo (.ups) / File (.ups)"};
        int seleccion = javax.swing.JOptionPane.showOptionDialog(
            null,
            "Seleccione el mecanismo de persistencia que desea usar:\n" +
            "Select the persistence mechanism you want to use:\n\n" +
            "Memoria: los datos se pierden al cerrar el programa.\n" +
            "Memory: data is lost when the program closes.\n\n" +
            "Archivo: los datos se guardan en archivos .ups y se conservan entre ejecuciones.\n" +
            "File: data is saved to .ups files and kept between runs.",
            "Sistema de Biblioteca - UPS",
            javax.swing.JOptionPane.DEFAULT_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[1]
        );

        usuarioDAO usuarioDao;
        libroDAO libroDao;
        prestamoDAO prestamoDao;

        if (seleccion == 0) {
            // Persistencia en memoria (los datos no se conservan al cerrar)
            usuarioDao = new UsuarioDaoImpl();
            libroDao = new LibroDaoImpl();
            prestamoDao = new PrestamoDaoImpl();
        } else {
            // Persistencia en archivos binarios .ups (opcion por defecto)
            usuarioDao = new UsuarioDaoArchivo();
            libroDao = new LibroDaoArchivo();
            prestamoDao = new PrestamoDaoArchivo();
        }

        BibliotecaControlador controlador = new BibliotecaControlador(usuarioDao, libroDao, prestamoDao);

        java.awt.EventQueue.invokeLater(() -> {
            new MenuInicio(controlador).setVisible(true);
        });
    }
}

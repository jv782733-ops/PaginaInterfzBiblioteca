/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package ec.edu.ups.bibliotecainterfaz;
import ec.edu.ups.bibliotecainterfaz.controlador.BibliotecaControlador;
import ec.edu.ups.bibliotecainterfaz.dao.libroDAO;
import ec.edu.ups.bibliotecainterfaz.dao.prestamoDAO;
import ec.edu.ups.bibliotecainterfaz.dao.usuarioDAO;
import ec.edu.ups.bibliotecainterfaz.idao.LibroDaoImpl;
import ec.edu.ups.bibliotecainterfaz.idao.PrestamoDaoImpl;
import ec.edu.ups.bibliotecainterfaz.idao.UsuarioDaoImpl;
import ec.edu.ups.bibliotecainterfaz.vista.MenuInicio;

public class BibliotecaInterfaz {

    public static void main(String[] args) {
   
    BibliotecaControlador controlador = new BibliotecaControlador(
        new ec.edu.ups.bibliotecainterfaz.idao.UsuarioDaoImpl(),
        new ec.edu.ups.bibliotecainterfaz.idao.LibroDaoImpl(),
        new ec.edu.ups.bibliotecainterfaz.idao.PrestamoDaoImpl()
    );

    java.awt.EventQueue.invokeLater(() -> {
        new MenuInicio(controlador).setVisible(true);
    });
}
}
    

  
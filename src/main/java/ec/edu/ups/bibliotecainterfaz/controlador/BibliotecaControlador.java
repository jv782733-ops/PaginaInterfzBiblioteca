/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.controlador;

import ec.edu.ups.bibliotecainterfaz.dao.libroDAO;
import ec.edu.ups.bibliotecainterfaz.dao.prestamoDAO;
import ec.edu.ups.bibliotecainterfaz.dao.usuarioDAO;
import ec.edu.ups.bibliotecainterfaz.modelo.Libro;
import ec.edu.ups.bibliotecainterfaz.modelo.Prestamo;
import ec.edu.ups.bibliotecainterfaz.modelo.Usuario;
import java.util.Date;
import java.util.ResourceBundle;

/**
 *
 * @author HP
 */
public class BibliotecaControlador {
    private usuarioDAO usuarioDao;
    private libroDAO libroDao;
    private prestamoDAO prestamoDao;

    public BibliotecaControlador(usuarioDAO usuarioDao, libroDAO libroDao, prestamoDAO prestamoDao) {
        this.usuarioDao = usuarioDao;
        this.libroDao = libroDao;
        this.prestamoDao = prestamoDao;
    }

  
    public boolean registrarUsuario(String cedula, String nombre) {
        if (cedula.isEmpty() || nombre.isEmpty()) return false;
        if (usuarioDao.buscar(cedula) != null) return false; 
        usuarioDao.crear(new Usuario(cedula, nombre));
        return true;
    }
    
    public Usuario buscarUsuario(String cedula) { return usuarioDao.buscar(cedula); }
    
    public boolean actualizarUsuario(String cedula, String nombre) {
        if (usuarioDao.buscar(cedula) == null) return false;
        usuarioDao.actualizar(new Usuario(cedula, nombre));
        return true;
    }
    
    public boolean eliminarUsuario(String cedula) {
        if (usuarioDao.buscar(cedula) == null) return false;
        usuarioDao.eliminar(cedula);
        return true;
    }

    
    public boolean registrarLibro(String codigo, String titulo, String autor) {
        if (codigo.isEmpty() || titulo.isEmpty() || autor.isEmpty()) return false;
        if (libroDao.buscar(codigo) != null) return false;
        libroDao.crear(new Libro(codigo, titulo, autor));
        return true;
    }
    
    public Libro buscarLibro(String codigo) { return libroDao.buscar(codigo); }
    
    public boolean actualizarLibro(String codigo, String titulo, String autor) {
        Libro l = libroDao.buscar(codigo);
        if (l == null) return false;
        Libro actualizado = new Libro(codigo, titulo, autor);
        actualizado.setDisponible(l.isDisponible());
        libroDao.actualizar(actualizado);
        return true;
    }
    
    public boolean eliminarLibro(String codigo) {
        if (libroDao.buscar(codigo) == null) return false;
        libroDao.eliminar(codigo);
        return true;
    }

    
    public boolean prestarLibro(String codigoLibro, String cedulaUsuario) {
        Libro libro = libroDao.buscar(codigoLibro);
        Usuario usuario = usuarioDao.buscar(cedulaUsuario);
        
        if (libro == null || usuario == null || !libro.isDisponible()) return false;
        
        libro.setDisponible(false);
        libroDao.actualizar(libro);
        prestamoDao.crear(new Prestamo(libro, usuario, new Date()));
        return true;
    }

    public boolean devolverLibro(String codigoLibro) {
        Libro libro = libroDao.buscar(codigoLibro);
        Prestamo prestamo = prestamoDao.buscar(codigoLibro);
        
        if (libro == null || prestamo == null) return false;
        
        libro.setDisponible(true);
        libroDao.actualizar(libro);
        
        prestamo.setFechaDevolucion(new Date());
        prestamoDao.actualizar(prestamo);
        return true;
    }
    public prestamoDAO getPrestamoDao() {
        return this.prestamoDao;
    }
    public class IdiomaContext {
    private static ResourceBundle mensajes;

    public static void set(ResourceBundle m) {
        mensajes = m;
    }

    public static ResourceBundle get() {
        return mensajes;
    }
}
}

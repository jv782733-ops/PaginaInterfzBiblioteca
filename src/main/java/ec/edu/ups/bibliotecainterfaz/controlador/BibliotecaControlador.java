/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.controlador;

import ec.edu.ups.bibliotecainterfaz.dao.libroDAO;
import ec.edu.ups.bibliotecainterfaz.dao.prestamoDAO;
import ec.edu.ups.bibliotecainterfaz.dao.usuarioDAO;
import ec.edu.ups.bibliotecainterfaz.excepciones.CampoObligatorioException;
import ec.edu.ups.bibliotecainterfaz.excepciones.FechaInvalidaException;
import ec.edu.ups.bibliotecainterfaz.excepciones.LongitudInvalidaException;
import ec.edu.ups.bibliotecainterfaz.excepciones.RestriccionNegocioException;
import ec.edu.ups.bibliotecainterfaz.excepciones.ValorNumericoInvalidoException;
import ec.edu.ups.bibliotecainterfaz.modelo.Libro;
import ec.edu.ups.bibliotecainterfaz.modelo.Prestamo;
import ec.edu.ups.bibliotecainterfaz.modelo.Usuario;
import ec.edu.ups.bibliotecainterfaz.util.Validador;
import java.util.Date;

/**
 * Controlador principal del sistema (patron MVC). A partir de esta version
 * el controlador valida los datos de entrada antes de tocar los DAO, y
 * lanza excepciones propias en lugar de simplemente devolver true/false.
 * Esto permite que la Vista pueda mostrar al usuario exactamente por que
 * fallo la operacion, con un mensaje claro y traducido.
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

    // ---------------------------------------------------------------
    // USUARIOS
    // ---------------------------------------------------------------

    public void registrarUsuario(String cedula, String nombre) throws CampoObligatorioException,
            LongitudInvalidaException, ValorNumericoInvalidoException, RestriccionNegocioException {
        Validador.validarCedula(cedula);
        Validador.validarObligatorio(nombre, "lbl.nombre");
        Validador.validarLongitud(nombre, 3, 50, "lbl.nombre");

        if (usuarioDao.buscar(cedula) != null) {
            throw new RestriccionNegocioException("error.usuarioYaExiste");
        }
        usuarioDao.crear(new Usuario(cedula, nombre));
    }

    public Usuario buscarUsuario(String cedula) {
        return usuarioDao.buscar(cedula);
    }

    public void actualizarUsuario(String cedula, String nombre) throws CampoObligatorioException,
            LongitudInvalidaException, RestriccionNegocioException {
        Validador.validarObligatorio(nombre, "lbl.nombre");
        Validador.validarLongitud(nombre, 3, 50, "lbl.nombre");

        if (usuarioDao.buscar(cedula) == null) {
            throw new RestriccionNegocioException("error.usuarioNoEncontrado");
        }
        usuarioDao.actualizar(new Usuario(cedula, nombre));
    }

    public void eliminarUsuario(String cedula) throws RestriccionNegocioException {
        if (usuarioDao.buscar(cedula) == null) {
            throw new RestriccionNegocioException("error.usuarioNoEncontrado");
        }
        usuarioDao.eliminar(cedula);
    }

    // ---------------------------------------------------------------
    // LIBROS
    // ---------------------------------------------------------------

    public void registrarLibro(String codigo, String titulo, String autor) throws CampoObligatorioException,
            LongitudInvalidaException, RestriccionNegocioException {
        Validador.validarObligatorio(codigo, "lbl.codigo");
        Validador.validarLongitud(codigo, 2, 15, "lbl.codigo");
        Validador.validarObligatorio(titulo, "lbl.titulo");
        Validador.validarLongitud(titulo, 2, 100, "lbl.titulo");
        Validador.validarObligatorio(autor, "lbl.autor");
        Validador.validarLongitud(autor, 2, 60, "lbl.autor");

        if (libroDao.buscar(codigo) != null) {
            throw new RestriccionNegocioException("error.libroYaExiste");
        }
        libroDao.crear(new Libro(codigo, titulo, autor));
    }

    public Libro buscarLibro(String codigo) {
        return libroDao.buscar(codigo);
    }

    public void actualizarLibro(String codigo, String titulo, String autor) throws CampoObligatorioException,
            LongitudInvalidaException, RestriccionNegocioException {
        Validador.validarObligatorio(titulo, "lbl.titulo");
        Validador.validarLongitud(titulo, 2, 100, "lbl.titulo");
        Validador.validarObligatorio(autor, "lbl.autor");
        Validador.validarLongitud(autor, 2, 60, "lbl.autor");

        Libro l = libroDao.buscar(codigo);
        if (l == null) {
            throw new RestriccionNegocioException("error.libroNoEncontrado");
        }
        Libro actualizado = new Libro(codigo, titulo, autor);
        actualizado.setDisponible(l.isDisponible());
        libroDao.actualizar(actualizado);
    }

    public void eliminarLibro(String codigo) throws RestriccionNegocioException {
        if (libroDao.buscar(codigo) == null) {
            throw new RestriccionNegocioException("error.libroNoEncontrado");
        }
        libroDao.eliminar(codigo);
    }

    // ---------------------------------------------------------------
    // PRESTAMOS Y DEVOLUCIONES
    // ---------------------------------------------------------------

    public void prestarLibro(String codigoLibro, String cedulaUsuario) throws CampoObligatorioException,
            RestriccionNegocioException {
        Validador.validarObligatorio(codigoLibro, "lbl.codigo");
        Validador.validarObligatorio(cedulaUsuario, "lbl.cedula");

        Libro libro = libroDao.buscar(codigoLibro);
        if (libro == null) {
            throw new RestriccionNegocioException("error.libroNoEncontrado");
        }
        Usuario usuario = usuarioDao.buscar(cedulaUsuario);
        if (usuario == null) {
            throw new RestriccionNegocioException("error.usuarioNoEncontrado");
        }
        if (!libro.isDisponible()) {
            throw new RestriccionNegocioException("error.libroNoDisponible");
        }

        libro.setEstado(ec.edu.ups.bibliotecainterfaz.modelo.EstadoLibro.PRESTADO);
        libroDao.actualizar(libro);
        prestamoDao.crear(new Prestamo(libro, usuario, new Date()));
    }

    public void devolverLibro(String codigoLibro, Date fechaDevolucion) throws CampoObligatorioException,
            RestriccionNegocioException, FechaInvalidaException {
        Validador.validarObligatorio(codigoLibro, "lbl.codigo");

        Libro libro = libroDao.buscar(codigoLibro);
        if (libro == null) {
            throw new RestriccionNegocioException("error.libroNoEncontrado");
        }
        Prestamo prestamo = prestamoDao.buscar(codigoLibro);
        if (prestamo == null) {
            throw new RestriccionNegocioException("error.prestamoNoEncontrado");
        }

        Validador.validarFechaDevolucion(fechaDevolucion, prestamo.getFechaPrestamo());

        libro.setEstado(ec.edu.ups.bibliotecainterfaz.modelo.EstadoLibro.DISPONIBLE);
        libroDao.actualizar(libro);

        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setEstado(ec.edu.ups.bibliotecainterfaz.modelo.EstadoPrestamo.DEVUELTO);
        prestamoDao.actualizar(prestamo);
    }

    public prestamoDAO getPrestamoDao() {
        return this.prestamoDao;
    }

    public libroDAO getLibroDao() {
        return this.libroDao;
    }

    public usuarioDAO getUsuarioDao() {
        return this.usuarioDao;
    }
}

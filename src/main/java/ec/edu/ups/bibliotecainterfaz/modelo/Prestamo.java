/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.modelo;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Prestamo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Libro libro;
    private Usuario usuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private EstadoPrestamo estado;

    public Prestamo(Libro libro, Usuario usuario, Date fechaPrestamo) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null; // No se dvuelve tdaviA
        this.estado = EstadoPrestamo.ACTIVO;
    }

    public Libro getLibro() { return libro; }
    public Usuario getUsuario() { return usuario; }
    public Date getFechaPrestamo() { return fechaPrestamo; }

    public Date getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(Date fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public EstadoPrestamo getEstado() { return estado; }
    public void setEstado(EstadoPrestamo estado) { this.estado = estado; }
}

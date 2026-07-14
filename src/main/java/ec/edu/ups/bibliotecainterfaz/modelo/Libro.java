/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.modelo;

/**
 *
 * @author HP
 */
public class Libro {
    private String codigo;
    private String titulo;
    private String autor;
    private EstadoLibro estado;

    public Libro() {}

    public Libro(String codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = EstadoLibro.DISPONIBLE;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public EstadoLibro getEstado() { return estado; }
    public void setEstado(EstadoLibro estado) { this.estado = estado; }

    /**
     * Metodo de conveniencia que se mantiene por compatibilidad con el
     * codigo existente. El dato real que se guarda es el enum EstadoLibro;
     * este metodo solo lo traduce a boolean para quien lo necesite asi.
     */
    public boolean isDisponible() {
        return estado == EstadoLibro.DISPONIBLE;
    }

    public void setDisponible(boolean disponible) {
        this.estado = disponible ? EstadoLibro.DISPONIBLE : EstadoLibro.PRESTADO;
    }
}

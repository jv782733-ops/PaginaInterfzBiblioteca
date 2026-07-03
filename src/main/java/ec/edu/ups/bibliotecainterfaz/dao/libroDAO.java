/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.dao;

import ec.edu.ups.bibliotecainterfaz.modelo.Libro;
import java.util.List;

/**
 *
 * @author HP
 */
public interface libroDAO {
    void crear(Libro libro);
    Libro buscar(String codigo);
    void actualizar(Libro libro);
    void eliminar(String codigo);
    List<Libro> listar();
}

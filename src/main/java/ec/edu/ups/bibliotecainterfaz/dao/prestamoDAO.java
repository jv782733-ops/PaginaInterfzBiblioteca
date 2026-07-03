/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.dao;

import ec.edu.ups.bibliotecainterfaz.modelo.Prestamo;
import java.util.List;

/**
 *
 * @author HP
 */
public interface prestamoDAO {
    void crear(Prestamo prestamo);
    Prestamo buscar(String codigoLibro);
    void actualizar(Prestamo prestamo);
    List<Prestamo> listar();
}

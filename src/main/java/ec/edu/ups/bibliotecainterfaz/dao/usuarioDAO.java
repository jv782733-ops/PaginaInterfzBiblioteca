/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.dao;

import ec.edu.ups.bibliotecainterfaz.modelo.Usuario;
import java.util.List;

/**
 *
 * @author HP
 */
public interface usuarioDAO {
    void crear(Usuario usuario);
    Usuario buscar(String cedula);
    void actualizar(Usuario usuario);
    void eliminar(String cedula);
    List<Usuario> listar();
}

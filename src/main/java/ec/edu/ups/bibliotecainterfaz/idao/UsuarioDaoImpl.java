/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.idao;

import ec.edu.ups.bibliotecainterfaz.dao.usuarioDAO;
import ec.edu.ups.bibliotecainterfaz.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class UsuarioDaoImpl implements usuarioDAO {
    private List<Usuario> listaUsuarios = new ArrayList<>();

    @Override
    public void crear(Usuario usuario) {
      listaUsuarios.add(usuario);
    }

    @Override
    public Usuario buscar(String cedula) {
        for (Usuario u : listaUsuarios) {
            if (u.getCedula().equals(cedula)) return u;
        }
        return null;
    }

    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getCedula().equals(usuario.getCedula())) {
                listaUsuarios.set(i, usuario);
                break;
            }
        }
    }

    @Override
    public void eliminar(String cedula) {
        listaUsuarios.removeIf(u -> u.getCedula().equals(cedula));
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(listaUsuarios);
    }
}

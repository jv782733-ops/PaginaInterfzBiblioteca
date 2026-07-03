/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.idao;

import ec.edu.ups.bibliotecainterfaz.dao.libroDAO;
import ec.edu.ups.bibliotecainterfaz.modelo.Libro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class LibroDaoImpl implements libroDAO {
    private List<Libro> listaLibros = new ArrayList<>();

    @Override
    public void crear(Libro libro) {
        listaLibros.add(libro);
    }

    @Override
    public Libro buscar(String codigo) {
        for (Libro l : listaLibros) {
            if (l.getCodigo().equals(codigo)) return l;
        }
        return null;
    }

    @Override
    public void actualizar(Libro libro) {
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getCodigo().equals(libro.getCodigo())) {
                listaLibros.set(i, libro);
                break;
            }
        }
    }

    @Override
    public void eliminar(String codigo) {
        listaLibros.removeIf(l -> l.getCodigo().equals(codigo));
    }

    @Override
    public List<Libro> listar() {
       return new ArrayList<>(listaLibros);
    }
}

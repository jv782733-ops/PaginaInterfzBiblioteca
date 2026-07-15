/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.bibliotecainterfaz.idao;

import ec.edu.ups.bibliotecainterfaz.dao.prestamoDAO;
import ec.edu.ups.bibliotecainterfaz.modelo.Prestamo;
import java.util.ArrayList;
import java.util.List;


public class PrestamoDaoImpl implements prestamoDAO {
    private List<Prestamo> listaPrestamos = new ArrayList<>();

    @Override
    public void crear(Prestamo prestamo) {
        listaPrestamos.add(prestamo);
    }

    @Override
    public Prestamo buscar(String codigoLibro) {
        for (Prestamo p : listaPrestamos) {
            if (p.getLibro().getCodigo().equals(codigoLibro) && p.getFechaDevolucion() == null) {
                return p ;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Prestamo prestamo) {
       for (int i = 0; i < listaPrestamos.size(); i++) {
            if (listaPrestamos.get(i).getLibro().getCodigo().equals(prestamo.getLibro().getCodigo())) {
                listaPrestamos.set(i, prestamo);
                break;
            }
        }
    }

    @Override
    public List<Prestamo> listar() {
        return new ArrayList<>(listaPrestamos);
    }
}

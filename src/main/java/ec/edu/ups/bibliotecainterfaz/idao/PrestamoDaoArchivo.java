package ec.edu.ups.bibliotecainterfaz.idao;

import ec.edu.ups.bibliotecainterfaz.dao.prestamoDAO;
import ec.edu.ups.bibliotecainterfaz.modelo.Prestamo;
import ec.edu.ups.bibliotecainterfaz.util.ArchivoUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion del DAO de prestamos que persiste la informacion en un
 * archivo binario propio (prestamos.ups). Ver LibroDaoArchivo para una
 * explicacion del mecanismo de carga/guardado.
 *
 * @author Grupo Biblioteca
 */
public class PrestamoDaoArchivo implements prestamoDAO {

    private static final String ARCHIVO = "prestamos.ups";
    private List<Prestamo> listaPrestamos;

    public PrestamoDaoArchivo() {
        List<Prestamo> cargados = ArchivoUtil.cargar(ARCHIVO);
        this.listaPrestamos = (cargados != null) ? cargados : new ArrayList<>();
    }

    private void guardarEnArchivo() {
        ArchivoUtil.guardar(ARCHIVO, (java.io.Serializable) listaPrestamos);
    }

    @Override
    public void crear(Prestamo prestamo) {
        listaPrestamos.add(prestamo);
        guardarEnArchivo();
    }

    @Override
    public Prestamo buscar(String codigoLibro) {
        for (Prestamo p : listaPrestamos) {
            if (p.getLibro().getCodigo().equals(codigoLibro) && p.getFechaDevolucion() == null) {
                return p;
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
        guardarEnArchivo();
    }

    @Override
    public List<Prestamo> listar() {
        return new ArrayList<>(listaPrestamos);
    }
}

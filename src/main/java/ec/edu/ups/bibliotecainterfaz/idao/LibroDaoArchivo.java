package ec.edu.ups.bibliotecainterfaz.idao;

import ec.edu.ups.bibliotecainterfaz.dao.libroDAO;
import ec.edu.ups.bibliotecainterfaz.modelo.Libro;
import ec.edu.ups.bibliotecainterfaz.util.ArchivoUtil;
import java.util.ArrayList;
import java.util.List;


public class LibroDaoArchivo implements libroDAO {

    private static final String ARCHIVO = "libros.ups";
    private List<Libro> listaLibros;

    public LibroDaoArchivo() {
        List<Libro> cargados = ArchivoUtil.cargar(ARCHIVO);
        this.listaLibros = (cargados != null) ? cargados : new ArrayList<>();
    }

    private void guardarEnArchivo() {
        ArchivoUtil.guardar(ARCHIVO, (java.io.Serializable) listaLibros);
    }

    @Override
    public void crear(Libro libro) {
        listaLibros.add(libro);
        guardarEnArchivo();
    }

    @Override
    public Libro buscar(String codigo) {
        for (Libro l : listaLibros) {
            if (l.getCodigo().equals(codigo)) {
                return l;
            }
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
        guardarEnArchivo();
    }

    @Override
    public void eliminar(String codigo) {
        listaLibros.removeIf(l -> l.getCodigo().equals(codigo));
        guardarEnArchivo();
    }

    @Override
    public List<Libro> listar() {
        return new ArrayList<>(listaLibros);
    }
}

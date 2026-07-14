package ec.edu.ups.bibliotecainterfaz.idao;

import ec.edu.ups.bibliotecainterfaz.dao.usuarioDAO;
import ec.edu.ups.bibliotecainterfaz.modelo.Usuario;
import ec.edu.ups.bibliotecainterfaz.util.ArchivoUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion del DAO de usuarios que persiste la informacion en un
 * archivo binario propio (usuarios.ups). Ver LibroDaoArchivo para una
 * explicacion del mecanismo de carga/guardado.
 *
 * @author Grupo Biblioteca
 */
public class UsuarioDaoArchivo implements usuarioDAO {

    private static final String ARCHIVO = "usuarios.ups";
    private List<Usuario> listaUsuarios;

    public UsuarioDaoArchivo() {
        List<Usuario> cargados = ArchivoUtil.cargar(ARCHIVO);
        this.listaUsuarios = (cargados != null) ? cargados : new ArrayList<>();
    }

    private void guardarEnArchivo() {
        ArchivoUtil.guardar(ARCHIVO, (java.io.Serializable) listaUsuarios);
    }

    @Override
    public void crear(Usuario usuario) {
        listaUsuarios.add(usuario);
        guardarEnArchivo();
    }

    @Override
    public Usuario buscar(String cedula) {
        for (Usuario u : listaUsuarios) {
            if (u.getCedula().equals(cedula)) {
                return u;
            }
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
        guardarEnArchivo();
    }

    @Override
    public void eliminar(String cedula) {
        listaUsuarios.removeIf(u -> u.getCedula().equals(cedula));
        guardarEnArchivo();
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(listaUsuarios);
    }
}

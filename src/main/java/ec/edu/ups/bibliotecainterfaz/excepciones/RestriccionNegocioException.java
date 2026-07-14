package ec.edu.ups.bibliotecainterfaz.excepciones;

/**
 * Se lanza cuando se viola una restriccion de negocio definida por el
 * grupo, que no es simplemente un error de formato del dato sino una
 * regla propia del dominio de la biblioteca. Ejemplos:
 *  - Registrar un usuario o libro con un codigo/cedula que ya existe.
 *  - Prestar un libro que no esta disponible.
 *  - Devolver un libro que no tiene un prestamo activo.
 *
 * @author Grupo Biblioteca
 */
public class RestriccionNegocioException extends ValidacionException {

    public RestriccionNegocioException(String claveMensaje, Object... argumentos) {
        super(claveMensaje, argumentos);
    }
}

package ec.edu.ups.bibliotecainterfaz.excepciones;

/**
 * Se lanza cuando una fecha ingresada no es valida: puede ser una fecha
 * futura donde no se permite, o una fecha de devolucion anterior a la
 * fecha del prestamo, etc. La clave de mensaje especifica indica la
 * razon exacta (para poder traducirla correctamente).
 *
 * @author Grupo Biblioteca
 */
public class FechaInvalidaException extends ValidacionException {

    public FechaInvalidaException(String claveMotivo, Object... argumentos) {
        super(claveMotivo, argumentos);
    }
}

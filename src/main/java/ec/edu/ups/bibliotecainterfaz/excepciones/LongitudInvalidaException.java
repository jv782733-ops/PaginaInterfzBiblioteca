package ec.edu.ups.bibliotecainterfaz.excepciones;

/**
 * Se lanza cuando un campo no cumple con la longitud minima o maxima
 * definida para ese dato (por ejemplo el nombre de un usuario, el
 * titulo de un libro, etc).
 *
 * @author Grupo Biblioteca
 */
public class LongitudInvalidaException extends ValidacionException {

    public LongitudInvalidaException(String nombreCampo, int minimo, int maximo) {
        super("error.longitudInvalida", nombreCampo, minimo, maximo);
    }
}

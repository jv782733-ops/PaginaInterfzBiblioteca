package ec.edu.ups.bibliotecainterfaz.excepciones;

/**
 * Se lanza cuando un campo obligatorio del formulario viene vacio.
 *
 * @author Grupo Biblioteca
 */
public class CampoObligatorioException extends ValidacionException {

    public CampoObligatorioException(String nombreCampo) {
        super("error.campoObligatorio", nombreCampo);
    }
}

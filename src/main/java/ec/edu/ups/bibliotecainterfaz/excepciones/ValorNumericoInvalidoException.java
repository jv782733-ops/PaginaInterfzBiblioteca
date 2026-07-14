package ec.edu.ups.bibliotecainterfaz.excepciones;

/**
 * Se lanza cuando un campo que debia contener solo digitos numericos
 * tiene letras u otros caracteres invalidos (por ejemplo la cedula).
 *
 * @author Grupo Biblioteca
 */
public class ValorNumericoInvalidoException extends ValidacionException {

    public ValorNumericoInvalidoException(String nombreCampo) {
        super("error.valorNumericoInvalido", nombreCampo);
    }
}

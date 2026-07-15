package ec.edu.ups.bibliotecainterfaz.excepciones;


public class ValorNumericoInvalidoException extends ValidacionException {

    public ValorNumericoInvalidoException(String nombreCampo) {
        super("error.valorNumericoInvalido", nombreCampo);
    }
}

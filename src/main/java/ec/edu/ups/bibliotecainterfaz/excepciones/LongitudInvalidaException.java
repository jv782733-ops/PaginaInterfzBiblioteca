package ec.edu.ups.bibliotecainterfaz.excepciones;


public class LongitudInvalidaException extends ValidacionException {

    public LongitudInvalidaException(String nombreCampo, int minimo, int maximo) {
        super("error.longitudInvalida", nombreCampo, minimo, maximo);
    }
}

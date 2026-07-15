package ec.edu.ups.bibliotecainterfaz.excepciones;


public class FechaInvalidaException extends ValidacionException {

    public FechaInvalidaException(String claveMotivo, Object... argumentos) {
        super(claveMotivo, argumentos);
    }
}

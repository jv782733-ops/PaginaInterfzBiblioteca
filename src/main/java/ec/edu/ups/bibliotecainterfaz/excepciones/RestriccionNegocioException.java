package ec.edu.ups.bibliotecainterfaz.excepciones;


public class RestriccionNegocioException extends ValidacionException {

    public RestriccionNegocioException(String claveMensaje, Object... argumentos) {
        super(claveMensaje, argumentos);
    }
}

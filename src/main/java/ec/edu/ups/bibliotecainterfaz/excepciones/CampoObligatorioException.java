package ec.edu.ups.bibliotecainterfaz.excepciones;


public class CampoObligatorioException extends ValidacionException {

    public CampoObligatorioException(String nombreCampo) {
        super("error.campoObligatorio", nombreCampo);
    }
}

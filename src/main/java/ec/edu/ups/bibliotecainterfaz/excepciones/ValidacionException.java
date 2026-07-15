package ec.edu.ups.bibliotecainterfaz.excepciones;

import java.text.MessageFormat;
import java.util.ResourceBundle;


public abstract class ValidacionException extends Exception {

    private final String claveMensaje;
    private final Object[] argumentos;

    public ValidacionException(String claveMensaje, Object... argumentos) {
        super(claveMensaje);
        this.claveMensaje = claveMensaje;
        this.argumentos = argumentos;
    }

    public String getClaveMensaje() {
        return claveMensaje;
    }

    public Object[] getArgumentos() {
        return argumentos;
    }

    /**
     * Devuelve el mensaje de error ya traducido y formateado, listo para
     * mostrarse al usuario en un JOptionPane.
     *
     * @param mensajes el ResourceBundle del idioma actualmente activo
     * @return el mensaje traducido, o la clave sin traducir si no se
     *         encuentra el ResourceBundle o la clave (para no romper la
     *         aplicacion si falta alguna traduccion)
     */
    public String getMensajeLocalizado(ResourceBundle mensajes) {
        if (mensajes == null) {
            return claveMensaje;
        }
        try {
            String patron = mensajes.getString(claveMensaje);
            Object[] argumentosTraducidos = new Object[argumentos.length];
            for (int i = 0; i < argumentos.length; i++) {
                argumentosTraducidos[i] = resolverArgumento(argumentos[i], mensajes);
            }
            return MessageFormat.format(patron, argumentosTraducidos);
        } catch (Exception e) {
            return claveMensaje;
        }
    }

    /**
     * Si el argumento es un String que a su vez es una clave valida del
     * ResourceBundle (por ejemplo "lbl.cedula"), lo traduce y le quita
     * los dos puntos finales (":") que suelen tener las etiquetas de
     * formulario, ya que dentro de una oracion no quedan bien. Si no es
     * una clave conocida, se devuelve el argumento tal cual (numeros,
     * fechas, texto libre, etc).
     */
    private Object resolverArgumento(Object argumento, ResourceBundle mensajes) {
        if (argumento instanceof String) {
            String texto = (String) argumento;
            if (mensajes.containsKey(texto)) {
                String traducido = mensajes.getString(texto);
                return traducido.endsWith(":") ? traducido.substring(0, traducido.length() - 1) : traducido;
            }
        }
        return argumento;
    }
}

package ec.edu.ups.bibliotecainterfaz.util;

import ec.edu.ups.bibliotecainterfaz.excepciones.CampoObligatorioException;
import ec.edu.ups.bibliotecainterfaz.excepciones.FechaInvalidaException;
import ec.edu.ups.bibliotecainterfaz.excepciones.LongitudInvalidaException;
import ec.edu.ups.bibliotecainterfaz.excepciones.ValorNumericoInvalidoException;

import java.util.Date;

/**
 * Clase utilitaria con metodos de validacion reutilizables. Cada metodo
 * lanza la excepcion propia correspondiente si el dato no es valido,
 * y simplemente no hace nada (retorna con normalidad) si el dato es
 * correcto. Asi el controlador puede encadenar varias validaciones de
 * forma clara sin repetir logica de if/else en cada metodo de negocio.
 *
 * @author Grupo Biblioteca
 */
public class Validador {

    private Validador() {
        // clase utilitaria, no se instancia
    }

    /**
     * Valida que un campo de texto no venga vacio ni sea null.
     */
    public static void validarObligatorio(String valor, String nombreCampo) throws CampoObligatorioException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new CampoObligatorioException(nombreCampo);
        }
    }

    /**
     * Valida que un texto tenga una longitud dentro del rango [minimo, maximo].
     */
    public static void validarLongitud(String valor, int minimo, int maximo, String nombreCampo)
            throws LongitudInvalidaException {
        int longitud = valor == null ? 0 : valor.trim().length();
        if (longitud < minimo || longitud > maximo) {
            throw new LongitudInvalidaException(nombreCampo, minimo, maximo);
        }
    }

    /**
     * Valida que un texto contenga unicamente digitos numericos.
     */
    public static void validarSoloNumeros(String valor, String nombreCampo) throws ValorNumericoInvalidoException {
        if (valor == null || !valor.trim().matches("\\d+")) {
            throw new ValorNumericoInvalidoException(nombreCampo);
        }
    }

    /**
     * Restriccion definida por el grupo: la cedula ecuatoriana debe tener
     * exactamente 10 digitos numericos.
     */
    public static void validarCedula(String cedula) throws CampoObligatorioException,
            ValorNumericoInvalidoException, LongitudInvalidaException {
        validarObligatorio(cedula, "lbl.cedula");
        validarSoloNumeros(cedula, "lbl.cedula");
        validarLongitud(cedula, 10, 10, "lbl.cedula");
    }

    /**
     * Valida que una fecha de devolucion no sea anterior a la fecha del
     * prestamo (no tendria sentido devolver un libro "antes" de haberlo
     * prestado) ni una fecha futura (no se puede registrar una devolucion
     * que todavia no ha ocurrido).
     */
    public static void validarFechaDevolucion(Date fechaDevolucion, Date fechaPrestamo) throws FechaInvalidaException {
        if (fechaDevolucion == null) {
            throw new FechaInvalidaException("error.fechaObligatoria");
        }
        Date hoy = new Date();
        if (fechaDevolucion.after(hoy)) {
            throw new FechaInvalidaException("error.fechaFutura");
        }
        if (fechaPrestamo != null && fechaDevolucion.before(fechaPrestamo)) {
            throw new FechaInvalidaException("error.fechaAnteriorAlPrestamo");
        }
    }
}

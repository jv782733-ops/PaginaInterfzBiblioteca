package ec.edu.ups.bibliotecainterfaz.util;

import ec.edu.ups.bibliotecainterfaz.excepciones.CampoObligatorioException;
import ec.edu.ups.bibliotecainterfaz.excepciones.FechaInvalidaException;
import ec.edu.ups.bibliotecainterfaz.excepciones.LongitudInvalidaException;
import ec.edu.ups.bibliotecainterfaz.excepciones.ValorNumericoInvalidoException;

import java.util.Date;


public class Validador {

    private Validador() {
        
    }

    public static void validarObligatorio(String valor, String nombreCampo) throws CampoObligatorioException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new CampoObligatorioException(nombreCampo);
        }
    }

    public static void validarLongitud(String valor, int minimo, int maximo, String nombreCampo)
            throws LongitudInvalidaException {
        int longitud = valor == null ? 0 : valor.trim().length();
        if (longitud < minimo || longitud > maximo) {
            throw new LongitudInvalidaException(nombreCampo, minimo, maximo);
        }
    }

    public static void validarSoloNumeros(String valor, String nombreCampo) throws ValorNumericoInvalidoException {
        if (valor == null || !valor.trim().matches("\\d+")) {
            throw new ValorNumericoInvalidoException(nombreCampo);
        }
    }

    public static void validarCedula(String cedula) throws CampoObligatorioException,
            ValorNumericoInvalidoException, LongitudInvalidaException {
        validarObligatorio(cedula, "lbl.cedula");
        validarSoloNumeros(cedula, "lbl.cedula");
        validarLongitud(cedula, 10, 10, "lbl.cedula");
    }

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

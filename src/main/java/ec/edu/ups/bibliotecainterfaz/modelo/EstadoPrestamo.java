package ec.edu.ups.bibliotecainterfaz.modelo;

/**
 * Representa el estado de un prestamo: si el libro todavia esta en
 * poder del usuario (ACTIVO) o si ya fue devuelto (DEVUELTO). Antes
 * este dato se mostraba con un texto fijo "PRESTADO" en la tabla sin
 * importar si el prestamo ya se habia cerrado o no; con el enum el
 * estado real del prestamo queda representado explicitamente.
 *
 * @author Grupo Biblioteca
 */
public enum EstadoPrestamo {
    ACTIVO,
    DEVUELTO
}

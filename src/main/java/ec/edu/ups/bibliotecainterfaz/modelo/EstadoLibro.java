package ec.edu.ups.bibliotecainterfaz.modelo;

/**
 * Representa el estado en el que puede encontrarse un libro dentro del
 * sistema. Se modela como enum en lugar de un simple boolean o String
 * porque es informacion de tipo fijo: un libro solo puede estar en uno
 * de estos dos estados posibles, no hay valores intermedios ni texto
 * libre que tenga sentido aqui.
 *
 * @author Grupo Biblioteca
 */
public enum EstadoLibro {
    DISPONIBLE,
    PRESTADO
}

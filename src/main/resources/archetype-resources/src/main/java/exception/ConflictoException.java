package ${package}.exception;

/**
 * Se lanza cuando la operacion viola una regla de negocio (HTTP 409).
 */
public class ConflictoException extends RuntimeException {

    public ConflictoException(String mensaje) {
        super(mensaje);
    }
}

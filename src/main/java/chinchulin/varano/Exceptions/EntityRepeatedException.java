package chinchulin.varano.Exceptions;

/**
 * Se lanza esta excepción cuando se pretende cargar un registro ya existente o
 * donde alguno de sus campos `unicos` ya se encuentran registrados.
 */
public class EntityRepeatedException extends RuntimeException {

    public EntityRepeatedException() {
        super();
    }

    public EntityRepeatedException(String message) {
        super(message);
    }

    public EntityRepeatedException(String message, Throwable cause) {
        super(message, cause);
    }
}

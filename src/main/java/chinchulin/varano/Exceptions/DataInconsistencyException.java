package chinchulin.varano.Exceptions;

/**
 * Esta excepción se lanza cuando hay una inconsistencia o incoherencia en los datos cargados.
 */
public class DataInconsistencyException extends RuntimeException {

    public DataInconsistencyException() {
        super();
    }

    public DataInconsistencyException(String message) {
        super(message);
    }
}

package nikolaev.artem.exceptions;

/**
 * Непроверяемое исключение.
 * Просиходит в том случае, когда не удается прочитать содержимое базовой директории.
 */
public class BaseDirReadException extends RuntimeException {
    public BaseDirReadException() {
    }

    public BaseDirReadException(String message) {
        super(message);
    }

    public BaseDirReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseDirReadException(Throwable cause) {
        super(cause);
    }
}

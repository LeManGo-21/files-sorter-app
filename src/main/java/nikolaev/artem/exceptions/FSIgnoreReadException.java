package nikolaev.artem.exceptions;

/**
 * Непроверяемое исключение.
 * Происходит в том случае, когда не удается прочитать файл FSIgnore.TXT
 */
public class FSIgnoreReadException extends RuntimeException {
    public FSIgnoreReadException() {
    }

    public FSIgnoreReadException(String message) {
        super(message);
    }

    public FSIgnoreReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FSIgnoreReadException(Throwable cause) {
        super(cause);
    }
}

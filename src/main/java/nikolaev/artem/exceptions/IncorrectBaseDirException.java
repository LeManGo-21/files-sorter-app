package nikolaev.artem.exceptions;

/**
 * Непроверяемое исключение.
 * Происходит в том случае, когда устанавливается некоректный путь директории
 * или когда дериктории по такому пути не существует.
 */
public class IncorrectBaseDirException extends RuntimeException{
    public IncorrectBaseDirException() {
    }

    public IncorrectBaseDirException(String message) {
        super(message);
    }

    public IncorrectBaseDirException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectBaseDirException(Throwable cause) {
        super(cause);
    }
}

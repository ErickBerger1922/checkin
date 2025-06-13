package backend.backend2.domain.exception;

public class ConflitoException extends RuntimeException {

    public ConflitoException(String message) {
        super(message);
    }

    public ConflitoException(String message, Throwable cause) {
        super(message, cause);
    }
}

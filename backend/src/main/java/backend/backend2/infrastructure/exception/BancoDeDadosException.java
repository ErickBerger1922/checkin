package backend.backend2.infrastructure.exception;

public class BancoDeDadosException extends RuntimeException {

    public BancoDeDadosException(String message) {
        super(message);
    }

    public BancoDeDadosException(String message, Throwable cause) {
        super(message, cause);
    }
}

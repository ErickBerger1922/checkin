package backend.backend2.domain.exception;

public class DadosInconsistentesException extends RuntimeException{

    public DadosInconsistentesException(String message) {
        super(message);
    }

    public DadosInconsistentesException(String message, Throwable cause) {
        super(message, cause);
    }
}

package exception;

// Excepción para validaciones de formato y datos de entrada
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
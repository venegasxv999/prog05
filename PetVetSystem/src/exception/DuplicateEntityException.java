package exception;

// Excepción para elementos que no pueden repetirse (ej. Cédula duplicada)
public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String message) {
        super(message);
    }
}
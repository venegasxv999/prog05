package exception;

// Excepción para reglas del negocio (ej. Límite de cupos superado)
public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
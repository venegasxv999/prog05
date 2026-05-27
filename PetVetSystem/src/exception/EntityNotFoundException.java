package exception;

// Excepción para búsquedas sin resultados (ej. Dueño o cita no encontrados)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
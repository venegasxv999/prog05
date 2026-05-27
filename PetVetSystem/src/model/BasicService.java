package model;

// Implementación de un servicio básico
public class BasicService extends Service {
    private int estimatedDurationMins;
    private boolean includesHealthCertificate;

    public BasicService(String code, String name, String description, String date,
                        String startTime, String endTime, int totalQuotas, double basePrice,
                        int estimatedDurationMins, boolean includesHealthCertificate) {
        super(code, name, description, date, startTime, endTime, totalQuotas, basePrice);
        this.estimatedDurationMins = estimatedDurationMins;
        this.includesHealthCertificate = includesHealthCertificate;
    }

    // Polimorfismo: El precio final de un servicio básico es igual a su precio base
    @Override
    public double calculateFinalPrice() {
        return this.basePrice;
    }
}
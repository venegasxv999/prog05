package model;

// Implementación de un servicio especializado
public class SpecializedService extends Service {
    private String specialty;
    private boolean requiresExams;
    private double additionalCharge;

    public SpecializedService(String code, String name, String description, String date,
                              String startTime, String endTime, int totalQuotas, double basePrice,
                              String specialty, boolean requiresExams, double additionalCharge) {
        super(code, name, description, date, startTime, endTime, totalQuotas, basePrice);
        this.specialty = specialty;
        this.requiresExams = requiresExams;
        this.additionalCharge = additionalCharge;
    }

    // Polimorfismo: Suma el recargo por especialización al precio base
    @Override
    public double calculateFinalPrice() {
        return this.basePrice + this.additionalCharge;
    }
}
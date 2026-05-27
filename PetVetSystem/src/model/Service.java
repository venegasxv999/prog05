package model;

import model.enums.ServiceStatus;

// Clase abstracta que define la estructura base de todo servicio
public abstract class Service {
    protected String code;
    protected String name;
    protected String description;
    protected String date;
    protected String startTime;
    protected String endTime;
    protected int totalQuotas;
    protected int remainingQuotas;
    protected double basePrice;
    protected ServiceStatus status;

    public Service(String code, String name, String description, String date,
                   String startTime, String endTime, int totalQuotas, double basePrice) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalQuotas = totalQuotas;
        this.remainingQuotas = totalQuotas; // Inicia con la misma cantidad de cupos totales
        this.basePrice = basePrice;
        this.status = ServiceStatus.AVAILABLE;
    }

    // Método abstracto que garantiza el polimorfismo
    public abstract double calculateFinalPrice();

    // Getters y setters principales
    public String getCode() { return code; }
    public String getName() { return name; }
    public int getRemainingQuotas() { return remainingQuotas; }
    public double getBasePrice() { return basePrice; }
    public ServiceStatus getStatus() { return status; }
 // Setters para actualizar los cupos y el estado del servicio
    public void setRemainingQuotas(int remainingQuotas) { this.remainingQuotas = remainingQuotas; }
    public void setStatus(ServiceStatus status) { this.status = status; }

    @Override
    public String toString() {
        return code + " - " + name + " (Cupos: " + remainingQuotas + ") | Precio: $" + calculateFinalPrice();
    }
}
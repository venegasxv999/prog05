package model;

import model.enums.AppointmentStatus;

// Representa una cita médica programada
public class Appointment {
    private String appointmentCode;
    private Owner owner;
    private Service service;
    private int reservedQuotas;
    private String bookingDate;
    private double totalPrice;
    private AppointmentStatus status;

    public Appointment(String appointmentCode, Owner owner, Service service,
                       int reservedQuotas, String bookingDate) {
        this.appointmentCode = appointmentCode;
        this.owner = owner;
        this.service = service;
        this.reservedQuotas = reservedQuotas;
        this.bookingDate = bookingDate;

        // Uso del polimorfismo para calcular el total sin importar qué tipo de servicio sea
        this.totalPrice = service.calculateFinalPrice() * reservedQuotas;
        this.status = AppointmentStatus.CONFIRMED;
    }

    public String getAppointmentCode() { return appointmentCode; }
    public Owner getOwner() { return owner; }
    public Service getService() { return service; }
    public int getReservedQuotas() { return reservedQuotas; }
    public AppointmentStatus getStatus() { return status; }
    public double getTotalPrice() { return totalPrice; }

    public void setStatus(AppointmentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Cita " + appointmentCode + " | Dueño: " + owner.getFirstName() + " | Servicio: "
                + service.getName() + " | Estado: " + status + " | Total: $" + totalPrice;
    }
}
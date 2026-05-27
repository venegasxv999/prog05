package service;

import model.*;
import model.enums.AppointmentStatus;
import model.enums.ServiceStatus;
import exception.*;
import java.util.ArrayList;
import java.util.List;

// Clase central encargada de procesar todas las reglas del negocio
public class VetManagementService {

    private List<Owner> owners;
    private List<Service> services;
    private List<Appointment> appointments;

    public VetManagementService() {
        this.owners = new ArrayList<>();
        this.services = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    // Caso 1: Registrar Dueño
    public void registerOwner(Owner owner) {
        // Validar reglas exactas de negocio solicitadas
        if (!owner.getEmail().contains("@")) {
            throw new ValidationException("El email debe contener el símbolo @");
        }
        if (owner.getPetAge() < 0) {
            throw new ValidationException("La edad de la mascota debe ser mayor o igual a cero");
        }
        if (findOwnerById(owner.getIdCard()) != null) {
            throw new DuplicateEntityException("Ya existe un dueño registrado con esa cédula");
        }
        owners.add(owner);
    }

    // Caso 2: Crear servicio
    public void registerService(Service service) {
        if (service.getBasePrice() <= 0) {
            throw new ValidationException("El precio del servicio debe ser mayor a cero");
        }
        if (service.getRemainingQuotas() <= 0) {
            throw new ValidationException("Los cupos totales deben ser mayor a 0");
        }
        for (Service s : services) {
            if (s.getCode().equals(service.getCode())) {
                throw new DuplicateEntityException("Ya existe un servicio con ese código");
            }
        }
        services.add(service);
    }

    // Caso 3: Agendar cita
    public void bookAppointment(String appointmentCode, String ownerId, String serviceCode, int quotas, String currentDate) {
        // Validaciones previas
        for (Appointment app : appointments) {
            if (app.getAppointmentCode().equals(appointmentCode)) {
                throw new DuplicateEntityException("Ya existe una cita con ese código");
            }
        }
        if (quotas < 1 || quotas > 3) {
            throw new BusinessRuleException("No se pueden reservar más de 3 cupos por cita");
        }

        Owner owner = findOwnerById(ownerId);
        if (owner == null) {
            throw new EntityNotFoundException("No se encontró el dueño con esa cédula");
        }

        Service service = findServiceByCode(serviceCode);
        if (service == null || service.getStatus() != ServiceStatus.AVAILABLE) {
            throw new BusinessRuleException("El servicio no está disponible o no existe");
        }

        if (service.getRemainingQuotas() < quotas) {
            throw new BusinessRuleException("No hay cupos disponibles para este servicio");
        }

        // Se reduce la cantidad de cupos (estado de los objetos)
        service.setRemainingQuotas(service.getRemainingQuotas() - quotas);

        // Se crea y almacena la cita
        Appointment appointment = new Appointment(appointmentCode, owner, service, quotas, currentDate);
        appointments.add(appointment);
    }

    // Caso 4: Cancelar cita
    public void cancelAppointment(String appointmentCode) {
        Appointment appointment = getAppointmentByCode(appointmentCode);
        if (appointment == null) {
            throw new EntityNotFoundException("No se encontró la cita con ese código");
        }

        if (appointment.getStatus() == AppointmentStatus.CANCELED) {
            throw new BusinessRuleException("La cita ya se encuentra cancelada");
        }

        appointment.setStatus(AppointmentStatus.CANCELED);

        // Devolver los cupos al servicio
        Service service = appointment.getService();
        service.setRemainingQuotas(service.getRemainingQuotas() + appointment.getReservedQuotas());
    }

    // Métodos de Reportes y Búsquedas
    public Appointment getAppointmentByCode(String code) {
        for (Appointment app : appointments) {
            if (app.getAppointmentCode().equals(code)) {
                return app;
            }
        }
        return null;
    }

    public int getTotalRegisteredOwners() {
        return owners.size();
    }

    public List<Appointment> getAppointmentsByOwnerId(String ownerId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment app : appointments) {
            if (app.getOwner().getIdCard().equals(ownerId)) {
                result.add(app);
            }
        }
        return result;
    }

    // Métodos auxiliares
    private Owner findOwnerById(String idCard) {
        for (Owner o : owners) {
            if (o.getIdCard().equals(idCard)) return o;
        }
        return null;
    }

    private Service findServiceByCode(String code) {
        for (Service s : services) {
            if (s.getCode().equals(code)) return s;
        }
        return null;
    }
}
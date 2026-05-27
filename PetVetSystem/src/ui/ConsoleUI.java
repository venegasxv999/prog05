package ui;

import service.VetManagementService;
import model.*;
import java.util.List;
import java.util.Scanner;

// Clase responsable de la interacción con el usuario, captura de datos y mostrar errores
public class ConsoleUI {
    private VetManagementService vetService;
    private Scanner scanner;

    public ConsoleUI() {
        this.vetService = new VetManagementService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int option = -1;
        while (option != 6) {
            System.out.println("\n--- PETVET COLOMBIA ---");
            System.out.println("1. Registrar nuevo dueño");
            System.out.println("2. Registrar servicio especializado");
            System.out.println("3. Agendar una cita");
            System.out.println("4. Cancelar una cita");
            System.out.println("5. Ver reportes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
                processOption(option);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            } catch (Exception e) {
                // Aquí es donde se atrapan las excepciones lanzadas por las validaciones de negocio
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void processOption(int option) {
        switch (option) {
            case 1:
                registerOwnerUI();
                break;
            case 2:
                registerServiceUI();
                break;
            case 3:
                bookAppointmentUI();
                break;
            case 4:
                cancelAppointmentUI();
                break;
            case 5:
                reportsUI();
                break;
            case 6:
                System.out.println("Cerrando sistema PetVet...");
                break;
            default:
                System.out.println("Opción incorrecta.");
        }
    }

    private void registerOwnerUI() {
        System.out.println("\n[Registro de Dueño]");
        System.out.print("Cédula: ");
        String idCard = scanner.nextLine();
        System.out.print("Nombres: ");
        String fName = scanner.nextLine();
        System.out.print("Apellidos: ");
        String lName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();
        System.out.print("Dirección: ");
        String address = scanner.nextLine();
        System.out.print("Nombre mascota: ");
        String petName = scanner.nextLine();
        System.out.print("Especie mascota: ");
        String species = scanner.nextLine();
        System.out.print("Raza mascota: ");
        String breed = scanner.nextLine();
        System.out.print("Edad mascota (años): ");
        int age = Integer.parseInt(scanner.nextLine());

        Owner owner = new Owner(idCard, fName, lName, email, phone, address, petName, species, breed, age);
        vetService.registerOwner(owner);
        System.out.println("Dueño registrado exitosamente.");
    }

    private void registerServiceUI() {
        System.out.println("\n[Registro de Servicio Especializado]");
        System.out.print("Código (ej. SV050): ");
        String code = scanner.nextLine();
        System.out.print("Nombre (ej. Cirugía de cadera): ");
        String name = scanner.nextLine();
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        System.out.print("Especialidad (ej. Ortopedia): ");
        String specialty = scanner.nextLine();
        System.out.print("Requiere exámenes previos (true/false): ");
        boolean exams = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Fecha (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        System.out.print("Hora inicio (HH:MM): ");
        String start = scanner.nextLine();
        System.out.print("Hora fin (HH:MM): ");
        String end = scanner.nextLine();
        System.out.print("Cupos totales: ");
        int quotas = Integer.parseInt(scanner.nextLine());
        System.out.print("Precio base: ");
        double basePrice = Double.parseDouble(scanner.nextLine());
        System.out.print("Cargo adicional por especialización: ");
        double extra = Double.parseDouble(scanner.nextLine());

        Service specialized = new SpecializedService(code, name, desc, date, start, end, quotas, basePrice, specialty, exams, extra);
        vetService.registerService(specialized);
        System.out.println("Servicio registrado exitosamente. Precio final: $" + specialized.calculateFinalPrice());
    }

    private void bookAppointmentUI() {
        System.out.println("\n[Agendar Cita]");
        System.out.print("Código de la cita (ej. C001): ");
        String appCode = scanner.nextLine();
        System.out.print("Cédula del dueño: ");
        String ownerId = scanner.nextLine();
        System.out.print("Código del servicio: ");
        String serviceCode = scanner.nextLine();
        System.out.print("Cantidad de cupos a reservar (1-3): ");
        int quotas = Integer.parseInt(scanner.nextLine());
        System.out.print("Fecha actual (DD/MM/YYYY): ");
        String date = scanner.nextLine();

        vetService.bookAppointment(appCode, ownerId, serviceCode, quotas, date);
        System.out.println("Cita agendada y confirmada exitosamente.");
    }

    private void cancelAppointmentUI() {
        System.out.println("\n[Cancelar Cita]");
        System.out.print("Código de la cita a cancelar: ");
        String code = scanner.nextLine();
        vetService.cancelAppointment(code);
        System.out.println("Cita cancelada y cupos devueltos exitosamente.");
    }

    private void reportsUI() {
        System.out.println("\n[Reportes del Sistema]");
        System.out.println("Total de dueños registrados: " + vetService.getTotalRegisteredOwners());

        System.out.print("\nIngrese cédula para ver citas de un dueño: ");
        String ownerId = scanner.nextLine();
        List<Appointment> apps = vetService.getAppointmentsByOwnerId(ownerId);
        if (apps.isEmpty()) {
            System.out.println("No hay citas para este dueño.");
        } else {
            for (Appointment app : apps) {
                System.out.println(app.toString());
            }
        }
    }
}
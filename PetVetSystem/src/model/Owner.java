package model;

// Representa al dueño de la mascota
public class Owner {
    private String idCard;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String petName;
    private String petSpecies;
    private String petBreed;
    private int petAge;

    public Owner(String idCard, String firstName, String lastName, String email,
                 String phone, String address, String petName, String petSpecies,
                 String petBreed, int petAge) {
        this.idCard = idCard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.petName = petName;
        this.petSpecies = petSpecies;
        this.petBreed = petBreed;
        this.petAge = petAge;
    }

    // Getters y setters encapsulados
    public String getIdCard() { return idCard; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getPetName() { return petName; }
    public String getPetSpecies() { return petSpecies; }
    public String getPetBreed() { return petBreed; }
    public int getPetAge() { return petAge; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (CC: " + idCard + ") - Mascota: " + petName;
    }
}
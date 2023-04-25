import java.io.Serializable;

public class Passenger implements Serializable {

    private String name;
    private String surname;
    private String phoneNumber = null;
    private String email;

    public Passenger(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Passenger(String name, String surname, String phoneNumber, String email) {
        this(name, surname, email);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        if (this.phoneNumber == null) {
            return String.format("{name=%s, surname=%s, email=%s}", this.name, this.surname, this.email);
        }
        return String.format("{name=%s, surname=%s, phoneNumber=%s, email=%s}",
                this.name, this.surname, this.phoneNumber, this.email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package model;

import java.util.Objects;

public class Patient {

    public String id;
    public String name;
    public String birthdate;
    public String phone;
    public String location;
    public String status;
    public String type;
    public String needed;

    public String status_descr() { return Integer.parseInt(this.status) == 1 ? "infected" : "recovered"; }
    public String type_descr() { return Integer.parseInt(this.type) == 1 ? "private person" : "public place"; }

    public Patient() { }
    public Patient(String id, String name, String birthdate, String phone, String location, String status, String type) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.phone = phone;
        this.location = location;
        this.status = status;
        this.type = type;
    }
    public Patient(String id, String name, String birthdate, String phone, String location, String status, String type, String needed) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.phone = phone;
        this.location = location;
        this.status = status;
        this.type = type;
        this.needed = needed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient p = (Patient) o;
        return Objects.equals(id, ((Patient)o).id);
    }
}

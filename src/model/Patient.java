package model;

import java.util.Objects;

public class Patient {

    private String id;
    private String name;
    private String birthdate;
    private String phone;
    private String location;
    private String status;
    private String type;
    private String needed;

        public Patient build(){
            Patient patient = new Patient();
            patient.setId(this.id);
            patient.setName(this.name);
            patient.setBirthdate(this.birthdate);
            patient.setPhone(this.phone);
            patient.setLocation(this.location);
            patient.setStatus(this.status);
            patient.setType(this.type);
            patient.setNeeded(this.needed);

            return patient;
        }
        public static PatientBuilder builder(){
            return new PatientBuilder();
        }

    public String status_descr() { return Integer.parseInt(this.status) == 1 ? "infected" : "recovered"; }
    public String type_descr() { return Integer.parseInt(this.type) == 1 ? "private person" : "public place"; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNeeded() {
        return needed;
    }

    public void setNeeded(String needed) {
        this.needed = needed;
    }
//    public Patient() { }
//    public Patient(String id, String name, String birthdate, String phone, String location, String status, String type) {
//        this.id = id;
//        this.name = name;
//        this.birthdate = birthdate;
//        this.phone = phone;
//        this.location = location;
//        this.status = status;
//        this.type = type;
//    }
//    public Patient(String id, String name, String birthdate, String phone, String location, String status, String type, String needed) {
//        this.id = id;
//        this.name = name;
//        this.birthdate = birthdate;
//        this.phone = phone;
//        this.location = location;
//        this.status = status;
//        this.type = type;
//        this.needed = needed;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient p = (Patient) o;
        return Objects.equals(id, ((Patient)o).id);
    }
}

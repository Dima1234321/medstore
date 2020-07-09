package model;

public class PatientBuilder {
    private String id;
    private String name;
    private String birthdate;
    private String phone;
    private String location;
    private String status;
    private String type;
    private String needed;

    public PatientBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PatientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PatientBuilder withBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public PatientBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PatientBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public PatientBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public PatientBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public PatientBuilder withNeed(String needed) {
        this.needed = needed;
        return this;
    }


    public Patient build() {
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


}
package model;

import java.util.Objects;

public class PatientSupply {
    public String name;
    public String device_name;
    public String location_patient;
    public String location_supply;
    public String needed;
    public String exist;
    public String s_id;
    public String s_name;
    public String id;

    public PatientSupply() { }
    public PatientSupply(String name, String device_name, String location_patient, String location_supply, String needed, String exist,  String s_id, String s_name, String id) {
        this.name = name;
        this.device_name = device_name;
        this.location_patient = location_patient;
        this.location_supply = location_supply;
        this.needed = needed;
        this.exist = exist;
        this.s_id = s_id;
        this.s_name = s_name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientSupply p = (PatientSupply) o;
        return Objects.equals(id, ((PatientSupply)o).id);
    }
}

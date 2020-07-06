package model;

import java.util.Objects;

public class Supply {
    public String id;
    public String name;
    public String location;
    public String device_name;
    public String amount;

    public Supply() { }
    public Supply(String id, String name, String location, String device_name, String amount) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.device_name = device_name;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supply p = (Supply) o;
        return Objects.equals(id, ((Supply)o).id);
    }
}

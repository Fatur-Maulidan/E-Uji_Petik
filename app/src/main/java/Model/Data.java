package Model;

import java.sql.Time;
import java.sql.Timestamp;

public class Data {
    private int id;
    private int parker_id;
    private int vehicle_id;
    private String license_plate;
    private int total_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParker_id() {
        return parker_id;
    }

    public void setParker_id(int parker_id) {
        this.parker_id = parker_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}

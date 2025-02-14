package org.megacity.cab_service.model;

public class Vehicle {

    private int id;
    private VehicleModel model;
    private String color;
    private String plate_no;
    private int seat_count;
    private boolean availability;
    private float price_per_km;
    private float liters_per_km;
    private UserAccount driver;
    private UserAccount owner;
    private String added_At;

    public Vehicle(int id, VehicleModel model, String color, String plate_no, int seat_count, boolean availability, float price_per_km, float liters_per_km, UserAccount driver, UserAccount owner, String added_At) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.plate_no = plate_no;
        this.seat_count = seat_count;
        this.availability = availability;
        this.price_per_km = price_per_km;
        this.liters_per_km = liters_per_km;
        this.driver = driver;
        this.owner = owner;
        this.added_At = added_At;
    }

    public Vehicle(VehicleModel model, String color, String plate_no, int seat_count, boolean availability, float price_per_km, float liters_per_km, UserAccount driver, UserAccount owner) {
        this.model = model;
        this.color = color;
        this.plate_no = plate_no;
        this.seat_count = seat_count;
        this.availability = availability;
        this.price_per_km = price_per_km;
        this.liters_per_km = liters_per_km;
        this.driver = driver;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }

    public int getSeat_count() {
        return seat_count;
    }

    public void setSeat_count(int seat_count) {
        this.seat_count = seat_count;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public float getPrice_per_km() {
        return price_per_km;
    }

    public void setPrice_per_km(float price_per_km) {
        this.price_per_km = price_per_km;
    }

    public float getLiters_per_km() {
        return liters_per_km;
    }

    public void setLiters_per_km(float liters_per_km) {
        this.liters_per_km = liters_per_km;
    }

    public UserAccount getDriver() {
        return driver;
    }

    public void setDriver(UserAccount driver) {
        this.driver = driver;
    }

    public UserAccount getOwner() {
        return owner;
    }

    public void setOwner(UserAccount owner) {
        this.owner = owner;
    }

    public String getAdded_At() {
        return added_At;
    }

    public void setAdded_At(String added_At) {
        this.added_At = added_At;
    }
}

package org.megacity.cab_service.model;

public class VehicleModel {

    private int modelId;
    private String modelName;
    private String vehicleType;
    private String manufacturer;
    private String year;
    private String fuelType;
    private String transmission;

    public VehicleModel(int modelId) {
        this.modelId = modelId;
    }

    public VehicleModel(int modelId, String modelName, String vehicleType, String manufacturer, String year, String fuelType, String transmission) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.vehicleType = vehicleType;
        this.manufacturer = manufacturer;
        this.year = year;
        this.fuelType = fuelType;
        this.transmission = transmission;
    }

    public VehicleModel(String modelName, String vehicleType, String manufacturer, String year, String fuelType, String transmission) {
        this.modelName = modelName;
        this.vehicleType = vehicleType;
        this.manufacturer = manufacturer;
        this.year = year;
        this.fuelType = fuelType;
        this.transmission = transmission;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}

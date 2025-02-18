package org.megacity.cab_service.service;

import org.megacity.cab_service.model.Vehicle;
import org.megacity.cab_service.repository.VehicleRepo;

import java.util.List;

public class VehicleService {

    private VehicleRepo vehicleRepo = new VehicleRepo();
    public boolean addVehicle(Vehicle vehicle) {
        return vehicleRepo.addVehicle(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return false;
    }

    public boolean updateVehicle(Vehicle vehicle) {
        return false;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.getAllVehicles();
    }
    public Vehicle getVehicleById(int id) {
        return null;
    }

}

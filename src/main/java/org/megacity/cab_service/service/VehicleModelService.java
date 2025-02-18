package org.megacity.cab_service.service;

import org.megacity.cab_service.model.VehicleModel;
import org.megacity.cab_service.repository.VehicleModelRepo;

import java.util.List;

public class VehicleModelService {

    private VehicleModelRepo vehicleModelRepo = new VehicleModelRepo();
    public boolean addVehicleModel(VehicleModel vehicleModel) {
        return vehicleModelRepo.addVehicleType(vehicleModel);
    }
    public boolean removeVehicleModel(VehicleModel vehicleModel) {
        return false;
    }
    public boolean updateVehicleModel(VehicleModel vehicleModel) {
        return false;
    }
    public List<VehicleModel> getAllVehicleModels() {
        return vehicleModelRepo.getAllVehicleModels();
    }
    public VehicleModel getVehicleModelById(int id) {
        return null;
    }
}

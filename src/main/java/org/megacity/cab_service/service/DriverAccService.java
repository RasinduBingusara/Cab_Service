package org.megacity.cab_service.service;

import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.repository.DriverAccRepo;

import java.util.List;

public class DriverAccService {

    private DriverAccRepo driverAccRepo = new DriverAccRepo();
    public List<UserAccount> getAllEmployeeDrivers() {
        return driverAccRepo.getAllDriversByEmplyementType("Employee");
    }
}

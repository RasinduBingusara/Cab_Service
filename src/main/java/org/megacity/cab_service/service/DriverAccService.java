package org.megacity.cab_service.service;

import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.repository.DriverAccRepo;
import org.megacity.cab_service.utill.PasswordUtill;

import java.util.List;

public class DriverAccService {

    private DriverAccRepo driverAccRepo = new DriverAccRepo();
    public List<UserAccount> getAllEmployeeDrivers() {
        return driverAccRepo.getAllDriversByEmplyementType("Employee");
    }
    public boolean addEmployeeDriverAcc(UserAccount userAccount) {
        String hashedPassword = PasswordUtill.hashPassword(userAccount.getPassword());
        userAccount.setPassword(hashedPassword);
        return driverAccRepo.addNewDriver(userAccount, "Employee");
    }
}

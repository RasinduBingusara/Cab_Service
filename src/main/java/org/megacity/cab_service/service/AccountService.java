package org.megacity.cab_service.service;

import org.megacity.cab_service.dto.driver_dto.DriverInsertDTO;
import org.megacity.cab_service.dto.user_dto.UserAuthDTO;
import org.megacity.cab_service.dto.user_dto.UserInsertDTO;
import org.megacity.cab_service.model.User;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.repository.AccountRepo;
import org.megacity.cab_service.utill.PasswordUtill;

public class AccountService {

    public AccountRepo accountRepo = new AccountRepo();

    public User login(UserAuthDTO userAuthDTO) {
        User user = accountRepo.getUserByEmail(userAuthDTO.getEmail());
        if(user != null) {
            System.out.println(userAuthDTO.getEmail());
            if(PasswordUtill.checkPassword(userAuthDTO.getPassword(), user.getPassword())) {
                return user;
            }
            else{
                return null;
            }
        }
        else{
            System.out.println("User not found:" + userAuthDTO.getEmail());
            return null;
        }
    }

    public Boolean createAccount(UserInsertDTO user) {

        if(accountRepo.isEmailExist(user.getEmail())) {
            return false;
        }
        else{
            String hashedPassword = PasswordUtill.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            return accountRepo.addNewCustomer(user);
        }
    }

    public boolean createAccount(DriverInsertDTO driver) {
        if(accountRepo.isEmailExist(driver.getEmail())) {
            return false;
        }
        else{
            String hashedPassword = PasswordUtill.hashPassword(driver.getPassword());
            driver.setPassword(hashedPassword);

            return accountRepo.addNewDriver(driver);
        }
    }
}

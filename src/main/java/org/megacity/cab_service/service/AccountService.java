package org.megacity.cab_service.service;

import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.repository.AccountRepo;
import org.megacity.cab_service.utill.PasswordUtill;

public class AccountService {

    public AccountRepo accountRepo = new AccountRepo();

    public UserAccount login(String email, String password) {
        UserAccount user = accountRepo.getUserByEmail(email);
        if(user != null) {
            if(PasswordUtill.checkPassword(password, user.getPassword())) {
                return user;
            }
            else{
                return null;
            }
        }
        else{
            System.out.println("User not found");
            return null;
        }
    }

    public Boolean createAccount(UserAccount user) {

        if(accountRepo.isEmailExist(user.getEmail())) {
            return false;
        }
        else{
            String hashedPassword = PasswordUtill.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            return user.getUserType().equals("customer")? accountRepo.addNewUser(user):
                    accountRepo.addNewDriver(user);
        }
    }
}

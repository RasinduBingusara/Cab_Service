package org.megacity.cab_service.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.service.AccountService;
import org.megacity.cab_service.utill.PasswordUtill;

import java.io.IOException;

public class SignupController extends HttpServlet {

    AccountService accountService = new AccountService();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String contactNo = req.getParameter("contactnumber");
        String usertype = req.getParameter("usertype");
        String confirmPassword = req.getParameter("confirmpassword");
        String nic = req.getParameter("nic");
        String address = req.getParameter("address");
        String driverLicense = req.getParameter("driverlicense");

        UserAccount userAccount;
        String returnValue;
        if(usertype.equals("customer")) {
            userAccount = new UserAccount.UserCreator(firstName,lastName,email,contactNo,password,usertype).createNewCustomer();
            returnValue = valueReturnResponse(userAccount);
        }
        else{
            userAccount = new UserAccount.UserCreator(firstName,lastName,email,contactNo,password,usertype).createNewDriver(nic,address,driverLicense);
            returnValue = valueReturnResponseForDriver(userAccount);
        }

        if(!PasswordUtill.isValidPassword(userAccount.getPassword())) {
            String error = "At least one uppercase letter (A-Z) </br>" +
                    "At least one lowercase letter (a-z) </br>" +
                    "At least one digit (0-9) </br>" +
                    "At least one special character </br>" +
                    "Minimum 8 characters in length";

            res.sendRedirect("signup.jsp?error=" + error + returnValue);
        } else if (!userAccount.getPassword().equals(confirmPassword)) {
            String error = "Passwords do not match";
            res.sendRedirect("signup.jsp?error=" + error + returnValue);
        } else if (accountService.createAccount(userAccount)) {
            res.sendRedirect("login.jsp");
        }
        else{
            String error = "Email already exists";
            res.sendRedirect("signup.jsp?error=" + error + returnValue);
        }
    }

    private String valueReturnResponse(UserAccount userAccount) {
        String response = "&type=customer" + "&email=" + userAccount.getEmail() + "&firstname=" + userAccount.getFirstname()
                + "&lastname=" + userAccount.getLastname() + "&contactnumber=" + userAccount.getContactNumber();
        return response;
    }
    private String valueReturnResponseForDriver(UserAccount userAccount) {
        String response = "&type=driver" + "&email=" + userAccount.getEmail() + "&firstname=" + userAccount.getFirstname()
                + "&lastname=" + userAccount.getLastname() + "&contactnumber=" + userAccount.getContactNumber()
                + "&driverlicense=" + userAccount.getDriverLicense() + "&nic=" + userAccount.getNic()
                + "&address=" + userAccount.getAddress();
        return response;
    }
}

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

        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(req.getParameter("email"));
        userAccount.setPassword(req.getParameter("password"));
        String confirmPassword = req.getParameter("confirmpassword");
        userAccount.setFirstname(req.getParameter("firstname"));
        userAccount.setLastname(req.getParameter("lastname"));
        userAccount.setContactNumber(req.getParameter("contactnumber"));

        if(!PasswordUtill.isValidPassword(userAccount.getPassword())) {
            String error = "At least one uppercase letter (A-Z) </br>" +
                    "At least one lowercase letter (a-z) </br>" +
                    "At least one digit (0-9) </br>" +
                    "At least one special character </br>" +
                    "Minimum 8 characters in length";

            res.sendRedirect("signup.jsp?error=" + error + valueReturnResponse(userAccount));
        } else if (!userAccount.getPassword().equals(confirmPassword)) {
            String error = "Passwords do not match";
            res.sendRedirect("signup.jsp?error=" + error + valueReturnResponse(userAccount));
        } else if (accountService.createAccount(userAccount)) {
            res.sendRedirect("login.jsp");
        }
        else{
            String error = "Email already exists";
            res.sendRedirect("signup.jsp?error=" + error + valueReturnResponse(userAccount));
        }
    }

    private String valueReturnResponse(UserAccount userAccount) {
        String response = "&email=" + userAccount.getEmail() + "&firstname=" + userAccount.getFirstname()
                + "&lastname=" + userAccount.getLastname() + "&contactnumber=" + userAccount.getContactNumber();
        return response;
    }
}

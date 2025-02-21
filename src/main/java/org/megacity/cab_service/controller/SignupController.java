package org.megacity.cab_service.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cab_service.dto.driver_dto.DriverInsertDTO;
import org.megacity.cab_service.dto.user_dto.UserInsertDTO;
import org.megacity.cab_service.dto.user_dto.UserResponseDTO;
import org.megacity.cab_service.mapper.DriverMapper;
import org.megacity.cab_service.mapper.UserMapper;
import org.megacity.cab_service.model.User;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.service.AccountService;
import org.megacity.cab_service.utill.PasswordUtill;

import java.io.IOException;

public class SignupController extends HttpServlet {

    AccountService accountService = new AccountService();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {


        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmpassword");
        String usertype = req.getParameter("usertype");
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String contactNo = req.getParameter("contactnumber");

        String nic = req.getParameter("nic");
        String address = req.getParameter("address");
        String driverLicense = req.getParameter("driverlicense");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setContactNumber(contactNo);
        user.setNic(nic);
        user.setAddress(address);
        user.setDriverLicense(driverLicense);
        user.setUserType(usertype);


        if(!PasswordUtill.isValidPassword(password)) {
            String error = "At least one uppercase letter (A-Z) </br>" +
                    "At least one lowercase letter (a-z) </br>" +
                    "At least one digit (0-9) </br>" +
                    "At least one special character </br>" +
                    "Minimum 8 characters in length";

            req.setAttribute("error", error);
            req.setAttribute("type", usertype);
            req.setAttribute("user", user);
            req.getRequestDispatcher("signup.jsp").forward(req,res);
        } else if (!password.equals(confirmPassword)) {
            String error = "Passwords do not match";
            req.setAttribute("error", error);
            req.setAttribute("type", usertype);
            req.setAttribute("user", user);
            req.getRequestDispatcher("signup.jsp").forward(req,res);
        }
        else{


            switch (usertype) {
                case "customer":
                    UserInsertDTO userInsertDTO = new UserInsertDTO(
                            firstName,
                            lastName,
                            email,
                            password,
                            contactNo,
                            "Active"
                    );
                    if (accountService.createAccount(userInsertDTO)) {
                        res.sendRedirect("login.jsp");
                    }
                    else{
                        user = UserMapper.getInstance().toEntity(userInsertDTO);
                        req.setAttribute("user", user);
                        String error = "Email already exists";
                        req.setAttribute("error", error);
                        req.setAttribute("type", usertype);
                        req.getRequestDispatcher("signup.jsp").forward(req,res);
                    }
                    break;
                case "driver":
                    DriverInsertDTO driverInsertDTO = new DriverInsertDTO(
                            firstName,
                            lastName,
                            email,
                            password,
                            contactNo,
                            "Active",
                            driverLicense,
                            nic,
                            address,
                            "Freelancer"

                    );
                    if (accountService.createAccount(driverInsertDTO)) {
                        res.sendRedirect("login.jsp");
                    }
                    else{
                        user = DriverMapper.getInstance().toEntity(driverInsertDTO);
                        req.setAttribute("user", user);
                        String error = "Email already exists";
                        req.setAttribute("error", error);
                        req.setAttribute("type", usertype);
                        req.getRequestDispatcher("signup.jsp").forward(req,res);
                    }
                    break;

            }

        }


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String type = req.getParameter("type");
        switch (type) {
            case "driver":
                req.setAttribute("type", "driver");
                req.getRequestDispatcher("signup.jsp").forward(req,res);
                break;
            case "customer":
                req.setAttribute("type", "customer");
                req.getRequestDispatcher("signup.jsp").forward(req,res);
                break;
        }
    }

}

package org.megacity.cab_service.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.service.DriverAccService;
import org.megacity.cab_service.utill.PasswordUtill;

import java.io.IOException;

public class DriverController extends HttpServlet {

    private DriverAccService driverAccService = new DriverAccService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmpassword");

        if (!PasswordUtill.isValidPassword(password)) {
            String error = "At least one uppercase letter (A-Z) </br>" +
                    "At least one lowercase letter (a-z) </br>" +
                    "At least one digit (0-9) </br>" +
                    "At least one special character </br>" +
                    "Minimum 8 characters in length";

            req.setAttribute("error", error);
            req.getRequestDispatcher( "add_driver.jsp").forward(req, resp);
        } else if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Passwords do not match");
            req.getRequestDispatcher("add_driver.jsp").forward(req, resp);
        }
        else{
            UserAccount driver = new UserAccount.UserCreator().createNewDriver(
                    req.getParameter("firstname"),
                    req.getParameter("lastname"),
                    req.getParameter("email"),
                    req.getParameter("password"),
                    req.getParameter("contactnumber"),
                    req.getParameter("nic"),
                    req.getParameter("address"),
                    req.getParameter("driverlicense")
            );

            if(driverAccService.addEmployeeDriverAcc(driver)) {
                req.setAttribute("message", "Driver added successfully");
                req.getRequestDispatcher("add_driver.jsp").forward(req, resp);
            }
            else {
                req.setAttribute("error", "Driver already exists");
                req.getRequestDispatcher( "add_driver.jsp?message=Driver Account Creation Failed").forward(req, resp);
            }
        }


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action.equals("all")) {
            req.setAttribute("drivers", driverAccService.getAllEmployeeDrivers());
            req.getRequestDispatcher("manage_drivers.jsp").forward(req, resp);
        }
        else if(action.equals("add")) {
            req.getRequestDispatcher("add_driver.jsp").forward(req, resp);
        }

    }
}

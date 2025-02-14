package org.megacity.cab_service.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.service.AccountService;

import java.io.IOException;

public class LoginController extends HttpServlet {

    private AccountService accountService = new AccountService();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserAccount user = accountService.login(email, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            switch (user.getUserType()){
                case "Customer":
                    System.out.println("Customer logged in");
                    res.sendRedirect("home.jsp");
                    break;
                case "Driver":
                    System.out.println("Driver logged in");
                    res.sendRedirect("dashboard_navigator.jsp");
                    break;
                case "Admin":
                    System.out.println("Admin logged in");
                    res.sendRedirect("admin_dashboard.jsp");
                    break;
            }
        }
        else {
            String error = "Invalid Email or Password";
            res.sendRedirect("login.jsp?error=" + error);
        }
    }

    //for logout
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        res.sendRedirect("home.jsp");
    }
}

package org.megacity.cab_service.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cab_service.dto.user_dto.UserAuthDTO;
import org.megacity.cab_service.mapper.AdminMapper;
import org.megacity.cab_service.mapper.DriverMapper;
import org.megacity.cab_service.mapper.UserMapper;
import org.megacity.cab_service.model.User;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.service.AccountService;

import java.io.IOException;

public class LoginController extends HttpServlet {

    private AccountService accountService = new AccountService();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserAuthDTO userAuthDTO = new UserAuthDTO(email, password);

        User user = accountService.login(userAuthDTO);
        if (user != null) {
            HttpSession session = req.getSession();
            switch (user.getUserType()){
                case "Customer":
                    session.setAttribute("user", user);
                    System.out.println("Customer logged in");
                    res.sendRedirect("home.jsp");
                    break;
                case "Driver":
                    session.setAttribute("user", user);
                    System.out.println("Driver logged in");
                    res.sendRedirect("dashboard_navigator.jsp");
                    break;
                case "Admin":
                    session.setAttribute("user", user);
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

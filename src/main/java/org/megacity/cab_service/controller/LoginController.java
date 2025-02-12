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
            res.sendRedirect("home.jsp");
        }
        else {
            String error = "Invalid Email or Password";
            res.sendRedirect("login.jsp?error=" + error);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        res.sendRedirect("home.jsp");
    }
}

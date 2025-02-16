package org.megacity.cab_service.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.model.Vehicle;
import org.megacity.cab_service.model.VehicleModel;
import org.megacity.cab_service.service.AccountService;
import org.megacity.cab_service.service.DriverAccService;
import org.megacity.cab_service.service.VehicleModelService;
import org.megacity.cab_service.service.VehicleService;

import java.io.IOException;

public class VehicleController extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();
    private VehicleModelService vehicleModelService = new VehicleModelService();
    private DriverAccService driverAccService = new DriverAccService();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int model = Integer.parseInt(req.getParameter("model"));
        String color = req.getParameter("color");
        String plateNo = req.getParameter("plateNo");
        int seatCount = Integer.parseInt(req.getParameter("seatCount"));
        boolean available = Boolean.parseBoolean(req.getParameter("available"));
        float price_per_km = Float.parseFloat(req.getParameter("pricePerKm"));
        float liters_per_km = Float.parseFloat(req.getParameter("litersPerKm"));
        int driverId = Integer.parseInt(req.getParameter("driverId"));
        int ownerId = Integer.parseInt(req.getParameter("ownerId"));

        Vehicle vehicle = new Vehicle(
                new VehicleModel(model),
                color,
                plateNo,
                seatCount,
                available,
                price_per_km,
                liters_per_km,
                new UserAccount.UserCreator().defaultUser(driverId),
                new UserAccount.UserCreator().defaultUser(ownerId)
        );

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String vehicleRequest = req.getParameter("action");
        if(vehicleRequest.equals("view")){
            req.setAttribute("VehicleList", vehicleService.getAllVehicles());
            res.sendRedirect("manage_vehicle.jsp");
        } else if (vehicleRequest.equals("add")) {
            req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
            req.setAttribute("drivers", driverAccService.getAllEmployeeDrivers());
            res.sendRedirect("add_vehicle.jsp");
        }
    }
}

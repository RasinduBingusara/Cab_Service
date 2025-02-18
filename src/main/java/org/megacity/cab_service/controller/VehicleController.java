package org.megacity.cab_service.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.model.Vehicle;
import org.megacity.cab_service.model.VehicleModel;
import org.megacity.cab_service.service.DriverAccService;
import org.megacity.cab_service.service.VehicleModelService;
import org.megacity.cab_service.service.VehicleService;

import java.io.IOException;

public class VehicleController extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();
    private VehicleModelService vehicleModelService = new VehicleModelService();
    private DriverAccService driverAccService = new DriverAccService();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int model = Integer.parseInt(req.getParameter("vehicleModel"));
        String color = req.getParameter("color");
        String plateNo = req.getParameter("plateNumber");
        int seatCount = Integer.parseInt(req.getParameter("seatCount"));
        boolean available = req.getParameter("availability")=="on" ? true : false;
        float price_per_km = Float.parseFloat(req.getParameter("pricePerKm"));
        float liters_per_km = Float.parseFloat(req.getParameter("litersPerKm"));
        int driverId = Integer.parseInt(req.getParameter("driver"));
        int ownerId = -1; // for company

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

        if(vehicleService.addVehicle(vehicle)) {
            req.setAttribute("message", "Vehicle added successfully");
            req.getRequestDispatcher("add_vehicle.jsp").forward(req,res);
        }
        else{
            req.setAttribute("error", "Vehicle addition failed");
            req.getRequestDispatcher("add_vehicle.jsp").forward(req,res);
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String vehicleRequest = req.getParameter("action");
        if(vehicleRequest.equals("view")){
            req.setAttribute("VehicleList", vehicleService.getAllVehicles());
            req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
            req.setAttribute("drivers", driverAccService.getAllEmployeeDrivers());
            req.getRequestDispatcher("manage_vehicle.jsp").forward(req,res);
        } else if (vehicleRequest.equals("add")) {
            req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
            req.setAttribute("drivers", driverAccService.getAllEmployeeDrivers());
            req.getRequestDispatcher("add_vehicle.jsp").forward(req,res);
        }
    }
}

package org.megacity.cab_service.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cab_service.model.VehicleModel;
import org.megacity.cab_service.service.VehicleModelService;

import java.io.IOException;

public class VehicleModelController extends HttpServlet {

    private VehicleModelService vehicleModelService = new VehicleModelService();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String type = req.getParameter("vehicleType");
        String modelName = req.getParameter("modelName");
        String modelYear = req.getParameter("year");
        String manufacturer = req.getParameter("manufacturer");
        String fuelType = req.getParameter("fuelType");
        String transmission = req.getParameter("transmission");

        VehicleModel model = new VehicleModel(modelName,type,manufacturer,modelYear,fuelType,transmission);
        if(vehicleModelService.addVehicleModel(model)) {
            res.sendRedirect("add_model.jsp" + "?response=Model Added Successfully");
        }
        else{
            res.sendRedirect("add_model.jsp" + "?response=Model Not Added Successfully");
        }
    }
}

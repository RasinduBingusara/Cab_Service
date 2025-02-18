package org.megacity.cab_service.repository;

import org.megacity.cab_service.config.DatabaseConnection;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.model.VehicleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleModelRepo {

    public boolean addVehicleType(VehicleModel vehicleModel) {
        String sql = "INSERT INTO vehicle_model(model_name, vehicle_type, manufacturer, year, fuel_type, transmission) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, vehicleModel.getModelName());
            statement.setString(2, vehicleModel.getVehicleType());
            statement.setString(3, vehicleModel.getManufacturer());
            statement.setString(4, vehicleModel.getYear());
            statement.setString(5, vehicleModel.getFuelType());
            statement.setString(6, vehicleModel.getTransmission());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }
    public boolean updateVehicleType(VehicleModel vehicleModel) {
        return false;
    }
    public boolean deleteVehicleType(VehicleModel vehicleModel) {
        return false;
    }
    public List<VehicleModel> getAllVehicleModels() {
        String sql = "SELECT * FROM vehicle_model";
        List<VehicleModel> vehicleModels = new ArrayList<>();
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    vehicleModels.add(new VehicleModel(
                            resultSet.getInt("model_id"),
                            resultSet.getString("model_name"),
                            resultSet.getString("vehicle_type"),
                            resultSet.getString("manufacturer"),
                            resultSet.getString("year"),
                            resultSet.getString("fuel_type"),
                            resultSet.getString("transmission")
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return vehicleModels;
    }
    public VehicleModel getVehicleTypeById(int id) {
        return null;
    }
}

package org.megacity.cab_service.repository;

import org.megacity.cab_service.config.DatabaseConnection;
import org.megacity.cab_service.model.UserAccount;
import org.megacity.cab_service.model.Vehicle;
import org.megacity.cab_service.model.VehicleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepo {

    public boolean addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicle(model, color, plate_no, seat_count, availability, price_per_km, liter_per_km, driver_id, owner_id, added_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, now())";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, String.valueOf(vehicle.getModel().getModelId()));
            statement.setString(2, vehicle.getColor());
            statement.setString(3, vehicle.getPlate_no());
            statement.setString(4, String.valueOf(vehicle.getSeat_count()));

            statement.setString(5, vehicle.isAvailability()==true?"1":"0");
            statement.setString(6, String.valueOf(vehicle.getPrice_per_km()));
            statement.setString(7, String.valueOf(vehicle.getLiters_per_km()));
            statement.setString(8, String.valueOf(vehicle.getDriver().getId()));
            statement.setString(9, String.valueOf(vehicle.getOwner().getId()));

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return false;
    }

    public boolean updateVehicle(Vehicle vehicle) {
        return false;
    }

    public List<Vehicle> getAllVehicles() {
        String sql = "SELECT * FROM vehicle";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    vehicles.add(new Vehicle(
                            resultSet.getInt("id"),
                            new VehicleModel(resultSet.getInt("model")),
                            resultSet.getString("color"),
                            resultSet.getString("plate_no"),
                            resultSet.getInt("seat_count"),
                            resultSet.getInt("availability")==1? true:false,
                            resultSet.getFloat("price_per_km"),
                            resultSet.getFloat("liter_per_km"),
                            new UserAccount.UserCreator().defaultUser(resultSet.getInt("driver_id")),
                            new UserAccount.UserCreator().defaultUser(resultSet.getInt("owner_id")),
                            resultSet.getString("added_at")
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return vehicles;
    }
    public Vehicle getVehicleById(int id) {
        return null;
    }
}

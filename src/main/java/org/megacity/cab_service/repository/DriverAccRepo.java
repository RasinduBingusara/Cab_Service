package org.megacity.cab_service.repository;

import org.megacity.cab_service.config.DatabaseConnection;
import org.megacity.cab_service.model.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverAccRepo {

    public List<UserAccount> getAllDriversByEmplyementType(String emplyementType) {
        String sql = "SELECT a.uid, a.firstname, a.lastname, a.email, a.contactnumber, a.created_at, d.nic, d.driver_license," +
                " d.address FROM account a JOIN driver d ON a.uid = d.user_id WHERE d.employment_type = ?";
        List<UserAccount> drivers = new ArrayList<UserAccount>();

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, emplyementType);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    UserAccount newAccount = new UserAccount.UserCreator().createExistingDriver(
                            resultSet.getInt("uid"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("email"),
                            null,
                            resultSet.getString("contactnumber"),
                            resultSet.getString("nic"),
                            resultSet.getString("address"),
                            resultSet.getString("driver_license")
                    );
                    newAccount.setCreatedAt(resultSet.getString("created_at"));
                    drivers.add(newAccount);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return drivers;
    }

    public boolean addNewDriver(UserAccount driver, String emplyementType) {
        String sql1 = "INSERT INTO account (email, firstname, lastname, password, contactnumber, user_type, created_at) VALUES (?, ?, ?, ?, ?, ?, now())";
        String sql2 = "INSERT INTO driver(user_id, driver_license, nic, address, employment_type) VALUES (?,?,?,?,?)";
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, driver.getEmail());
            statement.setString(2, driver.getFirstname());
            statement.setString(3, driver.getLastname());
            statement.setString(4, driver.getPassword());
            statement.setString(5, driver.getContactNumber());
            statement.setString(6, "Driver");

            int rowsInserted = statement.executeUpdate();

            int generatedId = -1;
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1); // Retrieve the auto-generated key
                    }
                }
            }

            try(Connection con2 = DatabaseConnection.connection();
                PreparedStatement statement2 = con2.prepareStatement(sql2)) {

                statement2.setString(1, String.valueOf(generatedId));
                statement2.setString(2, driver.getDriverLicense());
                statement2.setString(3, driver.getNic());
                statement2.setString(4, driver.getAddress());
                statement2.setString(5, emplyementType);

                rowsInserted = statement2.executeUpdate();

            }

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }

    }
}

package org.megacity.cab_service.repository;

import org.megacity.cab_service.config.DatabaseConnection;
import org.megacity.cab_service.model.UserAccount;

import java.sql.*;

public class AccountRepo {


    public boolean isEmailExist(String email) {
        String sql = "SELECT * FROM account WHERE email = ?";
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();  // Returns true if email exists
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }

    }

    public UserAccount getUserByEmail(String email) {
        String sql = "SELECT * FROM account WHERE email = ?";
        UserAccount user = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    user = new UserAccount.UserCreator().createExisttingCustomer(
                            resultSet.getInt("uid"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("contactnumber"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return user;
    }

    public Boolean addNewUser(UserAccount user) {
        String sql = "INSERT INTO account (email, firstname, lastname, password, contactnumber, user_type, created_at) VALUES (?, ?, ?, ?, ?, ?, now())";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getContactNumber());
            statement.setString(6, user.getUserType());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }

    }

    public Boolean addNewDriver(UserAccount user) {
        String sql = "INSERT INTO account (email, firstname, lastname, password, contactnumber, user_type, created_at) VALUES (?, ?, ?, ?, ?, ?, now())";
        String driverSql = "INSERT INTO driver (user_id, driver_license, nic, address) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getContactNumber());
            statement.setString(6, user.getUserType());

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
                PreparedStatement statement2 = con2.prepareStatement(driverSql)) {

                statement2.setString(1, String.valueOf(generatedId));
                statement2.setString(2, user.getDriverLicense());
                statement2.setString(3, user.getNic());
                statement2.setString(4, user.getAddress());

                rowsInserted = statement2.executeUpdate();

            }

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }
}

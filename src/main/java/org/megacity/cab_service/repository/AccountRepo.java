package org.megacity.cab_service.repository;

import org.megacity.cab_service.config.DatabaseConnection;
import org.megacity.cab_service.model.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        UserAccount user = new UserAccount();

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    user.setEmail(resultSet.getString("email"));
                    user.setFirstname(resultSet.getString("firstname"));
                    user.setLastname(resultSet.getString("lastname"));
                    user.setPassword(resultSet.getString("password"));
                    user.setContactNumber(resultSet.getString("contactnumber"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return user;
    }

    public Boolean addNewUser(UserAccount user) {
        String sql = "INSERT INTO account (email, firstname, lastname, password, contactnumber, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getContactNumber());
            statement.setString(6, user.getCreatedAt());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }

    }
}

package org.megacity.cab_service.repository;

import org.megacity.cab_service.config.DatabaseConnection;
import org.megacity.cab_service.dto.admin_dto.AdminResponseDTO;
import org.megacity.cab_service.dto.driver_dto.DriverInsertDTO;
import org.megacity.cab_service.dto.user_dto.UserInsertDTO;
import org.megacity.cab_service.dto.user_dto.UserResponseDTO;
import org.megacity.cab_service.model.Admin;
import org.megacity.cab_service.model.User;
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

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM account WHERE email = ?";
        User user = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    switch(resultSet.getString("user_type")){
                        case "Customer":
                            user = new User();
                            user.setEmail(resultSet.getString("email"));
                            user.setFirstName(resultSet.getString("firstname"));
                            user.setLastName(resultSet.getString("lastname"));
                            user.setPassword(resultSet.getString("password"));
                            user.setContactNumber(resultSet.getString("contactnumber"));
                            user.setUserType(resultSet.getString("user_type"));
                            break;
                        case "Driver":
                            user = new User();
                            user.setEmail(resultSet.getString("email"));
                            user.setFirstName(resultSet.getString("firstname"));
                            user.setLastName(resultSet.getString("lastname"));
                            user.setPassword(resultSet.getString("password"));
                            user.setContactNumber(resultSet.getString("contactnumber"));
                            user.setUserType(resultSet.getString("user_type"));

                            user = getDriverExtra(user);
                            System.out.println("it is a driver");
                            break;
                        case "Admin":
                            user = new User();
                            user.setEmail(resultSet.getString("email"));
                            user.setFirstName(resultSet.getString("firstname"));
                            user.setLastName(resultSet.getString("lastname"));
                            user.setPassword(resultSet.getString("password"));
                            user.setContactNumber(resultSet.getString("contactnumber"));
                            user.setUserType(resultSet.getString("user_type"));
                            break;
                    }

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return user;
    }

    private User getDriverExtra(User user) {
        String sql = "SELECT * FROM driver WHERE user_id = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, String.valueOf(user.getId()));
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    user.setId(resultSet.getString("nic"));
                    user.setAddress(resultSet.getString("address"));
                    user.setDriverLicense(resultSet.getString("driver_license"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return user;
    }

    public Boolean addNewCustomer(UserInsertDTO user) {
        String sql = "INSERT INTO account (email, firstname, lastname, password, contactnumber, user_type, created_at) VALUES (?, ?, ?, ?, ?, ?, now())";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getContactNumber());
            statement.setString(6, "Customer");

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }

    }

    public Boolean addNewDriver(DriverInsertDTO driver) {
        String sql = "INSERT INTO account (email, firstname, lastname, password, contactnumber, user_type, created_at) VALUES (?, ?, ?, ?, ?, ?, now())";
        String driverSql = "INSERT INTO driver (user_id, driver_license, nic, address) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, driver.getEmail());
            statement.setString(2, driver.getFirstName());
            statement.setString(3, driver.getLastName());
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
                PreparedStatement statement2 = con2.prepareStatement(driverSql)) {

                statement2.setString(1, String.valueOf(generatedId));
                statement2.setString(2, driver.getDriverLicense());
                statement2.setString(3, driver.getNic());
                statement2.setString(4, driver.getAddress());

                rowsInserted = statement2.executeUpdate();

            }

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }
}

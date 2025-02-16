package org.megacity.cab_service.repository;

import org.megacity.cab_service.config.DatabaseConnection;
import org.megacity.cab_service.model.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverAccRepo {

    public List<UserAccount> getAllDriversByEmplyementType(String emplyementType) {
        String sql = "SELECT a.uid, a.firstname, a.lastname, a.email, a.contactnumber, d.nic, d.driver_license," +
                " d.address FROM account a JOIN driver d ON a.uid = d.user_id WHERE d.employment_type = ?;";
        List<UserAccount> drivers = new ArrayList<UserAccount>();

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, emplyementType);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    drivers.add(new UserAccount.UserCreator().createExistingDriver(
                            resultSet.getInt("uid"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("email"),
                            null,
                            resultSet.getString("contactnumber"),
                            resultSet.getString("nic"),
                            resultSet.getString("address"),
                            resultSet.getString("driver_license")
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return drivers;
    }
}

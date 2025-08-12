package com.pahana.Dao;

import com.pahana.Model.Forget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgetDao {

	public static void addData(Forget forget) {
		String query = "SELECT id FROM login WHERE Email=?"; 

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(query)) {

            selectStmt.setString(1, forget.getEmail()); // Set email parameter
            ResultSet resultSet = selectStmt.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
              
                // Fix: Correct update query with placeholders
                String updateQuery = "UPDATE login SET Verify=? WHERE id=?";

                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, forget.getVerify()); // Set verify value
                    updateStmt.setString(2, id); // Set id value

                    int rowsUpdated = updateStmt.executeUpdate(); // Execute update

                    if (rowsUpdated > 0) {
                        System.out.println("Updated successfully for ID: " + id);
                    } else {
                        System.out.println("No update performed for ID: " + id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling in production
        }
		
	}
    public static void changeData(Forget forget) {

        String email = forget.getEmail();
        String password = forget.getPassword();
        System.out.println("email is"+email);
        System.out.println("password is" +password);
        String updateQuery = "UPDATE login SET Password=? WHERE Email=?";

        try (Connection connection = DbConnectionFactory.getConnection();
                PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
            updateStmt.setString(1, password);
            updateStmt.setString(2, email);

            updateStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling in production
        }
}

}

package com.pahana.Dao;

import com.pahana.Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    public static void addCustomer(Customer customer) {
        String query = "INSERT INTO customer (Cimage,Cname,Cmobile,Caddress,Cemail,Cstatus) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,customer.getImage());
            statement.setString(2,customer.getName());
            statement.setInt(3,   customer.getMobile());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getEmail());
            statement.setString(6, "Active");

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> getAllCustomer() {
        List<Customer> CustomerList = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE Cstatus = 'Active'";

        try(Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int id = resultSet.getInt("Cid");
                String fileName = resultSet.getString("Cimage");
                String uploadPath = "assets/user/img/Customer/";
                String image = uploadPath + fileName;
                String name = resultSet.getString("Cname");
                Integer mobile = resultSet.getInt("Cmobile");
                String address = resultSet.getString("Caddress");
                String email = resultSet.getString("Cemail");
                String status = resultSet.getString("Cstatus");
                System.out.println(name);

                CustomerList.add(new Customer(id,image,name,mobile,address,email,status));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CustomerList;

    }


    public static void addCustomerDetails(Customer customer) {
        Integer customerIdid = customer.getId();
        String query = "UPDATE customer SET Cimage=? ,Cname=? ,Cemail=?, Cmobile=?, Caddress=? WHERE Cid=?";

        try {

            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, customer.getImage());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, String.valueOf(customer.getMobile()));
            statement.setString(5, customer.getAddress());
            statement.setInt(6, customerIdid);

            statement.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void addremovedetails(Integer deleteid) {
        String query = "UPDATE customer SET Cstatus=? WHERE Cid=?";

        try {

            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, "Deactive");
            statement.setInt(2, deleteid);

            statement.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

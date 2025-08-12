package com.pahana.Dao;

import com.pahana.Model.Book;
import com.pahana.Model.Item;
import com.pahana.Model.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PaymentDao {
    public static void addPayment(Payment payment)
    {
        Integer id=0;
        Integer mobile = payment.getMobile();
        String query1 = "SELECT Cid FROM customer WHERE Cmobile=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query1)) {

            preparedStatement.setInt(1, mobile);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("Cid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String updateQuery = "UPDATE cart SET Cusid=? WHERE date=? AND Status=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
            updateStmt.setInt(1, id);
            updateStmt.setDate(2, Date.valueOf(LocalDate.now()));
            updateStmt.setInt(3, 0);

            int rows = updateStmt.executeUpdate();
            System.out.println("Rows updated: " + rows);


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update book category", e);
        }
        String query = "INSERT INTO invoice (subtotal,amount,balance,cusid,Status,date) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, payment.getSubtotal());
            statement.setDouble(2, payment.getAmount());
            statement.setDouble(3, payment.getBalance());
            statement.setInt(4, id);
            statement.setString(5, "Pending");
            statement.setDate(6, Date.valueOf(LocalDate.now()));


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Payment> getaddbill(Integer mobile) {
        List<Payment> addBillList = new ArrayList<>();
        Integer cusid=0;
        String Query = "SELECT * FROM customer WHERE Cmobile=?";
        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(Query)) {

            selectStmt.setInt(1, mobile);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                cusid = rs.getInt("Cid");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        System.out.println("cus id"+cusid);
        Double subtotal=0.0;
        Double amount=0.0;
        Double balance=0.0;
        Integer id=0;
        String Query1 = "SELECT * FROM invoice WHERE cusid=? AND Status=? ORDER BY id DESC LIMIT 1";
        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(Query1)) {

            selectStmt.setInt(1, cusid);
            selectStmt.setString(2, "Pending");
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                 id = rs.getInt("id");
                 subtotal = rs.getDouble("subtotal");
                 amount = rs.getDouble("amount");
                 balance = rs.getDouble("balance");
            }
        String date = String.valueOf(Date.valueOf(LocalDate.now()));
            System.out.println("subtotal id"+subtotal);
            System.out.println("amount id"+amount);
            System.out.println("balance id"+balance);

        String selectQuery = "SELECT B.Bid AS id, B.Bname AS name, B.Bprice AS price, B.Bquntity AS quntity, " +
                "C.Quntity AS qty, C.Cateid AS bookid,C.date as date " +
                "FROM bookcategory B " +
                "JOIN cart C ON B.Bid = C.Cateid " +
                "WHERE C.Status = ? AND C.date = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, 0);
            preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                String name = resultSet.getString("name");
                Integer quntity = resultSet.getInt("qty");
                String price = resultSet.getString("price");

                addBillList.add(new Payment(id,cusid, name, quntity, price,date,subtotal,amount,balance));
            }
            connection.close();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return addBillList;
    }

    public static void addprintbill(Integer printid)
    {
        String updateInvoiceQuery = "UPDATE invoice SET Status = ? WHERE Cusid = ? AND Status = ?";
        String updateCartQuery = "UPDATE cart SET Status = ? WHERE Cusid = ? AND Status = ? AND date = ?";

        try (Connection connection = DbConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);


            try (PreparedStatement updateInvoiceStmt = connection.prepareStatement(updateInvoiceQuery)) {
                updateInvoiceStmt.setString(1, "Done");
                updateInvoiceStmt.setInt(2, printid);
                updateInvoiceStmt.setString(3, "Pending");

                int invoiceRows = updateInvoiceStmt.executeUpdate();
                System.out.println("Invoice rows updated: " + invoiceRows);
            }


            try (PreparedStatement updateCartStmt = connection.prepareStatement(updateCartQuery)) {
                updateCartStmt.setInt(1, 1);
                updateCartStmt.setInt(2, printid);
                updateCartStmt.setInt(3, 0);
                updateCartStmt.setDate(4, Date.valueOf(LocalDate.now()));

                int cartRows = updateCartStmt.executeUpdate();
                System.out.println("Cart rows updated: " + cartRows);
            }

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update invoice or cart", e);
        }

    }

    public static List<Payment> getDaily() {

            Map<Integer, Payment> customerMap = new LinkedHashMap<>();
            String query = "SELECT * FROM invoice WHERE Status = ? AND date = ?";

            try (Connection connection = DbConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, "Done");
                statement.setDate(2, Date.valueOf(LocalDate.now()));

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int cusid = resultSet.getInt("cusid");
                    double subtotal = resultSet.getDouble("subtotal");


                    if (customerMap.containsKey(cusid)) {
                        Payment existing = customerMap.get(cusid);
                        existing.setSubtotal(existing.getSubtotal() + subtotal);
                    } else {

                        String cusname = "";
                        String query1 = "SELECT Cname FROM customer WHERE Cid = ?";
                        try (PreparedStatement statement1 = connection.prepareStatement(query1)) {
                            statement1.setInt(1, cusid);
                            try (ResultSet resultSet1 = statement1.executeQuery()) {
                                if (resultSet1.next()) {
                                    cusname = resultSet1.getString("Cname");
                                }
                            }
                        }


                        customerMap.put(cusid, new Payment(0, cusname, subtotal));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


            List<Payment> DayList = new ArrayList<>();
            int id = 1;
            for (Payment p : customerMap.values()) {
                p.setId(id++);
                DayList.add(p);
            }

            return DayList;

    }

        public static List<Payment> getMonthly()
    {

        List<Payment> MonthlyList = new ArrayList<>();
        Map<LocalDate, Double> groupedByDate = new LinkedHashMap<>();
        double totalForMonth = 0.0;

        String query = "SELECT subtotal, date FROM invoice WHERE Status = ? AND date BETWEEN ? AND ?";

        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "Done");
            statement.setDate(2, Date.valueOf(firstDayOfMonth));
            statement.setDate(3, Date.valueOf(lastDayOfMonth));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LocalDate date = resultSet.getDate("date").toLocalDate();
                double subtotal = resultSet.getDouble("subtotal");


                groupedByDate.put(date, groupedByDate.getOrDefault(date, 0.0) + subtotal);
            }


            int i = 1;
            for (Map.Entry<LocalDate, Double> entry : groupedByDate.entrySet()) {
                LocalDate date = entry.getKey();
                double subtotal = entry.getValue();
                MonthlyList.add(new Payment(i++, Date.valueOf(date), String.format("%.2f", subtotal)));

                totalForMonth += subtotal;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Total for the Month: Rs. " + totalForMonth);
        return MonthlyList;
    }


}

package com.pahana.Dao;

import com.pahana.Model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

public class BookDao {

	public static List<Book> getBook(String viewid) {
		List<Book> BookList = new ArrayList<>();
		String query = "SELECT * FROM bookcategory WHERE Bdetailid=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, viewid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String fileName = resultSet.getString("Bimage");
                String uploadPath = "assets/user/img/Book/";
                String image = uploadPath + fileName;

                Integer id = resultSet.getInt("Bid");
                String name = resultSet.getString("Bname");
                Integer quntity = resultSet.getInt("Bquntity");
                String price = resultSet.getString("Bprice");
                String description = resultSet.getString("Bdescription");

                BookList.add(new Book(id,name,image,price,description,quntity));
            }
            connection.close();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BookList;
	}

    public static List<Book> getaddBook(String bookid)
    {
        List<Book> addList = new ArrayList<>();
        Map<String, Integer> groupedQuantities = new HashMap<>();
        Map<String, String> itemPrices = new HashMap<>();

        String selectQuery = "SELECT B.Bname AS name, B.Bprice AS price, B.Bquntity AS quntity, " +
                "C.Quntity AS qty, C.Cateid AS bookid " +
                "FROM bookcategory B " +
                "JOIN cart C ON B.Bid = C.Cateid " +
                "WHERE C.Status = ? AND C.date = ?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {

            selectStmt.setString(1, "0");
            selectStmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));

            ResultSet rs = selectStmt.executeQuery();
            Map<String, String> bookIdMap = new HashMap<>();

            while (rs.next()) {
                String item = rs.getString("name");
                String price = rs.getString("price");
                int qty = rs.getInt("qty");
                String booksid = rs.getString("bookid");

                groupedQuantities.put(item, groupedQuantities.getOrDefault(item, 0) + qty);
                itemPrices.put(item, price);
                bookIdMap.put(item, booksid); // keep the latest book ID
            }

            // Delete existing cart items for today
            String deleteQuery = "DELETE FROM cart WHERE Status = ? AND date = ?";
            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
                deleteStmt.setString(1, "0");
                deleteStmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                deleteStmt.executeUpdate();
            }


            String insertQuery = "INSERT INTO cart (Quntity, Cateid, date, Status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                for (Map.Entry<String, Integer> entry : groupedQuantities.entrySet()) {
                    String itemName = entry.getKey();
                    int totalQty = entry.getValue();
                    String itemPriceStr = itemPrices.get(itemName);
                    double itemPrice = Double.parseDouble(itemPriceStr);
                    double subtotal = itemPrice * totalQty;

                    String booksid = bookIdMap.get(itemName);
                    insertStmt.setInt(1, totalQty);
                    insertStmt.setString(2, booksid);
                    insertStmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                    insertStmt.setString(4, "0");
                    insertStmt.addBatch();


                    addList.add(new Book(itemName, itemPrice, totalQty, subtotal));
                }
                insertStmt.executeBatch();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addList;

    }

    public static void addquntiy(Book book)
    {
        Integer bid = book.getId();
        Integer availableQty = 0;


        String selectQuery = "SELECT Bquntity FROM bookcategory WHERE Bid=?";
        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {

            selectStmt.setInt(1, bid);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                availableQty = rs.getInt("Bquntity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }


        String insertQuery = "INSERT INTO cart (Quntity, Cateid, date, Status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {

            insertStmt.setInt(1, book.getQuntity());
            insertStmt.setInt(2, bid);
            insertStmt.setDate(3, Date.valueOf(LocalDate.now()));
            insertStmt.setString(4, "0");

            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }


        int newQty = availableQty - book.getQuntity();
        if (newQty < 0) newQty = 0;

        String updateQuery = "UPDATE bookcategory SET Bquntity=? WHERE Bid=?";
        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {

            updateStmt.setInt(1, newQty);
            updateStmt.setInt(2, bid);

            int rows = updateStmt.executeUpdate();
            System.out.println("Rows updated: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update book quantity", e);
        }
    }
}

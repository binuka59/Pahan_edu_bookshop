package com.pahana.Dao;

import com.pahana.Model.Book;
import com.pahana.Model.Customer;
import com.pahana.Model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    public static void addItemDetails(Item item) {
        String category =item.getCategory();
        Integer categoryid=0;
        String query1 = "SELECT id FROM bookdetails WHERE Book_type=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query1)) {

            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categoryid = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "INSERT INTO bookcategory (Bimage,Bname,Bprice,Bdescription,Bquntity,Bdetailid) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getImage());
            statement.setString(2, item.getName());
            statement.setDouble(3, item.getPrice());
            statement.setString(4, item.getDescription());
            statement.setInt(5, item.getQuntity());
            statement.setInt(6, categoryid);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Item> getAllItems() {

            List<Item> ItemsList = new ArrayList<>();
            String query = "SELECT * FROM bookcategory";

            try (Connection connection = DbConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("Bid");
                    String fileName = resultSet.getString("Bimage");
                    String uploadPath = "assets/user/img/Book/";
                    String image = uploadPath + fileName;
                    String name = resultSet.getString("Bname");
                    Integer price = resultSet.getInt("Bprice");
                    String description = resultSet.getString("Bdescription");
                    String quantity = resultSet.getString("Bquntity");
                    String category = resultSet.getString("Bdetailid");
                    System.out.println("type num: " + category);
                    String bookType = "";

                    String query1 = "SELECT Book_type FROM bookdetails WHERE id = ?";
                    try (PreparedStatement statement1 = connection.prepareStatement(query1)) {
                        statement1.setString(1, category);
                        try (ResultSet resultSet1 = statement1.executeQuery()) {
                            if (resultSet1.next()) {
                                bookType = resultSet1.getString("Book_type");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }



                    ItemsList.add(new Item(id, image, name, price, description, quantity, bookType));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return ItemsList;
        }


    public static void addUpdatesitem(Item item) {
        Integer itemId = item.getId();
//        System.out.println("itemid"+itemId);
        String booktype = item.getCategory();
        Integer bookDetailsId = 0;

        String query1 = "SELECT id FROM bookdetails WHERE Book_type = ?";

        try (Connection connection = DbConnectionFactory.getConnection()) {


            try (PreparedStatement statement1 = connection.prepareStatement(query1)) {
                statement1.setString(1, booktype);
                try (ResultSet resultSet1 = statement1.executeQuery()) {
                    if (resultSet1.next()) {
                        bookDetailsId = resultSet1.getInt("id");
                    }
                }
            }


            String updateQuery = "UPDATE bookcategory SET Bimage=?, Bname=?, Bprice=?, Bdescription=?, Bquntity=?, Bdetailid=? WHERE Bid=?";

            try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                updateStmt.setString(1, item.getImage());
                updateStmt.setString(2, item.getName());
                updateStmt.setDouble(3, item.getPrice());
                updateStmt.setString(4, item.getDescription());
                updateStmt.setInt(5, item.getQuntity());
                updateStmt.setInt(6, bookDetailsId);
                updateStmt.setInt(7, itemId);

                int rows = updateStmt.executeUpdate();
                System.out.println("Rows updated: " + rows);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update book category", e);
        }
    }


    public static void addCategory(Item item) {
        String query = "INSERT INTO bookdetails (image,Book_type) VALUES (?,?)";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getImage());
            statement.setString(2, item.getCategory());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addDeleteitem(Integer deleteitemid)
    {
        String query = "DELETE FROM bookcategory WHERE Bid=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, deleteitemid);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void adddeletecategory(Integer deletecataid)
    {
        String query = "DELETE FROM  bookdetails WHERE id=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, deletecataid);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addUpdateCategory(Item item) {
        String updateQuery = "UPDATE bookdetails SET image=?, Book_type=? WHERE id=?";

        try (Connection connection = DbConnectionFactory.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
            updateStmt.setString(1, item.getImage());
            updateStmt.setString(2, item.getCategory());
            updateStmt.setInt(3, item.getId());

            int rows = updateStmt.executeUpdate();
            System.out.println("Rows updated: " + rows);


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update book category", e);
        }

    }

    public List<Item> getAllCategory()
    {
        List<Item> CategoryList = new ArrayList<>();
        String query = "SELECT * FROM bookdetails ";

        try(Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String fileName = resultSet.getString("image");
                String uploadPath = "assets/user/img/Category/";
                String image = uploadPath + fileName;
                String type = resultSet.getString("Book_type");


                CategoryList.add(new Item(id,image,type));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CategoryList;
    }
}

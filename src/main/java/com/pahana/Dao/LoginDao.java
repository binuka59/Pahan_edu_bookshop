package com.pahana.Dao;

import com.pahana.Model.Book;
import com.pahana.Model.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {
    public static List<Login> getAllLogin() {
        List<Login> Login = new ArrayList<>();
        String query = "SELECT * FROM login";

        try {
            Connection Connection = DbConnectionFactory.getConnection();
            Statement statement1 = Connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(query);

            while (resultSet.next()) {

                String password = resultSet.getString("Password");
                String email = resultSet.getString("Email");

                Login.add(new Login(email,password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Login;
    }

	public static List<Book> getAllBook() {
		List<Book> BookList = new ArrayList<>();
        String query = "SELECT * FROM bookdetails";

        try {
            Connection Connection = DbConnectionFactory.getConnection();
            Statement statement1 = Connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(query);

            while (resultSet.next()) {
                String fileName = resultSet.getString("image");
                String uploadPath = "assets/user/img/Category/";
                String image = uploadPath + fileName;
            	int id = resultSet.getInt("id");
                String booktype = resultSet.getString("Book_type");

                BookList.add(new Book(id,image,booktype));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BookList;
	}


    public static void updateData(Login login) {
        String query = "UPDATE login SET Address=? ,Mobile=?  WHERE id=?";

        try {

            Connection connection = DbConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, login.getAddress());
            statement.setInt(2, login.getMobile());
            statement.setInt(3, login.getId());

            statement.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

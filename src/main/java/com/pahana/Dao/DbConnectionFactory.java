package com.pahana.Dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionFactory {
    public static Connection getConnection() throws SQLException {
        return DbConnection.getInstance().getConnection();
    }

}




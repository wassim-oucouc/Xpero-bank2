package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static final String Url = "jdbc:postgresql://localhost:5555/xpero-bank";
    public static final String Username = "postgres";
    public static final String password = "123123";
    public static Connection connection = null;


    public static Connection getConnection(){
        if (connection == null) {
            try {
               connection = DriverManager.getConnection(Url, Username, password);
                System.out.println("DataBase Connected with success");
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return connection;
    }
}

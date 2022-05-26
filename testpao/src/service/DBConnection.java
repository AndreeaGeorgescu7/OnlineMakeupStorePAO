package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static DBConnection instance = null;
    private String url = "jdbc:mysql://localhost:3306/jdbc_pao";
    private String user = "root";
    private String password = "mysqlpaoA1509";
    Connection connection = null;

    public DBConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static  DBConnection getInstance() {
        if(instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
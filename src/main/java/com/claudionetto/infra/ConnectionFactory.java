package com.claudionetto.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private ConnectionFactory(){};

    public static Connection getConnection(){

        String url = "jdbc:postgresql://localhost/crud-expenses";
        String username = "postgres";
        String password = "postgres";

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

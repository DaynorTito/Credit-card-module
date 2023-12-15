package org.company.com.domain.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClientDB {

    private static final String DB_URL = "jdbc:mysql://viaduct.proxy*********";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "**********";

    private static Connection connection;
    private static ClientDB instance;


    private ClientDB() {
    }

    public static synchronized ClientDB getInstance() {
        if (instance == null) {
            instance = new ClientDB();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error connecting to database");
            return null;
        }
    }


}
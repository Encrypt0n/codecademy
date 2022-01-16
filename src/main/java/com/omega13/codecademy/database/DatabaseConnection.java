package com.omega13.codecademy.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
    This class makes the connection to the databasse
 */
public class DatabaseConnection {

    public Connection conn = null;

    DatabaseConnection() {

    }

    //Connects to the database
    public Connection makeConnection() {
        try {
            String url = "jdbc:sqlserver://aei-sql2.avans.nl:1443;database=CodeCademy13;user=Omega13;password=Fl3wby3487;encrypt=true;trustServerCertificate=true;loginTimeout=30;";
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        return conn;

    }

}

package com.omega13.codecademy.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection conn = null;

    DatabaseConnection() {
        
    }

    public Connection makeConnection() {
        try {
            String url = "jdbc:sqlserver://aei-sql2.avans.nl:1443;database=CodeCademy13;user=Omega13;password=Fl3wby3487;encrypt=true;trustServerCertificate=true;loginTimeout=30;";
            //jdbc:sqlserver://localhost;user=MyUserName;password=*****;
            //jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
            conn = DriverManager.getConnection(url);
    
            System.out.println("Got it!");
    
            } catch (SQLException e) {
                throw new Error("Problem", e);
            } 
            return conn;

    }
    
}

package com.omega13.codecademy.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Progress {

    DatabaseConnection connection = new DatabaseConnection();
    Connection conn = connection.makeConnection();

    public Progress() {

        
      

    }

    public void addProgress(int Pertentage, int CursistID, int ContentID) {

        try {

        String query9 = " update Voortgang set Percentage =? WHERE CursistID =? AND ContentID =?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt9 = conn.prepareStatement(query9);
        preparedStmt9.setInt (1, Pertentage);
        preparedStmt9.setInt (2, CursistID);
        preparedStmt9.setInt (3, ContentID);

        int count = preparedStmt9.executeUpdate();
        if(count > 0) {
            
             String query10 = " insert into Voortgang (Percentage, CursistID, ContentID)"
             + " values (?, ?, ?)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt8 = conn.prepareStatement(query10);
            preparedStmt8.setInt (1, Pertentage);
            preparedStmt8.setInt (2, CursistID);
            preparedStmt8.setInt (3, ContentID);
    
            // execute the preparedstatement
            preparedStmt8.execute();
        }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        }
    }
    
}

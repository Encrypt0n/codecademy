package com.omega13.codecademy.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class Enrollment {

    Calendar calendar = Calendar.getInstance();

    DatabaseConnection connection = new DatabaseConnection();
    

    public Enrollment() {

      //connection.makeConnection();

    }

    Connection conn = connection.makeConnection();

    public void addEnrollment(java.sql.Date startDate, int CursistID, int CertificaatID, int CursusID) {

        try {
        String query7 = " insert into Inschrijving (Inschrijfdatum, CursistID, CertificaatID, CursusID)"
             + " values (?, ?, ?, ?)";
    
            
        
            // create the mysql insert preparedstatement
        PreparedStatement preparedStmt7 = conn.prepareStatement(query7);
        preparedStmt7.setDate (1, startDate);
        preparedStmt7.setInt (2, CursistID);
        if(CertificaatID == -1) {
        preparedStmt7.setString   (3, null);
        } else {
            preparedStmt7.setInt(3, CertificaatID);
        }
        preparedStmt7.setInt   (4, CursusID);
  
        // execute the preparedstatement
        preparedStmt7.execute();

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

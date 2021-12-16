package com.omega13.codecademy.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class Course {
    
    Calendar calendar = Calendar.getInstance();

    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();
    

    Course() {
        //connection.makeConnection();
    }


    public void addCourse(String name, String subject, String introduction, String level, int interesting) {

        try {
            String query7 = " insert into Cursus (Naam, Onderwerp, Introductietekst, Niveau, Interessant)"
                 + " values (?, ?, ?, ?, ?)";
        
           
            
                // create the mysql insert preparedstatement
            PreparedStatement preparedStmt7 = conn.prepareStatement(query7);
            preparedStmt7.setString (1, name);
            preparedStmt7.setString (2, subject);
            preparedStmt7.setString   (3, introduction);
            preparedStmt7.setString   (4, level);
            if(interesting == -1) {
            preparedStmt7.setString(5, null);
            } else {
                preparedStmt7.setInt(5, interesting);
            }
      
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

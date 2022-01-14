package com.omega13.codecademy.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enrollment;
import com.omega13.codecademy.domain.Enums.Level;
import com.omega13.codecademy.domain.Progress;

public class ProgressData {

    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();



    public Progress getProgress(int moduleId, int memberId){
        Progress progress = null;
        int id;
        int Percentage;
        int CursistID;
        int ContentID;
        boolean gender;
        String address;
        String city;
        String country;
        ResultSet rs;

        try {
            String query = "" +
                    "SELECT Voortgang.ID, Voortgang.Percentage, Voortgang.CursistID, Voortgang.ContentID FROM Voortgang " +
                    "INNER JOIN Content ON Voortgang.ContentID = Content.ID " +
                    "INNER JOIN Module ON Content.ModuleID = Module.ID " +
                    "AND Module.ID = " + moduleId + "AND Voortgang.CursistID = " + memberId + "AND Cursist.ID = " + memberId + "AND Inschrijving.CursistID = " + memberId + "AND Inschrijving.Actief = true";

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            rs = preparedStmt.executeQuery();


            while (rs.next()) {               // Position the cursor                  4
                id = rs.getInt(1);
                Percentage = rs.getInt(2);        // Retrieve the first column value
                CursistID = rs.getInt(3);// Retrieve the first column value
                ContentID = rs.getInt(4);

                progress = new Progress(id, Percentage, CursistID, ContentID);
            }

            rs.close();
            preparedStmt.close();
            return progress;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }



    public void addProgress(int Pertentage, int CursistID, int ContentID, int ModuleID, int id) {
        //System.out.println(ContentID);
        ResultSet rs;

        try {

            String query9 = "update Voortgang set Percentage =? WHERE CursistID =? AND ContentID =? AND ID =?";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt9 = conn.prepareStatement(query9);
            preparedStmt9.setInt (1, Pertentage);
            preparedStmt9.setInt (2, CursistID);
            preparedStmt9.setInt (3, ContentID);
            preparedStmt9.setInt(4, id);

            int count = preparedStmt9.executeUpdate();
            if(count > 0) {



            String query10 =  "SELECT Content.ID FROM Content " +
                    "INNER JOIN Module ON Content.ModuleID = Module.ID " +

                    "AND Module.ID = " + ModuleID;
            /*String query10 = "select Content.ID FROM Module " +
                    "INNER JOIN Content ON Module.ID = Content.ModuleID"
                    + "AND Module.ID = " + ModuleID;*/
            PreparedStatement preparedStmt10 = conn.prepareStatement(query10);

            // execute the preparedstatement
            rs = preparedStmt10.executeQuery();


            while (rs.next()) {               // Position the cursor                  4
                ContentID = rs.getInt(1);


            }

            rs.close();
            preparedStmt10.close();

                String query11 = "insert into Voortgang (Percentage, CursistID, ContentID)"
                        + " values (?, ?, ?)";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt11 = conn.prepareStatement(query11);
                preparedStmt11.setInt (1, Pertentage);
                preparedStmt11.setInt (2, CursistID);
                preparedStmt11.setInt (3, ContentID);

                // execute the preparedstatement
                preparedStmt11.execute();
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }
}

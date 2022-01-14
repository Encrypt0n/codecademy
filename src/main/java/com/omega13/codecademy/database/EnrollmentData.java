package com.omega13.codecademy.database;

import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EnrollmentData {
    DatabaseConnection connection = new DatabaseConnection();
    Connection conn = connection.makeConnection();

    public void addEnrollment(java.sql.Date startDate, int CursistID, int CertificaatID, int CursusID) {

        try {
            updateActiveStatus(CursistID, CursusID);


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
        }
    }

    public List<Enrollment> getEnrollments() {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        int id;
        java.sql.Date registrationDate;
        int courseMemberId;
        int certificateId;
        int courseId;
        boolean active;
        ResultSet rs;
        try {
            String query = " select * from Inschrijving";



            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {               // Position the cursor                  4
                id = rs.getInt(1);
                registrationDate = rs.getDate(2);        // Retrieve the first column value
                courseMemberId = rs.getInt(3);// Retrieve the first column value
                certificateId = rs.getInt(4);
                courseId = rs.getInt(5);
                active = rs.getBoolean(6);

                enrollments.add(new Enrollment(id, registrationDate, courseMemberId, certificateId, courseId, active));
            }

            rs.close();
            preparedStmt.close();
            return enrollments;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }


    }

    private void updateActiveStatus(int CursistID, int CursusID){
        try{
            String updateQuery = "UPDATE Inschrijving set Inschrijving.Actief = 0 WHERE Inschrijving.CursistID = " + CursistID + " AND Inschrijving.CursusID = " + CursusID;
            PreparedStatement prepareStmtUpdate = conn.prepareStatement(updateQuery);
            prepareStmtUpdate.executeUpdate();
        }catch (SQLException e){
            throw new Error("Problem", e);
        }
    }

    public void deleteEnrollment(int id) {

        try {
            String query = " delete from Inschrijving WHERE ID =?";



            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, id);


            // execute the preparedstatement
            preparedStmt.execute();
            System.out.println("gelukt");

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }

}

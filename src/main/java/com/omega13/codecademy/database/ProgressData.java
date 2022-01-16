package com.omega13.codecademy.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enrollment;
import com.omega13.codecademy.domain.Enums.Level;
import com.omega13.codecademy.domain.Progress;

/*
    This class handles the table "Voortgang" inside the database
 */
public class ProgressData {

    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();



    //Gets the progress that belongs to the module and course member by the given moduleId
    public Progress getProgressForModule(int moduleId, int cursistId){
        Progress progress = null;
        int id;
        int Percentage;
        int CursistID;
        int ContentID;
        ResultSet rs;

        try {
            String query = "" +
                    "SELECT Voortgang.ID, Voortgang.Percentage, Voortgang.CursistID, Voortgang.ContentID, Inschrijving.Actief FROM Voortgang " +
                    "INNER JOIN Content ON Voortgang.ContentID = Content.ID " +
                    "INNER JOIN Module ON Content.ModuleID = Module.ID " +
                    "INNER JOIN Cursist ON Voortgang.CursistID = Cursist.ID " +
                    "INNER JOIN Inschrijving ON Cursist.ID = Inschrijving.CursistID " +
                    "AND Module.ID = " + moduleId + "AND Voortgang.CursistID = " + cursistId + "AND Cursist.ID = " + cursistId + "AND Inschrijving.CursistID = " + cursistId + "AND Inschrijving.Actief = " + 1;

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                Percentage = rs.getInt(2);
                CursistID = rs.getInt(3);
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

    //Gets the progress that belongs to the webcast
    public Progress getProgressForWebcast(int webcastId, int cursistId){
        Progress progress = null;
        int id;
        int Percentage;
        int CursistID;
        int ContentID;
        ResultSet rs;

        try {
            String query = "" +
                    "SELECT Voortgang.ID, Voortgang.Percentage, Voortgang.CursistID, Voortgang.ContentID FROM Voortgang " +
                    "INNER JOIN Content ON Voortgang.ContentID = Content.ID " +
                    "INNER JOIN Webcast ON Content.WebcastID = Webcast.ID " +
                    "AND Webcast.ID = " + webcastId + "AND Voortgang.CursistID = " + cursistId;

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                Percentage = rs.getInt(2);
                CursistID = rs.getInt(3);
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

    //Gets the average progress from the modules
    public int getAverageForModule(int moduleId){
        Progress progress = null;
        int Percentage = 0;
        ResultSet rs;

        try {
            String query = "" +
                    "SELECT AVG(Voortgang.Percentage) FROM Voortgang INNER JOIN Content ON Voortgang.ContentID = Content.ID INNER JOIN Module ON Content.ModuleID = Module.ID AND Module.ID = " + moduleId;

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {

                Percentage = rs.getInt(1);

            }

            rs.close();
            preparedStmt.close();
            return Percentage;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }



    //Adds the progress for the module
    public void addProgressForModule(int Pertentage, int CursistID, int ContentID, int ModuleID) {
        ResultSet rs;

        try {

            String query9 = "update Voortgang set Percentage =? WHERE CursistID =? AND ContentID =?";
            PreparedStatement preparedStmt9 = conn.prepareStatement(query9);
            preparedStmt9.setInt (1, Pertentage);
            preparedStmt9.setInt (2, CursistID);
            preparedStmt9.setInt (3, ContentID);

            preparedStmt9.executeUpdate();
            int count = preparedStmt9.getUpdateCount();
            if(count < 1) {



            String query10 =  "SELECT Content.ID FROM Content " +
                    "INNER JOIN Module ON Content.ModuleID = Module.ID " +

                    "AND Module.ID = " + ModuleID;
            PreparedStatement preparedStmt10 = conn.prepareStatement(query10);

            rs = preparedStmt10.executeQuery();

            while (rs.next()) {
                ContentID = rs.getInt(1);
            }

            rs.close();
            preparedStmt10.close();

                String query11 = "insert into Voortgang (Percentage, CursistID, ContentID)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStmt11 = conn.prepareStatement(query11);
                preparedStmt11.setInt (1, Pertentage);
                preparedStmt11.setInt (2, CursistID);
                preparedStmt11.setInt (3, ContentID);

                preparedStmt11.execute();
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    //Adds the progress for webcast
    public void addProgressForWebcast(int Pertentage, int CursistID, int ContentID, int WebcastID) {
        //System.out.println(ContentID);
        ResultSet rs;

        try {
            String query9 = "update Voortgang set Percentage =? WHERE CursistID =? AND ContentID =?";
            PreparedStatement preparedStmt9 = conn.prepareStatement(query9);
            preparedStmt9.setInt (1, Pertentage);
            preparedStmt9.setInt (2, CursistID);
            preparedStmt9.setInt (3, ContentID);

            preparedStmt9.executeUpdate();
            int count = preparedStmt9.getUpdateCount();
            if(count < 1) {

                String query10 =  "SELECT Content.ID FROM Content " +
                        "INNER JOIN Webcast ON Content.WebcastID = Webcast.ID " +
                        "AND Webcast.ID = " + WebcastID;
                PreparedStatement preparedStmt10 = conn.prepareStatement(query10);
                rs = preparedStmt10.executeQuery();


                while (rs.next()) {
                    ContentID = rs.getInt(1);


                }

                rs.close();
                preparedStmt10.close();

                String query11 = "insert into Voortgang (Percentage, CursistID, ContentID)"
                        + " values (?, ?, ?)";

                PreparedStatement preparedStmt11 = conn.prepareStatement(query11);
                preparedStmt11.setInt (1, Pertentage);
                preparedStmt11.setInt (2, CursistID);
                preparedStmt11.setInt (3, ContentID);


                preparedStmt11.execute();
            }


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    //Adds a certificate to the database
    public void addCertificate(int CursusID, int CursistID, int ModuleID) {

        ResultSet rs;
        int amountScored = 0;
        int amountModules = 0;

        try {

        String query10 =  "SELECT Count(Module.ID) FROM Voortgang " +
                "INNER JOIN Content ON Voortgang.ContentID = Content.ID " +
                "INNER JOIN Cursist ON Voortgang.CursistID = Cursist.ID " +
                "INNER JOIN Module ON Content.ModuleID = Module.ID " +
                "INNER JOIN CursusModule ON Module.ID = CursusModule.ModuleID " +
                "INNER JOIN Cursus ON CursusModule.CursusID = Cursus.ID " +

                "AND Cursus.ID = " + CursusID + "AND Cursist.ID = " + CursistID + "AND Voortgang.Percentage = " + 100;

        PreparedStatement preparedStmt10 = conn.prepareStatement(query10);

        rs = preparedStmt10.executeQuery();



        while(rs.next()){
            amountScored = Integer.parseInt(rs.getString(1));
        }



        String query11 =  "SELECT COUNT(Module.ID) FROM Cursus " +
                "INNER JOIN CursusModule ON Cursus.ID = CursusModule.CursusID " +
                "INNER JOIN Module ON CursusModule.ModuleID = Module.ID " +
                "AND Cursus.ID = " + CursusID;

        PreparedStatement preparedStmt11 = conn.prepareStatement(query11);

        rs = preparedStmt11.executeQuery();



        while(rs.next()){
            amountModules = Integer.parseInt(rs.getString(1));
        }

        rs.close();
        preparedStmt11.close();

        if(amountScored == amountModules) {
            String query12 = "insert into Certificaat (Beoordeling, NaamMedewerker)"
                    + " values (?, ?)";
            PreparedStatement preparedStmt12 = conn.prepareStatement(query12, Statement.RETURN_GENERATED_KEYS);
            preparedStmt12.setInt (1, 10);
            preparedStmt12.setString (2, "Rogier van der Gaag");


            preparedStmt12.execute();

            ResultSet rs2 = preparedStmt12.getGeneratedKeys();
            int CertificaatID = 0;
            if (rs2.next()) {
                CertificaatID = rs2.getInt(1);
                System.out.println(CertificaatID); // display inserted record
            }

            String query13 = "update Inschrijving set CertificaatID =?, Actief =? WHERE CursistID =? AND Actief = " + 1;
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt13 = conn.prepareStatement(query13);
            preparedStmt13.setInt (1, CertificaatID);
            preparedStmt13.setInt (2, 0);
            preparedStmt13.setInt(3, CursistID);

            preparedStmt13.executeUpdate();

        }



    } catch (SQLException e) {
        throw new Error("Problem", e);
    } finally {

    }

    }
}

package com.omega13.codecademy.database;

import com.omega13.codecademy.domain.Webcast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class WebcastData {



    Calendar calendar = Calendar.getInstance();

    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();

    public List<Webcast> getWebcasts() {
        ArrayList<Webcast> Webcasts = new ArrayList<>();
        int id;
        String title;


        ResultSet rs;
        try {
            String query = " select * from Webcast";




            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            /*preparedStmt.setString (1, name);
            preparedStmt.setString (2, email);
            preparedStmt.setDate   (3, birthday);
            preparedStmt.setBoolean(4, false);
            preparedStmt.setString (5, address);
            preparedStmt.setString (6, city);
            preparedStmt.setString (7, country);*/

            // execute the preparedstatement
            rs = preparedStmt.executeQuery();


            while (rs.next()) {               // Position the cursor                  4
                id = rs.getInt(1);
                title = rs.getString(2);        // Retrieve the first column value



                Webcasts.add(new Webcast(id, title));
            }

            rs.close();
            preparedStmt.close();
            return Webcasts;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }



    }

    public List<Webcast> getWebcastPerMember(int memberId){
        ArrayList<Webcast> Webcasts = new ArrayList<>();
        //ArrayList<CourseMember> courseMembers = new ArrayList<>();

        int id;
        String title;
        ResultSet rs;

        try{
            String query = "" +
                    "SELECT Webcast.ID, Webcast.Titel FROM Webcast " +
                    "INNER JOIN Content ON Webcast.ID = Content.ModuleID " +
                    "INNER JOIN Voortgang ON Content.ID = Voortgang.ContentID " +
                    "AND Voortgang.CursistID = " + memberId;





            PreparedStatement preparedStmt = conn.prepareStatement(query);
            rs = preparedStmt.executeQuery();

            while(rs.next()){
                id = rs.getInt(1);
                title = rs.getString(2);        // Retrieve the first column value

                Webcasts.add(new Webcast(id, title));
            }

            rs.close();
            preparedStmt.close();
            return Webcasts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Webcast getWebcast(int webcastId){
        Webcast webcast = null;
        int id;
        String title;

        ResultSet rs;

        try {
            String query = " select * from Webcast WHERE id = " + webcastId;

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            rs = preparedStmt.executeQuery();


            while (rs.next()) {               // Position the cursor                  4
                id = rs.getInt(1);
                title = rs.getString(2);        // Retrieve the first column value


                webcast = new Webcast(id, title);
            }

            rs.close();
            preparedStmt.close();
            return webcast;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    public List<Webcast> TopWebcasts() {
        List<Webcast> Webcasts = new ArrayList<>();
        List<Integer> top3 = new ArrayList<>();

        int id = 0;
        String title;

        ResultSet rs;


        try {

            String query10 =  "SELECT TOP 3 Webcast.ID, Webcast.Titel, COUNT(Voortgang.ID) as 'value' FROM Voortgang INNER JOIN Content ON Voortgang.ContentID = Content.ID INNER JOIN Webcast ON Content.WebcastID = Webcast.ID GROUP BY Webcast.ID, Webcast.Titel ORDER BY COUNT(Webcast.ID) DESC";

            PreparedStatement preparedStmt10 = conn.prepareStatement(query10);

            rs = preparedStmt10.executeQuery();



            while(rs.next()){
                id = Integer.parseInt(rs.getString(1));
                title = rs.getString(2);

                Webcasts.add(new Webcast(id, title));
                //System.out.println(contentIDs);





            }

            rs.close();
            preparedStmt10.close();
            //return amountWebcasts;




            return Webcasts;


        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }
}

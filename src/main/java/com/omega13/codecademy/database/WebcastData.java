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


/*
    This class handles the table "Webcast" inside the database
 */
public class WebcastData {
    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();

    //Gets all the webcasts from the database
    public List<Webcast> getWebcasts() {
        ArrayList<Webcast> Webcasts = new ArrayList<>();
        int id;
        String title;


        ResultSet rs;
        try {
            String query = " select * from Webcast";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                title = rs.getString(2);

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

    //Gets the webcast by webcastId
    public Webcast getWebcast(int webcastId){
        Webcast webcast = null;
        int id;
        String title;

        ResultSet rs;

        try {
            String query = " select * from Webcast WHERE id = " + webcastId;
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            rs = preparedStmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
                title = rs.getString(2);

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

    //Gets the top 3 webcasts
    public List<Webcast> TopWebcasts() {
        List<Webcast> Webcasts = new ArrayList<>();

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
            }

            rs.close();
            preparedStmt10.close();

            return Webcasts;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }
}

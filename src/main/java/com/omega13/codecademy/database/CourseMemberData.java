package com.omega13.codecademy.database;

import com.omega13.codecademy.database.DatabaseConnection;
import com.omega13.codecademy.domain.CourseMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CourseMemberData {

    Calendar calendar = Calendar.getInstance();

    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();

    public List<CourseMember> getUsers() {
        ArrayList<CourseMember> courseMembers = new ArrayList<>();
        String name;
        String email;
        java.sql.Date birthday;
        boolean gender;
        String address;
        String city;
        String country;
        ResultSet rs;
        try {
            String query = " select * from Cursist";



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
                name = rs.getString(2);        // Retrieve the first column value
                email = rs.getString(3);// Retrieve the first column value
                birthday = rs.getDate(4);
                gender = rs.getBoolean(5);
                address = rs.getString(6);
                city = rs.getString(7);
                country = rs.getString(8);

                courseMembers.add(new CourseMember(name, email, birthday, gender, address, city, country));
            }

            rs.close();
            preparedStmt.close();
            return courseMembers;

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

    public void addCourseMember(String name, String email, java.sql.Date birthday, boolean gender, String address, String city, String country) {

        try {
            String query = " insert into Cursist (Naam, Email, Geboortedatum, Geslacht, Adres, Woonplaats, Land)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";



            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, email);
            preparedStmt.setDate   (3, birthday);
            preparedStmt.setBoolean(4, false);
            preparedStmt.setString (5, address);
            preparedStmt.setString (6, city);
            preparedStmt.setString (7, country);

            // execute the preparedstatement
            preparedStmt.execute();

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

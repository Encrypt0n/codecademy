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

    public List<CourseMember> getCourseMembers() {
        ArrayList<CourseMember> courseMembers = new ArrayList<>();
        int id;
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
                id = rs.getInt(1);
                name = rs.getString(2);        // Retrieve the first column value
                email = rs.getString(3);// Retrieve the first column value
                birthday = rs.getDate(4);
                gender = rs.getBoolean(5);
                address = rs.getString(6);
                city = rs.getString(7);
                country = rs.getString(8);

                courseMembers.add(new CourseMember(id, name, email, birthday, gender, address, city, country));
            }

            rs.close();
            preparedStmt.close();
            return courseMembers;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }


    }

    public CourseMember getCourseMember(int courseMemberId){
        CourseMember member = new CourseMember();
        int id;
        String name;
        String email;
        java.sql.Date birthday;
        boolean gender;
        String address;
        String city;
        String country;
        ResultSet rs;

        try {
            String query = " select * from Cursist WHERE id = " + courseMemberId;

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            rs = preparedStmt.executeQuery();


            while (rs.next()) {               // Position the cursor                  4
                id = rs.getInt(1);
                name = rs.getString(2);        // Retrieve the first column value
                email = rs.getString(3);// Retrieve the first column value
                birthday = rs.getDate(4);
                gender = rs.getBoolean(5);
                address = rs.getString(6);
                city = rs.getString(7);
                country = rs.getString(8);

                member = new CourseMember(id, name, email, birthday, gender, address, city, country);
            }

            rs.close();
            preparedStmt.close();
            return member;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

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
            System.out.println("gelukt");

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }


    public void deleteCourseMember(int id) {

        try {
            String query = " delete from Cursist WHERE ID =?";



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

    public void updateCourseMember(int id, String name, String email, java.sql.Date birthday, boolean gender, String address, String city, String country) {

        try {
            String query = " update Cursist set Naam =?, Email =?, Geboortedatum =?, Geslacht =?, Adres =?, Woonplaats =?, Land =? WHERE ID =?";



            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, name);
            preparedStmt.setString (2, email);
            preparedStmt.setDate(3, birthday);
            preparedStmt.setBoolean (4, gender);
            preparedStmt.setString (5, address);
            preparedStmt.setString (6, city);
            preparedStmt.setString (7, country);
            preparedStmt.setInt (8, id);


            // execute the preparedstatement
            preparedStmt.executeUpdate();
            System.out.println("gelukt");

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }
}

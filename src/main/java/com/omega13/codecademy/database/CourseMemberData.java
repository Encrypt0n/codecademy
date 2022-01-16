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

/*
    This class handles the table "Cursisten" inside the database
 */
public class CourseMemberData {

    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();

    //Gets all of the course members from the database
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

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                email = rs.getString(3);
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

    //Gets the course member by id
    public CourseMember getCourseMember(int courseMemberId){
        CourseMember member = null;
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

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                email = rs.getString(3);
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

    //Adds a course member
    public void addCourseMember(String name, String email, java.sql.Date birthday, boolean gender, String address, String city, String country) {

        try {
            String query = " insert into Cursist (Naam, Email, Geboortedatum, Geslacht, Adres, Woonplaats, Land)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, email);
            preparedStmt.setDate   (3, birthday);
            preparedStmt.setBoolean(4, false);
            preparedStmt.setString (5, address);
            preparedStmt.setString (6, city);
            preparedStmt.setString (7, country);

            preparedStmt.execute();
            System.out.println("gelukt");

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }


    //Deletes a coursemember from the database
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

    //Updates a course member to the database
    public void updateCourseMember(int id, String name, String email, java.sql.Date birthday, boolean gender, String address, String city, String country) {

        try {
            String query = " update Cursist set Naam =?, Email =?, Geboortedatum =?, Geslacht =?, Adres =?, Woonplaats =?, Land =? WHERE ID =?";


            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, name);
            preparedStmt.setString (2, email);
            preparedStmt.setDate(3, birthday);
            preparedStmt.setBoolean (4, gender);
            preparedStmt.setString (5, address);
            preparedStmt.setString (6, city);
            preparedStmt.setString (7, country);
            preparedStmt.setInt (8, id);

            preparedStmt.executeUpdate();
            System.out.println("gelukt");

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }
}

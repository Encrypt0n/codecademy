package com.omega13.codecademy.database;

import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.Enums.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CourseData {

    Calendar calendar = Calendar.getInstance();

    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();

    public List<Course> getCourses() {
        ArrayList<Course> Courses = new ArrayList<>();
        int id;
        String title;
        String subject;
        String introtext;
        Level level;
        String interesting;

        ResultSet rs;
        try {
            String query = " select * from Cursus";




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
                subject = rs.getString(3);// Retrieve the first column value
                introtext = rs.getString(4);
                level = Level.valueOf(rs.getString(5));


                Courses.add(new Course(id, title, subject, introtext, level));
            }

            rs.close();
            preparedStmt.close();
            return Courses;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }



    }
}

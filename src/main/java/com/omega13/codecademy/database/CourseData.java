package com.omega13.codecademy.database;

import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enums.Level;
import com.omega13.codecademy.domain.Module;

import java.lang.reflect.Member;
import java.sql.*;
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
        String name;
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
                name = rs.getString(2);        // Retrieve the first column value
                subject = rs.getString(3);// Retrieve the first column value
                introtext = rs.getString(4);
                level = Level.valueOf(rs.getString(5));


                Courses.add(new Course(id, name, subject, introtext, level));
            }

            rs.close();
            preparedStmt.close();
            return Courses;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    public Course getCourse(int courseId){
        Course course = null;
        int id;
        String title;
        String subject;
        String introtext;
        Level level;
        String interesting;
        ResultSet rs;

        try {
            String query = " select * from Cursus WHERE id = " + courseId;

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            rs = preparedStmt.executeQuery();


            while (rs.next()) {               // Position the cursor                  4
                id = rs.getInt(1);
                title = rs.getString(2);        // Retrieve the first column value
                subject = rs.getString(3);// Retrieve the first column value
                introtext = rs.getString(4);
                level = Level.valueOf(rs.getString(5));

                course = new Course(id, title, subject, introtext, level);
            }

            rs.close();
            preparedStmt.close();
            return course;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    public List<Course> getCoursesPerMember(int memberId){
        ArrayList<Course> Courses = new ArrayList<>();
        //ArrayList<CourseMember> courseMembers = new ArrayList<>();

        int id;
        String name;
        String subject;
        String introtext;
        Level level;
        ResultSet rs;

        try{
            String query = "" +
                    "SELECT * FROM Cursus " +
                    "INNER JOIN Inschrijving ON Cursus.ID = Inschrijving.CursusID " +
                    "AND Inschrijving.CursistID = " + memberId + "AND Inschrijving.Actief = " + 1;

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            rs = preparedStmt.executeQuery();

            while(rs.next()){
                id = rs.getInt(1);
                name = rs.getString(2);        // Retrieve the first column value
                subject = rs.getString(3);// Retrieve the first column value
                introtext = rs.getString(4);
                level = Level.valueOf(rs.getString(5));

                Courses.add(new Course(id, name, subject, introtext, level));
            }

            rs.close();
            preparedStmt.close();
            return Courses;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addCourse(String name, String subject, String introtext, Level level, ArrayList<Module> modules) {

        try {
            String query = " insert into Cursus (Naam, Onderwerp, Introductietekst, Niveau)"
                    + " values (?, ?, ?, ?)";



            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, subject);
            preparedStmt.setString (3, introtext);
            preparedStmt.setString (4, String.valueOf(level));


            // execute the preparedstatement
            preparedStmt.execute();
            System.out.println("gelukt");



            ResultSet rs2 = preparedStmt.getGeneratedKeys();
            int CursusID = 0;
            if (rs2.next()) {
                CursusID = rs2.getInt(1);
                System.out.println(CursusID); // display inserted record
            }


            for(Module module : modules) {

                String query2 = " insert into Blabla (ModuleID, CursusID, Volgnummer)"
                        + " values (?, ?, ?)";



                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setInt (1, module.getId());
                preparedStmt2.setInt (2, CursusID);
                preparedStmt2.setInt (3, modules.indexOf(module));



                // execute the preparedstatement
                preparedStmt2.execute();
                System.out.println("gelukt");

            }




        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    public void deleteCourse(int id) {

        try {
            String query = " delete from Cursus WHERE ID =?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, id);


            // execute the preparedstatement
            preparedStmt.execute();

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }


    public void updateCourse(int id, String name, String subject, String introtext, Level level, ArrayList<Module> modules) {

        try {
            String query = " update Cursus set Naam =?, Onderwerp =?, Introductietekst =?, Niveau =? WHERE ID =?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, subject);
            preparedStmt.setString (3, introtext);
            preparedStmt.setString (4, String.valueOf(level));
            preparedStmt.setInt(5, id);


            // execute the preparedstatement
            preparedStmt.executeUpdate();


            String queryDelete = " delete from Blabla WHERE CursusID =?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmtDelete = conn.prepareStatement(queryDelete);
            preparedStmtDelete.setInt (1, id);


            // execute the preparedstatement
            preparedStmtDelete.execute();

            for (Module module: modules) {
                String query2 = " insert into Blabla (ModuleID, CursusID, Volgnummer)"
                        + " values (?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setInt (1, module.getId());
                preparedStmt2.setInt (2, id);
                preparedStmt2.setInt (3, modules.indexOf(module) + 1);

                // execute the preparedstatement
                preparedStmt2.execute();
            }



        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }

}

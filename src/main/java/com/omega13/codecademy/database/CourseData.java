package com.omega13.codecademy.database;

import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.Enums.Level;
import com.omega13.codecademy.domain.Module;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
    This class handles the table "Cursussen" inside the database
 */
public class CourseData {

    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();

    //Gets all the courses from the database
    public List<Course> getCourses() {
        ArrayList<Course> Courses = new ArrayList<>();
        int id;
        String name;
        String subject;
        String introtext;
        Level level;

        ResultSet rs;
        try {
            String query = " select * from Cursus";

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                subject = rs.getString(3);
                introtext = rs.getString(4);
                level = Level.valueOf(rs.getString(5));

                Courses.add(new Course(id, name, subject, introtext, level));
            }

            rs.close();
            preparedStmt.close();
            return Courses;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        }finally {

        }
    }

    //Gets the course by the courseId
    public Course getCourse(int courseId){
        Course course = null;
        int id;
        String title;
        String subject;
        String introtext;
        Level level;
        ResultSet rs;

        try {
            String query = " select * from Cursus WHERE id = " + courseId;

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                title = rs.getString(2);
                subject = rs.getString(3);
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

    //Gets all the courses where there are enrollments and where they're active
    public List<Course> getCoursesPerMember(int memberId){
        ArrayList<Course> Courses = new ArrayList<>();

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
                name = rs.getString(2);
                subject = rs.getString(3);
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

    //Adds a course to the database
    public void addCourse(String name, String subject, String introtext, Level level, ArrayList<Module> modules) {

        try {
            String query = " insert into Cursus (Naam, Onderwerp, Introductietekst, Niveau)"
                    + " values (?, ?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, subject);
            preparedStmt.setString (3, introtext);
            preparedStmt.setString (4, String.valueOf(level));

            preparedStmt.execute();
            System.out.println("gelukt");



            ResultSet rs2 = preparedStmt.getGeneratedKeys();
            int CursusID = 0;
            if (rs2.next()) {
                CursusID = rs2.getInt(1);
                System.out.println(CursusID);
            }


            for(Module module : modules) {

                String query2 = " insert into CursusModule (ModuleID, CursusID, Volgnummer)"
                        + " values (?, ?, ?)";


                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setInt (1, module.getId());
                preparedStmt2.setInt (2, CursusID);
                preparedStmt2.setInt (3, modules.indexOf(module) +1);


                preparedStmt2.execute();
                System.out.println("gelukt");

            }




        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    //Deletes a course by id
    public void deleteCourse(int id) {

        try {
            String query = " delete from Cursus WHERE ID =?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, id);


            preparedStmt.execute();

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }


    //Updates a course to the database
    public void updateCourse(int id, String name, String subject, String introtext, Level level, ArrayList<Module> modules) {

        try {
            String query = " update Cursus set Naam =?, Onderwerp =?, Introductietekst =?, Niveau =? WHERE ID =?";

            PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, subject);
            preparedStmt.setString (3, introtext);
            preparedStmt.setString (4, String.valueOf(level));
            preparedStmt.setInt(5, id);

            preparedStmt.executeUpdate();


            String queryDelete = " delete from CursusModule WHERE CursusID =?";

            PreparedStatement preparedStmtDelete = conn.prepareStatement(queryDelete);
            preparedStmtDelete.setInt (1, id);


            preparedStmtDelete.execute();

            for (Module module: modules) {
                String query2 = " insert into CursusModule (ModuleID, CursusID, Volgnummer)"
                        + " values (?, ?, ?)";

                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                preparedStmt2.setInt (1, module.getId());
                preparedStmt2.setInt (2, id);
                preparedStmt2.setInt (3, modules.indexOf(module) + 1);

                preparedStmt2.execute();
            }



        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }

    }

}

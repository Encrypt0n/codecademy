package com.omega13.codecademy.database;

import com.omega13.codecademy.domain.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ModuleData {

    Calendar calendar = Calendar.getInstance();

    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();

    public List<Module> getModules() {
        ArrayList<Module> Modules = new ArrayList<>();
        int id;
        String title;


        ResultSet rs;
        try {
            String query = " select * from Module";




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



                Modules.add(new Module(id, title));
            }

            rs.close();
            preparedStmt.close();
            return Modules;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }



    }

    public List<com.omega13.codecademy.domain.Module> getAvailableModules(){
        ArrayList<Module> Modules = new ArrayList<>();
        int id;
        String title;


        ResultSet rs;
        try {
            String query = " select Module.ID, Module.Titel from Module LEFT JOIN CursusModule ON Module.ID = CursusModule.ModuleID WHERE CursusModule.ModuleID IS NULL";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            rs = preparedStmt.executeQuery();


            while (rs.next()) {               // Position the cursor                  4
                id = rs.getInt(1);
                title = rs.getString(2);        // Retrieve the first column value

                Modules.add(new Module(id, title));
            }

            rs.close();
            preparedStmt.close();
            return Modules;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    public List<com.omega13.codecademy.domain.Module> getModulesPerCourse(int courseId){
        ArrayList<com.omega13.codecademy.domain.Module> Modules = new ArrayList<>();
        //ArrayList<CourseMember> courseMembers = new ArrayList<>();

        int id;
        String title;
        ResultSet rs;

        try{
            String query = "" +
                    "SELECT Module.ID, Module.Titel FROM Module " +
                    "INNER JOIN CursusModule ON Module.ID = CursusModule.ModuleID " +
                    "INNER JOIN Cursus ON CursusModule.CursusID = Cursus.ID " +
                    "AND Cursus.ID = " + courseId;





            PreparedStatement preparedStmt = conn.prepareStatement(query);
            rs = preparedStmt.executeQuery();

            while(rs.next()){
                id = rs.getInt(1);
                title = rs.getString(2);        // Retrieve the first column value

                Modules.add(new com.omega13.codecademy.domain.Module(id, title));
            }

            rs.close();
            preparedStmt.close();
            return Modules;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public com.omega13.codecademy.domain.Module getModule(int moduleId){
        com.omega13.codecademy.domain.Module module = null;
        int id;
        String title;

        ResultSet rs;

        try {
            String query = " select * from Module WHERE id = " + moduleId;

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            rs = preparedStmt.executeQuery();


            while (rs.next()) {               // Position the cursor                  4
                id = rs.getInt(1);
                title = rs.getString(2);        // Retrieve the first column value


                module = new com.omega13.codecademy.domain.Module(id, title);
            }

            rs.close();
            preparedStmt.close();
            return module;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }
}

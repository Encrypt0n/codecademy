package com.omega13.codecademy.database;

import com.omega13.codecademy.domain.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
    This class handles the table "Module" inside the database
 */
public class ModuleData {
    DatabaseConnection connection = new DatabaseConnection();

    Connection conn = connection.makeConnection();

    //Gets all of the modules from the database
    public List<Module> getModules() {
        ArrayList<Module> Modules = new ArrayList<>();
        int id;
        String title;
        int contentID = 0;


        ResultSet rs;
        try {
            String query = " select * from Module";

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                title = rs.getString(2);

                Modules.add(new Module(id, title, contentID));
            }

            rs.close();
            preparedStmt.close();
            return Modules;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    //Gets all the available course
    public List<com.omega13.codecademy.domain.Module> getAvailableModules(){
        ArrayList<Module> Modules = new ArrayList<>();
        int id;
        String title;
        int contentID = 0;

        ResultSet rs;
        try {
            String query = " select Module.ID, Module.Titel from Module LEFT JOIN CursusModule ON Module.ID = CursusModule.ModuleID WHERE CursusModule.ModuleID IS NULL";

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                title = rs.getString(2);

                Modules.add(new Module(id, title, contentID));
            }

            rs.close();
            preparedStmt.close();
            return Modules;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {

        }
    }

    //Gets the modules that belong to the course by the given courseId
    public List<com.omega13.codecademy.domain.Module> getModulesPerCourse(int courseId){
        ArrayList<com.omega13.codecademy.domain.Module> Modules = new ArrayList<>();

        int id;
        String title;
        int contentID;
        ResultSet rs;

        try{
            String query = "SELECT Module.ID, Module.Titel, Content.ID FROM Module INNER JOIN CursusModule ON Module.ID = CursusModule.ModuleID INNER JOIN Cursus ON CursusModule.CursusID = Cursus.ID AND Cursus.ID = " + courseId;

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            rs = preparedStmt.executeQuery();

            while(rs.next()){
                id = rs.getInt(1);
                title = rs.getString(2);
                contentID = rs.getInt(3);

                Modules.add(new com.omega13.codecademy.domain.Module(id, title, contentID));
            }

            rs.close();
            preparedStmt.close();
            return Modules;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Gets the module by moduleId
    public com.omega13.codecademy.domain.Module getModule(int moduleId){
        com.omega13.codecademy.domain.Module module = null;
        int id;
        String title;
        int contentID = 0;

        ResultSet rs;

        try {
            String query = " select * from Module WHERE id = " + moduleId;

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();


            while (rs.next()) {
                id = rs.getInt(1);
                title = rs.getString(2);

                module = new com.omega13.codecademy.domain.Module(id, title, contentID);
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

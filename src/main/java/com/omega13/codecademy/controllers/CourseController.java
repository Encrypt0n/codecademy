package com.omega13.codecademy.controllers;

import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.database.ModuleData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enums.Level;
import com.omega13.codecademy.domain.Module;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    @FXML
    TableView<Course> Courses;
    TableView<Module> Modules;

    @FXML
    TableColumn<CourseMember, String> Title;
    @FXML
    TableColumn<CourseMember, String> Subject;
    @FXML
    TableColumn<CourseMember, String> Introtext;
    @FXML
    TableColumn<CourseMember, com.omega13.codecademy.domain.Enums.Level> Level;

    public CourseData courseData = new CourseData();
    public ModuleData moduleData = new ModuleData();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Courses.getItems().setAll(courseData.getCourses());
        Modules.getItems().setAll(moduleData.getModules());

    }
}

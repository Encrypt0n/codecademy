package com.omega13.codecademy.controllers.crud;

import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.database.ModuleData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enums.Level;
import com.omega13.codecademy.domain.Module;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    @FXML
    VBox ModuleVBox;

    @FXML
    TextField newName;
    @FXML
    TextField newSubject;
    @FXML
    TextArea newIntrotext;
    @FXML
    ComboBox newLevel;



    @FXML
    private ComboBox<Module> ModuleDropdown;
    @FXML
    private ComboBox<Level> LevelDropdown;

    private Module selectedModule;
    private Level selectedLevel;

    public CourseData courseData = new CourseData();
    public ModuleData moduleData = new ModuleData();
    public Level level;

    ArrayList<Module> modules = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillModules();
        fillLevel();
    }

    public void addModule() {
        modules.add(selectedModule);
    }



    public void setDropdownValue(MenuButton dropdown){
        for(Module module : moduleData.getModules()){
            MenuItem newItem = new MenuItem();
            newItem.setText(module.getTitle());
            dropdown.getItems().add(newItem);
            newItem.setOnAction(actionEvent -> {
                dropdown.setText(module.getTitle());
            });
        }
    }

    public void fillModules() {
        ObservableList<Module> modules = FXCollections.observableArrayList();
        this.moduleData.getModules();
        modules.addAll(this.moduleData.getModules());

        ModuleDropdown.setItems(modules);


        ModuleDropdown.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null)
                this.selectedModule = newval;
        });
    }

    public void fillLevel() {
        ObservableList<Level> levels = FXCollections.observableArrayList();

        levels.addAll(Arrays.asList(Level.values()));

        LevelDropdown.setItems(levels);


        LevelDropdown.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null)
                this.selectedLevel = newval;
        });
    }

    @FXML
    public void addCourse(ActionEvent e){
        courseData.addCourse(newName.getText(), newSubject.getText(), newIntrotext.getText(), selectedLevel, modules);
        //Courses.refresh();
    }

    /*@FXML
    public void deleteCourse(ActionEvent e){
        courseData.deleteCourseMember(id);
        Courses.getItems().setAll(courseData.getCourses());
    }

    public void updateCourse(ActionEvent e){
        //System.out.println(CourseMember.getSelectionModel().getSelectedItem().getName());
        courseData.updateCourseMember(id, newName.getText(), newEmail.getText(), java.sql.Date.valueOf(newBirthday.getValue()), true, newAddress.getText(), newCity.getText(), newCountry.getText());
        Courses.refresh();
    }*/

}

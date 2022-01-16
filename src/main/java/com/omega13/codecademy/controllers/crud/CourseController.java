package com.omega13.codecademy.controllers.crud;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.database.ModuleData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.Enums.Level;
import com.omega13.codecademy.domain.Module;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/*
    The class CourseController is in connection with course-view.fxml, this class is responsible for the interactions with the user and the CRUD
 */
public class CourseController implements Initializable {
    @FXML
    TextField newName;
    @FXML
    TextField newSubject;
    @FXML
    TextArea newIntrotext;

    @FXML
    TableView<Course> CourseTable;
    @FXML
    TableColumn<Course, String> CourseColumn;
    @FXML
    TableColumn<Course, String> ModuleColumn;
    @FXML
    TableColumn<Course, String> SubjectColumn;
    @FXML
    TableColumn<Course, String> LevelColumn;
    @FXML
    TableColumn<Course, String> IntroTextColumn;

    @FXML
    ListView<Module> Modules;


    @FXML
    private ComboBox<Module> ModuleDropdown;
    @FXML
    private ComboBox<Level> LevelDropdown;

    @FXML
    Button btn_return;

    private Module selectedModule;
    private Level selectedLevel;
    private Course selectedCourse;

    private CourseData courseData = new CourseData();
    private ModuleData moduleData = new ModuleData();
    private SceneController sceneController = new SceneController();
    ArrayList<Module> modules = new ArrayList<>();

    //loads after the constructor but before the page is loaded and fills the tables and dropdown
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCourseTable();
        fillModules();
        fillLevel();
    }

    //Adds a selected module to an arraylist
    public void addModule() {
        if(modules.contains(selectedModule)) return;
        modules.add(selectedModule);
        Modules.getItems().add(selectedModule);
    }

    //Fills the table with all the courses
    private void fillCourseTable(){
        CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        ModuleColumn.setCellValueFactory(data -> new SimpleStringProperty(moduleData.getModulesPerCourse(data.getValue().getId()).size() + ""));
        SubjectColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSubject()));
        LevelColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLevel().toString()));
        IntroTextColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIntrotext()));

        CourseTable.getItems().setAll(courseData.getCourses());

        CourseTable.getSelectionModel().selectedIndexProperty().addListener((num) -> setCourseData());
    }


    //Fills the dropdown with all the modules
    public void fillModules() {
        ObservableList<Module> modules = FXCollections.observableArrayList();
        modules.addAll(this.moduleData.getAvailableModules());

        ModuleDropdown.setItems(modules);


        ModuleDropdown.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null)
                this.selectedModule = newval;
        });
    }

    //Fills the dropdown with all the enum "Level"
    public void fillLevel() {
        ObservableList<Level> levels = FXCollections.observableArrayList();

        levels.addAll(Arrays.asList(Level.values()));

        LevelDropdown.setItems(levels);


        LevelDropdown.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null)
                this.selectedLevel = newval;
        });
    }

    //If clicked on a course inside the table, fills the selected course and sets the input values to the course information
    private void setCourseData(){
        if(CourseTable.isPressed()){
            modules.clear();
            selectedCourse = CourseTable.getSelectionModel().selectedItemProperty().get();
            newName.setText(selectedCourse.getTitle());
            newSubject.setText(selectedCourse.getSubject());
            newIntrotext.setText(selectedCourse.getIntrotext());
            LevelDropdown.setValue(selectedCourse.getLevel());
            Modules.getItems().setAll(moduleData.getModulesPerCourse(selectedCourse.getId()));
            modules.addAll(moduleData.getModulesPerCourse(selectedCourse.getId()));
        }
    }

    //Adds course to database
    @FXML
    public void addCourse(ActionEvent e){
        if(modules.size() == 0) return;
        courseData.addCourse(newName.getText(), newSubject.getText(), newIntrotext.getText(), selectedLevel, modules);
        CourseTable.getItems().setAll(courseData.getCourses());
        //Courses.refresh();
    }

    //Delete course from database
    public void deleteCourse(ActionEvent e){
        System.out.println(selectedCourse.getId());
        courseData.deleteCourse(selectedCourse.getId());
        CourseTable.getItems().setAll(courseData.getCourses());
        fillModules();
    }


    //Updates course from database
    public void updateCourse(ActionEvent e) {
        courseData.updateCourse(selectedCourse.getId(), newName.getText(), newSubject.getText(), newIntrotext.getText(), selectedLevel, modules);
        CourseTable.getItems().setAll(courseData.getCourses());
        fillModules();
    }

    //Returns the user the the previous page
    @FXML
    private void onReturnClick() throws IOException {
        sceneController.sceneSwitcher("crud/CRUD-view.fxml", btn_return);
    }

}

package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.*;
import com.omega13.codecademy.domain.Course;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
    The class AvgProgressController is in connection with avgprogress-view.fxml, this class is responsible for the interactions with the user
 */
public class AvgProgressController implements Initializable {

    @FXML
    Button btn_return;

    SceneController sceneController;

    ModuleData moduleData = new ModuleData();

    CourseData courseData = new CourseData();

    ProgressData progressData = new ProgressData();

    @FXML
    TableView<Course> CourseTable;
    @FXML
    TableColumn<Course, String> CourseColumn;

    @FXML
    TableView<com.omega13.codecademy.domain.Module> ModuleTable;
    @FXML
    TableColumn<com.omega13.codecademy.domain.Module, String> ModuleColumn;
    @FXML
    Label avgPercentage;

    int courseId;
    int moduleId;
    int average;

    //Constructor
    public AvgProgressController(){
        this.sceneController = new SceneController();
    }

    //loads after the constructor but before the page is loaded and fills the table
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillCourseList();

    }

    //Fills the table with courses
    private void fillCourseList() {
            CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            CourseTable.getItems().setAll(courseData.getCourses());
            CourseTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getModules());
    }

    //Gets the selected module when clicked on one
    public void getModules() {

        if(CourseTable.isPressed()){
            courseId = CourseTable.getSelectionModel().getSelectedItem().getId();
            this.ModuleColumn.setCellValueFactory(data -> new SimpleStringProperty(this.moduleData.getModule(data.getValue().getId()).getTitle()));
            ModuleTable.getItems().setAll(moduleData.getModulesPerCourse(courseId));
            ModuleTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getAvarageProgress());
            //this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            //CourseTable.getItems().setAll(courseData.getCoursesPerMember((memberId)));
        }
    }

    //Gets the selected module when clicked on one and changes the text value with the average percentage
    public void getAvarageProgress() {

        if(ModuleTable.isPressed()){
            moduleId = ModuleTable.getSelectionModel().getSelectedItem().getId();
            System.out.println(moduleId);
            //this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.progressData.getProgress(data.getValue().getId()).toString());

            average = this.progressData.getAverageForModule(moduleId);


            if(average != 0) {
                avgPercentage.setText("Deze module is gemiddled " + average + "% afgerond");
            }else{
                avgPercentage.setText("Niemand is aan deze module begonnen");
            }
        }
    }


    @FXML
    private void onReturnClick() throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }
}

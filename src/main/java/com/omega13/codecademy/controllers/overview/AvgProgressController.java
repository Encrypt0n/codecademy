package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.*;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.Progress;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ResourceBundle;

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

    int courseId;
    int moduleId;
    int avarage;

    public AvgProgressController(){
        this.sceneController = new SceneController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillCourseList();

    }

    private void fillCourseList() {


            CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            CourseTable.getItems().setAll(courseData.getCourses());
            CourseTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getModules());
            //this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            //CourseTable.getItems().setAll(courseData.getCoursesPerMember((memberId)));


    }

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

    public void getAvarageProgress() {

        if(ModuleTable.isPressed()){
            moduleId = ModuleTable.getSelectionModel().getSelectedItem().getId();
            //this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.progressData.getProgress(data.getValue().getId()).toString());

            avarage = this.progressData.getAvarageForModule(moduleId);


            if(avarage != 0) {
                System.out.println(avarage);
                //id = progress.getId();
                //contentId = progress.getContentID();
                //ProgressSlider.adjustValue(avarage);

            }
            //CourseTable.getItems().setAll(courseData.getCoursesPerMember(moduleId));
            //CourseTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getModules());
            //this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            //CourseTable.getItems().setAll(courseData.getCoursesPerMember((memberId)));
        }
    }


    @FXML
    private void onReturnClick() throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }
}

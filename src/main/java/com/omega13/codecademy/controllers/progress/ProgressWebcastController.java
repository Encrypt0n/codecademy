package com.omega13.codecademy.controllers.progress;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.*;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Progress;
import com.omega13.codecademy.domain.Webcast;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;

public class ProgressWebcastController implements Initializable {
    @FXML
    Button btn_return;

    @FXML
    Slider ProgressSlider;

    @FXML
    TableView<CourseMember> MemberTable;
    @FXML
    TableColumn<CourseMember, String> MemberColumn;

    @FXML
    TableView<Webcast> WebcastTable;
    @FXML
    TableColumn<Webcast, String> WebcastColumn;



    CourseMemberData courseMemberData = new CourseMemberData();
    CourseData courseData = new CourseData();
    WebcastData webcastData = new WebcastData();
    ProgressData progressData = new ProgressData();





    SceneController sceneController;

    int memberId;
    int webcastId;
    int contentId;
    Progress progress;
    int id;

    public ProgressWebcastController(){
        this.sceneController = new SceneController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillMemberList();

    }

    private void fillMemberList(){
        MemberColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseMemberData.getCourseMember(data.getValue().getId()).getName()));
        MemberTable.getItems().setAll(courseMemberData.getCourseMembers());
        MemberTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getWebcasts());
    }

    private void getWebcasts() {
        if(MemberTable.isPressed()){
            memberId = MemberTable.getSelectionModel().getSelectedItem().getId();
            this.WebcastColumn.setCellValueFactory(data -> new SimpleStringProperty(this.webcastData.getWebcast(data.getValue().getId()).getTitle()));
            //System.out.println(moduleData.getModulesPerCourse(courseId));
            WebcastTable.getItems().setAll(webcastData.getWebcasts());
            WebcastTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getProgress());


            /*int courseId = CourseTable.getSelectionModel().getSelectedItem().getId();
            this.ModuleColumn.setCellValueFactory(data -> new SimpleStringProperty(this.moduleData.getModule(data.getValue().).getTitle()));
            //this.ModuleColumn.setCellValueFactory(data -> new SimpleStringProperty(this.moduleData.getModule(data.getValue().getId()).getTitle()));
            ModuleTable.getItems().setAll((Module) progressData.getModules(courseId));*/
        }

    }

    private void getProgress() {
        if(WebcastTable.isPressed()){
            webcastId = WebcastTable.getSelectionModel().getSelectedItem().getId();
            //this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.progressData.getProgress(data.getValue().getId()).toString());

            progress = this.progressData.getProgressForWebcast(webcastId, memberId);


            if(progress != null) {
                //System.out.println(progress.getPercentage());
                id = progress.getId();
                contentId = progress.getContentID();
                ProgressSlider.adjustValue(progress.getPercentage());

            }
            //CourseTable.getItems().setAll(courseData.getCoursesPerMember(moduleId));
            //CourseTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getModules());
            //this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            //CourseTable.getItems().setAll(courseData.getCoursesPerMember((memberId)));
        }

    }

    @FXML
    public void saveProgress(ActionEvent e){
        System.out.println("hoi");
        System.out.println(memberId);
        progressData.addProgressForWebcast((int)ProgressSlider.getValue(), memberId, contentId, webcastId);

        //CourseMembers.refresh();
    }



    @FXML
    private void onSliderChanged(){
        int sliderValue = (int) ProgressSlider.getValue();
        System.out.println(sliderValue);
    }

    @FXML
    private void onReturnClick() throws IOException {
        this.sceneController.sceneSwitcher("progress/progress-view.fxml", btn_return);
    }
}

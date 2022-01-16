package com.omega13.codecademy.controllers.progress;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.*;
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
import java.util.ResourceBundle;

/*
    The class ProgressWebcastController is in connection with progresswebcast-view.fxml, this class is responsible for the interactions with the user
 */
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

    //Constructor
    public ProgressWebcastController(){
        this.sceneController = new SceneController();
    }

    //loads after the constructor but before the page is loaded and fills the table
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillMemberList();

    }

    //Fills the table with course members
    private void fillMemberList(){
        MemberColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseMemberData.getCourseMember(data.getValue().getId()).getName()));
        MemberTable.getItems().setAll(courseMemberData.getCourseMembers());
        MemberTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getWebcasts());
    }

    //Gets the selected course member and fill the webcast table with all the webcasts
    private void getWebcasts() {
        if(MemberTable.isPressed()){
            memberId = MemberTable.getSelectionModel().getSelectedItem().getId();
            this.WebcastColumn.setCellValueFactory(data -> new SimpleStringProperty(this.webcastData.getWebcast(data.getValue().getId()).getTitle()));
            WebcastTable.getItems().setAll(webcastData.getWebcasts());
            WebcastTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getProgress());
        }
    }

    //Gets the selected webcast and (if the progress isn't null) fills the progress bar
    private void getProgress() {
        if(WebcastTable.isPressed()){
            webcastId = WebcastTable.getSelectionModel().getSelectedItem().getId();

            progress = this.progressData.getProgressForWebcast(webcastId, memberId);

            if(progress != null) {
                id = progress.getId();
                contentId = progress.getContentID();
                ProgressSlider.adjustValue(progress.getPercentage());

            }
        }
    }

    //Saves the progress to the database
    @FXML
    public void saveProgress(ActionEvent e){
        System.out.println("hoi");
        System.out.println(memberId);
        progressData.addProgressForWebcast((int)ProgressSlider.getValue(), memberId, contentId, webcastId);
    }

    //Returns the user to the previous page
    @FXML
    private void onReturnClick() throws IOException {
        this.sceneController.sceneSwitcher("progress/progress-view.fxml", btn_return);
    }
}

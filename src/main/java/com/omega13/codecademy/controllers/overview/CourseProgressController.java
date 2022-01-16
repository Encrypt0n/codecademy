package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.database.ModuleData;
import com.omega13.codecademy.database.ProgressData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Progress;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
    The class CourseProgressController is in connection with courseprogress-view.fxml, this class is responsible for the interactions with the user
 */
public class CourseProgressController implements Initializable {
    @FXML
    TableView<CourseMember> MemberTable;
    @FXML
    TableColumn<CourseMember, String> MemberColumn;

    @FXML
    TableView<Course> CourseTable;
    @FXML
    TableColumn<Course, String> CourseColumn;

    @FXML
    TableView<com.omega13.codecademy.domain.Module> ModuleTable;
    @FXML
    TableColumn<com.omega13.codecademy.domain.Module, String> ModuleColumn;

    @FXML
    Label percentageLabel;
    @FXML
    Button btn_return;

    CourseMemberData courseMemberData = new CourseMemberData();
    CourseData courseData = new CourseData();
    ModuleData moduleData = new ModuleData();
    ProgressData progressData = new ProgressData();
    SceneController sceneController = new SceneController();

    Progress progress;
    int moduleId;
    int memberId;
    int courseId;

    //loads after the constructor but before the page is loaded and fills the table
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillMemberList();

    }

    //Fills the table with course members
    private void fillMemberList(){
        MemberColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseMemberData.getCourseMember(data.getValue().getId()).getName()));
        MemberTable.getItems().setAll(courseMemberData.getCourseMembers());
        MemberTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getCourses());
    }

    //gets the selected course and fills another table
    private void getCourses() {
        if(MemberTable.isPressed()){
            memberId = MemberTable.getSelectionModel().getSelectedItem().getId();
            this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            CourseTable.getItems().setAll(courseData.getCoursesPerMember(memberId));
            CourseTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getModules());
        }

    }

    //Gets the selected module and fills another table
    private void getModules() {
        if(CourseTable.isPressed()){
            courseId = CourseTable.getSelectionModel().getSelectedItem().getId();
            this.ModuleColumn.setCellValueFactory(data -> new SimpleStringProperty(this.moduleData.getModule(data.getValue().getId()).getTitle()));
            ModuleTable.getItems().setAll(moduleData.getModulesPerCourse(courseId));
            ModuleTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getProgress());

        }

    }

    //Sets the text to the percentage of the module
    private void getProgress(){
        if(ModuleTable.isPressed()){

            moduleId = ModuleTable.getSelectionModel().getSelectedItem().getId();

            progress = this.progressData.getProgressForModule(moduleId, memberId);

            percentageLabel.setText("Deze module is voor " + progress.getPercentage() + "% af");

            if(progress == null) {
                percentageLabel.setText("Deze module is voor 0% af");
            }
        }
    }

    //Returns use to the previous page
    @FXML
    private void onReturnClick() throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }
}

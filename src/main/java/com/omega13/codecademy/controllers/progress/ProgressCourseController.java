package com.omega13.codecademy.controllers.progress;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.database.ModuleData;
import com.omega13.codecademy.database.ProgressData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Progress;
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
    The class ProgressCourseController is in connection with progresscourse-view.fxml, this class is responsible for the interactions with the user
 */
public class ProgressCourseController implements Initializable {
    @FXML
    Slider ProgressSlider;
    @FXML
    TableView<CourseMember> MemberTable;
    @FXML
    TableColumn<CourseMember, String> MemberColumn;

    @FXML
    TableView<Course> CourseTable;
    @FXML
    TableColumn<Course, String> CourseColumn;

    @FXML
    Button btn_return;

    @FXML
    TableView<com.omega13.codecademy.domain.Module> ModuleTable;
    @FXML
    TableColumn<com.omega13.codecademy.domain.Module, String> ModuleColumn;

    CourseMemberData courseMemberData = new CourseMemberData();
    CourseData courseData = new CourseData();
    ModuleData moduleData = new ModuleData();
    ProgressData progressData = new ProgressData();
    SceneController sceneController = new SceneController();

    int memberId;
    int courseId;
    int moduleId;
    int contentId;
    Progress progress;
    int id;

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

    //Gets the selected member and fills the the courses table
    private void getCourses() {
        if(MemberTable.isPressed()){
            memberId = MemberTable.getSelectionModel().getSelectedItem().getId();
            this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            CourseTable.getItems().setAll(courseData.getCoursesPerMember(memberId));
            CourseTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getModules());
        }

    }

    //Gets the selected course and fills the module table
    private void getModules() {
        if(CourseTable.isPressed()){
            courseId = CourseTable.getSelectionModel().getSelectedItem().getId();
            this.ModuleColumn.setCellValueFactory(data -> new SimpleStringProperty(this.moduleData.getModule(data.getValue().getId()).getTitle()));
            ModuleTable.getItems().setAll(moduleData.getModulesPerCourse(courseId));
            ModuleTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getProgress());
        }

    }

    //Gets the selected module and (if the progress isn't null) fills the progress bar
    private void getProgress() {
        if(ModuleTable.isPressed()){
            moduleId = ModuleTable.getSelectionModel().getSelectedItem().getId();
            contentId = ModuleTable.getSelectionModel().getSelectedItem().getContentID();
            progress = this.progressData.getProgressForModule(moduleId, memberId);
            if(progress != null) {
                id = progress.getId();
                contentId = progress.getContentID();
                ProgressSlider.adjustValue(progress.getPercentage());
            }
        }

    }


    @FXML
    public void onSliderChanged(){
        int sliderValue = (int) ProgressSlider.getValue();

        System.out.println(sliderValue);
    }


    //Saves the progress to the database
    @FXML
    public void saveProgress(ActionEvent e){
        progressData.addProgressForModule((int)ProgressSlider.getValue(), memberId, contentId, moduleId);
        progressData.addCertificate(courseId, memberId, moduleId);
    }

    //Returns the user to the previous page
    @FXML
    private void onReturnClick() throws IOException {
        sceneController.sceneSwitcher("progress/progress-view.fxml", btn_return);
    }
}

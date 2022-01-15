package com.omega13.codecademy.controllers.progress;

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
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.SynchronousQueue;

public class ProgressController implements Initializable {
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
    TableView<com.omega13.codecademy.domain.Module> ModuleTable;
    @FXML
    TableColumn<com.omega13.codecademy.domain.Module, String> ModuleColumn;

    CourseMemberData courseMemberData = new CourseMemberData();
    CourseData courseData = new CourseData();
    ModuleData moduleData = new ModuleData();
    ProgressData progressData = new ProgressData();

    int memberId;
    int courseId;
    int moduleId;
    int contentId;
    Progress progress;
    int id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillMemberList();

    }

    private void fillMemberList(){
        MemberColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseMemberData.getCourseMember(data.getValue().getId()).getName()));
        MemberTable.getItems().setAll(courseMemberData.getCourseMembers());
        MemberTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getCourses());
    }

    private void getCourses() {
        if(MemberTable.isPressed()){
            memberId = MemberTable.getSelectionModel().getSelectedItem().getId();
            this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            CourseTable.getItems().setAll(courseData.getCoursesPerMember(memberId));
            CourseTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getModules());
            //this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getId()).getTitle()));
            //CourseTable.getItems().setAll(courseData.getCoursesPerMember((memberId)));
        }

    }

    private void getModules() {
        if(CourseTable.isPressed()){
            courseId = CourseTable.getSelectionModel().getSelectedItem().getId();
            this.ModuleColumn.setCellValueFactory(data -> new SimpleStringProperty(this.moduleData.getModule(data.getValue().getId()).getTitle()));
            //System.out.println(moduleData.getModulesPerCourse(courseId));
            ModuleTable.getItems().setAll(moduleData.getModulesPerCourse(courseId));
            ModuleTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getProgress());


            /*int courseId = CourseTable.getSelectionModel().getSelectedItem().getId();
            this.ModuleColumn.setCellValueFactory(data -> new SimpleStringProperty(this.moduleData.getModule(data.getValue().).getTitle()));
            //this.ModuleColumn.setCellValueFactory(data -> new SimpleStringProperty(this.moduleData.getModule(data.getValue().getId()).getTitle()));
            ModuleTable.getItems().setAll((Module) progressData.getModules(courseId));*/
        }

    }

    private void getProgress() {
        if(ModuleTable.isPressed()){
            moduleId = ModuleTable.getSelectionModel().getSelectedItem().getId();
            //this.CourseColumn.setCellValueFactory(data -> new SimpleStringProperty(this.progressData.getProgress(data.getValue().getId()).toString());
            
            progress = this.progressData.getProgress(moduleId, memberId);


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
        progressData.addProgress((int)ProgressSlider.getValue(), memberId, contentId, moduleId, id);
        //CourseMembers.refresh();
    }

    @FXML
    public void onSliderChanged(){
        int sliderValue = (int) ProgressSlider.getValue();

        System.out.println(sliderValue);
    }
}

package com.omega13.codecademy.controllers.crud;

import com.omega13.codecademy.Home;
import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.database.EnrollmentData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enrollment;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;
import java.util.*;

public class EnrollmentController {
    @FXML
    private ComboBox<CourseMember> CourseMemberDropdown;
    @FXML
    private ComboBox<Course> CourseDropdown;
    @FXML
    private Label Feedback;
    @FXML
    private Button btn_return;

    @FXML
    TableView<Enrollment> Enrollments;

    @FXML
    TableColumn<Enrollment, String> Name;
    @FXML
    TableColumn<Enrollment, String> Course;
    @FXML
    TableColumn<Enrollment, String> RegistrationDate;
    @FXML
    TableColumn<Enrollment, String> Active;

    private CourseMemberData courseMemberData;
    private CourseData courseData;
    private CourseMember selectedMember;
    private Course selectedCourse;
    private EnrollmentData enrollmentData;
    private SceneController sceneController;
    private int selectedId;

    public EnrollmentController(){
        this.courseMemberData = new CourseMemberData();
        this.enrollmentData = new EnrollmentData();
        this.courseData = new CourseData();
        this.sceneController = new SceneController();
    }

    @FXML
    public void initialize(){
        fillTable();
        fillCourseMembers();
        fillCourse();
    }

    private void fillTable(){
        Name.setCellValueFactory(data -> new SimpleStringProperty(this.courseMemberData.getCourseMember(data.getValue().getCourseMemberId()).getName()));
        Course.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getCourseId()).getTitle()));
        RegistrationDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRegistrationDate().toString()));
        Active.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getActiveStatus()));

        Enrollments.getItems().setAll(enrollmentData.getEnrollments());
        Enrollments.getSelectionModel().selectedIndexProperty().addListener((num) -> setEnrollmentData());
    }

    private void setEnrollmentData(){
        if(Enrollments.isPressed()) {
            Enrollment object = Enrollments.getSelectionModel().selectedItemProperty().get();
            selectedId = object.getId();
            CourseMemberDropdown.setValue(courseMemberData.getCourseMember(object.getCourseMemberId()));
            CourseDropdown.setValue(courseData.getCourse(object.getCourseId()));
        }
    }

    @FXML
    private void deleteEnrollment(ActionEvent e){
        enrollmentData.deleteEnrollment(this.selectedId);
        Enrollments.getItems().setAll(enrollmentData.getEnrollments());
    }


    private void fillCourseMembers(){
        ObservableList<CourseMember> members = FXCollections.observableArrayList();
        members.addAll(this.courseMemberData.getCourseMembers());

        CourseMemberDropdown.setItems(members);


        CourseMemberDropdown.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null)
                this.selectedMember = newval;
        });
    }

    private void fillCourse(){
        ObservableList<Course> courses = FXCollections.observableArrayList();
        this.courseData.getCourses();
        courses.addAll(this.courseData.getCourses());

        CourseDropdown.setItems(courses);


        CourseDropdown.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null)
                this.selectedCourse = newval;
        });
    }

    @FXML
    private void enrollCourse(){
        if(selectedMember == null || selectedCourse == null) return;
            Date date = new Date();
            java.sql.Date currentDate = new java.sql.Date(date.getTime());
            enrollmentData.addEnrollment(currentDate, selectedMember.getId(), -1, selectedCourse.getId());
            Enrollments.getItems().setAll(enrollmentData.getEnrollments());
    }

    @FXML
    public void returnHome(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("crud/CRUD-view.fxml", btn_return);
    }
}

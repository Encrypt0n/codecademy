package com.omega13.codecademy.controllers.crud;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.database.EnrollmentData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enrollment;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.*;

/*
    The class EnrollmentController is in connection with enrollment-view.fxml, this class is responsible for the interactions with the user and the CRUD
 */
public class EnrollmentController {
    @FXML
    private ComboBox<CourseMember> CourseMemberDropdown;
    @FXML
    private ComboBox<Course> CourseDropdown;
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

    //Constructor
    public EnrollmentController(){
        this.courseMemberData = new CourseMemberData();
        this.enrollmentData = new EnrollmentData();
        this.courseData = new CourseData();
        this.sceneController = new SceneController();
    }

    //loads after the constructor but before the page is loaded and fills the tables and dropdowns
    @FXML
    public void initialize(){
        fillTable();
        fillCourseMembers();
        fillCourse();
    }

    //Fills the enrollment table with data
    private void fillTable(){
        Name.setCellValueFactory(data -> new SimpleStringProperty(this.courseMemberData.getCourseMember(data.getValue().getCourseMemberId()).getName()));
        Course.setCellValueFactory(data -> new SimpleStringProperty(this.courseData.getCourse(data.getValue().getCourseId()).getTitle()));
        RegistrationDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRegistrationDate().toString()));
        Active.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getActiveStatus()));

        Enrollments.getItems().setAll(enrollmentData.getEnrollments());
        Enrollments.getSelectionModel().selectedIndexProperty().addListener((num) -> setEnrollmentData());
    }

    //If clicked on a enrollment inside the table, fills the selected enrollment and sets the input values to the member information
    private void setEnrollmentData(){
        if(Enrollments.isPressed()) {
            Enrollment object = Enrollments.getSelectionModel().selectedItemProperty().get();
            selectedId = object.getId();
            CourseMemberDropdown.setValue(courseMemberData.getCourseMember(object.getCourseMemberId()));
            CourseDropdown.setValue(courseData.getCourse(object.getCourseId()));
        }
    }

    //Delete the enrollment from the database
    @FXML
    private void deleteEnrollment(ActionEvent e){
        enrollmentData.deleteEnrollment(this.selectedId);
        Enrollments.getItems().setAll(enrollmentData.getEnrollments());
    }


    //Fills the dropdown with coursemembers
    private void fillCourseMembers(){
        ObservableList<CourseMember> members = FXCollections.observableArrayList();
        members.addAll(this.courseMemberData.getCourseMembers());

        CourseMemberDropdown.setItems(members);


        CourseMemberDropdown.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null)
                this.selectedMember = newval;
        });
    }

    //Fills the dropdown with courses
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

    //Adds the course to the database
    @FXML
    private void enrollCourse(){
        if(selectedMember == null || selectedCourse == null) return;
            Date date = new Date();
            java.sql.Date currentDate = new java.sql.Date(date.getTime());
            enrollmentData.addEnrollment(currentDate, selectedMember.getId(), -1, selectedCourse.getId());
            Enrollments.getItems().setAll(enrollmentData.getEnrollments());
    }

    //Returns the use to the previous page
    @FXML
    public void returnHome(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("crud/CRUD-view.fxml", btn_return);
    }
}

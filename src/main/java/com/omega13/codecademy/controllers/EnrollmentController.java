package com.omega13.codecademy.controllers;

import com.omega13.codecademy.Home;
import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.database.EnrollmentData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enrollment;
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



    private CourseMemberData courseMemberData;
    private CourseData courseData;
    private CourseMember selectedMember;
    private Course selectedCourse;
    private EnrollmentData enrollmentData;

    public EnrollmentController(){
        this.courseMemberData = new CourseMemberData();
        this.enrollmentData = new EnrollmentData();
        this.courseData = new CourseData();
    }

    @FXML
    public void initialize(){
        fillCourseMembers();
        fillCourse();
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
        if(checkIfEnrolled()){
            Date date = new Date();
            java.sql.Date currentDate = new java.sql.Date(date.getTime());
            //enrollmentData.addEnrollment(currentDate, selectedMember.getId(), -1, selectedCourse.getId());
            Feedback.setText("Succesvol ingeschreven!");
        }
    }

    private boolean checkIfEnrolled(){
        List<Enrollment> enrollments = enrollmentData.getEnrollments();
        for(Enrollment e : enrollments){
            if(e.getCourseMemberId() == selectedMember.getId() && e.getCourseId() == selectedCourse.getId()){
                Feedback.setText("Cursist is al ingeschreven!");
                return false;
            }
        }
        return true;
    }

    @FXML
    public void returnHome(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Home.class.getResource("home-view.fxml"));
        Stage stage = (Stage) btn_return.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}

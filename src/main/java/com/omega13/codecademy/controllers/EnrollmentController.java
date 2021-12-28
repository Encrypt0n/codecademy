package com.omega13.codecademy.controllers;

import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.database.EnrollmentData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EnrollmentController {
    @FXML
    private MenuButton CourseMemberDropdown;
    @FXML
    private MenuButton CourseDropdown;

    private CourseMemberData courseMemberData;
    private CourseMember selectedMember;
    private EnrollmentData enrollmentData;

    public EnrollmentController(){
        this.courseMemberData = new CourseMemberData();
        this.enrollmentData = new EnrollmentData();
    }

    @FXML
    public void initialize(){
        fillCourseMembers();
        fillCourse();
    }

    private void fillCourseMembers(){
        ArrayList<MenuItem> items = new ArrayList<>();
        for (CourseMember courseMember : this.courseMemberData.getCourseMembers()) {
            MenuItem newItem = new MenuItem(courseMember.getName());
            items.add(newItem);

            newItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    CourseMemberDropdown.setText(courseMember.getName());
                    selectedMember = courseMember;
                }
            });
        }
        this.CourseMemberDropdown.getItems().setAll(items);
    }

    private void fillCourse(){

    }

    @FXML
    private void enrollCourse(){
        if(selectedMember == null) return;
        Date date = new Date();
        java.sql.Date currentDate = new java.sql.Date(date.getTime());
        enrollmentData.addEnrollment(currentDate, selectedMember.getId(), -1, 1);

    }
}

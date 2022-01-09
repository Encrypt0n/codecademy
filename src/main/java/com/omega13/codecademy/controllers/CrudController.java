package com.omega13.codecademy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class CrudController {
    @FXML
    Button btn_user;
    @FXML
    Button btn_enrollment;
    @FXML
    Button btn_course;
    @FXML
    Button btn_return;

    SceneController sceneController;

    public CrudController(){
        sceneController = new SceneController();
    }

    @FXML
    private void onUserButtonClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("coursemember-view.fxml", btn_user);
    }

    @FXML
    private void onEnrollmentClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("enrollment-view.fxml", btn_enrollment);
    }

    @FXML
    private void onCourseClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("course-view.fxml", btn_course);
    }

    @FXML
    private void onReturnClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("home-view.fxml", btn_return);
    }
}

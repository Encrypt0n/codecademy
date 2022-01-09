package com.omega13.codecademy.controllers;

import com.omega13.codecademy.Home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    Button btn_user;

    @FXML
    Button btn_enrollment;

    @FXML
    Button btn_course;

    SceneController sceneController;

    public HomeController(){
        this.sceneController = new SceneController();
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
}

package com.omega13.codecademy.controllers.crud;

import com.omega13.codecademy.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


/*
    The class CrudController is in connection with CRUD-view.fxml, this class is responsible for the interactions with the user and the CRUD
 */
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

    //Constructor
    public CrudController(){
        sceneController = new SceneController();
    }

    //Sends user to coursemember page
    @FXML
    private void onUserButtonClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("crud/coursemember-view.fxml", btn_user);
    }

    //Sends user to enrollment page
    @FXML
    private void onEnrollmentClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("crud/enrollment-view.fxml", btn_enrollment);
    }

    //Sends user course page
    @FXML
    private void onCourseClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("crud/course-view.fxml", btn_course);
    }

    //Sends user to previous page
    @FXML
    private void onReturnClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("home-view.fxml", btn_return);
    }
}

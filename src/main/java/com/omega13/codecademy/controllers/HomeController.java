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
    private void onUserButtonClick(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Home.class.getResource("coursemember-view.fxml"));
        Stage stage = (Stage) btn_user.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void onEnrollmentClick(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Home.class.getResource("enrollment-view.fxml"));
        Stage stage = (Stage) btn_user.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}

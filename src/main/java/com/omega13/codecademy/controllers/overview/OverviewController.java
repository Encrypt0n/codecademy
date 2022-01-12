package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class OverviewController {
    SceneController sceneController;

    @FXML
    private Button btn_certificateGender;
    @FXML
    private Button btn_achievedCertificate;

    public OverviewController(){
        this.sceneController = new SceneController();
    }

    @FXML
    public void onCertificateGenderClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("overview/certificategender-view.fxml", btn_certificateGender);
    }

    @FXML
    public void onAchievedCertificateClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("overview/achievedcertificate-view.fxml", btn_achievedCertificate);
    }
}

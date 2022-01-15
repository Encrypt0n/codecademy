package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class OverviewController {
    SceneController sceneController;

    @FXML
    Button btn_certificateGender;
    @FXML
    Button btn_achievedCertificate;
    @FXML
    Button btn_return;
    @FXML
    Button btn_courseCertificate;
    @FXML
    Button btn_topWebcasts;
    @FXML
    Button btn_avgProgress;
    @FXML
    Button btn_courseProgress;

    public OverviewController(){
        this.sceneController = new SceneController();
    }

    @FXML
    private void onCertificateGenderClick(ActionEvent e) throws IOException {
        this.sceneController.sceneSwitcher("overview/certificategender-view.fxml", btn_certificateGender);
    }

    @FXML
    private void onAchievedCertificateClick(ActionEvent e) throws IOException {
        this.sceneController.sceneSwitcher("overview/achievedcertificate-view.fxml", btn_achievedCertificate);
    }

    @FXML
    private void onCourseCertificateClick() throws IOException {
        this.sceneController.sceneSwitcher("overview/coursecertificate-view.fxml", btn_courseCertificate);
    }

    @FXML
    private void onAvgProgressClick() throws IOException {
        this.sceneController.sceneSwitcher("overview/avgprogress-view.fxml", btn_avgProgress);
    }

    @FXML
    private void onCourseProgressClick() throws IOException {
        this.sceneController.sceneSwitcher("overview/courseprogress-view.fxml", btn_courseCertificate);
    }

    @FXML
    private void onTopWebcastsClick() throws IOException {
        this.sceneController.sceneSwitcher("overview/topwebcasts-view.fxml", btn_topWebcasts);
    }

    @FXML
    private void onReturnClick() throws IOException{
        this.sceneController.sceneSwitcher("home-view.fxml", btn_return);
    }

}

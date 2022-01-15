package com.omega13.codecademy.controllers.progress;

import com.omega13.codecademy.controllers.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ProgressCourseController {
    @FXML
    Button btn_return;

    @FXML
    Button btn_modules;

    @FXML
    Button btn_webcasts;

    SceneController sceneController;

    public ProgressCourseController() {
        this.sceneController = new SceneController();
    }

    @FXML
    private void onReturnClick() throws IOException {
        sceneController.sceneSwitcher("home-view.fxml", btn_return);
    }

    @FXML
    private void onModulesClick() throws IOException {
        sceneController.sceneSwitcher("progress/progresscourse-view.fxml", btn_modules);
    }

    @FXML
    private void onWebcastsClick() throws IOException {
        sceneController.sceneSwitcher("progress/progresswebcast-view.fxml", btn_webcasts);
    }
}

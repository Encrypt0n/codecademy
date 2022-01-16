package com.omega13.codecademy.controllers.progress;

import com.omega13.codecademy.controllers.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/*
    The class ProgressHomeController is in connection with progress-view.fxml, this class is responsible for the interactions with the user
 */
public class ProgressHomeController {
    @FXML
    Button btn_return;

    @FXML
    Button btn_modules;

    @FXML
    Button btn_webcasts;

    SceneController sceneController;

    //Constructor
    public ProgressHomeController() {
        this.sceneController = new SceneController();
    }

    //Returns the user to the previous page
    @FXML
    private void onReturnClick() throws IOException {
        sceneController.sceneSwitcher("home-view.fxml", btn_return);
    }

    //Sends the user to the progresscourse page
    @FXML
    private void onModulesClick() throws IOException {
        sceneController.sceneSwitcher("progress/progresscourse-view.fxml", btn_modules);
    }

    //Sends the user to the progresswebcast page
    @FXML
    private void onWebcastsClick() throws IOException {
        sceneController.sceneSwitcher("progress/progresswebcast-view.fxml", btn_webcasts);
    }
}

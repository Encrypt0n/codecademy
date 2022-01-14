package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class TopWebcastsController {
    @FXML
    Button btn_return;

    SceneController sceneController;

    public TopWebcastsController(){
        this.sceneController = new SceneController();
    }

    @FXML
    private void onReturnClick() throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }
}

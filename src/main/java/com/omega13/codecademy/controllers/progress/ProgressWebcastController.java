package com.omega13.codecademy.controllers.progress;

import com.omega13.codecademy.controllers.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.io.IOException;

public class ProgressWebcastController {
    @FXML
    Button btn_return;

    @FXML
    Slider ProgressSlider;

    SceneController sceneController;

    public ProgressWebcastController(){
        this.sceneController = new SceneController();
    }

    @FXML
    private void onSliderChanged(){
        int sliderValue = (int) ProgressSlider.getValue();
        System.out.println(sliderValue);
    }

    @FXML
    private void onReturnClick() throws IOException {
        this.sceneController.sceneSwitcher("progress/progress-view.fxml", btn_return);
    }
}

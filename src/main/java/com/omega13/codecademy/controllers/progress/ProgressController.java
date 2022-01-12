package com.omega13.codecademy.controllers.progress;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressController implements Initializable {
    @FXML
    Slider ProgressSlider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public void onSliderChanged(){
        int sliderValue = (int) ProgressSlider.getValue();
        System.out.println(sliderValue);
    }
}

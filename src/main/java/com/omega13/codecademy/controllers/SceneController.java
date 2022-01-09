package com.omega13.codecademy.controllers;

import com.omega13.codecademy.Home;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    public void sceneSwitcher(String fxml, Button btn) throws IOException {
        FXMLLoader loader = new FXMLLoader(Home.class.getResource(fxml));
        Stage stage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}

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
    Button btn_crud;

    @FXML
    Button btn_overview;


    SceneController sceneController;

    public HomeController(){
        this.sceneController = new SceneController();
    }

    @FXML
    private void onCRUDClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("crud/crud-view.fxml", btn_crud);
    }

    @FXML
    private void onOverviewClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_overview);
    }


}

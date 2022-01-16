package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.WebcastData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
    The class TopWebcastsController is in connection with topwebcasts-view.fxml, this class is responsible for the interactions with the user
 */
public class TopWebcastsController implements Initializable {
    @FXML
    Button btn_return;

    @FXML
    Label positionOne;
    @FXML
    Label positionTwo;
    @FXML
    Label positionThree;

    SceneController sceneController;

    WebcastData webcastData = new WebcastData();

    //Constructor
    public TopWebcastsController(){
        this.sceneController = new SceneController();
    }

    //loads after the constructor but before the page is loaded and gets the best webcasts
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getTop3Webcasts();

    }

    //Sets the text of the top webcasts
    public void getTop3Webcasts() {
        positionOne.setText("1. " + webcastData.TopWebcasts().get(0).getTitle());
        positionTwo.setText("2. " + webcastData.TopWebcasts().get(1).getTitle());
        positionThree.setText("3. " + webcastData.TopWebcasts().get(2).getTitle());
    }


    //Returns user to the previous page
    @FXML
    private void onReturnClick() throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }
}

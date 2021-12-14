package com.omega13.codecademy;

import com.omega13.codecademy.domain.Cursist;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    TableColumn<Cursist, String> tcName;

    @FXML
    TableView<Cursist> tvCursist;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        tvCursist.getItems().setAll(cursists());
    }

    private List<Cursist> cursists(){
        ArrayList<Cursist> list = new ArrayList<>();
        list.add(new Cursist("Bert"));
        return list;
    }

    @FXML
    public void addUser(ActionEvent e){
        tvCursist.getItems().add(new Cursist("Bert"));
    }
}

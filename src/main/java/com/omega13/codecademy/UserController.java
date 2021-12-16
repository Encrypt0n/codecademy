package com.omega13.codecademy;


import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.domain.CourseMember;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    TableColumn<CourseMember, String> Name;
    @FXML
    TableColumn<CourseMember, String> Email;
    @FXML
    TableColumn<CourseMember, String> Birthday;
    @FXML
    TableColumn<CourseMember, String> Gender;
    @FXML
    TableColumn<CourseMember, String> Address;
    @FXML
    TableColumn<CourseMember, String> City;
    @FXML
    TableColumn<CourseMember, String> Country;

    @FXML
    TableView<CourseMember> CourseMember;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        Email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        Birthday.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBirthday().toString()));
        Gender.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGender()));
        Address.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));
        City.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCity()));
        Country.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCountry()));
        CourseMemberData CourseMemberdata = new CourseMemberData();
        CourseMember.getItems().setAll(CourseMemberdata.getUsers());


        CourseMember.setRowFactory(tv -> {
            TableRow<CourseMember> row = new TableRow<>();
            TableCell<CourseMember> cell = new TableCell();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    CourseMember clickedRow = row.getItem();
                    System.out.println(clickedRow.getName());
                }
            });
            return row ;
        });
    }

  /*  private List<Cursist> cursists(){
        ArrayList<Cursist> list = new ArrayList<>();
        list.add(new Cursist("Bert"));
        return list;
    }*/

    @FXML
    public void addUser(ActionEvent e){
      //  tvCursist.getItems().add(new Cursist("Bert"));

    }
}

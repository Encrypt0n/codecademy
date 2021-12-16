package com.omega13.codecademy.controllers;


import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.domain.CourseMember;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    //TABLE DATA
    @FXML
    TableView<CourseMember> CourseMembers;

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

    //NEW TABLE DATA
    @FXML
    TextField newName;
    @FXML
    TextField newAddress;
    @FXML
    TextField newCountry;
    @FXML
    TextField newEmail;
    @FXML
    TextField newCity;
    @FXML
    DatePicker newBirthday;

    public CourseMemberData memberData = new CourseMemberData();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        Email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        Birthday.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBirthday().toString()));
        Gender.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGender()));
        Address.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));
        City.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCity()));
        Country.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCountry()));

        CourseMembers.getItems().setAll(memberData.getUsers());



        CourseMembers.getSelectionModel().selectedIndexProperty().addListener((num) -> printcell());

        /*CourseMember.setRowFactory(tv -> {
            TableRow<CourseMember> row = new TableRow<>();
            TableCell<CourseMember, String> cell = new TableCell<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    CourseMember clickedRow = row.getItem();
                    System.out.println(clickedRow);
                }
            });
            cell.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    CourseMember clickedRow = cell;
                    System.out.println(clickedRow);
                }
            });
            return row ;
        });*/
    }

    int id;

    @FXML
    public void printcell() {
        CourseMember object =  CourseMembers.getSelectionModel().selectedItemProperty().get();
        //int index = CourseMember.getSelectionModel().selectedIndexProperty().get();

        id = object.getId();

        newName.setText(object.getName());
        newEmail.setText(object.getEmail());
        newBirthday.setValue(object.getBirthday().toLocalDate());
        newAddress.setText(object.getAddress());
        newCity.setText(object.getCity());
        newCountry.setText(object.getCountry());

        System.out.println(object.getName());
        System.out.println(object.getId());
    }

    @FXML
    public void addUser(ActionEvent e){
        //System.out.println(CourseMember.getSelectionModel().getSelectedItem().getName());
        memberData.addCourseMember(newName.getText(), newEmail.getText(), Date.valueOf(newBirthday.getValue()), true, newAddress.getText(), newCity.getText(), newCountry.getText());
        CourseMembers.refresh();
    }

    @FXML
    public void deleteUser(ActionEvent e){
        //System.out.println(CourseMember.getSelectionModel().getSelectedItem().getName());
        memberData.deleteCourseMember(id);
        CourseMembers.refresh();
    }

    @FXML
    public void updateUser(ActionEvent e){
        //System.out.println(CourseMember.getSelectionModel().getSelectedItem().getName());
        memberData.updateCourseMember(id, newName.getText(), newEmail.getText(), java.sql.Date.valueOf(newBirthday.getValue()), true, newAddress.getText(), newCity.getText(), newCountry.getText());
        CourseMembers.refresh();
    }




}

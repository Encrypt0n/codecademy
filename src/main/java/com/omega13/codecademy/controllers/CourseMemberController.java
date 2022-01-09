package com.omega13.codecademy.controllers;


import com.omega13.codecademy.Home;
import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.domain.CourseMember;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CourseMemberController implements Initializable {
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
    @FXML
    RadioButton Male;
    @FXML
    RadioButton Female;
    @FXML
    ToggleGroup genderGroup = new ToggleGroup();

    @FXML
    Button btn_return;

    boolean gender;
    int id;

    private CourseMemberData memberData;
    private SceneController sceneController;

    public CourseMemberController(){
        this.memberData = new CourseMemberData();
        this.sceneController = new SceneController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        Email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        Birthday.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBirthday().toString()));
        Gender.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGender()));
        Address.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));
        City.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCity()));
        Country.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCountry()));

        CourseMembers.getItems().setAll(memberData.getCourseMembers());

        CourseMembers.getSelectionModel().selectedIndexProperty().addListener((num) -> setMemberData());

        Male.setToggleGroup(genderGroup);
        Female.setToggleGroup(genderGroup);
        genderGroup.selectedToggleProperty().addListener(
                (observable, oldToggle, newToggle) -> {
                    if (newToggle == Male) {
                        gender = true;
                    } else if (newToggle == Female) {
                        gender = false;

                    }
                }
        );

    }


    @FXML
    public void setMemberData() {
        if(CourseMembers.isPressed()) {
            CourseMember object = CourseMembers.getSelectionModel().selectedItemProperty().get();
            id = object.getId();

            newName.setText(object.getName());
            newEmail.setText(object.getEmail());
            newBirthday.setValue(object.getBirthday().toLocalDate());
            newAddress.setText(object.getAddress());
            newCity.setText(object.getCity());
            newCountry.setText(object.getCountry());

        }
    }

    @FXML
    public void addCourseMember(ActionEvent e){
        memberData.addCourseMember(newName.getText(), newEmail.getText(), Date.valueOf(newBirthday.getValue()), true, newAddress.getText(), newCity.getText(), newCountry.getText());
        CourseMembers.refresh();
    }

    @FXML
    public void deleteCourseMember(ActionEvent e){
        memberData.deleteCourseMember(id);
        CourseMembers.getItems().setAll(memberData.getCourseMembers());
    }

    public void updateCourseMember(ActionEvent e){
        //System.out.println(CourseMember.getSelectionModel().getSelectedItem().getName());
        memberData.updateCourseMember(id, newName.getText(), newEmail.getText(), java.sql.Date.valueOf(newBirthday.getValue()), true, newAddress.getText(), newCity.getText(), newCountry.getText());
        CourseMembers.refresh();
    }

    @FXML
    public void returnHome(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("CRUD-view.fxml", btn_return);
    }



}

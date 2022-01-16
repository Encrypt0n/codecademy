package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.CertificateData;
import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.domain.CourseMember;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/*
    The class AchievedCertificateController is in connection with achievedcertificate-view.fxml, this class is responsible for the interactions with the user
 */
public class AchievedCertificateController implements Initializable {
    @FXML
    TableView<CourseMember> MemberTable;
    @FXML
    TableColumn<CourseMember, String> MemberColumn;

    @FXML
    TableView<String> CertificateTable;
    @FXML
    TableColumn<String, String> CertificateColumn;

    @FXML
    Button btn_return;

    private CourseMemberData courseMemberData;
    private CertificateData certificateData;
    private SceneController sceneController;

    //Constructor
    public AchievedCertificateController(){
        this.courseMemberData = new CourseMemberData();
        this.certificateData = new CertificateData();
        this.sceneController = new SceneController();
    }

    //loads after the constructor but before the page is loaded and fills the table
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillMemberList();

    }

    //Fills the table with course members
    private void fillMemberList(){
        MemberColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseMemberData.getCourseMember(data.getValue().getId()).getName()));
        MemberTable.getItems().setAll(courseMemberData.getCourseMembers());
        MemberTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getCertificates());
    }

    //Gets the selected certificate when clicked on one
    private void getCertificates() {
        if(MemberTable.isPressed()){
            int memberId = MemberTable.getSelectionModel().getSelectedItem().getId();
            this.CertificateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
            this.CertificateTable.getItems().setAll(this.certificateData.getCertificates(memberId));
        }

    }

    //Returns the use to the previous page
    @FXML
    private void onReturnClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }


}

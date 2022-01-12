package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.CertificateData;
import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.database.EnrollmentData;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enrollment;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    public AchievedCertificateController(){
        this.courseMemberData = new CourseMemberData();
        this.certificateData = new CertificateData();
        this.sceneController = new SceneController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillMemberList();

    }

    private void fillMemberList(){
        MemberColumn.setCellValueFactory(data -> new SimpleStringProperty(this.courseMemberData.getCourseMember(data.getValue().getId()).getName()));
        MemberTable.getItems().setAll(courseMemberData.getCourseMembers());
        MemberTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getCertificates());
    }

    private void getCertificates() {
        if(MemberTable.isPressed()){
            int memberId = MemberTable.getSelectionModel().getSelectedItem().getId();
            this.CertificateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
            this.CertificateTable.getItems().setAll(this.certificateData.getCertificates(memberId));
        }

    }

    @FXML
    private void onReturnClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }


}

package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.CourseMemberData;
import com.omega13.codecademy.database.EnrollmentData;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enrollment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

/*
    The class CertificateGenderController is in connection with certificategender-view.fxml, this class is responsible for the interactions with the user
 */

public class CertificateGenderController {
    @FXML
    private Label percentageMsg;

    @FXML
    private Button btn_return;

    private EnrollmentData enrollmentData;
    private CourseMemberData courseMemberData;
    private SceneController sceneController;

    //Constructor
    public CertificateGenderController(){
        this.enrollmentData = new EnrollmentData();
        this.courseMemberData = new CourseMemberData();
        this.sceneController = new SceneController();
    }

    //When clicked on the "man" button this function is called
    @FXML
    private void onMaleClick(ActionEvent e){
       percentageMsg.setText(calculatePercentage("man") + "% van de mannen heeft een certificaat behaald");
    }

    //When clicked on the "vrouw" button this function is called
    @FXML
    private void onFemaleClick(ActionEvent e){
        percentageMsg.setText(calculatePercentage("vrouw") + "% van de vrouwen heeft een certificaat behaald");
    }

    //Method that calculates the percentage of man or woman who got their certificate
    private String calculatePercentage(String gender){
        List<CourseMember> members = courseMemberData.getCourseMembers().stream().filter(courseMember -> courseMember.getGender().equalsIgnoreCase(gender)).toList();
        List<Enrollment> memberCertificate = enrollmentData.getEnrollments().stream().filter(enrollment -> courseMemberData.getCourseMember(enrollment.getCourseMemberId()).getGender().equalsIgnoreCase(gender) && enrollment.getCertificateId() != 0).toList();
        return new DecimalFormat("##.##").format(((double)memberCertificate.size() / (double)members.size()) * 100);
    }

    //Returns the user to the previous page
    @FXML
    private void onBtnReturnClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }

}

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

public class CertificateGenderController {
    @FXML
    private Label percentageMsg;

    @FXML
    private Button btn_return;

    private EnrollmentData enrollmentData;
    private CourseMemberData courseMemberData;
    private SceneController sceneController;

    public CertificateGenderController(){
        this.enrollmentData = new EnrollmentData();
        this.courseMemberData = new CourseMemberData();
        this.sceneController = new SceneController();
    }

    @FXML
    private void onMaleClick(ActionEvent e){
       percentageMsg.setText(calculatePercentage("man") + "% van de mannen heeft een certificaat behaald");
    }

    @FXML
    private void onFemaleClick(ActionEvent e){
        percentageMsg.setText(calculatePercentage("vrouw") + "% van de vrouwen heeft een certificaat behaald");
    }

    private String calculatePercentage(String gender){
        List<CourseMember> members = courseMemberData.getCourseMembers().stream().filter(courseMember -> courseMember.getGender().equalsIgnoreCase(gender)).toList();
        List<Enrollment> memberCertificate = enrollmentData.getEnrollments().stream().filter(enrollment -> courseMemberData.getCourseMember(enrollment.getCourseMemberId()).getGender().equalsIgnoreCase(gender) && enrollment.getCertificateId() != 0).toList();
        return new DecimalFormat("##.##").format(((double)memberCertificate.size() / (double)members.size()) * 100);
    }

    @FXML
    private void onBtnReturnClick(ActionEvent e) throws IOException {
        sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }

}

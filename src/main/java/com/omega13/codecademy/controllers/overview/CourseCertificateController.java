package com.omega13.codecademy.controllers.overview;

import com.omega13.codecademy.controllers.SceneController;
import com.omega13.codecademy.database.CertificateData;
import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.domain.Course;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseCertificateController implements Initializable {
    @FXML
    TableView<Course> courseTable;
    @FXML
    TableColumn<Course, String> course;
    @FXML
    Label certificateAmount;
    @FXML
    Button btn_return;


    private SceneController sceneController;
    private CourseData courseData;
    private CertificateData certificateData;

    public CourseCertificateController(){
        this.sceneController = new SceneController();
        this.courseData = new CourseData();
        this.certificateData = new CertificateData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCourseTable();

    }

    private void fillCourseTable(){
        course.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        courseTable.getItems().setAll(this.courseData.getCourses());
        courseTable.getSelectionModel().selectedIndexProperty().addListener((num) -> getCertificates());
    }

    private void getCertificates() {
        if (courseTable.isPressed()) {
            int courseId = courseTable.getSelectionModel().getSelectedItem().getId();
            certificateAmount.setText("Aantal behaalde certificaten: " + this.certificateData.getAmountCertificate(courseId));

        }
    }

    @FXML
    private void onReturnClick() throws IOException {
        this.sceneController.sceneSwitcher("overview/overview-view.fxml", btn_return);
    }

}

package com.omega13.codecademy.controllers.crud;

import com.omega13.codecademy.database.CourseData;
import com.omega13.codecademy.database.ModuleData;
import com.omega13.codecademy.domain.Course;
import com.omega13.codecademy.domain.CourseMember;
import com.omega13.codecademy.domain.Enums.Level;
import com.omega13.codecademy.domain.Module;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    @FXML
    VBox ModuleVBox;

    @FXML
    private ComboBox<Module> ModuleDropdown;

    private Module selectedModule;

    public CourseData courseData = new CourseData();
    public ModuleData moduleData = new ModuleData();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillModules();
    }

    @FXML
    public void addModuleInput(ActionEvent e){
        MenuButton newDropdown = new MenuButton();
        newDropdown.setText("Kies module");
        newDropdown.setPrefWidth(182);
        ModuleVBox.getChildren().add(newDropdown);
        setDropdownValue(newDropdown);
    }

    public void setDropdownValue(MenuButton dropdown){
        for(Module module : moduleData.getModules()){
            MenuItem newItem = new MenuItem();
            newItem.setText(module.getTitle());
            dropdown.getItems().add(newItem);
            newItem.setOnAction(actionEvent -> {
                dropdown.setText(module.getTitle());
            });
        }
    }

    public void fillModules() {
        ObservableList<Module> modules = FXCollections.observableArrayList();
        this.moduleData.getModules();
        modules.addAll(this.moduleData.getModules());

        ModuleDropdown.setItems(modules);


        ModuleDropdown.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null)
                this.selectedModule = newval;
        });
    }
}

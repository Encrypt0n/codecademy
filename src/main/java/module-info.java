module com.omega13.codecademy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.omega13.codecademy to javafx.fxml;
    exports com.omega13.codecademy;
    exports com.omega13.codecademy.controllers;
    opens com.omega13.codecademy.controllers to javafx.fxml;
}
module com.omega13.codecademy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.omega13.codecademy to javafx.fxml;
    exports com.omega13.codecademy;
}
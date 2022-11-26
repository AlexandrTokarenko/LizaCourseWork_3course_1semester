module com.example.coursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.coursework to javafx.fxml;
    exports com.example.coursework;
    exports com.example.coursework.controllers;
    opens com.example.coursework.controllers to javafx.fxml;
}
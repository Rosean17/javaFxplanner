module com.example.studyplanner {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.studyplanner to javafx.fxml;
    opens com.example.studyplanner.models to javafx.base;
    opens com.example.studyplanner.utils to javafx.base;

    exports com.example.studyplanner;
    exports com.example.studyplanner.models;
    exports com.example.studyplanner.utils;
    exports com.example.studyplanner.controller;
    opens com.example.studyplanner.controller to javafx.fxml;

}
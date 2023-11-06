module com.example.testguiapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testguiapp to javafx.fxml;
    exports com.example.testguiapp;
}
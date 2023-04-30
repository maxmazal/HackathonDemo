module com.example.hackathondemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hackathondemo to javafx.fxml;
    exports com.example.hackathondemo;
}
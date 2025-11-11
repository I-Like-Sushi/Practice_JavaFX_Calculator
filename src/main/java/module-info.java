module org.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.calculator to javafx.fxml;
    exports org.example.calculator;
    exports org.example.calculator.controller;
    opens org.example.calculator.controller to javafx.fxml;
}
package org.example.calculator.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class Controller {

    @FXML
    public AnchorPane calculator;
    @FXML
    private Button zero;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button decimal;
    @FXML
    private Button enter;
    @FXML
    private Button add;
    @FXML
    private Button subtract;
    @FXML
    private Button divide;
    @FXML
    private Button multiply;

    @FXML
    Label arithmeticOperator;

    Double number;
    Double secondNumber;
    Double finalNumber;

    @FXML
    private TextField screen;

    @FXML
    private TextField previousNumber;

    @FXML
    public void clearScreen() {
        screen.clear();
        arithmeticOperator.setText("");
        previousNumber.clear();
    }

    @FXML
    private void initialize() {

        List<Button> digits = List.of(zero, one, two, three, four, five, six, seven, eight, nine, enter, add, subtract, divide, multiply, decimal);

        calculator.setFocusTraversable(true);
        digits.forEach(button -> {
            button.setFocusTraversable(false);
            button.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                e.consume();
                button.fire();
            });
            button.setOnAction(e -> screen.appendText(button.getText()));
        });

        add.setOnAction(e -> handleAdd());
        subtract.setOnAction(e -> handleSubtract());
        multiply.setOnAction(e -> handleMultiply());
        divide.setOnAction(e -> handleDivide());

        enter.setFocusTraversable(false);
        enter.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { e.consume(); enter.fire(); });
        enter.setOnAction(e -> handleEnter());


        Platform.runLater(() -> {
            Scene scene = calculator.getScene();
            if (scene == null) return;

            // typed characters (digits and printable operators)
            scene.addEventFilter(KeyEvent.KEY_TYPED, e -> {
                String ch = e.getCharacter();
                if (ch != null && ch.matches("[0-9]")) {
                    screen.appendText(ch);
                    e.consume();
                } else if ("+".equals(ch)) {
                    handleAdd();
                    e.consume();
                } else if ("-".equals(ch)) {
                    handleSubtract();
                    e.consume();
                } else if ("*".equals(ch)) {
                    handleMultiply();
                    e.consume();
                } else if ("/".equals(ch)) {
                    handleDivide();
                    e.consume();
                } else if (".".equals(ch))
                    if (screen.getText().contains(".")) {
                        e.consume();
                    } else {
                    decimal.fire();
                    e.consume();
                } else {
                    e.consume();
                }
            });

            scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.BACK_SPACE) {
                    screen.deletePreviousChar();
                    e.consume();
                } else if (e.getCode() == KeyCode.ENTER) {
                    enter.fire();
                    e.consume();
                } else if (e.getCode() == KeyCode.ADD || e.getCode() == KeyCode.PLUS) {
                    add.fire();
                    e.consume();
                } else if (e.getCode() == KeyCode.SUBTRACT || e.getCode() == KeyCode.MINUS) {
                    subtract.fire();
                    e.consume();
                } else if (e.getCode() == KeyCode.DIVIDE) {
                    divide.fire();
                    e.consume();
                } else if (e.getCode() == KeyCode.MULTIPLY) {
                    multiply.fire();
                    e.consume();
                }
            });
        });
    }

    @FXML
    public void handleAdd() {
        if (screen.getText().isBlank()) return;
        number = Double.parseDouble(screen.getText());
        arithmeticOperator.setText("+");
        previousNumber.setText(screen.getText());
        screen.clear();
    }

    @FXML
    public void handleSubtract() {
        if (screen.getText().isBlank()) return;
        number = Double.parseDouble(screen.getText());
        arithmeticOperator.setText("-");
        previousNumber.setText(screen.getText());
        screen.clear();
    }

    @FXML
    public void handleMultiply() {
        if (screen.getText().isBlank()) return;
        number = Double.parseDouble(screen.getText());
        arithmeticOperator.setText("x");
        previousNumber.setText(screen.getText());
        screen.clear();
    }

    @FXML
    public void handleDivide() {
        if (screen.getText().isBlank()) return;
        number = Double.parseDouble(screen.getText());
        arithmeticOperator.setText("/");
        previousNumber.setText(screen.getText());
        screen.clear();
    }

    @FXML
    public void handleEnter() {
        if (arithmeticOperator.getText().equals("+")){
            secondNumber = Double.parseDouble(screen.getText());
            finalNumber = number + secondNumber;
            screen.setText(String.valueOf(finalNumber));
            arithmeticOperator.setText("");
            previousNumber.clear();
        }
        if (arithmeticOperator.getText().equals("-")) {
            secondNumber = Double.parseDouble(screen.getText());
            finalNumber = number - secondNumber;
            screen.setText(String.valueOf(finalNumber));
            arithmeticOperator.setText("");
            previousNumber.clear();
        }
        if (arithmeticOperator.getText().equals("x")) {
            secondNumber = Double.parseDouble(screen.getText());
            finalNumber = number * secondNumber;
            screen.setText(String.valueOf(finalNumber));
            arithmeticOperator.setText("");
            previousNumber.clear();
        }
        if (arithmeticOperator.getText().equals("/")) {
            secondNumber = Double.parseDouble(screen.getText());
            finalNumber = number / secondNumber;
            screen.setText(String.valueOf(finalNumber));
            arithmeticOperator.setText("");
            previousNumber.clear();
        }
    }

}

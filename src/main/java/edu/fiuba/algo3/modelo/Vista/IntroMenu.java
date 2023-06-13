package edu.fiuba.algo3.modelo.Vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IntroMenu {

    private TextField textField;
    private Button okButton;
    private Button iniButton;
    private Label validationLabel;

    public VBox crearUI() {
        textField = new TextField();
        okButton = new Button("OK");
        iniButton = new Button("Iniciar Partida");
        validationLabel = new Label();

        iniButton.setVisible(false); 

        okButton.setOnAction(event -> {
            validarNombre();
        });

        iniButton.setOnAction(event -> {
            start();
        });

        VBox root = new VBox(textField, okButton, validationLabel, iniButton);
        root.setSpacing(10);

        return root;
    }

    private void validarNombre() {
        String inputText = textField.getText();
        if (inputText.length() >= 6) {
            validationLabel.setText("Nombre valido: " + inputText);
            textField.setDisable(true);
            okButton.setDisable(true);
            iniButton.setVisible(true);
        } else {
            validationLabel.setText("Ingrese un nombre de al menos 6 caracteres");
        }
    }

    private void start() {
        validationLabel.setText("Partida iniciada");
    }
}

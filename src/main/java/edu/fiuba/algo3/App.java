package edu.fiuba.algo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();
        Button okButton = new Button("OK");
        Button iniButton = new Button("Iniciar Partida");
        Label validationLabel = new Label();

        iniButton.setVisible(false);

        okButton.setOnAction(event -> {
            String inputText = textField.getText();
            if (inputText.length() >= 6) {
                validationLabel.setText("Nombre valido: " + inputText);
                textField.setDisable(true);
                okButton.setDisable(true);
                iniButton.setVisible(true);
            } else {
                validationLabel.setText("Ingrese un nombre de al menos 6 caracteres");
            }
        });

        iniButton.setOnAction(event -> {
            validationLabel.setText("Partida iniciada");
        });

        VBox root = new VBox(textField, okButton, validationLabel, iniButton);
        root.setSpacing(10);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
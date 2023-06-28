package edu.fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

public class BotonOkEventHandler implements EventHandler<ActionEvent> {

    private TextField textoNombre;
    private Label validationLabel;
    private VBox inicio;
    private Button okButton;
    private Button iniButton;
    private AudioClip sonidoEnter;

    public BotonOkEventHandler(TextField textoNombre, Label label, VBox inicio, Button okButton, Button iniButton, AudioClip sonido) {
        this.textoNombre = textoNombre;
        this.validationLabel = label;
        this.inicio = inicio;
        this.okButton = okButton;
        this.iniButton = iniButton;
        this.sonidoEnter = sonido;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        sonidoEnter.play();
        String inputText = textoNombre.getText();
        Label valLabel = new Label();
        inicio.getChildren().removeAll(valLabel, validationLabel);
        String nombre;
        if (inputText.length() >= 6) {
            nombre = inputText;
            valLabel.setText("Nombre elegido: " + nombre);
            valLabel.setStyle("-fx-text-fill: #a86f13;");
            inicio.getChildren().removeAll(valLabel, textoNombre, okButton);
            inicio.getChildren().addAll(valLabel, iniButton);
        } else {
            validationLabel.setText("Ingrese un nombre de al menos 6 caracteres");
            validationLabel.setStyle("-fx-text-fill: #FF0000;");
            inicio.getChildren().add(validationLabel);
        }
    }
}
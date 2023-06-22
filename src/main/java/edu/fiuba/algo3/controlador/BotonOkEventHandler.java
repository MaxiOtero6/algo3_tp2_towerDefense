package edu.fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BotonOkEventHandler implements EventHandler<ActionEvent> {

    private TextField textoNombre;
    private Label validationLabel;
    private VBox inicio;

    public BotonOkEventHandler(TextField textoNombre, Label label, VBox inicio) {
        this.textoNombre = textoNombre;
        this.validationLabel = label;
        this.inicio = inicio;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String inputText = textoNombre.getText();
        Label valLabel = new Label();
        inicio.getChildren().removeAll(valLabel, validationLabel);
        if (inputText.length() >= 6) {
            String nombre = inputText;
            valLabel.setText("Nombre elegido: " + nombre);
            valLabel.setStyle("-fx-text-fill: #a86f13;");
        } else {
            validationLabel.setText("Ingrese un nombre de al menos 6 caracteres");
            validationLabel.setStyle("-fx-text-fill: #FF0000;");
            inicio.getChildren().add(validationLabel);
        }
    }
}
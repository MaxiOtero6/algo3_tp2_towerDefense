package edu.fiuba.algo3.modelo.Vista;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.Tierra;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Rocoso;
import edu.fiuba.algo3.modelo.Parser.CreadorMapa;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IntroMenu {

    private TextField textField;
    private Button okButton;
    private Button iniButton;
    private Label validationLabel;

    public VBox crearUI(Stage stagePrincipal) {
        textField = new TextField();
        okButton = new Button("OK");
        iniButton = new Button("Iniciar Partida");
        validationLabel = new Label();

        iniButton.setVisible(false); 

        okButton.setOnAction(event -> {
            validarNombre();
        });

        iniButton.setOnAction(event -> {
            IniciarPartida(stagePrincipal);
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

    private void IniciarPartida(Stage stagePrincipal) {
        validationLabel.setText("Partida iniciada");

        List<Parcela> pasarelas = new LinkedList<>();
        List<List<Parcela>> mapa = CreadorMapa.crearMapa(pasarelas);
        
        int SIZE = 15;
        int length = SIZE;
        int width = SIZE;
        
        GridPane root = new GridPane();    

        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){

                // Create a new TextField in each Iteration
                TextField matriz = new TextField();
                matriz.setPrefHeight(50);
                matriz.setPrefWidth(50);
                matriz.setAlignment(Pos.CENTER);
                matriz.setEditable(false);
                if(mapa.get(x).get(y) instanceof Tierra) {matriz.setStyle("-fx-background-color: #699922;");}
                if(mapa.get(x).get(y) instanceof Largada) {matriz.setStyle("-fx-background-color: #599ed4;");}
                if(mapa.get(x).get(y) instanceof Meta) {matriz.setStyle("-fx-background-color: #599ed4;");}
                if(mapa.get(x).get(y) instanceof Rocoso) {matriz.setStyle("-fx-background-color: #38393b;");}
                if(mapa.get(x).get(y) instanceof Pasarela) {matriz.setStyle("-fx-background-color: #d1b680;");}

                // Iterate the Index using the loops
                root.setRowIndex(matriz,y);
                root.setColumnIndex(matriz,x);    
                root.getChildren().add(matriz);
            }
        }

        Scene scene = new Scene(root, 500, 500);    
        stagePrincipal.setTitle("Random Binary Matrix (JavaFX)");
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }
  
}


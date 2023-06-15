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
import javafx.application.Application;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

                StackPane matriz = new StackPane();
                matriz.setPrefHeight(50);
                matriz.setPrefWidth(50);
                matriz.setAlignment(Pos.CENTER);

                if(mapa.get(x).get(y) instanceof Tierra) {matriz.setStyle("-fx-background-color: #699922;");}
                if(mapa.get(x).get(y) instanceof Largada) {matriz.setStyle("-fx-background-color: #599ed4;");}
                if(mapa.get(x).get(y) instanceof Meta) {matriz.setStyle("-fx-background-color: #599ed4;");}
                if(mapa.get(x).get(y) instanceof Rocoso) {matriz.setStyle("-fx-background-color: #38393b;");}
                if(mapa.get(x).get(y) instanceof Pasarela) {matriz.setStyle("-fx-background-color: #d1b680;");}
 
                root.add(matriz, x, y);
                
            }
        }

        //TORRE PLATEADA DE EJEMPLO
        ImageView torrePlateada = new ImageView(getClass().getResource("torrePlateada.png").toExternalForm());
        root.add(torrePlateada,3,2);

        //TORRE BLANCA DE EJEMPLO
        ImageView torreBlanca = new ImageView(getClass().getResource("torreBlanca.png").toExternalForm());
        root.add(torreBlanca,7,7);

        //HORMIGA DE EJEMPLO
        ImageView hormiga = new ImageView(getClass().getResource("hormiga.png").toExternalForm());
        root.add(hormiga,1,1);

        //ARANIA DE EJEMPLO
        ImageView arania = new ImageView(getClass().getResource("arania.png").toExternalForm());
        root.add(arania,6,4);

        //TOPO DE EJEMPLO
        ImageView topo = new ImageView(getClass().getResource("topo.png").toExternalForm());
        root.add(topo,6,1);

        //LECHUZA DE EJEMPLO
        ImageView lechuza = new ImageView(getClass().getResource("lechuza.png").toExternalForm());
        root.add(lechuza,5,10);



        ImageView imagenBotonPlateada = new ImageView(new Image(getClass().getResourceAsStream("torrePlateada.png")));
        Button botonPlateada = new Button();
        botonPlateada.setGraphic(imagenBotonPlateada);
        botonPlateada.setText("Torre Plateada");

        ImageView imagenBotonBlanca = new ImageView(new Image(getClass().getResourceAsStream("torreBlanca.png")));
        Button botonBlanca = new Button();
        botonBlanca.setGraphic(imagenBotonBlanca);
        botonBlanca.setText("Torre Blanca");

        VBox vbox = new VBox();
        vbox.getChildren().addAll(botonPlateada, botonBlanca);
        vbox.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(root);
        borderPane.setMargin(root, new Insets(10));

        borderPane.setRight(vbox);

        Scene scene = new Scene(borderPane);    
        stagePrincipal.setTitle("Mapa");
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }
  
}


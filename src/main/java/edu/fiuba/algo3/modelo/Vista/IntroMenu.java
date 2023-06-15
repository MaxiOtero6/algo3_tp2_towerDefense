package edu.fiuba.algo3.modelo.Vista;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.Tierra;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Rocoso;
import edu.fiuba.algo3.modelo.Parser.CreadorMapa;

import edu.fiuba.algo3.modelo.Defensas.Torres.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

        // //TORRE PLATEADA DE EJEMPLO
        // ImageView torrePlateada = new ImageView(getClass().getResource("torrePlateada.png").toExternalForm());
        // root.add(torrePlateada,3,2);

        // //TORRE BLANCA DE EJEMPLO
        // ImageView torreBlanca = new ImageView(getClass().getResource("torreBlanca.png").toExternalForm());
        // root.add(torreBlanca,7,7);

        // //HORMIGA DE EJEMPLO
        // ImageView hormiga = new ImageView(getClass().getResource("hormiga.png").toExternalForm());
        // root.add(hormiga,1,1);

        // //ARANIA DE EJEMPLO
        // ImageView arania = new ImageView(getClass().getResource("arania.png").toExternalForm());
        // root.add(arania,6,4);

        // //TOPO DE EJEMPLO
        // ImageView topo = new ImageView(getClass().getResource("topo.png").toExternalForm());
        // root.add(topo,6,1);

        // //LECHUZA DE EJEMPLO
        // ImageView lechuza = new ImageView(getClass().getResource("lechuza.png").toExternalForm());
        // root.add(lechuza,5,10);


        validationLabel.setText("Partida iniciada");

        List<Parcela> pasarelas = new LinkedList<>();
        List<List<Parcela>> mapa = CreadorMapa.crearMapa(pasarelas);

        ContenedorTorre torreAux = new ContenedorTorre();
        
        int SIZE = 15;
        int length = SIZE;
        int width = SIZE;
        
        GridPane root = new GridPane();

        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){

                int coordenadaX = x;
                int coordenaday = y;

                Button casillaMapa = new Button();
                casillaMapa.setPrefHeight(50);
                casillaMapa.setPrefWidth(50);
                casillaMapa.setAlignment(Pos.CENTER);

                Parcela parcelaActual = mapa.get(x).get(y);

                if(parcelaActual instanceof Tierra) {casillaMapa.setStyle("-fx-background-color: #699922;");}
                if(parcelaActual instanceof Largada) {casillaMapa.setStyle("-fx-background-color: #599ed4;");}
                if(parcelaActual instanceof Meta) {casillaMapa.setStyle("-fx-background-color: #599ed4;");}
                if(parcelaActual instanceof Rocoso) {casillaMapa.setStyle("-fx-background-color: #38393b;");}
                if(parcelaActual instanceof Pasarela) {casillaMapa.setStyle("-fx-background-color: #d1b680;");}

                casillaMapa.setOnAction(event -> {
                    if(!torreAux.puseTorre){
                        Torre torreActual = torreAux.getTorre();
                        parcelaActual.construir(torreActual);

                        if(torreActual instanceof TorrePlateada) {
                            torreAux.ponerTorre();
                            ImageView torrePlateada = new ImageView(getClass().getResource("torrePlateada.png").toExternalForm());
                            root.add(torrePlateada,coordenadaX,coordenaday);
                        }
                        if(torreActual instanceof TorreBlanca) {
                            torreAux.ponerTorre();
                            ImageView torreBlanca = new ImageView(getClass().getResource("torreBlanca.png").toExternalForm());
                            root.add(torreBlanca,coordenadaX,coordenaday);
                        }
                    }

                });

 
                root.add(casillaMapa, x, y);
                
            }
        }

        //HORMIGA DE EJEMPLO
        ImageView hormiga = new ImageView((new File("src/main/java/edu/fiuba/algo3/modelo/Vista/hormiga.png")).toURI().toString());
        root.add(hormiga,1,1);

        //ARANIA DE EJEMPLO
        ImageView arania = new ImageView((new File("src/main/java/edu/fiuba/algo3/modelo/Vista/arania.png")).toURI().toString());
        root.add(arania,6,4);

        //TOPO DE EJEMPLO
        ImageView topo = new ImageView((new File("src/main/java/edu/fiuba/algo3/modelo/Vista/topo.png")).toURI().toString());
        root.add(topo,6,1);

        //LECHUZA DE EJEMPLO
        ImageView lechuza = new ImageView((new File("src/main/java/edu/fiuba/algo3/modelo/Vista/lechuza.png")).toURI().toString());
        root.add(lechuza,5,10);

        ImageView imagenBotonPlateada = new ImageView((new File("src/main/java/edu/fiuba/algo3/modelo/Vista/torrePlateada.png")).toURI().toString());
        Button botonPlateada = new Button();
        botonPlateada.setGraphic(imagenBotonPlateada);
        botonPlateada.setText("Torre Plateada");
        botonPlateada.setOnAction(event -> {
            TorrePlateada torreCreada = new TorrePlateada();
            torreAux.setTorre(torreCreada);
        });

        ImageView imagenBotonBlanca = new ImageView((new File("src/main/java/edu/fiuba/algo3/modelo/Vista/torreBlanca.png")).toURI().toString());
        Button botonBlanca = new Button();
        botonBlanca.setGraphic(imagenBotonBlanca);
        botonBlanca.setText("Torre Blanca");
        botonBlanca.setOnAction(event -> {
            TorreBlanca torreCreada = new TorreBlanca();
            torreAux.setTorre(torreCreada);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(botonPlateada, botonBlanca);
        vbox.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(root);

        borderPane.setRight(vbox);

        Scene scene = new Scene(borderPane);    
        stagePrincipal.setTitle("Mapa");
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    class ContenedorTorre {
    private Torre torre;
    public boolean puseTorre = false;

    public Torre getTorre() {
        return torre;
    }

    public void setTorre(Torre torre) {
        this.torre = torre;
        puseTorre = false;
    }

    public void ponerTorre() {
        puseTorre = true;
    }

    public boolean puseTorre() {
        return puseTorre;
    }
}
  
}


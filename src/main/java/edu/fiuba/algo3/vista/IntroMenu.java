package edu.fiuba.algo3.vista;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Parcelas.Largada;
import edu.fiuba.algo3.modelo.Parcelas.Meta;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Rocoso;
import edu.fiuba.algo3.modelo.Parcelas.Tierra;
import edu.fiuba.algo3.modelo.Parser.CreadorMapa;

import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.scene.layout.BorderStroke;

public class IntroMenu {

    private TextField textoNombre;
    private Button okButton;
    private Button iniButton;
    private Label validationLabel;
    private String nombre;

    private Button botonInicial;
    List<Parcela> pasarelas;
    List<List<Parcela>> mapa;
    GridPane root;

    public void crearUI(Stage stagePrincipal) {
        String css = "-fx-prompt-text-fill: black;";
        ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/logo.png")).toURI().toString());
        ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/image.png")).toURI().toString());
        backgroundImageView.fitWidthProperty().bind(stagePrincipal.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stagePrincipal.heightProperty());

        textoNombre = new TextField();
        textoNombre.setPromptText("Ingrese nombre");
        textoNombre.setStyle("-fx-control-inner-background: #FFC864;");
        textoNombre.setStyle(textoNombre.getStyle() + css);
        

        okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: #FFA500;");

        iniButton = new Button("Iniciar Partida");
        iniButton.setStyle("-fx-background-color: #FFA500;");

        validationLabel = new Label();

        iniButton.setOnAction(event -> {
            
            IniciarPartida(stagePrincipal);
        });  

        botonInicial = new Button("Siguiente");
        botonInicial.setStyle("-fx-background-color: #FFA500;");
        botonInicial.setFont(new Font(15));
        botonInicial.setPrefWidth(botonInicial.getPrefWidth() + 100);
        botonInicial.setPrefHeight(botonInicial.getPrefHeight() + 50);

        Scene nuevaEscena = new Scene(new VBox(textoNombre, okButton), 800, 600);
        stagePrincipal.setScene(nuevaEscena);


        StackPane pantallaInicial = new StackPane();

        VBox inicio = new VBox();
        inicio.setAlignment(Pos.CENTER);
        inicio.setSpacing(10);

        okButton.setOnAction(event -> {
            validarNombre(inicio);
        });

        inicio.getChildren().addAll(logoAlgoDefense, botonInicial);

        botonInicial.setOnAction(event -> {
            inicio.getChildren().remove(botonInicial);
            inicio.getChildren().addAll(textoNombre, okButton);
        });


        pantallaInicial.getChildren().addAll(backgroundImageView, inicio);

        Scene escenaInicial = new Scene(pantallaInicial, 800, 600);
        stagePrincipal.setScene(escenaInicial);
    }

    private void validarNombre(VBox inicio) {
        String inputText = textoNombre.getText();
        if (inputText.length() >= 6) {
            nombre = inputText;
            validationLabel.setText("Nombre elegido: " + nombre);
            validationLabel.setStyle("-fx-text-fill: #a86f13;");
            inicio.getChildren().removeAll(botonInicial, textoNombre, okButton);
            inicio.getChildren().addAll(validationLabel, iniButton);
        } else {
            validationLabel.setText("Ingrese un nombre de al menos 6 caracteres");
            validationLabel.setStyle("-fx-text-fill: #FF0000;");
            inicio.getChildren().add(validationLabel);
        }
    }

    private void IniciarPartida(Stage stagePrincipal) {

        validationLabel.setText("Partida iniciada");

        pasarelas = new LinkedList<>();
        mapa = CreadorMapa.crearMapa(pasarelas);

        ContenedorTorre torreAux = new ContenedorTorre();
        
        int SIZE = 15;
        int length = SIZE;
        int width = SIZE;
        
        root = new GridPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #A9A9A9;");

        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){

                int coordenadaX = x;
                int coordenaday = y;

                Button casillaMapa = new Button();
                casillaMapa.setPrefHeight(50);
                casillaMapa.setPrefWidth(50);
                casillaMapa.setAlignment(Pos.CENTER);

                Parcela parcelaActual = mapa.get(y).get(x);

                if(parcelaActual instanceof Tierra) {casillaMapa.setStyle("-fx-background-color: #699922;");}
                if(parcelaActual instanceof Largada) {casillaMapa.setStyle("-fx-background-color: #599ed4;");}
                if(parcelaActual instanceof Meta) {casillaMapa.setStyle("-fx-background-color: #FFA500;");}
                if(parcelaActual instanceof Rocoso) {casillaMapa.setStyle("-fx-background-color: #38393b;");}
                if(parcelaActual instanceof Pasarela) {casillaMapa.setStyle("-fx-background-color: #d1b680;");}

                casillaMapa.setOnAction(event -> {
                    if(!torreAux.puseDefensa){
                        Defensa torreActual = torreAux.getTorre();
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
                        if(torreActual instanceof TrampaArenosa) {
                            torreAux.ponerTorre();
                            ImageView trampaArenosa = new ImageView(getClass().getResource("trampaArenosa.png").toExternalForm());
                            root.add(trampaArenosa,coordenadaX,coordenaday);
                        }
                        activarBotones();
                    }

                });

 
                root.add(casillaMapa, x, y);
                
            }
        }

        // //TORRE PLATEADA DE EJEMPLO
        ImageView torrePlateada = new ImageView((new File("src/main/resources/image/torrePlateada.png")).toURI().toString());
        root.add(torrePlateada,3,2);

        // //TORRE BLANCA DE EJEMPLO
        ImageView torreBlanca = new ImageView((new File("src/main/resources/image/torreBlanca.png")).toURI().toString());
        root.add(torreBlanca,7,7);
        
        //TRAMPA ARENOSA DE EJEMPLO
        ImageView trampaArenosa = new ImageView((new File("src/main/resources/image/trampaArenosa.png")).toURI().toString());
        root.add(trampaArenosa,6,6);

        //HORMIGA DE EJEMPLO
        ImageView hormiga = new ImageView((new File("src/main/resources/image/hormiga.png")).toURI().toString());
        root.add(hormiga,1,1);

        //ARANIA DE EJEMPLO
        ImageView arania = new ImageView((new File("src/main/resources/image/arania.png")).toURI().toString());
        root.add(arania,7,6);

        //TOPO DE EJEMPLO
        ImageView topo = new ImageView((new File("src/main/resources/image/topo.png")).toURI().toString());
        root.add(topo,8,6);

        //TOPO ESCONDIDO DE EJEMPLO
        ImageView topoEscondido = new ImageView((new File("src/main/resources/image/topo_escondido.png")).toURI().toString());
        root.add(topoEscondido,5,6);

        //LECHUZA DE EJEMPLO
        ImageView lechuza = new ImageView((new File("src/main/resources/image/lechuza.png")).toURI().toString());
        root.add(lechuza,5,10);

        ImageView imagenBotonPlateada = new ImageView((new File("src/main/resources/image/torrePlateada.png")).toURI().toString());
        Button botonPlateada = new Button();
        botonPlateada.setGraphic(imagenBotonPlateada);
        botonPlateada.setText("TorreBlanca Plateada");
        botonPlateada.setOnAction(event -> {
            desactivarBotonesInvalidos();
            TorrePlateada torreCreada = new TorrePlateada();
            torreAux.setTorre(torreCreada);
        });

        ImageView imagenBotonBlanca = new ImageView((new File("src/main/resources/image/torreBlanca.png")).toURI().toString());
        Button botonBlanca = new Button();
        botonBlanca.setGraphic(imagenBotonBlanca);
        botonBlanca.setText("TorreBlanca Blanca");
        botonBlanca.setOnAction(event -> {
            desactivarBotonesInvalidos();
            TorreBlanca torreCreada = new TorreBlanca();
            torreAux.setTorre(torreCreada);
        });

        ImageView imagenTrampaArenosa = new ImageView((new File("src/main/resources/image/trampaArenosa.png")).toURI().toString());
        Button botonTrampa = new Button();
        botonTrampa.setGraphic(imagenTrampaArenosa);
        botonTrampa.setText("Trampa Arenosa");
        botonTrampa.setOnAction(event -> {
            desactivarBotonesInvalidos();
            TrampaArenosa trampaCreada = new TrampaArenosa();
            torreAux.setTorre(trampaCreada);
        });

        Button botonSkipTurno = new Button();
        botonSkipTurno.setText("Skip Turno");
        botonSkipTurno.setOnAction(event -> {
            //ACA VA EL CODIGO PARA PASAR DE TURNO
        });



        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #A9A9A9;");
        vbox.getChildren().addAll(botonPlateada, botonBlanca, botonTrampa, botonSkipTurno);
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
    private Defensa defensa;
    public boolean puseDefensa = false;

    public Defensa getTorre() {
        return defensa;
    }

    public void setTorre(Defensa defensa) {
        this.defensa = defensa;
        puseDefensa = false;
    }

    public void ponerTorre() {
        puseDefensa = true;
    }

    public boolean puseTorre() {
        return puseDefensa;
    }
}

    private void desactivarBotonesInvalidos(){

        Color bordeRojo = Color.RED;
        Color borderVerde = Color.GREENYELLOW;
        double borderWidth = 2.0; 
        
        for(int y = 0; y < 15; y++){
            for(int x = 0; x < 15; x++){

                Parcela parcelaActual = mapa.get(y).get(x);
                if(!(parcelaActual instanceof Tierra)){
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button)botonActual;
                            
                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeRojo, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                        break;
                        }           
                    }
                } else {
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button)botonActual;
                            
                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(borderVerde, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                        break;
                        }           
                    }
                }
            }
        }

    
        
    }

    private void activarBotones(){
            for (Node botonActual : root.getChildren()) {
                    Button boton = (Button)botonActual;
                    
                    boton.setOnMouseEntered(null);
                break;
            }           
    }     
}

  



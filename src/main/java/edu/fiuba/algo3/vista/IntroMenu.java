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
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Enemigos.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
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

    private int turno = 0;
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
        Label valLabel = new Label();
        inicio.getChildren().removeAll(valLabel, validationLabel);
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


    private void IniciarPartida(Stage stagePrincipal) {


        Partida partida = new Partida();

        String imagenTorrePlateada = (new File("src/main/resources/image/torrePlateada.png")).toURI().toString();
        String imagenTorreBlanca = (new File("src/main/resources/image/torreBlanca.png")).toURI().toString();
        String imagenTrampaArenosa = (new File("src/main/resources/image/trampaArenosa.png")).toURI().toString();
        String imagenHormiga = (new File("src/main/resources/image/hormiga.png")).toURI().toString();
        String imagenArania = (new File("src/main/resources/image/arania.png")).toURI().toString();
        String imagenTopo = (new File("src/main/resources/image/topo.png")).toURI().toString();
        String imagenTopoEscondido = (new File("src/main/resources/image/topo_escondido.png")).toURI().toString();
        String imagenLechuza = (new File("src/main/resources/image/lechuza.png")).toURI().toString();

        validationLabel.setText("Partida iniciada");

        mapa = partida.obtenerMapa();

        
        ContenedorTorre torreAux = new ContenedorTorre();
        
        int SIZE = 15;
        int length = SIZE;
        int width = SIZE;
        
        root = new GridPane();
        root.setPadding(new Insets(10));

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
                        partida.construirDefensa(torreActual, coordenadaX, coordenaday);


                        if(torreActual instanceof TorrePlateada) {
                            torreAux.ponerTorre();
                            root.add(new ImageView(imagenTorrePlateada),coordenadaX,coordenaday);
                        } else if(torreActual instanceof TorreBlanca) {
                            torreAux.ponerTorre();
                            root.add(new ImageView(imagenTorreBlanca),coordenadaX,coordenaday);
                        } else if(torreActual instanceof TrampaArenosa) {
                            torreAux.ponerTorre();
                            root.add(new ImageView(imagenTrampaArenosa),coordenadaX,coordenaday);
                        }
                        activarBotones();
                    }

                });

 
                root.add(casillaMapa, x, y);
                
            }
        }
/* 
        // //TORRE PLATEADA DE EJEMPLO
        root.add(new ImageView(imagenTorrePlateada),4,7);

        // //TORRE BLANCA DE EJEMPLO
        root.add(new ImageView(imagenTorreBlanca),7,7);
        
        //TRAMPA ARENOSA DE EJEMPLO
        root.add(new ImageView(imagenTrampaArenosa),6,6);

        //HORMIGA DE EJEMPLO
        root.add(new ImageView(imagenHormiga),1,1);

        //ARANIA DE EJEMPLO
        root.add(new ImageView(imagenArania),7,6);

        //TOPO DE EJEMPLO
        root.add(new ImageView(imagenTopo),8,6);

        //TOPO ESCONDIDO DE EJEMPLO
        root.add(new ImageView(imagenTopoEscondido),5,6);

        //LECHUZA DE EJEMPLO
        root.add(new ImageView(imagenLechuza),5,10);
*/
        Jugador jugador = partida.obtenerJugador();
        VBox datosUsuario = new VBox();
        datosUsuario.setSpacing(10);
        datosUsuario.setPadding(new Insets(10));
        

        Label label1 = new Label("Nombre: " + nombre);
        Label label2 = new Label(jugador.obtenerVidaRestante() + "/20");
        Label label3 = new Label("Creditos Restantes: " + jugador.obtenerCreditosRestantes());
        datosUsuario.getChildren().addAll(label1, label2, label3);

        Button botonPlateada = new Button();
        botonPlateada.setGraphic(new ImageView(imagenTorrePlateada));
        botonPlateada.setText("Torre Plateada");
        botonPlateada.setOnAction(event -> {
            activarBordesTorres();
            TorrePlateada torreCreada = new TorrePlateada();
            torreAux.setTorre(torreCreada);
            label3.setText("Creditos Restantes: " + jugador.obtenerCreditosRestantes());
        });

        Button botonBlanca = new Button();
        botonBlanca.setGraphic(new ImageView(imagenTorreBlanca));
        botonBlanca.setText("Torre Blanca");
        botonBlanca.setOnAction(event -> {
            activarBordesTorres();
            TorreBlanca torreCreada = new TorreBlanca();
            torreAux.setTorre(torreCreada);
            label3.setText("Creditos Restantes: " + jugador.obtenerCreditosRestantes());
        });

        Button botonTrampa = new Button();
        botonTrampa.setGraphic(new ImageView(imagenTrampaArenosa));
        botonTrampa.setText("Trampa Arenosa");
        botonTrampa.setOnAction(event -> {
            activarBordesTrampaArena();
            TrampaArenosa trampaCreada = new TrampaArenosa();
            torreAux.setTorre(trampaCreada);
            label3.setText("Creditos Restantes: " + jugador.obtenerCreditosRestantes());
        });

        Button botonSkipTurno = new Button();
        botonSkipTurno.setText("Skip Turno");
        botonSkipTurno.setOnAction(event -> {
            //Avanzar turno
            partida.avanzarTurno(turno);
            turno++;
            //ubica la imagen de los enemigos en el mapa
            for(int i = 0; i < partida.obtenerEnemigos().size(); i++){
                Enemigo enemigoActual = partida.obtenerEnemigos().get(i);
                int coordenadaX = enemigoActual.obtenerPosicion().obtenerCoordenadaX();
                int coordenadaY = enemigoActual.obtenerPosicion().obtenerCoordenadaY();
                if(enemigoActual instanceof Hormiga) {
                    root.add(new ImageView(imagenHormiga),coordenadaX,coordenadaY);
                } else if(enemigoActual instanceof Arania) {
                    root.add(new ImageView(imagenArania),coordenadaX,coordenadaY);
                } else if(enemigoActual instanceof Topo) {
                    root.add(new ImageView(imagenTopo),coordenadaX,coordenadaY);
                } else if(enemigoActual instanceof Topo) {
                    root.add(new ImageView(imagenTopoEscondido),coordenadaX,coordenadaY);
                } else if(enemigoActual instanceof Lechuza) {
                    root.add(new ImageView(imagenLechuza),coordenadaX,coordenadaY);
                }
            }
        });

        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, new CornerRadii(8), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        datosUsuario.setBackground(background);

        Color bordeAzul = Color.BLANCHEDALMOND;
        double borderWidth = 2.0; 
        datosUsuario.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeAzul, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        VBox seccionBotones = new VBox();
        seccionBotones.setSpacing(10);
        
        seccionBotones.getChildren().addAll(datosUsuario, botonPlateada, botonBlanca, botonTrampa, botonSkipTurno);
        seccionBotones.setPadding(new Insets(10));

        HBox seccionMapa = new HBox();
        seccionMapa.getChildren().addAll(root, seccionBotones);

        ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/image.png")).toURI().toString());
        backgroundImageView.fitWidthProperty().bind(seccionMapa.widthProperty());
        backgroundImageView.fitHeightProperty().bind(seccionMapa.heightProperty());

        StackPane fondoMapa = new StackPane();
        fondoMapa.getChildren().addAll(backgroundImageView, seccionMapa);

        Scene scene = new Scene(fondoMapa);    
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

    private void activarBordesTorres(){

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

    private void activarBordesTrampaArena(){

        Color bordeRojo = Color.RED;
        Color borderVerde = Color.GREENYELLOW;
        double borderWidth = 2.0; 
        
        for(int y = 0; y < 15; y++){
            for(int x = 0; x < 15; x++){

                Parcela parcelaActual = mapa.get(y).get(x);
                if(!(parcelaActual instanceof Pasarela)){
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
        for(int y = 0; y < 15; y++){
            for(int x = 0; x < 15; x++){

                Parcela parcelaActual = mapa.get(y).get(x);
                if((parcelaActual instanceof Parcela)){
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button)botonActual;
                            
                            boton.setOnMouseEntered(event -> boton.setBorder(null));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                        break;
                        }           
                    }
                }   
            }               
        }  
    }
}



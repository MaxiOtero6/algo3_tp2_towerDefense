package edu.fiuba.algo3.vista;

import java.io.File;
import java.util.ArrayList;
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
import edu.fiuba.algo3.controlador.BotonOkEventHandler;
import edu.fiuba.algo3.controlador.NombreEventHandler;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Enemigos.*;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class IntroMenu {

    private int turno = 0;
    private TextField textoNombre;
    private Button okButton;
    private Button iniButton;
    private Button volverAJugarButton;
    private Label validationLabel;
    private Label labelVida;
    private Label labelCreditos;
    private String nombre;
    private Partida partida;
    private Jugador jugador;
    private static MediaPlayer mediaPlayer;
    private String imagenTorrePlateada = (new File("src/main/resources/image/torrePlateada.png")).toURI().toString();
    private String imagenTorreBlanca = (new File("src/main/resources/image/torreBlanca.png")).toURI().toString();
    private String imagenTrampaArenosa = (new File("src/main/resources/image/trampaArenosa.png")).toURI().toString();
    private String imagenHormiga = (new File("src/main/resources/image/hormiga.png")).toURI().toString();
    private String imagenArania = (new File("src/main/resources/image/arania.png")).toURI().toString();
    private String imagenTopo = (new File("src/main/resources/image/topo.png")).toURI().toString();
    private String imagenTopoEscondido = (new File("src/main/resources/image/topo_escondido.png")).toURI().toString();
    private String imagenLechuza = (new File("src/main/resources/image/lechuza.png")).toURI().toString();
    private Alert alert;
    AudioClip sonidoPerder = new AudioClip(new File("src/main/resources/sound/fail.mp3").toURI().toString());
    AudioClip sonidoGanar = new AudioClip(new File("src/main/resources/sound/win.mp3").toURI().toString());
    AudioClip sonidoPonerTorre = new AudioClip(new File("src/main/resources/sound/place.mp3").toURI().toString());
    AudioClip sonidoPonerTrampa = new AudioClip(new File("src/main/resources/sound/sand.mp3").toURI().toString());
    AudioClip sonidoError = new AudioClip(new File("src/main/resources/sound/error.mp3").toURI().toString());
    AudioClip sonidoClick = new AudioClip(new File("src/main/resources/sound/click.mp3").toURI().toString());
    AudioClip sonidoStart = new AudioClip(new File("src/main/resources/sound/start.mp3").toURI().toString());
    AudioClip sonidoEnter = new AudioClip(new File("src/main/resources/sound/enter.mp3").toURI().toString());

    private Button botonInicial;
    private List<VistaDefensas> listaVistaDefensas;
    List<Parcela> pasarelas;
    List<List<Parcela>> mapa;
    GridPane root;

    

    public void crearUI(Stage stagePrincipal) {
        
        String musicFile = "src/main/resources/sound/music.wav";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
        mediaPlayer.setVolume(0.3);

        mediaPlayer.play();

        listaVistaDefensas = new ArrayList<>();
        alert = new Alert(AlertType.INFORMATION);
        partida = new Partida();
        jugador = partida.obtenerJugador();
        labelVida = new Label("Vida Restante: " + jugador.obtenerVidaRestante() + "/20");
        labelCreditos = new Label("Creditos Restantes: " + jugador.obtenerCreditosRestantes());
        String css = "-fx-prompt-text-fill: black;";
        ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/logo.png")).toURI().toString());
        ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/image.png")).toURI().toString());
        backgroundImageView.fitWidthProperty().bind(stagePrincipal.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stagePrincipal.heightProperty());

        Color bordeClaro = Color.BLANCHEDALMOND;
        double borderWidth = 2.0; 
        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, new CornerRadii(8), Insets.EMPTY);
        Background background = new Background(backgroundFill);

        Label volumenMusica = new Label("Volumen de la musica:");
        Slider sliderMusica = new Slider(0, 100, 50);
        sliderMusica.setValue(mediaPlayer.getVolume() * 100);
        sliderMusica.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(sliderMusica.getValue() / 100);
        });

        Label volumenSonidos = new Label("Volumen de los sonidos:");
        Slider sliderSonidos = new Slider(0, 100, 50);
        sliderSonidos.setValue(sonidoClick.getVolume() * 100);
        sliderSonidos.valueProperty().addListener((observable, oldValue, newValue) -> {
            sonidoGanar.setVolume(sliderSonidos.getValue() / 100);
            sonidoPerder.setVolume(sliderSonidos.getValue() / 100);
            sonidoError.setVolume(sliderSonidos.getValue() / 100);
            sonidoClick.setVolume(sliderSonidos.getValue() / 100);
            sonidoPonerTorre.setVolume(sliderSonidos.getValue() / 100);
            sonidoPonerTrampa.setVolume(sliderSonidos.getValue() / 100);
            sonidoEnter.setVolume(sliderSonidos.getValue() / 100);
            sonidoStart.setVolume(sliderSonidos.getValue() / 100);
        });

        VBox seccionVolumen = new VBox();
        seccionVolumen.setSpacing(10);
        seccionVolumen.setPadding(new Insets(10));
        seccionVolumen.setBackground(background);

        seccionVolumen.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeClaro, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        seccionVolumen.getChildren().addAll(volumenMusica, sliderMusica, volumenSonidos, sliderSonidos);


        textoNombre = new TextField();
        
        textoNombre.setPromptText("Ingrese nombre: ");
        textoNombre.setMaxWidth(200);
        textoNombre.setStyle("-fx-control-inner-background: #FFC864;");
        textoNombre.setStyle(textoNombre.getStyle() + css);
        

        okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: #FFA500;");

        iniButton = new Button("Iniciar Partida");
        iniButton.setStyle("-fx-background-color: #FFA500;");

        validationLabel = new Label();

        iniButton.setOnAction(event -> {
            IniciarPartida(stagePrincipal);
            sonidoStart.play();
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

        NombreEventHandler nombreEventHandler = new NombreEventHandler(okButton);
        textoNombre.setOnKeyPressed(nombreEventHandler);
        
        
        okButton.setOnAction(event -> {
            sonidoEnter.play();
            validarNombre(inicio);
        });

        

        inicio.getChildren().addAll(logoAlgoDefense, botonInicial);

        botonInicial.setOnAction(event -> {
            sonidoEnter.play();
            inicio.getChildren().remove(botonInicial);
            inicio.getChildren().addAll(textoNombre, okButton);
        });

        inicio.setAlignment(Pos.CENTER);
        seccionVolumen.setAlignment(Pos.BOTTOM_LEFT);
        seccionVolumen.setMaxWidth(200);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(inicio);
        borderPane.setBottom(seccionVolumen);

        pantallaInicial.getChildren().addAll(backgroundImageView, borderPane);

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


    private void ejecutarBotonSkipTurno(Stage stagePrincipal) {

        partida.avanzarTurno();
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : root.getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                Image image = imageView.getImage();
                String imageUrl = image.getUrl();
                if (imageUrl.equals(imagenHormiga) ||
                    imageUrl.equals(imagenArania) ||
                    imageUrl.equals(imagenTopo) ||
                    imageUrl.equals(imagenLechuza)||
                    imageUrl.equals(imagenTopoEscondido)) {
                    nodesToRemove.add(node);
                }
            }
        }
        root.getChildren().removeAll(nodesToRemove);
        for (int i = 0; i < partida.obtenerEnemigos().size(); i++) {
            Enemigo enemigoActual = partida.obtenerEnemigos().get(i);
            int coordenadaX = enemigoActual.obtenerPosicion().obtenerCoordenadaX();
            int coordenadaY = enemigoActual.obtenerPosicion().obtenerCoordenadaY();
            if (enemigoActual instanceof Hormiga) {
                root.add(new ImageView(imagenHormiga), coordenadaX, coordenadaY);
            } else if (enemigoActual instanceof Arania) {
                root.add(new ImageView(imagenArania), coordenadaX, coordenadaY);
            } else if (enemigoActual instanceof Topo) {
                Topo topo = (Topo) enemigoActual;
                if (topo.esSubterraneo()){
                    root.add(new ImageView(imagenTopoEscondido), coordenadaX, coordenadaY);
                }
                else{
                    root.add(new ImageView(imagenTopo), coordenadaX, coordenadaY);
                }
                
            } else if (enemigoActual instanceof Lechuza) {
                root.add(new ImageView(imagenLechuza), coordenadaX, coordenadaY);
            }
        }
        labelVida.setText("Vida Restante: " + jugador.obtenerVidaRestante() + "/20");
        labelCreditos.setText("Creditos Restantes: " + jugador.obtenerCreditosRestantes());
        //ACTUALIZAR DEFENSAS
        for (VistaDefensas vista: listaVistaDefensas) {
            vista.update();
        }

        //ALERTA DE PERDIDA
        if(jugador.obtenerVidaRestante() <= 0  && jugador.obtenerVidaRestante() < 0){
            sonidoPerder.play();
            StackPane pantallaFinalPerdida = new StackPane();
            ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/perdiste.png")).toURI().toString());
            ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/imagenPerder.png")).toURI().toString());
            backgroundImageView.fitWidthProperty().bind(stagePrincipal.widthProperty());
            backgroundImageView.fitHeightProperty().bind(stagePrincipal.heightProperty());
            
            volverAJugarButton = new Button("Volver a jugar");
            volverAJugarButton.setStyle("-fx-background-color: #FFA500;");
            volverAJugarButton.setTranslateY(60);
            volverAJugarButton.setOnAction(event -> {
                sonidoEnter.play();
                crearUI(stagePrincipal);
            });

            pantallaFinalPerdida.getChildren().addAll(backgroundImageView, logoAlgoDefense, volverAJugarButton);

            Scene escenaInicial = new Scene(pantallaFinalPerdida, 800, 600);
            stagePrincipal.setTitle("Terminó la partida!");
            stagePrincipal.setScene(escenaInicial);
            mediaPlayer.stop();
        }
        //ALERTA DE VICTORIA
        if(partida.obtenerEnemigos().size() == 0 && jugador.obtenerVidaRestante() > 0){
            sonidoGanar.play();
            StackPane pantallaFinalGanar = new StackPane();
            ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/ganaste.png")).toURI().toString());
            ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/imagenGanar.png")).toURI().toString());
            backgroundImageView.fitWidthProperty().bind(stagePrincipal.widthProperty());
            backgroundImageView.fitHeightProperty().bind(stagePrincipal.heightProperty());

            volverAJugarButton = new Button("Volver a jugar");
            volverAJugarButton.setStyle("-fx-background-color: #FFA500;");
            volverAJugarButton.setTranslateY(60);
            volverAJugarButton.setOnAction(event -> {
                sonidoEnter.play();
                crearUI(stagePrincipal);
            });

            pantallaFinalGanar.getChildren().addAll(backgroundImageView, logoAlgoDefense, volverAJugarButton);

            Scene escenaInicial = new Scene(pantallaFinalGanar, 800, 600);
            stagePrincipal.setTitle("Terminó la partida!");
            stagePrincipal.setScene(escenaInicial);
            mediaPlayer.stop();
        }
    }
        HBox enemigosEnParcela = new HBox();

    private void IniciarPartida(Stage stagePrincipal) {

        //SECCION PARA MOSTRAR ENEMIGOS EN LA PARCELA ACTUAL
        Color bordeClaro = Color.BLANCHEDALMOND;
        Color bordeAzul = Color.DARKCYAN;
        double borderWidth = 2.0; 

        enemigosEnParcela.setSpacing(10);
        enemigosEnParcela.setPadding(new Insets(10));
        enemigosEnParcela.setAlignment(Pos.CENTER);
        enemigosEnParcela.setPrefHeight(75);

        BackgroundFill backgroundFillEnemigos = new BackgroundFill(Color.ORANGE, new CornerRadii(8), Insets.EMPTY);
        Background backgroundEnemigos = new Background(backgroundFillEnemigos);
        enemigosEnParcela.setBackground(backgroundEnemigos);

        enemigosEnParcela.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeClaro, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        validationLabel.setText("Partida iniciada");

        //Mapa getter
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
                        //sound
                        if((torreActual instanceof TorrePlateada || torreActual instanceof TorreBlanca) && parcelaActual instanceof Tierra){
                            sonidoPonerTorre.play();
                        }
                        if(torreActual instanceof TrampaArenosa && parcelaActual instanceof Pasarela){
                            sonidoPonerTrampa.play();
                        }
                        try {
                            partida.construirDefensa(torreActual, coordenadaX, coordenaday);
                            VistaDefensas vistaDefensa = new VistaDefensas(root, torreActual);
                            vistaDefensa.dibujar();
                            torreAux.ponerTorre();
                            listaVistaDefensas.add(vistaDefensa);
                            ejecutarBotonSkipTurno(stagePrincipal);
                            activarBotones();
                        } catch (NullPointerException e){
                            sonidoError.play();
                        }

                    }

                });

                casillaMapa.setOnMouseEntered(event -> mostrarEnemigos(enemigosEnParcela, coordenadaX, coordenaday));
                casillaMapa.setOnMouseExited(event -> sacarEnemigos(enemigosEnParcela));
 
                root.add(casillaMapa, x, y);
                
            }
        }

        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, new CornerRadii(8), Insets.EMPTY);
        Background background = new Background(backgroundFill);

        Label volumenMusica = new Label("Volumen de la musica:");
        Slider sliderMusica = new Slider(0, 100, 50);
        sliderMusica.setValue(mediaPlayer.getVolume() * 100);
        sliderMusica.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(sliderMusica.getValue() / 100);
        });

        Label volumenSonidos = new Label("Volumen de los sonidos:");
        Slider sliderSonidos = new Slider(0, 100, 50);
        sliderSonidos.setValue(sonidoClick.getVolume() * 100);
        sliderSonidos.valueProperty().addListener((observable, oldValue, newValue) -> {
            sonidoGanar.setVolume(sliderSonidos.getValue() / 100);
            sonidoPerder.setVolume(sliderSonidos.getValue() / 100);
            sonidoError.setVolume(sliderSonidos.getValue() / 100);
            sonidoClick.setVolume(sliderSonidos.getValue() / 100);
            sonidoPonerTorre.setVolume(sliderSonidos.getValue() / 100);
            sonidoPonerTrampa.setVolume(sliderSonidos.getValue() / 100);
        });

        VBox seccionVolumen = new VBox();
        seccionVolumen.setSpacing(10);
        seccionVolumen.setPadding(new Insets(10));
        seccionVolumen.setBackground(background);

        seccionVolumen.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeClaro, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        seccionVolumen.getChildren().addAll(volumenMusica, sliderMusica, volumenSonidos, sliderSonidos);

        Jugador jugador = partida.obtenerJugador();
        VBox datosUsuario = new VBox();
        datosUsuario.setSpacing(10);
        datosUsuario.setPadding(new Insets(10));
        datosUsuario.prefWidthProperty().bind(seccionVolumen.widthProperty());
        
        datosUsuario.setBackground(background);

        datosUsuario.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeClaro, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        Label label1 = new Label("Nombre: " + nombre);
        labelVida = new Label("Vida Restante: " + jugador.obtenerVidaRestante() + "/20");

        datosUsuario.getChildren().addAll(label1, labelVida, labelCreditos);

        BackgroundFill backgroundFillAzul = new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(8), Insets.EMPTY);
        Background backgroundAzul = new Background(backgroundFillAzul);

        Button botonPlateada = new Button();
        botonPlateada.setGraphic(new ImageView(imagenTorrePlateada));
        botonPlateada.setText("Torre Plateada");
        botonPlateada.prefWidthProperty().bind(datosUsuario.widthProperty());
        botonPlateada.setMinWidth(datosUsuario.getMinWidth());
        botonPlateada.setOnAction(event -> {
            sonidoClick.play();
            if(jugador.obtenerCreditosRestantes() >= 20){
                activarBordesTorres();
                TorrePlateada torreCreada = new TorrePlateada();
                torreAux.setTorre(torreCreada);
            }
        });

        botonPlateada.setBackground(backgroundAzul);
        botonPlateada.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeAzul, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        Button botonBlanca = new Button();
        botonBlanca.setGraphic(new ImageView(imagenTorreBlanca));
        botonBlanca.setText("Torre Blanca");
        botonBlanca.prefWidthProperty().bind(datosUsuario.widthProperty());
        botonBlanca.setOnAction(event -> {
            sonidoClick.play();
            if(jugador.obtenerCreditosRestantes() >= 10){
                activarBordesTorres();
                TorreBlanca torreCreada = new TorreBlanca();
                torreAux.setTorre(torreCreada);
            }    
        });

        botonBlanca.setBackground(backgroundAzul);
        botonBlanca.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeAzul, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        Button botonTrampa = new Button();
        botonTrampa.setGraphic(new ImageView(imagenTrampaArenosa));
        botonTrampa.setText("Trampa Arenosa");
        botonTrampa.prefWidthProperty().bind(datosUsuario.widthProperty());
        botonTrampa.setOnAction(event -> {
            sonidoClick.play();
            if(jugador.obtenerCreditosRestantes() >= 25){
                activarBordesTrampaArena();
                TrampaArenosa trampaCreada = new TrampaArenosa();
                torreAux.setTorre(trampaCreada);
            }
        
        });

        botonTrampa.setBackground(backgroundAzul);
        botonTrampa.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeAzul, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        Button botonSkipTurno = new Button();
        botonSkipTurno.setText("Skip Turno");
        botonSkipTurno.prefWidthProperty().bind(botonTrampa.widthProperty());
        botonSkipTurno.setOnAction(event -> {
            sonidoClick.play();
            ejecutarBotonSkipTurno(stagePrincipal);
        });

        botonSkipTurno.setBackground(backgroundAzul);
        botonSkipTurno.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeAzul, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        VBox seccionBotones = new VBox();
        seccionBotones.setSpacing(10);

        seccionBotones.setMargin(botonSkipTurno, new Insets(0, 0, 0, 0));
        seccionBotones.getChildren().addAll(datosUsuario, botonPlateada, botonBlanca, botonTrampa, botonSkipTurno, seccionVolumen);
        seccionBotones.setPadding(new Insets(10));

        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setBottomAnchor(seccionVolumen, 15.0);
        AnchorPane.setRightAnchor(seccionVolumen, 15.0);
        anchorPane.getChildren().addAll(seccionBotones, seccionVolumen); 

        HBox seccionMapa = new HBox();
        seccionMapa.getChildren().addAll(root, anchorPane);

        VBox seccionTotal = new VBox();
        seccionTotal.getChildren().addAll(enemigosEnParcela, seccionMapa);

        ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/image.png")).toURI().toString());
        backgroundImageView.fitWidthProperty().bind(seccionTotal.widthProperty());
        backgroundImageView.fitHeightProperty().bind(seccionTotal.heightProperty());

        StackPane fondoMapa = new StackPane();
        fondoMapa.getChildren().addAll(backgroundImageView, seccionTotal);

        Scene scene = new Scene(fondoMapa);    
        stagePrincipal.setTitle("Jugando");
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
                int coordenadaX = x;
                int coordenadaY = y;

                Parcela parcelaActual = mapa.get(y).get(x);
                if((parcelaActual instanceof Parcela)){
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button)botonActual;
                            boton.setBorder(null);
                            boton.setOnMouseEntered(event -> mostrarEnemigos(enemigosEnParcela, coordenadaX, coordenadaY));
                            boton.setOnMouseExited(event -> sacarEnemigos(enemigosEnParcela));

                        break;
                        }           
                    }
                }   
            }               
        }  
    }

    private void mostrarEnemigos(HBox enemigosDeParcela, int x, int y){
        Parcela parcelaActual = mapa.get(y).get(x);
        LinkedList<Enemigo> enemigosEnLaParcela = parcelaActual.devolverEnemigos();
        for(Enemigo enemigo : enemigosEnLaParcela){
            if(enemigo instanceof Arania) {enemigosDeParcela.getChildren().add(new ImageView(imagenArania));}
            if(enemigo instanceof Hormiga) {enemigosDeParcela.getChildren().add(new ImageView(imagenHormiga));}
            if(enemigo instanceof Lechuza) {enemigosDeParcela.getChildren().add(new ImageView(imagenLechuza));}
            if(enemigo instanceof Topo) {
                Topo topoAux = (Topo)enemigo;
                if(topoAux.esSubterraneo()){
                    enemigosDeParcela.getChildren().add(new ImageView(imagenTopoEscondido));
                } else {
                    enemigosDeParcela.getChildren().add(new ImageView(imagenTopo));
                }   
            }
        }
    }
    private void sacarEnemigos(HBox enemigosDeParcela){
        enemigosDeParcela.getChildren().clear();
    }
}




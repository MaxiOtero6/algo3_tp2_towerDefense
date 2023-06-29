package edu.fiuba.algo3.vista;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.controlador.BotonInicialEventHandler;
import edu.fiuba.algo3.controlador.BotonIniciarEventHandler;
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
        labelVida = new Label("Vida Restante: " + jugador.obtenerVidaRestante() + "/20 ");
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
        HBox vida = new HBox();
        String imagenCorazon = (new File("src/main/resources/image/corazon.png")).toURI().toString();
        ImageView corazon = new ImageView(imagenCorazon);
        vida.getChildren().addAll(labelVida, corazon);

        List sonidos = new ArrayList<AudioClip>();
        sonidos.add(sonidoGanar);
        sonidos.add(sonidoPerder);
        sonidos.add(sonidoPonerTorre);
        sonidos.add(sonidoPonerTrampa);
        sonidos.add(sonidoError);
        sonidos.add(sonidoClick);
        sonidos.add(sonidoEnter);
        ContenedorPartida contenedorPartida = new ContenedorPartida(stagePrincipal, partida, jugador, labelVida, vida, labelCreditos, textoNombre, mediaPlayer, sonidos);
        Scene escenaPartida = new Scene(contenedorPartida);
        BotonIniciarEventHandler botonIniciarEventHandler = new BotonIniciarEventHandler(escenaPartida, stagePrincipal, contenedorPartida);

        iniButton.setOnAction(botonIniciarEventHandler);
            //sonidoStart.play();

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

        BotonOkEventHandler botonOkEventHandler = new BotonOkEventHandler(textoNombre, validationLabel, inicio, okButton, iniButton, sonidoEnter, contenedorPartida);
        okButton.setOnAction(botonOkEventHandler);

        inicio.getChildren().addAll(logoAlgoDefense, botonInicial);

        BotonInicialEventHandler botonInicialEventHandler = new BotonInicialEventHandler(sonidoEnter, botonInicial, okButton, textoNombre, inicio);
        botonInicial.setOnAction(botonInicialEventHandler);

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

}




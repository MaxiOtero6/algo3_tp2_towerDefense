package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.*;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.NoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorrePlateada;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Partida;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContenedorPartida extends StackPane {

    private List<List<Parcela>> mapa;
    private HBox enemigosEnParcela;
    private GridPane root;
    private List<Vista> listaVistaDefensas;

    private String imagenTorrePlateada = (new File("src/main/resources/image/torrePlateada.png")).toURI().toString();
    private String imagenTorreBlanca = (new File("src/main/resources/image/torreBlanca.png")).toURI().toString();
    private String imagenTrampaArenosa = (new File("src/main/resources/image/trampaArenosa.png")).toURI().toString();
    private String imagenMoneda = (new File("src/main/resources/image/coin.png")).toURI().toString();
    private VistaDatosUsuario datosUsuario;
    

    private AudioClip sonidoPonerTorre;
    private AudioClip sonidoPonerTrampa;
    private AudioClip sonidoError;
    private AudioClip sonidoClick;
    private AudioClip sonidoGanar;
    private AudioClip sonidoPerder;
    private Slider sliderMusica;
    private Slider sliderSonidos;


    public ContenedorPartida(Stage stagePrincipal, Partida partida, Jugador jugador, TextField textoNombre, MediaPlayer mediaPlayer, ControladorSonidos controladorSonidos) {


        super();
        enemigosEnParcela = new HBox();
        listaVistaDefensas = new ArrayList<Vista>();

        List<AudioClip> sonidos = controladorSonidos.devolverSonidos();
        sonidoGanar = (AudioClip)sonidos.get(0);
        sonidoPerder = (AudioClip)sonidos.get(1);
        sonidoPonerTorre = (AudioClip)sonidos.get(2);
        sonidoPonerTrampa = (AudioClip)sonidos.get(3);
        sonidoError = (AudioClip)sonidos.get(4);
        sonidoClick = (AudioClip)sonidos.get(5);

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

        enemigosEnParcela.setBorder(new Border(
                new BorderStroke(bordeClaro, BorderStrokeStyle.SOLID,
                        new CornerRadii(6), new BorderWidths(borderWidth))));

        Label validationLabel = new Label();
        validationLabel.setText("Partida iniciada");

        //Mapa getter
        mapa = partida.obtenerMapa();


        int SIZE = 15;
        int length = SIZE;
        int width = SIZE;

        root = new GridPane();
        root.setPadding(new Insets(10));

        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, new CornerRadii(8), Insets.EMPTY);
        Background background = new Background(backgroundFill);

        Label volumenMusica = new Label("Volumen de la musica:");
        sliderMusica = new Slider(0, 100, 50);
        sliderMusica.setValue(mediaPlayer.getVolume() * 100);
        sliderMusica.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(sliderMusica.getValue() / 100);
        });

        Label volumenSonidos = new Label("Volumen de los sonidos:");
        sliderSonidos = new Slider(0, 100, 50);
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

        seccionVolumen.setBorder(new Border(
                new BorderStroke(bordeClaro, BorderStrokeStyle.SOLID,
                        new CornerRadii(6), new BorderWidths(borderWidth))));

        BordesDefensas bordesDefensas = new BordesDefensas(root, mapa, enemigosEnParcela);
        datosUsuario = new VistaDatosUsuario(seccionVolumen, bordeClaro, jugador, textoNombre);
        VistaEnemigos vistaEnemigos = new VistaEnemigos(root, partida);
        listaVistaDefensas.add(datosUsuario);
        listaVistaDefensas.add(vistaEnemigos);
        BotonSkipEventHandler botonSkipEventHandler = new BotonSkipEventHandler(listaVistaDefensas, partida, mediaPlayer,
                stagePrincipal, sonidoClick, sonidoGanar, sonidoPerder);


        List<CasillaMapaEventHandler> casillaMapaEventHandlerList = new ArrayList<>();
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {

                int coordenadaX = x;
                int coordenaday = y;

                Button casillaMapa = new Button();
                casillaMapa.setPrefHeight(50);
                casillaMapa.setPrefWidth(50);
                casillaMapa.setAlignment(Pos.CENTER);

                Parcela parcelaActual = mapa.get(y).get(x);


                if (parcelaActual instanceof Tierra) {
                    casillaMapa.setStyle("-fx-background-color: #699922;");
                }
                if (parcelaActual instanceof Largada) {
                    casillaMapa.setStyle("-fx-background-color: #599ed4;");
                }
                if (parcelaActual instanceof Meta) {
                    casillaMapa.setStyle("-fx-background-color: #FFA500;");
                }
                if (parcelaActual instanceof Rocoso) {
                    casillaMapa.setStyle("-fx-background-color: #38393b;");
                }
                if (parcelaActual instanceof Pasarela) {
                    casillaMapa.setStyle("-fx-background-color: #d1b680;");
                }


                CasillaMapaEventHandler casillaMapaEventHandler = new CasillaMapaEventHandler(parcelaActual, root, coordenadaX,
                        coordenaday, partida, sonidoPonerTorre, sonidoPonerTrampa, sonidoError, botonSkipEventHandler, bordesDefensas);
                casillaMapaEventHandlerList.add(casillaMapaEventHandler);
                casillaMapa.setOnAction(casillaMapaEventHandler);

                casillaMapa.setOnMouseEntered(event -> bordesDefensas.mostrarEnemigos(coordenadaX, coordenaday));
                casillaMapa.setOnMouseExited(event -> bordesDefensas.sacarEnemigos());

                root.add(casillaMapa, x, y);

            }
        }

        sliderMusica = new Slider(0, 100, 50);
        controladorSonidos.setSliderMusica(sliderMusica);
        sliderMusica.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(sliderMusica.getValue() / 100);
        });

        sliderSonidos = new Slider(0, 100, 50);
        controladorSonidos.setSliderSonidos(sliderSonidos);
        sliderSonidos.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (Object sonido : sonidos) {
                AudioClip sonidoAux2 = (AudioClip)sonido;
                sonidoAux2.setVolume(sliderSonidos.getValue() / 100);
            }
        });

        seccionVolumen.getChildren().addAll(volumenMusica, sliderMusica, volumenSonidos, sliderSonidos);

        datosUsuario.setSpacing(10);
        datosUsuario.setPadding(new Insets(10));
        datosUsuario.prefWidthProperty().bind(seccionVolumen.widthProperty());

        datosUsuario.setBackground(background);

        datosUsuario.setBorder(new Border(
                new BorderStroke(bordeClaro, BorderStrokeStyle.SOLID,
                        new CornerRadii(6), new BorderWidths(borderWidth))));


        BackgroundFill backgroundFillAzul = new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(8), Insets.EMPTY);
        Background backgroundAzul = new Background(backgroundFillAzul);

        Button botonPlateada = new Button();

        HBox plateada = new HBox();
        VBox precioPlat = new VBox();
        HBox precioPlat2 = new HBox();

        ImageView imagenMonedaView1 = new ImageView(imagenMoneda);
        ImageView imageviewTorrePlateada = new ImageView(imagenTorrePlateada);
        Label torrePlateadaTexto = new Label("Torre Plateada");
        Label precioPlateadaTexto = new Label("Costo: 25 ");

        precioPlat2.getChildren().addAll(precioPlateadaTexto, imagenMonedaView1);
 
        precioPlat.getChildren().addAll(torrePlateadaTexto, precioPlat2);
        plateada.getChildren().addAll(imageviewTorrePlateada, precioPlat);
        botonPlateada.setGraphic(plateada);

        botonPlateada.prefWidthProperty().bind(datosUsuario.widthProperty());
        botonPlateada.setMinWidth(datosUsuario.getMinWidth());


        BotonPlateadaEventHandler botonPlateadaEventHandler = new BotonPlateadaEventHandler(bordesDefensas, jugador,
                casillaMapaEventHandlerList, sonidoClick, sonidoError);
        botonPlateada.setOnAction(botonPlateadaEventHandler);

        botonPlateada.setBackground(backgroundAzul);
        botonPlateada.setBorder(new Border(
                new BorderStroke(bordeAzul, BorderStrokeStyle.SOLID,
                        new CornerRadii(6), new BorderWidths(borderWidth))));

        Button botonBlanca = new Button();

        HBox blanca = new HBox();
        VBox precioBlanca = new VBox();
        HBox precioBlanca2 = new HBox();

        ImageView imagenMonedaView2 = new ImageView(imagenMoneda);
        ImageView imageviewTorreBlanca = new ImageView(imagenTorreBlanca);
        Label torreBlancaTexto = new Label("Torre Blanca");
        Label precioBlancaTexto = new Label("Costo: 10 ");

        precioBlanca2.getChildren().addAll(precioBlancaTexto, imagenMonedaView2);

        precioBlanca.getChildren().addAll(torreBlancaTexto, precioBlanca2);
        blanca.getChildren().addAll(imageviewTorreBlanca, precioBlanca);
        botonBlanca.setGraphic(blanca);

        botonBlanca.prefWidthProperty().bind(datosUsuario.widthProperty());

        BotonBlancaEventHandler botonBlancaEventHandler = new BotonBlancaEventHandler(bordesDefensas, jugador,
                casillaMapaEventHandlerList, sonidoClick, sonidoError);
        botonBlanca.setOnAction(botonBlancaEventHandler);
        botonBlanca.setBackground(backgroundAzul);
        botonBlanca.setBorder(new Border(
                new BorderStroke(bordeAzul, BorderStrokeStyle.SOLID,
                        new CornerRadii(6), new BorderWidths(borderWidth))));

        Button botonTrampa = new Button();
            
        HBox arena = new HBox();
        VBox precioArena = new VBox();
        HBox precioArena2 = new HBox();

        ImageView imagenMonedaView3 = new ImageView(imagenMoneda);
        ImageView imageviewTrampaArena = new ImageView(imagenTrampaArenosa);
        Label trampaArenosaTexto = new Label("Trampa Arenosa");
        Label precioTrampaTexto = new Label("Costo: 25 ");

        precioArena2.getChildren().addAll(precioTrampaTexto, imagenMonedaView3);
        
        precioArena.getChildren().addAll(trampaArenosaTexto, precioArena2);
        arena.getChildren().addAll(imageviewTrampaArena, precioArena);
        botonTrampa.setGraphic(arena);

        BotonArenosaEventHandler botonArenosaEventHandler = new BotonArenosaEventHandler(bordesDefensas, jugador,
                casillaMapaEventHandlerList, sonidoClick, sonidoError);
        botonTrampa.setOnAction(botonArenosaEventHandler);

        botonTrampa.setBackground(backgroundAzul);
        botonTrampa.setBorder(new Border(
                new BorderStroke(bordeAzul, BorderStrokeStyle.SOLID,
                        new CornerRadii(6), new BorderWidths(borderWidth))));

        Button botonSkipTurno = new Button();
        botonSkipTurno.setText("Skip Turno");
        botonSkipTurno.prefWidthProperty().bind(botonTrampa.widthProperty());


        botonSkipTurno.setOnAction(botonSkipEventHandler);

        botonSkipTurno.setBackground(backgroundAzul);
        botonSkipTurno.setBorder(new Border(
                new BorderStroke(bordeAzul, BorderStrokeStyle.SOLID,
                        new CornerRadii(6), new BorderWidths(borderWidth))));

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

        this.getChildren().addAll(backgroundImageView, seccionTotal);
    }
    

    public void actualizarNombre(){
        datosUsuario.update();
    }
}

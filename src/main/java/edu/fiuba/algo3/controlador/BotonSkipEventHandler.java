package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.vista.IntroMenu;
import edu.fiuba.algo3.vista.Vista;
import edu.fiuba.algo3.vista.VistaDefensas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class BotonSkipEventHandler implements EventHandler<ActionEvent> {

    AudioClip sonidoClick = new AudioClip(new File("src/main/resources/sound/click.mp3").toURI().toString());
    AudioClip sonidoGanar = new AudioClip(new File("src/main/resources/sound/win.mp3").toURI().toString());
    AudioClip sonidoPerder = new AudioClip(new File("src/main/resources/sound/fail.mp3").toURI().toString());
    AudioClip sonidoEnter = new AudioClip(new File("src/main/resources/sound/enter.mp3").toURI().toString());

    private List<Vista> vistas;
    private Partida partida;
    private Jugador jugador;
    private MediaPlayer mediaPlayer;
    private Stage  stage;
    public BotonSkipEventHandler(List<Vista> vistas, Partida partida, MediaPlayer mediaPlayer, Stage  stage){
        this.vistas = vistas;
        this.partida = partida;
        this.jugador = partida.obtenerJugador();
        this.mediaPlayer = mediaPlayer;
        this.stage = stage;
    }
    @Override
    public void handle(ActionEvent actionEvent){
        this.accion();
    }
    public void accion(){

        partida.avanzarTurno();
        sonidoClick.play();

        //ALERTA DE PERDIDA
        if (jugador.obtenerVidaRestante() <= 0 && jugador.obtenerVidaRestante() < 0) {
            sonidoPerder.play();
            StackPane pantallaFinalPerdida = new StackPane();
            ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/perdiste.png")).toURI().toString());
            ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/imagenPerder.png")).toURI().toString());
            backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
            backgroundImageView.fitHeightProperty().bind(stage.heightProperty());

            Button volverAJugarButton = new Button("Volver a jugar");
            volverAJugarButton.setStyle("-fx-background-color: #FFA500;");
            volverAJugarButton.setTranslateY(60);
            volverAJugarButton.setOnAction(event -> {
                sonidoEnter.play();
                IntroMenu intro = new IntroMenu();
                intro.crearUI(stage);
            });

            pantallaFinalPerdida.getChildren().addAll(backgroundImageView, logoAlgoDefense, volverAJugarButton);

            Scene escenaInicial = new Scene(pantallaFinalPerdida, 800, 600);
            stage.setTitle("Terminó la partida!");
            stage.setScene(escenaInicial);
            mediaPlayer.stop();
        }
        //ALERTA DE VICTORIA
        if (partida.obtenerEnemigos().size() == 0 && jugador.obtenerVidaRestante() > 0) {
            sonidoGanar.play();
            StackPane pantallaFinalGanar = new StackPane();
            ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/ganaste.png")).toURI().toString());
            ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/imagenGanar.png")).toURI().toString());
            backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
            backgroundImageView.fitHeightProperty().bind(stage.heightProperty());

            Button volverAJugarButton = new Button("Volver a jugar");
            volverAJugarButton.setStyle("-fx-background-color: #FFA500;");
            volverAJugarButton.setTranslateY(60);
            volverAJugarButton.setOnAction(event -> {
                sonidoEnter.play();
                IntroMenu intro = new IntroMenu();
                intro.crearUI(stage);
            });

            pantallaFinalGanar.getChildren().addAll(backgroundImageView, logoAlgoDefense, volverAJugarButton);

            Scene escenaInicial = new Scene(pantallaFinalGanar, 800, 600);
            stage.setTitle("Terminó la partida!");
            stage.setScene(escenaInicial);
            mediaPlayer.stop();
        }
        for (Vista vista: vistas) {
            vista.update();
        }
    }
    public void agregarDefensa(VistaDefensas vistaDefensa){
        vistas.add(vistaDefensa);
        this.accion();
    }

}



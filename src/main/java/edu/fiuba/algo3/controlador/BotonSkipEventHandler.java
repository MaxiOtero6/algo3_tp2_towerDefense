package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.vista.EscenaFinalDePartida;
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

    private AudioClip sonidoClick;
    private AudioClip sonidoGanar;
    private AudioClip sonidoPerder;

    private List<Vista> vistas;
    private Partida partida;
    private Jugador jugador;
    private MediaPlayer mediaPlayer;
    private Stage  stage;
    public BotonSkipEventHandler(List<Vista> vistas, Partida partida, MediaPlayer mediaPlayer, Stage  stage,
                                 AudioClip sonidoClick, AudioClip sonidoGanar, AudioClip sonidoPerder){
        this.vistas = vistas;
        this.partida = partida;
        this.jugador = partida.obtenerJugador();
        this.mediaPlayer = mediaPlayer;
        this.stage = stage;
        this.sonidoClick = sonidoClick;
        this.sonidoGanar = sonidoGanar;
        this.sonidoPerder = sonidoPerder;
    }
    @Override
    public void handle(ActionEvent actionEvent){
        this.accion();
    }
    public void accion(){

        partida.avanzarTurno();
        sonidoClick.play();

        //ALERTA DE PERDIDA
        if (jugador.obtenerVidaRestante() <= 0) {
            sonidoPerder.play();

            ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/perdiste.png")).toURI().toString());
            ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/imagenPerder.png")).toURI().toString());
            EscenaFinalDePartida escenaFinalDePartida = new EscenaFinalDePartida(backgroundImageView,stage,
                    logoAlgoDefense);

            Scene escenaFinal = new Scene(escenaFinalDePartida, 800, 600);
            stage.setTitle("Terminó la partida!");
            stage.setScene(escenaFinal);
            mediaPlayer.stop();
        }
        //ALERTA DE VICTORIA
        if (partida.obtenerEnemigos().size() == 0) {
            sonidoGanar.play();
            ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/ganaste.png")).toURI().toString());
            ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/imagenGanar.png")).toURI().toString());
            EscenaFinalDePartida escenaFinalDePartida = new EscenaFinalDePartida(backgroundImageView,stage,
                    logoAlgoDefense);
            Scene escenaInicial = new Scene(escenaFinalDePartida, 800, 600);
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



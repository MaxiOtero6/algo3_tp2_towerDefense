package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.vista.ControladorSonidos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class BotonIniciarEventHandler implements EventHandler<ActionEvent> {
    AudioClip sonidoStart;
    private Stage stage;
    private Scene proximaEscena;
    private ControladorSonidos controladorSonidos;
    public BotonIniciarEventHandler(Scene proximaEscena, Stage stage, ControladorSonidos sonidos, AudioClip sonidoStart){
        this.stage = stage;
        this.proximaEscena = proximaEscena;
        this.controladorSonidos = sonidos;
        this.sonidoStart = sonidoStart;
    }

    @Override
    public void handle(ActionEvent actionEvent){
        sonidoStart.play();
        stage.setTitle("Jugando");
        controladorSonidos.actualizarSliders();
        stage.setScene(proximaEscena);
        stage.show();
    }
}

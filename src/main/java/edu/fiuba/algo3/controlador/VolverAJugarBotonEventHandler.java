package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.vista.IntroMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;

public class VolverAJugarBotonEventHandler implements EventHandler<ActionEvent> {

    private Stage stage;
    private AudioClip sonidoEnter = new AudioClip(new File("src/main/resources/sound/enter.mp3").toURI().toString());
    public VolverAJugarBotonEventHandler(Stage stage){
        this.stage = stage;
    }
    @Override
    public void handle(ActionEvent actionEvent){
        sonidoEnter.play();
        IntroMenu intro = new IntroMenu();
        intro.crearUI(stage);
    }
}

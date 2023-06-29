package edu.fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

public class VolverAJugarEventHandler implements EventHandler<ActionEvent> {

    private Button volverAJugarButton;
    private AudioClip sonido;

    public VolverAJugarEventHandler(AudioClip sonido, Button boton, VBox vBox){
        this.sonido = sonido;
        this.volverAJugarButton = boton;
    }

    @Override
    public void handle(ActionEvent actionEvent){
        sonido.play();
    }
}

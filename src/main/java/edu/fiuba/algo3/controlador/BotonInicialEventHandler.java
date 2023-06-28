package edu.fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

public class BotonInicialEventHandler implements EventHandler<ActionEvent> {

    private AudioClip sonido;
    private Button botonInicial;
    private Button botonOk;
    private TextField textoNombre;
    private VBox vBox;

    public BotonInicialEventHandler(AudioClip sonido, Button botonIniciar, Button botonOk, TextField textoNombre, VBox vBox){
        this.sonido = sonido;
        this.botonInicial = botonIniciar;
        this.botonOk = botonOk;
        this.textoNombre = textoNombre;
        this.vBox = vBox;
    }
    @Override
    public void handle(ActionEvent actionEvent){
        sonido.play();
        vBox.getChildren().remove(botonInicial);
        vBox.getChildren().addAll(textoNombre, botonOk);
    }
}

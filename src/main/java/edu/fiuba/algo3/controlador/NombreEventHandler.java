package edu.fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NombreEventHandler implements EventHandler<KeyEvent> {

    private Button botonOk;

    public NombreEventHandler(Button boton) {
        this.botonOk = boton;
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            Event.fireEvent(botonOk, new ActionEvent());
        }
    }
}

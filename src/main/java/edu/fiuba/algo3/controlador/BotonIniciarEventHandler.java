package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.vista.ControladorSonidos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BotonIniciarEventHandler implements EventHandler<ActionEvent> {

    private Stage stage;
    private Scene proximaEscena;
    private ControladorSonidos controladorSonidos;
    public BotonIniciarEventHandler(Scene proximaEscena, Stage stage, ControladorSonidos sonidos){
        this.stage = stage;
        this.proximaEscena = proximaEscena;
        this.controladorSonidos = sonidos;
    }

    @Override
    public void handle(ActionEvent actionEvent){

        stage.setTitle("Jugando");
        controladorSonidos.actualizarSliders();
        stage.setScene(proximaEscena);
        stage.show();
    }
}

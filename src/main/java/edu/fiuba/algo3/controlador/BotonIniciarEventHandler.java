package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.vista.ContenedorPartida;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BotonIniciarEventHandler implements EventHandler<ActionEvent> {

    private Stage stage;
    private Scene proximaEscena;
    private ContenedorPartida contenedorPartida;
    public BotonIniciarEventHandler(Scene proximaEscena, Stage stage, ContenedorPartida contenedorPartida){
        this.stage = stage;
        this.proximaEscena = proximaEscena;
        this.contenedorPartida = contenedorPartida;
    }

    @Override
    public void handle(ActionEvent actionEvent){

        stage.setTitle("Jugando");
        contenedorPartida.actualizarSliders();
        stage.setScene(proximaEscena);
        stage.show();
    }
}

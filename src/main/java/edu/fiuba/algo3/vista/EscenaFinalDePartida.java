package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.VolverAJugarBotonEventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.Stack;

public class EscenaFinalDePartida extends StackPane{

    public EscenaFinalDePartida(ImageView backgroundImageView, Stage stage, ImageView logo){


        backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stage.heightProperty());

        Button volverAJugarButton = new Button("Volver a jugar");
        volverAJugarButton.setStyle("-fx-background-color: #FFA500;");
        volverAJugarButton.setTranslateY(60);

        VolverAJugarBotonEventHandler volverAJugarBotonEventHandler = new VolverAJugarBotonEventHandler(stage);
        volverAJugarButton.setOnAction(volverAJugarBotonEventHandler);

        this.getChildren().addAll(backgroundImageView, logo, volverAJugarButton);

    }
}

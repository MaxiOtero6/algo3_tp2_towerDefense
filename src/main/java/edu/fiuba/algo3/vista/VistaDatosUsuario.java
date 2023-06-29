package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;

public class VistaDatosUsuario extends VBox implements Vista {

    private Label labelNombre;
    private Label labelVida;
    private Label labelCreditos;
    private Jugador jugador;
    private TextField textoNombre;
    public VistaDatosUsuario(VBox seccionVolumen, Color colorBorde, Jugador jugador, TextField textoNombre){
        this.jugador = jugador;
        this.textoNombre = textoNombre;

        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.prefWidthProperty().bind(seccionVolumen.widthProperty());

        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, new CornerRadii(8), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);

        this.setBorder(new Border(
                new BorderStroke(colorBorde, BorderStrokeStyle.SOLID,
                        new CornerRadii(6), new BorderWidths(2.0))));

        labelNombre = new Label();
        labelVida = new Label("Vida Restante: " + jugador.obtenerVidaRestante() + "/20");
        labelCreditos = new Label();

        HBox creditos = new HBox();
        String imagenMoneda = (new File("src/main/resources/image/coin.png")).toURI().toString();
        ImageView moneda = new ImageView(imagenMoneda);
        creditos.getChildren().addAll(labelCreditos, moneda);

        HBox vida = new HBox();
        String imagenCorazon = (new File("src/main/resources/image/corazon.png")).toURI().toString();
        ImageView corazon = new ImageView(imagenCorazon);
        vida.getChildren().addAll(labelVida, corazon);

        this.getChildren().addAll(labelNombre, vida, creditos);
    }
    public void update(){
        labelNombre.setText("Nombre: " + textoNombre.getText());
        labelVida.setText("Vida Restante: " + jugador.obtenerVidaRestante() + "/20 ");
        labelCreditos.setText("Creditos Restantes: " + jugador.obtenerCreditosRestantes() + " ");
    }
}

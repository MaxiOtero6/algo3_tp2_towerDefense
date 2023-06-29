package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class BordesDefensas {

    private String imagenHormiga = (new File("src/main/resources/image/hormiga.png")).toURI().toString();
    private String imagenArania = (new File("src/main/resources/image/arania.png")).toURI().toString();
    private String imagenTopo = (new File("src/main/resources/image/topo.png")).toURI().toString();
    private String imagenTopoEscondido = (new File("src/main/resources/image/topo_escondido.png")).toURI().toString();
    private String imagenLechuza = (new File("src/main/resources/image/lechuza.png")).toURI().toString();

    private GridPane root;
    private List<List<Parcela>> mapa;
    HBox enemigosEnParcela;
    public BordesDefensas(GridPane root, List<List<Parcela>> mapa){
        this.root = root;
        this.mapa = mapa;

        enemigosEnParcela = new HBox();
        enemigosEnParcela.setSpacing(10);
        enemigosEnParcela.setPadding(new Insets(10));
        enemigosEnParcela.setAlignment(Pos.CENTER);
        enemigosEnParcela.setPrefHeight(75);

        BackgroundFill backgroundFillEnemigos = new BackgroundFill(Color.ORANGE, new CornerRadii(8), Insets.EMPTY);
        Background backgroundEnemigos = new Background(backgroundFillEnemigos);
        enemigosEnParcela.setBackground(backgroundEnemigos);

        enemigosEnParcela.setBorder(new Border(
                new BorderStroke(Color.BLANCHEDALMOND, BorderStrokeStyle.SOLID,
                        new CornerRadii(6), new BorderWidths(2.0))));
    }
    public void activarBordesTorres() {

        Color bordeRojo = Color.RED;
        Color borderVerde = Color.GREENYELLOW;
        double borderWidth = 2.0;

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {

                Parcela parcelaActual = mapa.get(y).get(x);
                if (!(parcelaActual instanceof Tierra)) {
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button) botonActual;

                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeRojo, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                            break;
                        }
                    }
                } else {
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button) botonActual;

                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(borderVerde, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                            break;
                        }
                    }
                }
            }
        }
    }
    public void activarBordesTrampaArena() {

        Color bordeRojo = Color.RED;
        Color borderVerde = Color.GREENYELLOW;
        double borderWidth = 2.0;

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {

                Parcela parcelaActual = mapa.get(y).get(x);
                if (!(parcelaActual instanceof Pasarela)) {
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button) botonActual;

                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeRojo, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                            break;
                        }
                    }
                } else {
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button) botonActual;

                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(borderVerde, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                            break;
                        }
                    }
                }
            }
        }
    }
    public void activarBotones() {
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                int coordenadaX = x;
                int coordenadaY = y;

                Parcela parcelaActual = mapa.get(y).get(x);
                if ((parcelaActual != null)) {
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button) botonActual;
                            boton.setBorder(null);
                            boton.setOnMouseEntered(event -> mostrarEnemigos(enemigosEnParcela, coordenadaX, coordenadaY));
                            boton.setOnMouseExited(event -> sacarEnemigos(enemigosEnParcela));

                            break;
                        }
                    }
                }
            }
        }
    }

    private void mostrarEnemigos(HBox enemigosDeParcela, int x, int y) {
        Parcela parcelaActual = mapa.get(y).get(x);
        LinkedList<Enemigo> enemigosEnLaParcela = parcelaActual.devolverEnemigos();
        for (Enemigo enemigo : enemigosEnLaParcela) {
            if (enemigo instanceof Arania) {
                enemigosDeParcela.getChildren().add(new ImageView(imagenArania));
            }
            if (enemigo instanceof Hormiga) {
                enemigosDeParcela.getChildren().add(new ImageView(imagenHormiga));
            }
            if (enemigo instanceof Lechuza) {
                enemigosDeParcela.getChildren().add(new ImageView(imagenLechuza));
            }
            if (enemigo instanceof Topo) {
                Topo topoAux = (Topo) enemigo;
                if (topoAux.esSubterraneo()) {
                    enemigosDeParcela.getChildren().add(new ImageView(imagenTopoEscondido));
                } else {
                    enemigosDeParcela.getChildren().add(new ImageView(imagenTopo));
                }
            }
        }
    }

    private void sacarEnemigos(HBox enemigosDeParcela) {
        enemigosDeParcela.getChildren().clear();
    }
}

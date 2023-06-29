package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.List;

public class BordesDefensas {

    private GridPane root;
    private List<List<Parcela>> mapa;
    public BordesDefensas(GridPane root, List<List<Parcela>> mapa){
        this.root = root;
        this.mapa = mapa;
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
}

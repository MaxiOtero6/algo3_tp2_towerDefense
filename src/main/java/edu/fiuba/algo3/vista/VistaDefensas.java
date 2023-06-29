package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Salud.Destruida;
import edu.fiuba.algo3.modelo.Defensas.Salud.Operativa;
import edu.fiuba.algo3.modelo.Defensas.Torres.EstadoDesactivado;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorrePlateada;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class VistaDefensas implements Vista{

    private String imagenTorreBlanca = (new File("src/main/resources/image/torreBlanca.png")).toURI().toString();
    private String imagenTorrePlateada = (new File("src/main/resources/image/torrePlateada.png")).toURI().toString();
    private String imagenTrampaArenosa = (new File("src/main/resources/image/trampaArenosa.png")).toURI().toString();
    private String imagenTorrePlateadaEnConstruccion = (new File("src/main/resources/image/TpConstruccion.png")).toURI().toString();
    private String imagenTorrePlateadaDestruida = (new File("src/main/resources/image/TpDestruida.png")).toURI().toString();
    private String imagenTorreBlancaEnConstruccion = (new File("src/main/resources/image/TbConstruccion.png")).toURI().toString();
    private String imagenTrampaArenosaDestruida = (new File("src/main/resources/image/TaDestruida.png")).toURI().toString();
    private String imagenTorreBlancaDestruida = (new File("src/main/resources/image/TbDestruida.png")).toURI().toString();
    private Defensa defensa;
    private GridPane grid;
    private int coordenadaX;
    private int coordenadaY;

    public VistaDefensas(GridPane grid, Defensa defensa){
        this.defensa = defensa;
        this.grid = grid;
        this.coordenadaX = defensa.getPosicion().obtenerCoordenadaX();
        this.coordenadaY = defensa.getPosicion().obtenerCoordenadaY();
    }
    public void dibujar(){
        this.clean();
        grid.add(seleccionarImagenDefensa(), coordenadaX, coordenadaY);
    }
    private ImageView seleccionarImagenDefensa(){
        if(defensa instanceof TorrePlateada) {

            return seleccionarImagenEstadoPlateada((TorrePlateada) defensa);
        } else if(defensa instanceof TorreBlanca) {

            return seleccionarImagenEstadoBlanca((TorreBlanca) defensa);
        } else if(defensa instanceof TrampaArenosa) {

            return seleccionarImagenEstadoArenosa((TrampaArenosa) defensa);
        } else {
            return null;
        }
    }
    private ImageView seleccionarImagenEstadoBlanca(TorreBlanca torre){
        if (torre.getSalud() instanceof Destruida){
            return new ImageView(imagenTorreBlancaDestruida);
        } else if (torre.getEstado() instanceof EstadoDesactivado) {
            return new ImageView(imagenTorreBlancaEnConstruccion);
        } else {
            return new ImageView(imagenTorreBlanca);
        }
    }
    private ImageView seleccionarImagenEstadoPlateada(TorrePlateada torre){
        if (torre.getSalud() instanceof Destruida){
            return new ImageView(imagenTorrePlateadaDestruida);
        } else if (torre.getEstado() instanceof EstadoDesactivado) {
            return new ImageView(imagenTorrePlateadaEnConstruccion);
        } else {
            return new ImageView(imagenTorrePlateada);
        }
    }
    private ImageView seleccionarImagenEstadoArenosa(TrampaArenosa trampa){
        if (trampa.getSalud() instanceof Destruida){
            return new ImageView(imagenTrampaArenosaDestruida);
        } else {
            return new ImageView(imagenTrampaArenosa);
        }
    }
    private void clean() {
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == coordenadaX && GridPane.getRowIndex(node) == coordenadaY) {
                if (node instanceof ImageView) {
                    ImageView imageView = (ImageView) node;
                    Image image = imageView.getImage();
                    String imageUrl = image.getUrl();
                    if (imageUrl.equals(imagenTorreBlanca) ||
                            imageUrl.equals(imagenTorreBlancaDestruida) ||
                            imageUrl.equals(imagenTorreBlancaEnConstruccion) ||
                            imageUrl.equals(imagenTorrePlateadaDestruida) ||
                            imageUrl.equals(imagenTorrePlateada) ||
                            imageUrl.equals(imagenTorrePlateadaEnConstruccion) ||
                            imageUrl.equals(imagenTrampaArenosa) ||
                            imageUrl.equals(imagenTrampaArenosaDestruida)) {
                        nodesToRemove.add(node);
                    }
                }
            }
        }
        grid.getChildren().removeAll(nodesToRemove);
    }
    public void update(){
        this.dibujar();
    }
}

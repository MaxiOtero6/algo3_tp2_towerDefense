package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Partida;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VistaEnemigos implements Vista{

    private Enemigo enemigo;
    private GridPane root;
    private Partida partida;

    private String imagenHormiga = (new File("src/main/resources/image/hormiga.png")).toURI().toString();
    private String imagenArania = (new File("src/main/resources/image/arania.png")).toURI().toString();
    private String imagenTopo = (new File("src/main/resources/image/topo.png")).toURI().toString();
    private String imagenTopoEscondido = (new File("src/main/resources/image/topo_escondido.png")).toURI().toString();
    private String imagenLechuza = (new File("src/main/resources/image/lechuza.png")).toURI().toString();

    public VistaEnemigos(GridPane root, Partida partida){
        this.root = root;
        this.partida = partida;
    }
    public void dibujar() {
        for (int i = 0; i < partida.obtenerEnemigos().size(); i++) {
            Enemigo enemigoActual = partida.obtenerEnemigos().get(i);
            int coordenadaX = enemigoActual.obtenerPosicion().obtenerCoordenadaX();
            int coordenadaY = enemigoActual.obtenerPosicion().obtenerCoordenadaY();
            if (enemigoActual instanceof Hormiga) {
                root.add(new ImageView(imagenHormiga), coordenadaX, coordenadaY);
            } else if (enemigoActual instanceof Arania) {
                root.add(new ImageView(imagenArania), coordenadaX, coordenadaY);
            } else if (enemigoActual instanceof Topo) {
                Topo topo = (Topo) enemigoActual;
                if (topo.esSubterraneo()) {
                    root.add(new ImageView(imagenTopoEscondido), coordenadaX, coordenadaY);
                } else {
                    root.add(new ImageView(imagenTopo), coordenadaX, coordenadaY);
                }

            } else if (enemigoActual instanceof Lechuza) {
                root.add(new ImageView(imagenLechuza), coordenadaX, coordenadaY);
            }

        }
    }
    public void update(){
        this.clean();
        this.dibujar();
    }
    private void clean(){
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : root.getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                Image image = imageView.getImage();
                String imageUrl = image.getUrl();
                if (imageUrl.equals(imagenHormiga) ||
                        imageUrl.equals(imagenArania) ||
                        imageUrl.equals(imagenTopo) ||
                        imageUrl.equals(imagenLechuza) ||
                        imageUrl.equals(imagenTopoEscondido)) {
                    nodesToRemove.add(node);
                }
            }
        }
        root.getChildren().removeAll(nodesToRemove);
    }
}

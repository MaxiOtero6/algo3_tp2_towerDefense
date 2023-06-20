package edu.fiuba.algo3;

import edu.fiuba.algo3.vista.IntroMenu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        IntroMenu intro = new IntroMenu();
        intro.crearUI(primaryStage);
        
        primaryStage.setTitle("AlgoDefense");
        Image icon = new Image("file:src/main/resources/image/torrePlateada.png");
        primaryStage.getIcons().add(icon);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
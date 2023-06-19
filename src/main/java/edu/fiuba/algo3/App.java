package edu.fiuba.algo3;

import edu.fiuba.algo3.vista.IntroMenu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
        VBox root = intro.crearUI(primaryStage);
        root.setStyle("-fx-background-color: grey;");
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
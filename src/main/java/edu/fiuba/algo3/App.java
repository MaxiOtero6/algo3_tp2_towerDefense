package edu.fiuba.algo3;

import java.io.File;

import edu.fiuba.algo3.vista.IntroMenu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

/**
 * JavaFX App
 */
public class App extends Application {
    private static MediaPlayer mediaPlayer;
    @Override
    public void start(Stage primaryStage) {
            String musicFile = "src/main/resources/sound/music.wav";
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
            mediaPlayer.setVolume(0.3);

            mediaPlayer.play();
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

package scenes;

import characters.Tile;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.File;

import static constants.GlobalConstants.*;

public class Round2 {
    public static Scene getScene(Stage stage) {
        StackPane gameBackground = new StackPane();
        Tile mountain = new Tile("./resources/img/Round2_backGround.png");
        gameBackground.getChildren().add(mountain.getImageView());

        MediaPlayer Round2Music = new MediaPlayer(
                new Media(new File("./src/resources/music/Challengers.mp3").toURI().toString()));
        Round2Music.setCycleCount(MediaPlayer.INDEFINITE);
        Round2Music.setVolume(Round2Music.getVolume() / GAME_MUSIC);
        Round2Music.play();
        MediaView Round1mediaView = new MediaView(Round2Music);

        StackPane R2StackPane = new StackPane();
        R2StackPane.getChildren().addAll(Round1mediaView, gameBackground);
        return new Scene(R2StackPane, GAME_WIDTH, GAME_HEIGHT);
    }

    public static Path createPath() {
        Path path = new Path();
        // TO - DO
        return path;
    }
}

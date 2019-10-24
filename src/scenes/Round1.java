package scenes;

import characters.Tile;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

import static constants.GlobalConstants.GAME_HEIGHT;
import static constants.GlobalConstants.GAME_WIDTH;

public class Round1 {
    public static Scene getScene(Stage stage) {
        StackPane gameBackground = new StackPane();
        Tile mountain = new Tile("./resources/img/Round1_backGround.png");
        gameBackground.getChildren().add(mountain.getImageView());

        MediaPlayer Round1Music = new MediaPlayer(
                new Media(new File("./src/resources/music/Challengers.mp3").toURI().toString()));
        Round1Music.setCycleCount(MediaPlayer.INDEFINITE);
        Round1Music.setVolume(Round1Music.getVolume() / 2);
        Round1Music.play();
        MediaView Round1mediaView = new MediaView(Round1Music);

        gameBackground.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("(" + mouseEvent.getX() + "," + mouseEvent.getY() + ")");
            }
        });

        StackPane R1StackPane = new StackPane();
        R1StackPane.getChildren().addAll(Round1mediaView, gameBackground);


        return new Scene(R1StackPane, GAME_WIDTH, GAME_HEIGHT);
    }

}

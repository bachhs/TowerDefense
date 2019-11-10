package scenes;

import characters.Tile;
import characters.enemy.*;
import characters.turret.BlastMissileTurret;
import characters.turret.Turret;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.io.File;

import static constants.GlobalConstants.*;

public class Round1 {

    private static final String ROUND_BACKGROUND = "./resources/img/Round_backGround.png";
    private static final String ROUND1_WAY = "./resources/img/Round1_way.png";

    public static Scene getScene(Stage stage) {
        Tile gameBackground = new Tile(ROUND_BACKGROUND);
        Tile way = new Tile(ROUND1_WAY);

        MediaPlayer Round1Music = new MediaPlayer(new Media(new File(GAME_MUSIC_URL).toURI().toString()));
        Round1Music.setCycleCount(MediaPlayer.INDEFINITE);
        Round1Music.setVolume(Round1Music.getVolume() / GAME_MUSIC);
        Round1Music.play();
        MediaView Round1mediaView = new MediaView(Round1Music);

        StackPane R1StackPane = new StackPane();
        R1StackPane.getChildren().addAll(Round1mediaView, gameBackground.getImageView(), way.getImageView());
        Controller controller = new Controller(R1StackPane, createPath());
        controller.start();
        gameBackground.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                controller.addTurret(way.getImageView(), mouseEvent.getX(), mouseEvent.getY());
            }
        });


        return new Scene(R1StackPane, GAME_WIDTH, GAME_HEIGHT);
    }

    public static Path createPath() {
        Path path = new Path();
        MoveTo spawn = new MoveTo(-325.0, 500.0);
        CubicCurveTo cubicCurveTo = new CubicCurveTo(-325, 0, -320, 0, -240, -10);
        LineTo line = new LineTo(800.0, -10.0);
        path.getElements().addAll(spawn, cubicCurveTo, line);
        return path;
    }
}
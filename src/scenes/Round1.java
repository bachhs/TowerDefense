package scenes;

import characters.Tile;
import characters.enemy.Chaser;
import characters.enemy.Enemy;
import characters.enemy.HUNK;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.File;

import static constants.GlobalConstants.*;

public class Round1 {
    public static Scene getScene(Stage stage) {
        Enemy chaser = new Chaser("./resources/img/Chaser.png");
        Enemy hunk = new Chaser("./resources/img/Chaser.png");
        Path pt = createPath();

        StackPane gameBackground = new StackPane();
        Tile mountain = new Tile("./resources/img/Round1_backGround.png");
        gameBackground.getChildren().add(mountain.getImageView());

        MediaPlayer Round1Music = new MediaPlayer(
                new Media(new File("./src/resources/music/Challengers.mp3").toURI().toString()));
        Round1Music.setCycleCount(MediaPlayer.INDEFINITE);
        Round1Music.setVolume(Round1Music.getVolume() / GAME_MUSIC);
        Round1Music.play();
        MediaView Round1mediaView = new MediaView(Round1Music);

        gameBackground.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("(" + mouseEvent.getX() + "," + mouseEvent.getY() + ")");
            }
        });

        PathTransition move1 = new PathTransition(Duration.millis(GAME_SPEED / chaser.getSpeed()), pt,
                chaser.getImageView());
        PathTransition move2 = new PathTransition(Duration.millis(GAME_SPEED / hunk.getSpeed()), pt,
                hunk.getImageView());
        move1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        move2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        move1.setAutoReverse(false);
        move2.setAutoReverse(false);
        Enemy[] enemies = new Enemy[2];
        enemies[0] = chaser;
        enemies[1] = hunk;

        PathTransition[] moves = new PathTransition[] { move1, move2 };

        AnimationTimer h = new AnimationTimer() {
            int i = 0;

            private long lastUpdate = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                long elapsedSeconds = (System.currentTimeMillis() - lastUpdate) / 1000;
                if (elapsedSeconds == 1) {
                    lastUpdate = System.currentTimeMillis();
                    if (i < 2) {
                        moves[i++].play();
                    }

                }
            }
        };
        h.start();

        StackPane R1StackPane = new StackPane();
        R1StackPane.getChildren().addAll(Round1mediaView, gameBackground, chaser.getImageView(), hunk.getImageView());

        return new Scene(R1StackPane, GAME_WIDTH, GAME_HEIGHT);
    }

    public static Path createPath() {
        Path path = new Path();
        MoveTo spawn = new MoveTo(-325.0, 500.0);
        LineTo line1 = new LineTo(-325.0, -10.0);
        LineTo line2 = new LineTo(800.0, -10.0);
        path.getElements().addAll(spawn, line1, line2);
        return path;
    }
}

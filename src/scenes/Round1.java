package scenes;

import characters.Tile;
import characters.enemy.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.File;

import static constants.GlobalConstants.*;

public class Round1 {
    public static Scene getScene(Stage stage) {

        StackPane gameBackground = new StackPane();
        Tile mountain = new Tile("./resources/img/Round1_backGround.png");
        gameBackground.getChildren().add(mountain.getImageView());

        MediaPlayer Round1Music = new MediaPlayer(
                new Media(new File("./src/resources/music/Challengers.mp3").toURI().toString()));
        Round1Music.setCycleCount(MediaPlayer.INDEFINITE);
        Round1Music.setVolume(Round1Music.getVolume() / GAME_MUSIC);
        Round1Music.play();
        MediaView Round1mediaView = new MediaView(Round1Music);


        StackPane R1StackPane = new StackPane();
        R1StackPane.getChildren().addAll(Round1mediaView, gameBackground);

        ChaserWave chaserWave = new ChaserWave(new Chaser(), 3, R1StackPane, createPath());
        HUNKwave hunKwave = new HUNKwave(new Hunk(), 4, R1StackPane, createPath());
        MeatHarvesterWave meatHarvesterWave = new MeatHarvesterWave(new MeatHarvester(), 2, R1StackPane, createPath());
        PeaceEnvogWave peaceEnvogWave = new PeaceEnvogWave(new PeaceEnvog(), 3, R1StackPane, createPath());

        AnimationTimer animationTimer = new AnimationTimer() {
            long time = 0;

            @Override
            public void handle(long l) {
                if (time == 0) {
                    time = l;
                    System.out.println(l);
                    System.out.println(System.currentTimeMillis());
                    chaserWave.move();
                    return;
                }
                long delta = l - time;
                time = l;
                double dis = delta / 1.0e9;

                if (dis > 1000) {
                    hunKwave.move();
                    System.out.println(l);
                }
            }
        };
        animationTimer.start();

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

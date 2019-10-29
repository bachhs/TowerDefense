package scenes;

import characters.Tile;
import characters.enemy.Chaser;
import characters.enemy.Enemy;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

import static constants.GlobalConstants.*;

public class Round1 {
    public static Scene getScene(Stage stage) {
        // tạo enemy
        Enemy chaser = new Chaser("./resources/img/Chaser.png");
        Path chasermove = createPath();

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
        PauseTransition pause = new PauseTransition(Duration.millis(5000));
        PathTransition pt = new PathTransition(Duration.millis(10000), chasermove, chaser.getImageView();)
        pt.setCycleCount(Animation.INDEFINITE);
        pt.play();

        StackPane R1StackPane = new StackPane();
        R1StackPane.getChildren().addAll(Round1mediaView, gameBackground, chaser.getImageView());

        return new Scene(R1StackPane, GAME_WIDTH, GAME_HEIGHT);
    }

    public static Path createPath() {
        Path path = new Path();
        MoveTo spawn = new MoveTo(-325.0, 380.0);
        LineTo line1 = new LineTo(-325.0, 280.0);
        LineTo line2 = new LineTo(-325.0, 180.0);
        LineTo line3 = new LineTo(-325.0, -10.0);
        LineTo line4 = new LineTo(-100.0, -10.0);
        LineTo line5 = new LineTo(50.0, -10.0);
        LineTo line6 = new LineTo(200.0, -10.0);
        LineTo line7 = new LineTo(350.0, -10.0);
        LineTo line8 = new LineTo(500.0, -10.0);
        LineTo line9 = new LineTo(650.0, -10.0);
        LineTo line10 = new LineTo(800.0, -10.0);
        path.getElements().addAll(spawn, line1, line2, line3, line4, line5,
                line6, line7, line8, line9, line10);
        return path;
    }
}

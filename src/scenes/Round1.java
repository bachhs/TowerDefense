package scenes;

import characters.Tile;
import characters.enemy.Chaser;
import characters.enemy.Enemy;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
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
import java.util.ArrayList;

import static constants.GlobalConstants.*;

public class Round1 {
    public static Scene getScene(Stage stage) {
        // tạo enemy
        //Enemy chaser = new Chaser("./resources/img/Chaser.png");
        ArrayList<Enemy> listchaser = new ArrayList<>(5);
        ArrayList<Path> ChaserMove = new ArrayList<>(5);

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

        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        ArrayList<PathTransition> listpt = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listchaser.add(new Chaser("./resources/img/Chaser.png"));
            Path pt = createPath();
            ChaserMove.add(pt);
            listpt.add(new PathTransition(Duration.millis(GAME_SPEED/listchaser.get(i).getSpeed()), ChaserMove.get(i), listchaser.get(i).getImageView()));
            listpt.get(i).setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            listpt.get(i).setAutoReverse(false);
        }

        System.out.println(listchaser.size() + "," +listpt.size() + "+" + ChaserMove.size());
        listpt.get(0).play();
        pause.play();
        listpt.get(1).play();

        StackPane R1StackPane = new StackPane();
        R1StackPane.getChildren().addAll(Round1mediaView, gameBackground);
        for(int i =0; i< 5; i++) {
            R1StackPane.getChildren().add(listchaser.get(i).getImageView());
        }

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

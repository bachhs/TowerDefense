package scenes;

import characters.Tile;
import characters.enemy.Chaser;
import characters.enemy.Enemy;
<<<<<<< HEAD
import characters.enemy.HUNK;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
=======
>>>>>>> b9caa480477bc8d851a8a42ea78eac054b7fee14
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
<<<<<<< HEAD
        Enemy chaser = new Chaser("./resources/img/Chaser.png");
        Enemy chaser1 = new Chaser("./resources/img/Chaser.png");
        Enemy hunk = new HUNK("./resources/img/HUNK.png");
        Path pt = createPath();

=======
        // Enemy chaser = new Chaser("./resources/img/Chaser.png");
        ArrayList<Enemy> listchaser = new ArrayList<>(5);
        ArrayList<Path> ChaserMove = new ArrayList<>(5);
>>>>>>> b9caa480477bc8d851a8a42ea78eac054b7fee14

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

<<<<<<< HEAD

        PathTransition move1 = new PathTransition(Duration.millis(GAME_SPEED/chaser.getSpeed()), pt, chaser.getImageView());
        PathTransition move11 = new PathTransition(Duration.millis(GAME_SPEED/(chaser1.getSpeed())), pt, chaser1.getImageView());
        PathTransition move2 = new PathTransition(Duration.millis(GAME_SPEED/hunk.getSpeed()), pt, hunk.getImageView());
        move1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        move11.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        move2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        move1.setAutoReverse(false);
        move11.setAutoReverse(false);
        move2.setAutoReverse(false);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move1.play();
                move2.play();
            }
        };
        timer.start();
        timer.wait(Duration.seconds(1));
        move11.play();


        StackPane R1StackPane = new StackPane();
        R1StackPane.getChildren().addAll(Round1mediaView, gameBackground, chaser.getImageView(),chaser1.getImageView(),hunk.getImageView());
=======
        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        ArrayList<PathTransition> listpt = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listchaser.add(new Chaser("./resources/img/Chaser.png"));
            Path pt = createPath();
            ChaserMove.add(pt);
            listpt.add(new PathTransition(Duration.millis(GAME_SPEED / listchaser.get(i).getSpeed()), ChaserMove.get(i),
                    listchaser.get(i).getImageView()));
            listpt.get(i).setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            listpt.get(i).setAutoReverse(false);
        }

        System.out.println(listchaser.size() + "," + listpt.size() + "+" + ChaserMove.size());
        listpt.get(0).play();
        pause.play();
        listpt.get(1).play();

        StackPane R1StackPane = new StackPane();
        R1StackPane.getChildren().addAll(Round1mediaView, gameBackground);
        for (int i = 0; i < 5; i++) {
            R1StackPane.getChildren().add(listchaser.get(i).getImageView());
        }
>>>>>>> b9caa480477bc8d851a8a42ea78eac054b7fee14

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

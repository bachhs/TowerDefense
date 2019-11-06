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
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.File;

import static constants.GlobalConstants.*;

public class Round1 {
    public static Scene getScene(Stage stage) {
        Tile gameBackground = new Tile("./resources/img/Round1_backGround.png");

        MediaPlayer Round1Music = new MediaPlayer(
                new Media(new File("./src/resources/music/Challengers.mp3").toURI().toString()));
        Round1Music.setCycleCount(MediaPlayer.INDEFINITE);
        Round1Music.setVolume(Round1Music.getVolume() / GAME_MUSIC);
        Round1Music.play();
        MediaView Round1mediaView = new MediaView(Round1Music);

        StackPane R1StackPane = new StackPane();
        R1StackPane.getChildren().addAll(Round1mediaView, gameBackground.getImageView());

        ChaserWave chaserWave = new ChaserWave(new Chaser(), 3, R1StackPane, createPath());
        HUNKwave hunKwave = new HUNKwave(new Hunk(), 4, R1StackPane, createPath());
        MeatHarvesterWave meatHarvesterWave = new MeatHarvesterWave(new MeatHarvester(), 2, R1StackPane, createPath());
        PeaceEnvogWave peaceEnvogWave = new PeaceEnvogWave(new PeaceEnvog(), 3, R1StackPane, createPath());

        Turret turret = new BlastMissileTurret();
        turret.setTranslateXY(-240, 65);
        /*R1StackPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                turret.setRotate(mouseEvent.getX() - BOUND_X, mouseEvent.getY() - BOUND_Y);
                System.out.println(mouseEvent.getX());
            }
        });*/


        chaserWave.move(turret);


        R1StackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(mouseEvent.getX() + "," + mouseEvent.getY());
            }
        });

        R1StackPane.getChildren().add(turret.getNode());
        turret.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Circle rangeCircle = new Circle(turret.getRange());
                rangeCircle.setTranslateX(-240);
                rangeCircle.setTranslateY(65);
                rangeCircle.setFill(Color.TRANSPARENT);
                rangeCircle.setStroke(Color.BLUE);
                R1StackPane.getChildren().add(rangeCircle);
            }
        });

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

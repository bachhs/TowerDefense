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

    private static final String ROUND_BACKGROUND = "./resources/img/Round1_backGround.png";

    public static Scene getScene(Stage stage) {
        Tile gameBackground = new Tile(ROUND_BACKGROUND);

        MediaPlayer Round1Music = new MediaPlayer(new Media(new File(GAME_MUSIC_URL).toURI().toString()));
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

        WaveManage waveManage = new WaveManage(new Chaser(), 3, new Hunk(), 4, new MeatHarvester(), 2, new PeaceEnvog(),
                1, R1StackPane, createPath());

        Turret turret = new BlastMissileTurret();
        turret.setTranslateXY(-240, 65);

        waveManage.move(turret);

        R1StackPane.getChildren().add(turret.getNode());

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

package scenes;

import characters.Tile;
import characters.turret.CannonTurret;
import characters.turret.DoubleMissileTurret;
import characters.turret.SnipMissileTurret;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

import static constants.GlobalConstants.*;

public class Round1 {

    private static final String ROUND_BACKGROUND = "./resources/img/Round2_backGround.png";
    private static final String ROUND1_WAY = "./resources/img/Round2_way.png";
    private static final int y_end = -198;

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
        Controller controller = new Controller(stage, R1StackPane, createPath(), y_end);
        controller.start();

        gameBackground.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent1) {
                double x = mouseEvent1.getX();
                double y = mouseEvent1.getY();


                VBox turretSelector = new VBox();
                turretSelector.setOpacity(0.8);
                turretSelector.setAlignment(Pos.TOP_RIGHT);
                VBox cannonTurret = new CannonTurret().getInfo();
                VBox doubleTurret = new DoubleMissileTurret().getInfo();
                VBox snipTurret = new SnipMissileTurret().getInfo();
                Button cancel = new Button("Cancel");
                cancel.setTranslateX(-80);
                cancel.setTranslateY(0);
                cancel.setCancelButton(true);
                cancel.setFont(Font.font(25));

                turretSelector.getChildren().addAll(cannonTurret, doubleTurret, snipTurret, cancel);
                R1StackPane.getChildren().add(turretSelector);
                cannonTurret.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        controller.addTurret(way.getImageView(), "CannonTurret", x, y);
                        R1StackPane.getChildren().remove(turretSelector);
                    }
                });
                doubleTurret.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        controller.addTurret(way.getImageView(), "DoubleMissileTurret", x, y);
                        R1StackPane.getChildren().remove(turretSelector);
                    }
                });
                snipTurret.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        controller.addTurret(way.getImageView(), "SnipMissileTurret", x, y);
                        R1StackPane.getChildren().remove(turretSelector);
                    }
                });
                cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        R1StackPane.getChildren().remove(turretSelector);
                        mouseEvent1.consume();
                    }
                });
            }
        });

        return new Scene(R1StackPane, GAME_WIDTH, GAME_HEIGHT);
    }

    public static Path createPath() {
        Path path = new Path();
        MoveTo spawn = new MoveTo(-800.0, 260.0);
        LineTo line = new LineTo(395.0, 260.0);
        LineTo line1 = new LineTo(395,68);
        LineTo line2 = new LineTo(-300,68);
        LineTo line3 = new LineTo(-300,-134);
        LineTo line4 = new LineTo(900,-134);
        path.getElements().addAll(spawn, line, line1, line2, line3, line4);
        return path;
    }
}
package scenes;

import static constants.GlobalConstants.GAME_HEIGHT;
import static constants.GlobalConstants.GAME_WIDTH;

import java.io.File;
import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

enum MainMenuOption {
    Start, Continue, Setting, Exit;
}

public class MainMenu {
    public static Scene getScene(Stage stage) {
        VBox menuOption = new VBox(10);
        menuOption.setPrefWidth(100);
        menuOption.setPrefHeight(35);
        menuOption.setAlignment(Pos.CENTER);

        MediaPlayer mainMusic = new MediaPlayer(
                new Media(new File("./src/resources/music/urf.mp3").toURI().toString()));
        mainMusic.setCycleCount(MediaPlayer.INDEFINITE);
        mainMusic.play();
        MediaView mediaView = new MediaView(mainMusic);

        Label label = new Label("");
        Image logo = new Image("./resources/img/Logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(200);
        logoView.setPreserveRatio(true);
        label.setGraphic(logoView);
        menuOption.getChildren().add(label);
        VBox.setMargin(label, new Insets(0, 0, 20, 0));

        Button[] button = new Button[MainMenuOption.values().length];
        for (int i = 0; i < button.length; i++) {
            button[i] = new Button(MainMenuOption.values()[i].toString());
            button[i].setMinHeight(menuOption.getPrefHeight());
            button[i].setMinWidth(menuOption.getPrefWidth());
            button[i].setFont(Font.loadFont("file:./src/resources/font/OETZTYP_.TTF", 40));
            button[i].setStyle("-fx-background-color:transparent; -fx-text-fill: #f4c20d");
            int finalI = i;
            button[i].setOnMouseEntered(new EventHandler<>() {

                @Override
                public void handle(MouseEvent t) {
                    button[finalI].setStyle("-fx-background-color:#e5f3ff; -fx-text-fill:#f4c20d ");
                }
            });

            int finalI1 = i;
            button[i].setOnMouseExited(new EventHandler<>() {

                @Override
                public void handle(MouseEvent t) {
                    button[finalI1].setStyle("-fx-background-color:transparent; -fx-text-fill: #f4c20d");
                }
            });
            menuOption.getChildren().add(button[i]);
        }

        /////////////////////////////////////////
        Button backbutton = new Button("Back");
        button[MainMenuOption.Setting.ordinal()].addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Slider volumeSlider = new Slider();
                        volumeSlider.setValue(mainMusic.getVolume() * 100);
                        volumeSlider.valueProperty().addListener(new InvalidationListener() {
                            @Override
                            public void invalidated(Observable observable) {
                                mainMusic.setVolume(volumeSlider.getValue() / 100);
                            }
                        });
                        Label label1 = new Label("Volume Bar");
                        label1.setFont(Font.loadFont("file:./src/resources/font/OETZTYP_.TTF", 60));
                        label1.setStyle("-fx-text-fill: #7FFF00;");
                        label1.setScaleX(1);
                        label1.setScaleY(2);
                        label1.setTranslateY(-110);

                        backbutton.setFont(Font.loadFont("file:./src/resources/font/OETZTYP_.TTF", 35));
                        backbutton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
                        backbutton.setOnMouseEntered(new EventHandler<>() {

                            @Override
                            public void handle(MouseEvent t) {
                                backbutton.setStyle("-fx-background-color:#FFF5EE; -fx-text-fill: #7FFF00 ");
                            }
                        });
                        backbutton.setOnMouseExited(new EventHandler<>() {

                            @Override
                            public void handle(MouseEvent t) {
                                backbutton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
                            }
                        });

                        backbutton.setTranslateX(-540);
                        backbutton.setTranslateY(-300);

                        StackPane stackPane = new StackPane();
                        stackPane.getChildren().addAll(new ImageView(new Image("./resources/img/MainBackground.jpg")),
                                label1, volumeSlider,backbutton);
                        Scene setwal = new Scene(stackPane, GAME_WIDTH, GAME_HEIGHT);
                        stage.setScene(setwal);
                    }
                });
        ////////////////////////////////////////////////////

        button[MainMenuOption.Exit.ordinal()].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit Tower Defense?",
                        ButtonType.NO, ButtonType.YES);
                alert.setHeaderText("");
                alert.initOwner(stage);
                alert.setTitle("Really Exit?");
                Optional<ButtonType> type = alert.showAndWait();
                if (type.isPresent() && type.get() == ButtonType.YES)
                    Platform.exit();

            }
        });

        Image background = new Image("./resources/img/MainBackground.jpg");
        ImageView imageView = new ImageView(background);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(GAME_WIDTH);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mediaView, imageView, menuOption);
        Scene scenemain = new Scene(stackPane, GAME_WIDTH, GAME_HEIGHT);

        backbutton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(scenemain);
            }
        });

        return  scenemain;
    }
}

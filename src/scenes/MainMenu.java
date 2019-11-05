package scenes;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

import static constants.GlobalConstants.*;

enum MainMenuOption {
    Start, Continue, Setting, Exit
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
        mainMusic.setVolume(mainMusic.getVolume() / MAIN_MUSIC);
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

        button[MainMenuOption.Start.ordinal()].addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        mainMusic.stop();
                        if (ROUND == 3)
                            stage.setScene(Round1.getScene(stage));
                        else if (ROUND == 2)
                            stage.setScene(Round2.getScene(stage));
                        else
                            stage.setScene(Round1.getScene(stage));

                    }
                });

        Button backButton = new Button("Back");
        button[MainMenuOption.Setting.ordinal()].addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Slider volumeSlider = new Slider();
                        volumeSlider.setValue(mainMusic.getVolume() * 100);
                        volumeSlider.setPrefWidth(500);
                        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
                        volumeSlider.setMinWidth(30);
                        volumeSlider.setTranslateX(250);
                        volumeSlider.setTranslateY(20);
                        volumeSlider.setShowTickLabels(true);
                        volumeSlider.setShowTickMarks(true);
                        volumeSlider.setStyle("-fx-background-color: yellow");
                        volumeSlider.valueProperty().addListener(new InvalidationListener() {
                            @Override
                            public void invalidated(Observable observable) {
                                mainMusic.setVolume(volumeSlider.getValue() / 100);
                                constants.GlobalConstants.update(ROUND, (int) (100 / volumeSlider.getValue()),
                                        GAME_MUSIC);
                            }
                        });
                        Label label1 = new Label("Volume Bar");
                        label1.setFont(Font.loadFont("file:./src/resources/font/OETZTYP_.TTF", 60));
                        label1.setStyle("-fx-text-fill: #7FFF00;");
                        label1.setScaleX(1);
                        label1.setScaleY(2);
                        label1.setTranslateX(-250);

                        backButton.setFont(Font.loadFont("file:./src/resources/font/OETZTYP_.TTF", 35));
                        backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
                        backButton.setOnMouseEntered(new EventHandler<>() {

                            @Override
                            public void handle(MouseEvent t) {
                                backButton.setStyle("-fx-background-color:#FFF5EE; -fx-text-fill: #7FFF00 ");
                            }
                        });
                        backButton.setOnMouseExited(new EventHandler<>() {

                            @Override
                            public void handle(MouseEvent t) {
                                backButton.setStyle("-fx-background-color:transparent; -fx-text-fill: #7FFF00");
                            }
                        });

                        backButton.setTranslateX(-540);
                        backButton.setTranslateY(-300);

                        StackPane stackPane = new StackPane();
                        stackPane.getChildren().addAll(new ImageView(new Image("./resources/img/MainBackground.jpg")),
                                label1, volumeSlider, backButton);
                        Scene setwal = new Scene(stackPane, GAME_WIDTH, GAME_HEIGHT);
                        stage.setScene(setwal);
                    }
                });

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

        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(scenemain);
            }
        });

        return scenemain;
    }
}

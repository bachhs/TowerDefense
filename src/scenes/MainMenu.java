package scenes;

import static constants.GlobalConstants.GAME_HEIGHT;
import static constants.GlobalConstants.GAME_WIDTH;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

enum MainMenuOption {
    Start, Continue, Setting, Exit;
}

public class MainMenu {
    public static Scene getScene(Stage stage) {
        VBox menuOption = new VBox(10);
        menuOption.setPrefWidth(100);
        menuOption.setPrefHeight(35);
        menuOption.setAlignment(Pos.CENTER);

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
        stackPane.getChildren().addAll(imageView, menuOption);
        return new Scene(stackPane, GAME_WIDTH, GAME_HEIGHT);
    }
}

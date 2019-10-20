package scenes;

import static constants.GlobalConstants.*;


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

import java.util.Optional;

enum MainMenuOption {
    Start, Continue, Setting, Exit;
}

public class MainMenu {
    public static Scene getScene() {
        VBox menuOption = new VBox(10);
        menuOption.setPrefWidth(100);
        menuOption.setPrefHeight(50);
        menuOption.setAlignment(Pos.CENTER);

        Label label = new Label("TOWER DEFENSE PRO");
        label.setFont(new Font(40));
        label.setTextFill(Color.DARKORANGE);
        menuOption.getChildren().add(label);
        VBox.setMargin(label, new Insets(0, 00, 100, 0));

        Button[] button = new Button[MainMenuOption.values().length];
        for (int i = 0; i < button.length; i++) {
            button[i] = new Button(MainMenuOption.values()[i].toString());
            button[i].setMinHeight(menuOption.getPrefHeight());
            button[i].setMinWidth(menuOption.getPrefWidth());
            menuOption.getChildren().add(button[i]);
        }

        button[MainMenuOption.Exit.ordinal()].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit Tower Defense?",
                        ButtonType.NO, ButtonType.YES);
                alert.setHeaderText("");
                alert.setTitle("Really Exit?");
                Optional<ButtonType> type = alert.showAndWait();
                if (type.isPresent() && type.get() == ButtonType.YES)
                    Platform.exit();

            }
        });

        javafx.scene.image.Image background = new Image("./resources/img/MainBackground.jpg");
        ImageView imageView = new ImageView(background);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(GAME_WIDTH);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, menuOption);

        return new Scene(stackPane, GAME_WIDTH, GAME_HEIGHT);
    }
}

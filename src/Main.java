import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 720;

    private static boolean exitCheck(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit Tower Defense?",
                ButtonType.NO, ButtonType.YES);
        alert.setHeaderText("");
        alert.setTitle("Really Exit?");
        alert.initOwner(stage);
        Optional<ButtonType> type = alert.showAndWait();
        return type.isPresent() && type.get() == ButtonType.YES;
    }

    public static boolean close(Stage stage) {
        if (exitCheck(stage)) {
            Platform.exit();
            return true;
        }

        return false;
    }

    @Override
    public void start(Stage stage) {
        stage.setOnCloseRequest(event -> {
            if (!close(stage)) {
                event.consume();
            }
        });

        VBox menuOption = new VBox();
        menuOption.setPrefWidth(100);
        menuOption.setPrefHeight(50);
        Button startButton = new Button("Start");
        Button continueButton = new Button("Continue");
        Button settingButton = new Button("Setting");
        Button exitButton = new Button("Exit");

        startButton.setMinWidth(menuOption.getPrefWidth());
        continueButton.setMinWidth(menuOption.getPrefWidth());
        settingButton.setMinWidth(menuOption.getPrefWidth());
        exitButton.setMinWidth(menuOption.getPrefWidth());

        exitButton.setStyle("-fx-opacity: 0.1");

        startButton.setMinHeight(menuOption.getPrefHeight());
        continueButton.setMinHeight(menuOption.getPrefHeight());
        settingButton.setMinHeight(menuOption.getPrefHeight());
        exitButton.setMinHeight(menuOption.getPrefHeight());
        Label label = new Label("TOWER DEFENSE PRO");
        label.setFont(new Font(40));
        label.setTextFill(Color.DARKORANGE);

        menuOption.getChildren().addAll(label, startButton, continueButton, settingButton, exitButton);
        VBox.setMargin(label, new Insets(0,00,100,0));
        menuOption.setSpacing(10);
        menuOption.setAlignment(Pos.CENTER);

        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                close(stage);
            }
        });

        Image background = new Image("./resources/img/MainBackground.jpg");
        ImageView imageView = new ImageView(background);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(GAME_WIDTH);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, menuOption);

        Scene scene = new Scene(stackPane, GAME_WIDTH, GAME_HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
import java.io.InputStream;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

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

        startButton.setMinHeight(menuOption.getPrefHeight());
        continueButton.setMinHeight(menuOption.getPrefHeight());
        settingButton.setMinHeight(menuOption.getPrefHeight());
        exitButton.setMinHeight(menuOption.getPrefHeight());
        Label label = new Label("TOWER DEFENSE PRO");
        label.setFont(new Font(40));
        label.setTextFill(Color.DARKORANGE);

        menuOption.getChildren().addAll(label,startButton,continueButton,settingButton,exitButton);
        menuOption.setSpacing(10);
        menuOption.setAlignment(Pos.CENTER);

        InputStream is;
        Image backgroud = new Image("./resources/img/MainBackground.jpg");
        ImageView imageView = new ImageView(backgroud);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, menuOption);

        Scene scene = new Scene(stackPane, 600, 400);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}
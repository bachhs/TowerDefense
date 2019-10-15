package app;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    private static boolean exitCheck(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.NO, ButtonType.YES);
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
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300);
        scene.setFill(Color.GRAY);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
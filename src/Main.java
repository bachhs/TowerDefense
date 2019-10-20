import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;;
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

        stage.setScene(scenes.MainMenu.getScene());
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
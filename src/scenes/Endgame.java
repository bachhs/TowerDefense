package scenes;

import constants.GlobalConstants;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Endgame {

    public static Scene getLoseScene(Stage stage) {
        VBox victory = new VBox();

        Label youLose = new Label("You Lose!");
        youLose.setFont(Font.loadFont("file:./src/resources/font/COMIC.TTF", 60));
        youLose.setTextFill(Color.YELLOWGREEN);

        Button playAgain = new Button("Play again?");
        playAgain.setFont(Font.loadFont("file:./src/resources/font/COMIC.TTF", 20));
        playAgain.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(Round1.getScene(stage));
            }
        });

        Button back = new Button("Back to Main Menu");
        back.setFont(Font.loadFont("file:./src/resources/font/COMIC.TTF", 20));
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(MainMenu.getScene(stage));
            }
        });

        victory.getChildren().addAll(youLose, playAgain, back);
        victory.setSpacing(50);
        victory.setAlignment(Pos.CENTER);
        BackgroundImage sceneBackground = new BackgroundImage(new Image("./resources/img/MainBackground.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        victory.setBackground(new Background(sceneBackground));
        return new Scene(victory, GlobalConstants.GAME_WIDTH, GlobalConstants.GAME_HEIGHT);
    }

    public static Scene getVictoryScene(Stage stage) {
        VBox victory = new VBox();

        Label youWin = new Label("Victory!");
        youWin.setFont(Font.loadFont("file:./src/resources/font/COMIC.TTF", 60));
        youWin.setTextFill(Color.YELLOWGREEN);

        Button nextRound = new Button("Next Round");
        nextRound.setFont(Font.loadFont("file:./src/resources/font/COMIC.TTF", 20));
        nextRound.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(Round1.getScene(stage));
            }
        });

        Button back = new Button("Back to Main Menu");
        back.setFont(Font.loadFont("file:./src/resources/font/COMIC.TTF", 20));
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(MainMenu.getScene(stage));
            }
        });

        victory.getChildren().addAll(youWin, nextRound, back);
        victory.setSpacing(50);
        victory.setAlignment(Pos.CENTER);
        BackgroundImage sceneBackground = new BackgroundImage(new Image("./resources/img/MainBackground.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        victory.setBackground(new Background(sceneBackground));
        return new Scene(victory, GlobalConstants.GAME_WIDTH, GlobalConstants.GAME_HEIGHT);
    }
}

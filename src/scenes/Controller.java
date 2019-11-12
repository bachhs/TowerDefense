package scenes;

import characters.Nexus;
import characters.enemy.*;
import characters.turret.CannonTurret;
import characters.turret.Turret;
import constants.GlobalConstants;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Turret> turrets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private Nexus nexus = new Nexus();
    private Path path;
    private Pane pane;
    private Text text;
    private static final int enemyPerTurn = 20;

    public Controller(Pane pane, Path path) {
        for (int i = 0; i < 20; i++)
            enemies.add(new MeatHarvester());
        this.path = path;
        this.pane = pane;
    }

    public void addTurret(ImageView way, double x, double y) {
        Turret turret = new CannonTurret();
        turret.setTranslateXY(x - GlobalConstants.BOUND_X, y - GlobalConstants.BOUND_Y);
        pane.getChildren().add(turret.getNode(pane));
        turrets.add(turret);
    }

    public void start() {
        for (Enemy enemy : enemies)
            pane.getChildren().add(enemy.getHealthBar());
        text = setText();
        pane.getChildren().add(text);
        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        AnimationTimer gameLoop = new AnimationTimer() {
            long lastUpdate = System.currentTimeMillis();
            long lastShootUpdate = System.currentTimeMillis();
            int i = 0;

            @Override
            public void handle(long l) {
                long elapsedSeconds = (System.currentTimeMillis() - lastUpdate) / 1000;
                long elapsedShoot = (System.currentTimeMillis() - lastShootUpdate) / 1000;
                if (elapsedSeconds == 2) {
                    lastUpdate = System.currentTimeMillis();
                    if (i < enemyPerTurn && i < enemies.size()) {
                        enemies.get(i).move(pane, path);
                        i++;
                    }
                }

                for (Enemy enemy : enemies)
                    enemy.relocateHealthBar(enemy.getImageView().getTranslateX(), enemy.getImageView().getTranslateY());

                for (Turret turret : turrets)
                    turret.checkingEnemy(enemies);

                for (Turret turret : turrets) {
                    if (elapsedShoot == turret.getShootTime()) {
                        lastShootUpdate = System.currentTimeMillis();
                        turret.Shoot(turret.getTarget(enemies), pane);
                    }
                }
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).isDead()) {
                        nexus.addScore(enemies.get(i));

                        pane.getChildren().removeAll(enemies.get(i).getImageView(), enemies.get(i).getHealthBar());
                        enemies.remove(enemies.get(i));
                        i--;
                        pane.getChildren().remove(text);
                        text = setText();
                        pane.getChildren().add(text);
                    }
                }

                if(nexus.isLose()) {
                    pane.getChildren().add(nexus.lose());
                    this.stop();
                }

                //setFinish
                for (int i = 0; i < enemies.size(); i++) {
                    if (!enemies.get(i).isDead() && enemies.get(i).getImageView().getTranslateY() == -74 && enemies.get(i).getImageView().getTranslateX() > 640) {
                        nexus.decHealth(enemies.get(i));
                        pane.getChildren().removeAll(enemies.get(i).getImageView(), enemies.get(i).getHealthBar());
                        enemies.remove(enemies.get(i));
                        i--;
                        pane.getChildren().remove(text);
                        text = setText();
                        pane.getChildren().add(text);
                    }
                }
            }
        };
        gameLoop.start();
    }

    private void spawnEnemies(String enemyType) {
        switch (enemyType) {
        case "Chaser":
            enemies.add(new Chaser());
            break;
        case "Hunk":
            enemies.add(new Hunk());
            break;
        case "MeatHarvester":
            enemies.add(new MeatHarvester());
            break;
        case "PeaceEnvog":
            enemies.add(new PeaceEnvog());
            break;
        }
    }

    protected Text setText() {
        Text text = new Text("Health: " + nexus.getHealth() + "      " + nexus.getScore() + "$");
        text.setFont(Font.loadFont("file:./src/resources/font/CF.TTF", 50));
        text.setFill(Color.DARKRED);
        text.setTranslateX(-370);
        text.setTranslateY(-325);
        return text;
    }

}

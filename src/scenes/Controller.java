package scenes;

import characters.Nexus;
import characters.enemy.Chaser;
import characters.enemy.Enemy;
import characters.enemy.Hunk;
import characters.enemy.MeatHarvester;
import characters.turret.CannonTurret;
import characters.turret.DoubleMissileTurret;
import characters.turret.SnipMissileTurret;
import characters.turret.Turret;
import constants.GlobalConstants;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Turret> turrets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private Nexus nexus = new Nexus();
    private Path path;
    private Pane pane;
    private Label label = new Label();
    private Stage stage;

    public Controller(Stage stage, Pane pane, Path path) {
        this.stage = stage;
        this.pane = pane;
        this.path = path;
        spawnEnemies();
    }

    public void addTurret(ImageView way, String turretType, double x, double y) {
        Turret turret;
        switch (turretType) {
        case "DoubleMissileTurret":
            turret = new DoubleMissileTurret();
            break;
        case "SnipMissileTurret":
            turret = new SnipMissileTurret();
            break;
        default:
            turret = new CannonTurret();
        }
        if (nexus.getScore() >= turret.getScore()) {
            nexus.decreaseScore(turret.getScore());
            turret.setTranslateXY(x - GlobalConstants.BOUND_X, y - GlobalConstants.BOUND_Y);
            pane.getChildren().add(turret.getNode(nexus, pane, turrets, label));
            turrets.add(turret);
            label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
            System.out.println(turrets.size());
        }
    }

    public void start() {
        label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
        label.setFont(Font.loadFont("file:./src/resources/font/CF.TTF", 50));
        label.setTextFill(Color.DARKRED);
        label.setTranslateX(-370);
        label.setTranslateY(-325);
        pane.getChildren().add(label);
        for (Enemy enemy : enemies)
            pane.getChildren().add(enemy.getHealthBar());

        AnimationTimer gameLoop = new AnimationTimer() {
            long lastUpdate = System.currentTimeMillis();
            long lastCannonUpdate = System.currentTimeMillis();
            long lastDoubleUpdate = System.currentTimeMillis();
            long lastSnipUpdate = System.currentTimeMillis();
            int enemyCount = 0;

            @Override
            public void handle(long l) {
                long elapsedSeconds = (System.currentTimeMillis() - lastUpdate) / 1000;
                long elapsedCannon = (System.currentTimeMillis() - lastCannonUpdate) / 1000;
                long elapsedDouble = (System.currentTimeMillis() - lastDoubleUpdate) / 1000;
                long elapsedSnip = (System.currentTimeMillis() - lastSnipUpdate) / 1000;

                if (elapsedSeconds == 3) {
                    lastUpdate = System.currentTimeMillis();
                    if (enemyCount < enemies.size()) {
                        enemies.get(enemyCount).move(pane, path);
                        enemyCount++;
                    }
                }

                for (Enemy enemy : enemies) {
                    enemy.relocateHealthBar(enemy.getImageView().getTranslateX(), enemy.getImageView().getTranslateY());
                }

                for (Turret turret : turrets)
                    turret.checkingEnemy(enemies);

                for (int i = 0; i < turrets.size(); i++) {
                    if (turrets.get(i).getClass().toString().equals("class characters.turret.CannonTurret")
                            && elapsedCannon == turrets.get(i).getShootTime()) {
                        lastCannonUpdate = System.currentTimeMillis();
                        turrets.get(i).shoot(turrets.get(i).getTarget(enemies), pane);
                    }

                    if (turrets.get(i).getClass().toString().equals("class characters.turret.DoubleMissileTurret")
                            && elapsedDouble == turrets.get(i).getShootTime()) {
                        lastDoubleUpdate = System.currentTimeMillis();
                        turrets.get(i).shoot(turrets.get(i).getTarget(enemies), pane);
                    }

                    if (turrets.get(i).getClass().toString().equals("class characters.turret.SnipMissileTurret")
                            && elapsedSnip == turrets.get(i).getShootTime()) {
                        lastSnipUpdate = System.currentTimeMillis();
                        turrets.get(i).shoot(turrets.get(i).getTarget(enemies), pane);
                    }

                }

                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).isDead()) {
                        nexus.addScore(enemies.get(i).getScore());
                        pane.getChildren().removeAll(enemies.get(i).getImageView(), enemies.get(i).getHealthBar());
                        enemies.remove(enemies.get(i));
                        i--;
                        label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
                    } else if (enemies.get(i).getImageView().getTranslateY() == -74
                            && enemies.get(i).getImageView().getTranslateX() > 640) {
                        nexus.decreaseHP(enemies.get(i).getDamage());
                        pane.getChildren().removeAll(enemies.get(i).getImageView(), enemies.get(i).getHealthBar());
                        enemies.remove(enemies.get(i));
                        i--;
                        label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
                    }
                }
                if (nexus.isLose()) {
                    this.stop();
                    for (Node find : pane.getChildren())
                        if (find instanceof MediaView)
                            ((MediaView) find).getMediaPlayer().stop();
                    pane.getChildren().clear();
                    stage.setScene(Endgame.getLoseScene(stage));
                }
            }
        };
        gameLoop.start();
    }

    private void spawnEnemies() {
        for (int i = 0; i < 10; i++)
            enemies.add(new Chaser());
        for (int i = 0; i < 5; i++)
            enemies.add(new Hunk());
        for (int i = 0; i < 5; i++)
            enemies.add(new MeatHarvester());
        for (int i = 0; i < 2; i++)
            enemies.add(new Hunk());
    }
}

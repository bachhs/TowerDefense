package scenes;

import characters.Nexus;
import characters.enemy.*;
import characters.turret.CannonTurret;
import characters.turret.DoubleMissileTurret;
import characters.turret.SnipMissileTurret;
import characters.turret.Turret;
import constants.GlobalConstants;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
    private List<Turret> turrets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private Nexus nexus = new Nexus();
    private Path path;
    private Pane pane;
    private Label label = new Label();
    private Stage stage;
    private List<List<Enemy>> wave = new ArrayList<>(wave());
    private int maxWave = 10;
    private int currentWave = 0;
    private int y_end;

    public Controller(Stage stage, Pane pane, Path path, int y_end) {
        this.stage = stage;
        this.pane = pane;
        this.path = path;
        this.y_end = y_end;
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
        }
    }

    public void start() {
        label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
        label.setFont(Font.loadFont("file:./src/resources/font/CF.TTF", 50));
        label.setTextFill(Color.DARKRED);
        label.setTranslateX(-370);
        label.setTranslateY(-325);
        pane.getChildren().add(label);


        Button nextWave = new Button("Next Wave");
        nextWave.setFont(Font.font(20));
        nextWave.setTranslateX(0);
        nextWave.setTranslateY(-325);
        nextWave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (enemies.isEmpty()) {
                    spawnEnemies(wave, currentWave);
                    currentWave++;
                    nextWave.setText("Next Wave " + currentWave + "/" + maxWave);
                }

            }
        });
        pane.getChildren().add(nextWave);

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

                if (elapsedSeconds == 1) {
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

                if (elapsedCannon == 1) {
                    lastCannonUpdate = System.currentTimeMillis();
                    for (int i = 0; i < turrets.size(); i++) {
                        if (turrets.get(i).getClass().toString().equals("class characters.turret.CannonTurret")) {
                            turrets.get(i).shoot(turrets.get(i).getTarget(enemies), pane);
                        }
                    }
                }

                if (elapsedDouble == 2) {
                    lastDoubleUpdate = System.currentTimeMillis();
                    for (int i = 0; i < turrets.size(); i++) {
                        if (turrets.get(i).getClass().toString()
                                .equals("class characters.turret.DoubleMissileTurret")) {
                            turrets.get(i).shoot(turrets.get(i).getTarget(enemies), pane);
                        }
                    }
                }

                if (elapsedSnip == 3) {
                    lastSnipUpdate = System.currentTimeMillis();
                    for (int i = 0; i < turrets.size(); i++) {
                        if (turrets.get(i).getClass().toString().equals("class characters.turret.SnipMissileTurret")) {
                            turrets.get(i).shoot(turrets.get(i).getTarget(enemies), pane);
                        }
                    }
                }

                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).isDead()) {
                        nexus.addScore(enemies.get(i).getScore());
                        pane.getChildren().removeAll(enemies.get(i).getImageView(), enemies.get(i).getHealthBar());
                        enemies.remove(enemies.get(i));
                        i--;
                        enemyCount--;
                        label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
                    } else if (enemies.get(i).getImageView().getTranslateY() == y_end
                            && enemies.get(i).getImageView().getTranslateX() > 640) {
                        nexus.decreaseHP(enemies.get(i).getDamage());
                        pane.getChildren().removeAll(enemies.get(i).getImageView(), enemies.get(i).getHealthBar());
                        enemies.remove(enemies.get(i));
                        i--;
                        enemyCount--;
                        label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
                    }
                }
                if (currentWave == maxWave && enemies.isEmpty()) {
                    this.stop();
                    for (Node find : pane.getChildren())
                        if (find instanceof MediaView)
                            ((MediaView) find).getMediaPlayer().stop();
                    pane.getChildren().clear();
                    stage.setScene(Endgame.getVictoryScene(stage));
                }

                if (nexus.isLose()) {
                    this.stop();
                    for (Node find : pane.getChildren())
                        if (find instanceof MediaView)
                            ((MediaView) find).getMediaPlayer().stop();
                    pane.getChildren().clear();
                    stage.setScene(Endgame.getLoseScene(stage));
                }

                if(currentWave == 10 && enemies.isEmpty()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (enemies.isEmpty() && currentWave != 0) {
                    label.setTranslateX(-73);
                    label.setTranslateY(-325);
                    label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$" + "                    " + "Wave Completed");
                }
                else {
                    label.setTranslateX(-370);
                    label.setTranslateY(-325);
                    label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
                }
            }
        };

        gameLoop.start();
    }

    private void spawnEnemies(List<List<Enemy>> x, int a) {
        enemies.clear();
        enemies = x.get(a);

        for (Enemy enemy : enemies)
            pane.getChildren().add(enemy.getHealthBar());
    }


    private List<List<Enemy>> wave() {
        List<List<Enemy>> waveE = new ArrayList<>();


        List<Enemy> enemies1 = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            enemies1.add(new Chaser());
        waveE.add(enemies1);


        List<Enemy> enemies2 = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            enemies2.add(new Chaser());
        waveE.add(enemies2);


        List<Enemy> enemies3 = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            enemies3.add(new Chaser());
        enemies3.add(new MeatHarvester());
        for (int i = 0; i < 2; i++) {
            enemies3.add(new Chaser());
            enemies3.add(new Hunk());
        }
        waveE.add(enemies3);


        List<Enemy> enemies4 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            enemies4.add(new MeatHarvester());
            enemies4.add(new Hunk());
        }
        for (int i = 0; i < 3; i++) {
            enemies4.add(new Chaser());
            enemies4.add(new Hunk());
        }
        waveE.add(enemies4);


        List<Enemy> enemies5 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            enemies5.add(new Hunk());
            enemies5.add(new MeatHarvester());
        }
        enemies5.add(new PeaceEnvog());
        waveE.add(enemies5);


        List<Enemy> enemies6 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            enemies6.add(new MeatHarvester());
            enemies6.add(new Hunk());
        }
        waveE.add(enemies6);


        List<Enemy> enemies7 = new ArrayList<>();
        for (int i = 0; i < 13; i++)
            enemies7.add(new MeatHarvester());
        waveE.add(enemies7);


        List<Enemy> enemies8 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            enemies8.add(new MeatHarvester());
            enemies8.add(new Hunk());
        }
        for (int i = 0; i < 8; i++) {
            enemies8.add(new Chaser());
            enemies8.add(new Hunk());
        }
        waveE.add(enemies8);


        List<Enemy> enemies9 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            enemies9.add(new MeatHarvester());
            enemies9.add(new Hunk());
        }
        for (int i = 0; i < 2; i++) {
            enemies9.add(new Hunk());
            enemies9.add(new Chaser());
        }
        for (int i = 0; i < 4; i++)
            enemies9.add(new Chaser());
        for (int i = 0; i < 6; i++) {
            enemies9.add(new MeatHarvester());
            enemies9.add(new Hunk());
        }
        enemies9.add(new MeatHarvester());
        enemies9.add(new PeaceEnvog());
        waveE.add(enemies9);


        List<Enemy> enemies10 = new ArrayList<>();
       for(int i = 0; i < 10; i++) {
           enemies10.add(new MeatHarvester());
           enemies10.add(new Hunk());
       }
        for (int i = 0; i < 3; i++) {
            enemies10.add(new Hunk());
            enemies10.add(new MeatHarvester());
            enemies10.add(new PeaceEnvog());
        }
        waveE.add(enemies10);

        return waveE;
    }

}

package scenes;

import characters.enemy.Chaser;
import characters.enemy.Enemy;
import characters.enemy.Hunk;
import characters.enemy.MeatHarvester;
import characters.enemy.PeaceEnvog;
import characters.turret.BlastMissileTurret;
import characters.turret.Turret;
import constants.GlobalConstants;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Turret> turrets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private Path path;
    private Pane pane;
    private static final int enemyPerTurn = 6;

    public Controller(Pane pane, Path path) {
        for (int i = 0; i < 6; i++)
            enemies.add(new Chaser());
        this.path = path;
        this.pane = pane;
    }

    public void addTurret(ImageView way, double x, double y) {
        Turret turret = new BlastMissileTurret();
        turret.setTranslateXY(x - GlobalConstants.BOUND_X, y - GlobalConstants.BOUND_Y);
        if (!way.getBoundsInParent().intersects(turret.getImageView().getBoundsInParent())) {
            pane.getChildren().add(turret.getNode());
            turrets.add(turret);
        }
    }

    public void start() {
        for (Enemy enemy : enemies) pane.getChildren().add(enemy.getHealthBar());
        AnimationTimer gameLoop = new AnimationTimer() {
            long lastUpdate = System.currentTimeMillis();
            long lastShootUpdate = System.currentTimeMillis();
            int i = 0;

            @Override
            public void handle(long l) {
                long elapsedSeconds = (System.currentTimeMillis() - lastUpdate) / 1000;
                long elapsedShoot = (System.currentTimeMillis() - lastShootUpdate) / 1000;
                if (elapsedSeconds == 1) {
                    lastUpdate = System.currentTimeMillis();
                    if (i < enemyPerTurn && i < enemies.size()) {
                        enemies.get(i).move(pane, path);
                        i++;
                    }
                }
                for (Enemy enemy : enemies)
                    enemy.relocateHealthBar(enemy.getImageView().getTranslateX(),
                            enemy.getImageView().getTranslateY());

                for (Turret turret : turrets) turret.checkingEnemy(enemies);

                for (Turret turret : turrets) {
                    if (elapsedShoot == turret.getShootTime()) {
                        lastShootUpdate = System.currentTimeMillis();
                        turret.Shoot(turret.getTarget(enemies), pane);
                    }
                }
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).isDead()) {
                        pane.getChildren().removeAll(enemies.get(i).getImageView(), enemies.get(i).getHealthBar());

                        enemies.remove(enemies.get(i));
                        i--;
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

}

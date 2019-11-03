package characters.enemy;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import static constants.GlobalConstants.*;

import java.util.ArrayList;
import java.util.List;

public class ActionDemo {
    private long timeSinceLastSpawn, spawnTime;
    private Enemy enemyType;
    private int enemyPerWave;
    List<Enemy> enemies;

    public ActionDemo(Enemy enemyType, long spawnTime, int enemyPerWave) {
        this.spawnTime = spawnTime;
        this.enemyType = enemyType;
        this.enemyPerWave = enemyPerWave;
        enemies = new ArrayList<>();
        Render();
    }

    public void Render() {
        enemies.add(enemyType);
    }

    public void Spawn() {
        if(enemies.size() < enemyPerWave) {
            Render();
        }
    }

    public void Update(StackPane pane, Path pt) {
        AnimationTimer h = new AnimationTimer() {
            int i = 0;

            spawnTime = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                long elapsedSeconds = (System.currentTimeMillis() - lastUpdate) / 1000;
                if (elapsedSeconds == 1) {
                    lastUpdate = System.currentTimeMillis();
                        enemies.get(i++).move(pane, pt);
                }
            }
        };
        h.start();
    }

}

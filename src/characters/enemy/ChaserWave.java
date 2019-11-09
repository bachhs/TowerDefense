package characters.enemy;

import characters.turret.Turret;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;

import java.util.ArrayList;

public class ChaserWave {
    private Chaser enemyType;
    private int enemyPerWave;
    private StackPane pane;
    private Path path;

    private ArrayList<Enemy> enemies;

    public ChaserWave(Chaser enemyType, int enemyPerWave, StackPane pane, Path path) {
        this.enemyType = enemyType;
        this.enemyPerWave = enemyPerWave;
        this.pane = pane;
        this.path = path;
        enemies = new ArrayList<>();
        Update();
    }

    public void Render() {
        enemies.add(new Chaser());
    }

    public void Update() {
        enemies.add(enemyType);
        while (enemies.size() != enemyPerWave) {
            Render();
        }
    }

    public void move(Turret turret) {
        AnimationTimer h = new AnimationTimer() {
            int i = 0;
            int j = 1;
            private long lastUpdate = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                long elapsedSeconds = (System.currentTimeMillis() - lastUpdate) / 1000;
                if (elapsedSeconds == 3) {
                    lastUpdate = System.currentTimeMillis();
                    if (i < enemyPerWave) {
                        enemies.get(i++).move(pane, path);
                    }
                }
                    turret.setRotate(enemies.get(0));
            }
        };
        h.start();
    }



}

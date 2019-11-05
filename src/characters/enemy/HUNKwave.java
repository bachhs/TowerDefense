package characters.enemy;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;

import java.util.ArrayList;

public class HUNKwave {
    private Hunk enemyType;
    private int enemyPerWave;
    private StackPane pane;
    private Path path;

    private ArrayList<Hunk> enemies;

    public HUNKwave(Hunk enemyType, int enemyPerWave, StackPane pane, Path path) {
        this.enemyType = enemyType;
        this.enemyPerWave = enemyPerWave;
        this.pane = pane;
        this.path = path;
        enemies = new ArrayList<>();
        Update();
    }

    public void Render() {
        enemies.add(new Hunk());
    }

    public void Update() {
        enemies.add(enemyType);
        while (enemies.size() != enemyPerWave) {
            Render();
        }
    }

    public void move() {
        AnimationTimer h = new AnimationTimer() {
            int i = 0;
            private long lastUpdate = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                long elapsedSeconds = (System.currentTimeMillis() - lastUpdate) / 1000;
                if (elapsedSeconds == 2) {
                    lastUpdate = System.currentTimeMillis();
                    if (i < enemyPerWave) {
                        enemies.get(i++).move(pane, path);
                    }

                }
            }
        };
        h.start();
    }

}

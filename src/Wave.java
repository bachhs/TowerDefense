import characters.enemy.Enemy;

import java.util.ArrayList;

public class Wave {
    private float timeSinceLastSpawn, spawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy> enemyList;
    private int enemiesPerWave;
    private boolean waveCompleted;

    public Wave(Enemy enemyType, float spawnTime, int enemiesPerWave) {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new ArrayList<>();
        this.waveCompleted = false;
    }
}

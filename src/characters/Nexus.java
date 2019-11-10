package characters;

import characters.enemy.Enemy;
import characters.turret.Turret;

public class Nexus {
    private int health;
    private int score;

    public Nexus() {
        this.health = 100;
        this.score = 50;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return this.score;
    }

    public void buyTurret(Turret turret) {
        if(haveScore()) {
            score = score - turret.getScore();
        }
    }

    public void subHealth(Enemy enemy) {
        if(health - enemy.getDamage() > 0)
            health -= enemy.getDamage();
        else
            health = 0;
    }

    public boolean isLose() {
        return health == 0;
    }

    public boolean haveScore() {
        return score > 0;
    }

    public void addScore(Enemy e) {
        this.score += e.getScore();
    }
}

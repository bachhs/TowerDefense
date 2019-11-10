package characters.enemy;

import characters.Entity;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import static constants.GlobalConstants.GAME_SPEED;

public class Enemy extends Entity {
    private HealthBar healthBar = new HealthBar();
    protected int HP;
    protected int speed;
    protected int armor;
    protected int score;
    protected int damage;
    protected int MAX_HP = 100;

    public Enemy(String imagePath) {
        super(imagePath);
        imageView.setTranslateX(1000);
        imageView.setTranslateY(1000);
        healthBar.relocate(1000, 1000);
        healthBar.setScaleX(0.7);
        healthBar.setScaleY(0.7);
    }

    public int getHP() {
        return HP;
    }

    public int getSpeed() {
        return speed;
    }

    public int getArmor() {
        return armor;
    }

    public int getScore() {
        return score;
    }

    public int getDamage() {
        return damage;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void decreaseHP(int dame) {
        int dec = dame - dame * armor / 100;
        if (HP > dec) {
            HP = HP - dec;
            healthBar.setProgress(HP, MAX_HP);
        } else HP = 0;
    }

    public void move(Pane pane, Path pt) {
        PathTransition move1 = new PathTransition(Duration.millis(GAME_SPEED / speed), pt, getImageView());
        move1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        move1.setAutoReverse(false);
        move1.play();
        pane.getChildren().add(getImageView());
    }

    public void relocateHealthBar(double x, double y) {
        healthBar.setTranslateX(x);
        healthBar.setTranslateY(y - 50);
    }

    public boolean isDead() {
        return HP == 0;
    }

}

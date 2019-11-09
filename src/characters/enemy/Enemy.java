package characters.enemy;

import characters.Entity;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.List;

import static constants.GlobalConstants.GAME_SPEED;

public class Enemy extends Entity {
    public HealthBar healthBar = new HealthBar();
    protected int HP;
    protected int speed;
    protected int armor;
    protected int score;
    protected int damage;

    public Enemy(String imagePath) {
        super(imagePath);
        imageView.setTranslateX(1000);
        imageView.setTranslateY(1000);
        healthBar.relocate(1000, 1000);
        healthBar.setScaleX(0.7);
        healthBar.setScaleY(0.7);
        healthBar.setProgress(74, 100);

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

    public void decreaseHP(int dec) {
        if (HP > dec)
            HP = HP - dec;
        else
            HP = 0;

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

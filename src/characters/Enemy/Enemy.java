package characters.Enemy;

import characters.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Enemy extends Entity {
    protected int HP;
    protected int speed;
    protected int armor;
    protected int score;
    protected int damage;
    protected String imagePath;

    public Enemy(String imagePath) {
        this.imagePath = imagePath;
        image = new Image(imagePath);
        imageView = new ImageView(image);
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
        if (HP > dec) HP = HP - dec;
        else HP = 0;
    }

    public boolean isDead() {
        return HP == 0;
    }
}

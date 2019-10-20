package characters.Enemy;

public class Chaser extends Enemy {
    public Chaser(String imagePath) {
        super(imagePath);
        HP = 20;
        speed = 5;
        armor = 10;
        score = 5;
        damage = 1;
    }
}

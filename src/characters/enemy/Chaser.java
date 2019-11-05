package characters.enemy;

public class Chaser extends Enemy {
    private static final String path = "./resources/img/Chaser.png";
    public Chaser() {
        super(path);
        HP = 20;
        speed = 5;
        armor = 10;
        score = 5;
        damage = 1;
    }
}
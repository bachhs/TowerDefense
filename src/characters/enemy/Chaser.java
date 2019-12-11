package characters.enemy;

public class Chaser extends Enemy {
    private static final String path = "./resources/img/Chaser.png";

    public Chaser() {
        super(path);
        MAX_HP = 20;
        HP = MAX_HP;
        speed = 6;
        armor = 20;
        score = 7;
        damage = 5;
    }
}
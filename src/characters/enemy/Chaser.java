package characters.enemy;

public class Chaser extends Enemy {
    private static final String path = "./resources/img/Chaser.png";
    public Chaser() {
        super(path);
        MAX_HP = 20;
        HP = MAX_HP;
        speed = 5;
        armor = 20;
        score = 5;
        damage = 1;
    }
}
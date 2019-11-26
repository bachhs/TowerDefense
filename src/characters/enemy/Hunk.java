package characters.enemy;

public class Hunk extends Enemy {
    private static final String path = "./resources/img/HUNK.png";

    public Hunk() {
        super(path);
        MAX_HP = 70;
        HP = MAX_HP;
        speed = 2;
        armor = 55;
        score = 25;
        damage = 10;
    }
}

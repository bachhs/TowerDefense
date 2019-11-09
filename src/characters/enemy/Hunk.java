package characters.enemy;

public class Hunk extends Enemy {
    private static final String path = "./resources/img/HUNK.png";
    private static final int MAX_HP = 0;

    public Hunk() {
        super(path);
        HP = 40;
        speed = 3;
        armor = 35;
        score = 15;
        damage = 3;
    }
}

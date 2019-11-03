package characters.enemy;

public class HUNK extends Enemy {
    public HUNK() {
        super("./resources/img/HUNK.png");
        HP = 40;
        speed = 4;
        armor = 35;
        score = 15;
        damage = 3;
    }
}

package characters.enemy;

public class Chaser extends Enemy {
    public Chaser() {
        super("./resources/img/Chaser.png");
        HP = 20;
        speed = 5;
        armor = 10;
        score = 5;
        damage = 1;
    }
}

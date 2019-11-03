package characters.enemy;

public class MeatHarvester extends Enemy {
    public MeatHarvester() {
        super("./resources/img/MeatHarvester.png");
        HP = 15;
        speed = 8;
        armor = 15;
        score = 15;
        damage = 3;
    }
}

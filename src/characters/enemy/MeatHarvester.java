package characters.enemy;

public class MeatHarvester extends Enemy {
    private static final String path = "./resources/img/MeatHarvester.png";

    public MeatHarvester() {
        super(path);
        MAX_HP = 45;
        HP = MAX_HP;
        speed = 9;
        armor = 45;
        score = 15;
        damage = 10;
    }
}

package characters.enemy;

public class MeatHarvester extends Enemy {
    private static final String path = "./resources/img/MeatHarvester.png";
    public MeatHarvester() {
        super(path);
        HP = 15;
        speed = 8;
        armor = 15;
        score = 15;
        damage = 3;
    }
}

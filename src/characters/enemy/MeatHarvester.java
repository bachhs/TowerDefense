package characters.enemy;

public class MeatHarvester extends Enemy {
    private static final String path = "./resources/img/MeatHarvester.png";
    
    public MeatHarvester() {
        super(path);
        MAX_HP = 40;
        HP = MAX_HP;
        speed = 8;
        armor = 40;
        score = 15;
        damage = 10;
    }
}

package characters.enemy;

public class MeatHarvester extends Enemy {
    private static final String path = "./resources/img/MeatHarvester.png";
    
    public MeatHarvester() {
        super(path);
        MAX_HP = 30;
        HP = MAX_HP;
        speed = 6;
        armor = 40;
        score = 20;
        damage = 10;
    }
}

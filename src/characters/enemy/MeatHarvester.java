package characters.enemy;

public class MeatHarvester extends Enemy {
    private static final String path = "./resources/img/MeatHarvester.png";
    private static final int MAX_HP = 0;
    
    public MeatHarvester() {
        super(path);
        HP = 15;
        speed = 8;
        armor = 15;
        score = 15;
        damage = 3;
    }
}

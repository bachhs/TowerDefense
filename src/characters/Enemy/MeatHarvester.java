package characters.enemy;

public class MeatHarvester extends Enemy {
    public MeatHarvester(String imagePath) {
        super(imagePath);
        HP = 15;
        speed = 8;
        armor = 15;
        score = 15;
        damage = 3;
    }
}

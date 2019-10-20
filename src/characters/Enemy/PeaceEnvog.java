package characters.enemy;

public class PeaceEnvog extends Enemy {
    public PeaceEnvog(String imagePath) {
        super(imagePath);
        HP = 70;
        speed = 2;
        armor = 50;
        score = 30;
        damage = 5;
    }
}

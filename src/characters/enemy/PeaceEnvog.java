package characters.enemy;

public class PeaceEnvog extends Enemy {
    private static final String path = "./resources/img/PeaceEnvog.png";
    public PeaceEnvog() {
        super(path);
        HP = 70;
        speed = 2;
        armor = 50;
        score = 30;
        damage = 5;
    }
}

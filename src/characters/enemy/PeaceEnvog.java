package characters.enemy;

public class PeaceEnvog extends Enemy {
    private static final String path = "./resources/img/PeaceEnvog.png";

    public PeaceEnvog() {
        super(path);
        MAX_HP = 150;
        HP = MAX_HP;
        speed = 1;
        armor = 95;
        score = 50;
        damage = 20;

    }
}

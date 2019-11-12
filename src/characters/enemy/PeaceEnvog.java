package characters.enemy;

public class PeaceEnvog extends Enemy {
    private static final String path = "./resources/img/PeaceEnvog.png";

    public PeaceEnvog() {
        super(path);
        MAX_HP = 100;
        HP = MAX_HP;
        speed = 1;
        armor = 90;
        score = 30;
        damage = 20;

    }
}

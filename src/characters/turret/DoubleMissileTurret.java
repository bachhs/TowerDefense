package characters.turret;

public class DoubleMissileTurret extends Turret {
    private static final String path = "";

    public DoubleMissileTurret() {
        super(path);
        speed = 4;
        range = 8;
        damage = 80;
        score = 50;
    }
}

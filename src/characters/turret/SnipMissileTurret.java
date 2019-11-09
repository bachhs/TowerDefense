package characters.turret;

public class SnipMissileTurret extends Turret {
    private static final String path = "";

    public SnipMissileTurret() {
        super(path);
        speed = 2;
        range = 25;
        rangeCircle.setRadius(range);
        damage = 110;
        score = 80;
    }
}

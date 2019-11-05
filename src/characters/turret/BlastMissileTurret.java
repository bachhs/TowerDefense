package characters.turret;

public class BlastMissileTurret extends Turret {

    private static final String path = "";

    public BlastMissileTurret() {
        super(path);
        speed = 4;
        range = 10;
        damage = 50;
        score = 35;
    }
}

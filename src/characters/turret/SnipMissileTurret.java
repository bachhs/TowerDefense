package characters.turret;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SnipMissileTurret extends Turret {
    private static final String path = "./resources/img/plinthSnip.png";
    private static final String link = "./resources/img/SnipMissile.png";
    private static final String Bullet = "./resources/img/bulletSnipMissile.png";

    public SnipMissileTurret() {
        super(path);
        cannon = new ImageView(new Image(link));
        bulletView = Bullet;
        setRange(300);
        rangeCircle.setRadius(range);
        speedBullet = 200;
        damage = 110;
        score = 75;
        shootTime = 3;
        fullTurretPath = "./resources/img/SnipTurret.png";
    }
}

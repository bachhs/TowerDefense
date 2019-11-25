package characters.turret;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DoubleMissileTurret extends Turret {
    private static final String path = "./resources/img/plithDoubleMissile.png";
    private static final String link = "./resources/img/DoubleMissile.png";
    private static final String Bullet = "./resources/img/bulletDoubleMissile.png";

    public DoubleMissileTurret() {
        super(path);
        cannon = new ImageView(new Image(link));
        bulletView = Bullet;
        setRange(250);
        speedBullet = 140;
        damage = 30;
        score = 50;
        shootTime = 3;
        fullTurretPath = "./resources/img/DoubleFull.png";
    }
}

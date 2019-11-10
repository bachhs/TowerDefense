package characters.turret;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class SnipMissileTurret extends Turret {
    private static final String path = "./resources/img/plinthSnip.png";
    private static final String link = "./resources/img/SnipMissile.png";
    private static final String Bullet = "./resources/img/bulletSnipMissile.png";

    public SnipMissileTurret() {
        super(path);
        cannon = new ImageView(new Image(link));
        bullet = new Image( Bullet);
        setRange(400);
        rangeCircle.setRadius(range);
        speedBullet = 200;
        damage = 110;
        score = 100;
        shootTime = 6;
    }
}

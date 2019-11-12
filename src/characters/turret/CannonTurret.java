package characters.turret;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CannonTurret extends Turret {

    private static final String path = "./resources/img/plithCannon.png";
    private static final String link = "./resources/img/Cannon.png";
    private static final String Bullet = "./resources/img/bulletCannon.png";

    public CannonTurret() {
        super(path);
        cannon = new ImageView(new Image(link));
        bulletView = Bullet;
        speedBullet = 150;
        setRange(200);
        damage = 20;
        score = 25;
        shootTime = 2;
        fullTurretPath = "./resources/img/CannonFull.png";
    }
}

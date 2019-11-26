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
        damage = 15;
        score = 30;
        shootTime = 1;
        fullTurretPath = "./resources/img/CannonFull.png";
    }
}

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
        bullet = new Image(Bullet);
        speedBullet = 150;
        setRange(220);
        damage = 20;
        score = 30;
        shootTime = 2;
    }
}

package characters.turret;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlastMissileTurret extends Turret {

    private static final String path = "./resources/img/Turr.png";
    private static final String link = "./resources/img/cannon.png";
    private static final String amorC = "./resources/img/amorCannon.png";

    public BlastMissileTurret() {
        super(path);
        cannon = new ImageView(new Image(link));
        amor = new Image(amorC);
        speed = 4;
        setRange(250);
        damage = 50;
        score = 35;
        shootTime = 2;
    }
}

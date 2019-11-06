package characters.turret;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlastMissileTurret extends Turret {

    private static final String path = "./resources/img/Turr.png";
    private static final String link = "./resources/img/cannon.png";

    public BlastMissileTurret() {
        super(path);
        cannon = new ImageView(new Image(link));
        speed = 4;
        range = 10;
        damage = 50;
        score = 35;
    }
}

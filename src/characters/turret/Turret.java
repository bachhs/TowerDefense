package characters.turret;

import characters.Tile;
import javafx.scene.image.ImageView;

import java.awt.*;

public abstract class Turret extends Tile {
    protected int speed;
    protected int range;
    protected int damage;
    protected int score;
}

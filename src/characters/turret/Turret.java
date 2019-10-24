package characters.turret;

import characters.Tile;

public abstract class Turret extends Tile {
    protected int speed;
    protected int range;
    protected int damage;
    protected int score;

    public Turret(String imageURL) {
        super(imageURL);
    }

    public Turret() {
        super();
    }
}

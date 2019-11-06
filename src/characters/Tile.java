package characters;

import static constants.GlobalConstants.GAME_WIDTH;

public class Tile extends Entity {

    public Tile(String imageURL) {
        super(imageURL);
    }

    public void setTranslateXY(double x, double y) {
        this.imageView.setTranslateX(x);
        this.imageView.setTranslateY(y);
    }
}

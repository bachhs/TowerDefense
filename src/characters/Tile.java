package characters;

import static constants.GlobalConstants.GAME_WIDTH;

public class Tile extends Entity {

    public Tile(String imageURL) {
        super(imageURL);
        imageView.setFitWidth(GAME_WIDTH);
        imageView.setPreserveRatio(true);
    }
}

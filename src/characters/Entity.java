package characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {
    protected Image image;
    protected ImageView imageView;

    public Entity(String imageURL) {
        image = new Image(imageURL);
        imageView = new ImageView(image);
    }

    public ImageView getImageView() {
        return imageView;
    }
}

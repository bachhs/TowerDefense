package characters;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public abstract class Entity {
    protected Image image;
    protected ImageView imageView;

    public Entity(String imageURL) {
        image = new Image(imageURL);
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
    }

    public ImageView getImageView() {
        return imageView;
    }
}

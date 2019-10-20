package characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {
    protected Image image;
    protected ImageView imageView;

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }
}

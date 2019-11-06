package characters;

import constants.GlobalConstants;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
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
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(imageView.getTranslateX());
            }
        };
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getTranslateX() {
        return imageView.getTranslateX() + GlobalConstants.BOUND_X;
    }

    public double getTranslateY() {
        return imageView.getTranslateY() + GlobalConstants.BOUND_Y;
    }
}

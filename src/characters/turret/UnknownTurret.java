package characters.turret;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class UnknownTurret extends Turret {
    private static final String path = "./resources/img/UnknownTurret.png";

    public UnknownTurret() {
        super(path);
    }

    @Override
    public void setTranslateXY(double x, double y) {
        this.imageView.setTranslateX(x);
        this.imageView.setTranslateY(y);
    }

    @Override
    public Node getNode() {
        StackPane castle = new StackPane();

        castle.getChildren().addAll(imageView);
        return castle;
    }


}

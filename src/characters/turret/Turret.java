package characters.turret;

import characters.Tile;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Turret extends Tile {

    protected int speed = 0;
    protected int range = 0;
    protected int damage = 0;
    protected int score = 0;
    protected ImageView cannon;

    public Turret(String imageURL) {
        super(imageURL);
    }

    public int getSpeed() {
        return speed;
    }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public int getScore() {
        return score;
    }

    public<T extends Event> void addEventHandler(EventType<T> var1, EventHandler<? super T> var2) {
        imageView.addEventHandler(var1, var2);
    }

    public void setRotate(double x, double y) {
        if (cannon != null) {
            double angle = Math.toDegrees(Math.atan2(y - cannon.getTranslateY() , x - cannon.getTranslateX()))+90;
            cannon.setRotate(angle);
        }
    }

    public double getDistance(double x, double y) {
            return Math.sqrt((x - cannon.getTranslateX())*(x - cannon.getTranslateX()) + (y - cannon.getTranslateY())*(y - cannon.getTranslateY()));
    }


    @Override
    public void setTranslateXY(double x, double y) {
        this.imageView.setTranslateX(x);
        this.imageView.setTranslateY(y);
        this.cannon.setTranslateX(x);
        this.cannon.setTranslateY(y);
    }

    public Node getNode() {
        StackPane tru = new StackPane();
        tru.getChildren().addAll(imageView, cannon);
        return tru;
    }


}

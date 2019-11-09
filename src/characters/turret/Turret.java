package characters.turret;

import characters.Tile;
import characters.enemy.Enemy;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Turret extends Tile {

    protected int speed = 0;
    protected double range = 0;
    protected int damage = 0;
    protected int score = 0;
    protected ArrayList<Enemy> enemies;
    protected ImageView cannon;
    protected Circle rangeCircle;

    public Turret(String imageURL) {
        super(imageURL);
        rangeCircle = new Circle();
        rangeCircle.setFill(Color.GRAY);
        rangeCircle.setOpacity(0);
    }

    public int getSpeed() {
        return speed;
    }

    public double getRange() {
        return range;
    }

    protected void setRange(double range) {
        this.range = range;
        rangeCircle.setRadius(range);
    }

    public int getDamage() {
        return damage;
    }

    public int getScore() {
        return score;
    }

    public List<Enemy> getEnemies() {
        enemies = new ArrayList<>();
        return enemies;
    }

    public void setRotate(Enemy e) {
        cannon.setRotate(getAngle(e));
    }

    public double getAngle(Enemy e) {
        return Math.toDegrees(Math.atan2(e.getImageView().getTranslateY() - cannon.getTranslateY(),
                e.getImageView().getTranslateX() - cannon.getTranslateX())) + 90;
    }

    public boolean isInRange(Enemy e) {
        if (Math.sqrt((e.getImageView().getTranslateX() - cannon.getTranslateX())
                * (e.getImageView().getTranslateX() - cannon.getTranslateX())
                + (e.getImageView().getTranslateY() - cannon.getTranslateY())
                        * (e.getImageView().getTranslateY() - cannon.getTranslateY())) < getRange())
            return true;
        return false;
    }

    public void checkingEnemy(List<Enemy> enemies) {
        int i = 0;
        setRotate(enemies.get(i));
        for (i = 0; i < enemies.size() - 1; i++) {
            if (!isInRange(enemies.get(i))) {
                if (isInRange(enemies.get(i + 1)))
                    setRotate(enemies.get(i + 1));
            }
        }
    }

    @Override
    public void setTranslateXY(double x, double y) {
        this.imageView.setTranslateX(x);
        this.imageView.setTranslateY(y);
        this.cannon.setTranslateX(x);
        this.cannon.setTranslateY(y);
        this.rangeCircle.setTranslateX(x);
        this.rangeCircle.setTranslateY(y);
    }

    public Node getNode() {
        StackPane castle = new StackPane();

        EventHandler<MouseEvent> rangeEntered = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rangeCircle.setOpacity(0.5);
            }
        };

        EventHandler<MouseEvent> rangeExited = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rangeCircle.setOpacity(0);
            }
        };

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, rangeEntered);
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, rangeExited);
        cannon.addEventHandler(MouseEvent.MOUSE_ENTERED, rangeEntered);
        cannon.addEventHandler(MouseEvent.MOUSE_EXITED, rangeExited);

        castle.getChildren().addAll(rangeCircle, imageView, cannon);
        return castle;
    }

}

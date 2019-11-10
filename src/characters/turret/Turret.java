package characters.turret;

import characters.Tile;
import characters.enemy.Enemy;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.List;

public abstract class Turret extends Tile {

    protected double range = 0;
    protected int damage = 0;
    protected int score = 0;
    protected double shootTime = 0;
    protected int speedBullet = 0;
    protected Image bullet;
    protected ImageView cannon;
    protected Circle rangeCircle;

    public Turret(String imageURL) {
        super(imageURL);
        rangeCircle = new Circle();
        rangeCircle.setFill(Color.GRAY);
        rangeCircle.setOpacity(0);
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

    public double getShootTime() {
        return shootTime;
    }

    public void setRotate(Enemy e) {
        cannon.setRotate(getAngle(e));
    }

    public double getAngle(Enemy e) {
        return Math.toDegrees(Math.atan2(e.getImageView().getTranslateY() - cannon.getTranslateY(),
                e.getImageView().getTranslateX() - cannon.getTranslateX())) + 90;
    }

    public boolean isInRange(Enemy e) {
        return Math.sqrt((e.getImageView().getTranslateX() - cannon.getTranslateX())
                * (e.getImageView().getTranslateX() - cannon.getTranslateX())
                + (e.getImageView().getTranslateY() - cannon.getTranslateY())
                * (e.getImageView().getTranslateY() - cannon.getTranslateY())) < getRange();
    }

    public void checkingEnemy(List<Enemy> enemies) {
        if(getTarget(enemies) != null) setRotate(getTarget(enemies));
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

        castle.setMinWidth(imageView.getFitHeight());
        castle.setPrefWidth(imageView.getFitHeight());
        castle.setMaxWidth(imageView.getFitHeight());
        castle.setMinHeight(imageView.getFitHeight());
        castle.setPrefHeight(imageView.getFitHeight());
        castle.setMaxHeight(imageView.getFitHeight());


        return castle;
    }

    public Path ShootWay(double x, double y, double X, double Y) {
        Path path = new Path();
        MoveTo moveTo = new MoveTo(x, y);
        LineTo line = new LineTo(X, Y);
        path.getElements().addAll(moveTo, line);
        return path;
    }

    public Enemy getTarget(List<Enemy> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            if (isInRange(enemies.get(i)) && !enemies.get(i).isDead()) {
                return enemies.get(i);
            }
        }
        return null;
    }

    public void Shoot(Enemy e, Pane pane) {
        if (e != null) {
            ImageView shot = new ImageView(bullet);
            shot.setTranslateX(1000);
            shot.setTranslateY(1000);
            PathTransition pt = new PathTransition(Duration.millis(speedBullet), ShootWay(imageView.getTranslateX() + 30,
                    imageView.getTranslateY() + 30, e.getImageView().getTranslateX(), e.getImageView().getTranslateY()),
                    shot);
            pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pt.setAutoReverse(true);
            pane.getChildren().addAll(shot);
            pt.setOnFinished(event -> {
                pane.getChildren().removeAll(shot);
            });
            e.decreaseHP(getDamage());

            pt.play();

        }
    }
}

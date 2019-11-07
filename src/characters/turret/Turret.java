package characters.turret;

import characters.Tile;
import characters.enemy.Enemy;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class Turret extends Tile {

    protected int speed = 0;
    protected double range = 0;
    protected int damage = 0;
    protected int score = 0;
    protected ArrayList<Enemy> enemies;
    protected ImageView cannon;

    public Turret(String imageURL) {
        super(imageURL);
    }

    public int getSpeed() {
        return speed;
    }

    public double getRange() {
        return range;
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

    public<T extends Event> void addEventHandler(EventType<T> var1, EventHandler<? super T> var2) {
        imageView.addEventHandler(var1, var2);
    }

    public void setRoTate(Enemy e) {
        cannon.setRotate(getAngle(e));
    }

    public double getAngle(Enemy e) {
        return Math.toDegrees(Math.atan2(e.getImageView().getTranslateY() - cannon.getTranslateY() , e.getImageView().getTranslateX() - cannon.getTranslateX()))+90;
    }

    public boolean isInRange(Enemy e) {
        if(Math.sqrt((e.getImageView().getTranslateX() - cannon.getTranslateX()) * (e.getImageView().getTranslateX() - cannon.getTranslateX()) + (e.getImageView().getTranslateY() - cannon.getTranslateY()) * (e.getImageView().getTranslateY() - cannon.getTranslateY())) < getRange()) return true;
        return false;
    }

    public void checkingEnemy(List<Enemy> enemies) {
        int i = 0;
        setRoTate(enemies.get(i));
        for(i = 0; i < enemies.size()-1; i ++) {
            if(!isInRange(enemies.get(i))) {
                if(isInRange(enemies.get(i+1)))
                        setRoTate(enemies.get(i+1));
                        System.out.println(i);
            }
        }
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

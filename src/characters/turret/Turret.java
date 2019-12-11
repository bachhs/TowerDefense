package characters.turret;

import characters.Bullet;
import characters.Nexus;
import characters.Tile;
import characters.enemy.Enemy;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

public abstract class Turret extends Tile {

    protected double range = 0;
    protected int damage = 0;
    protected int score = 0;
    protected double shootTime = 0;
    protected int speedBullet = 0;
    protected String bulletView;
    protected ImageView cannon;
    protected Circle rangeCircle;
    protected String fullTurretPath;

    public Turret(String imageURL) {
        super(imageURL);
        rangeCircle = new Circle();
        rangeCircle.setFill(Color.GRAY);
        rangeCircle.setOpacity(0.5);
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
        if (getTarget(enemies) != null)
            setRotate(getTarget(enemies));
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

    public Node getNode(Nexus nexus, Pane pane, List<Turret> turrets, Label label) {
        StackPane castle = new StackPane();
        Turret temp = this;

        EventHandler<MouseEvent> rangeEntered = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!pane.getChildren().contains(rangeCircle)) {
                    pane.getChildren().add(rangeCircle);
                }
                VBox vBoxInfo = new VBox();
                vBoxInfo.setPrefSize(150, 40);
                vBoxInfo.setSpacing(10);

                Button upgrade = new Button("Upgrade " + getScore()/3 + "$");
                upgrade.setTranslateX(0);
                upgrade.setTranslateY(50);
                upgrade.setFont(Font.font(20));
                upgrade.setPrefSize(vBoxInfo.getPrefWidth(), vBoxInfo.getPrefHeight());
                upgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        pane.getChildren().remove(rangeCircle);
                        if (nexus.getScore() >= getScore() / 4) {
                            upgrade();
                            nexus.decreaseScore(getScore() / 3);
                            score *= 1.5;
                            label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
                        }
                        pane.getChildren().remove(vBoxInfo);
                    }
                });

                Button sell = new Button("Sell " + getScore()/2 + "$");
                sell.setTranslateX(0);
                sell.setTranslateY(75);
                sell.setFont(Font.font(20));
                sell.setPrefSize(vBoxInfo.getPrefWidth(), vBoxInfo.getPrefHeight());
                sell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        nexus.addScore(getScore() / 2);
                        pane.getChildren().remove(rangeCircle);
                        pane.getChildren().remove(castle);
                        pane.getChildren().remove(vBoxInfo);
                        turrets.remove(temp);
                        label.setText("Heath: " + nexus.getHealth() + "         " + nexus.getScore() + "$");
                    }
                });

                Button cancel = new Button("Cancel");
                cancel.setTranslateX(0);
                cancel.setTranslateY(100);
                cancel.setFont(Font.font(25));
                cancel.setPrefSize(vBoxInfo.getPrefWidth(), vBoxInfo.getPrefHeight());
                cancel.setCancelButton(true);
                cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        pane.getChildren().remove(rangeCircle);
                        pane.getChildren().remove(vBoxInfo);
                    }
                });

                vBoxInfo.getChildren().addAll(getInfo(), upgrade, sell, cancel);
                vBoxInfo.setAlignment(Pos.CENTER);
                vBoxInfo.setTranslateX(500);
                pane.getChildren().addAll(vBoxInfo);
            }
        };

        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, rangeEntered);
        cannon.addEventHandler(MouseEvent.MOUSE_CLICKED, rangeEntered);

        castle.getChildren().addAll(imageView, cannon);

        castle.setMinWidth(imageView.getFitHeight());
        castle.setPrefWidth(imageView.getFitHeight());
        castle.setMaxWidth(imageView.getFitHeight());
        castle.setMinHeight(imageView.getFitHeight());
        castle.setPrefHeight(imageView.getFitHeight());
        castle.setMaxHeight(imageView.getFitHeight());

        return castle;
    }

    public Path shootWay(double x, double y, double X, double Y) {
        Path path = new Path();
        MoveTo moveTo = new MoveTo(x, y);
        LineTo line = new LineTo(X, Y);
        path.getElements().addAll(moveTo, line);
        return path;
    }

    public void upgrade() {
        damage *= 1.2;
    }

    public Enemy getTarget(List<Enemy> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            if (isInRange(enemies.get(i)) && !enemies.get(i).isDead()) {
                return enemies.get(i);
            }
        }
        return null;
    }

    public void shoot(Enemy e, Pane pane) {
        if (e != null) {
            Bullet shot = new Bullet(bulletView);
            shot.setTranslateX(1000);
            shot.setTranslateY(1000);
            PathTransition pt = new PathTransition(Duration.millis(speedBullet),
                    shootWay(imageView.getTranslateX() + 30, imageView.getTranslateY() + 30,
                            e.getImageView().getTranslateX(), e.getImageView().getTranslateY()),
                    shot.getImageView());
            pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pt.setAutoReverse(true);
            pane.getChildren().addAll(shot.getImageView());
            MediaPlayer shootFXPlayer = new MediaPlayer(
                    new Media(new File("./src/resources/music/AttackFX.mp3").toURI().toString()));
            shootFXPlayer.setVolume(0.5);
            shootFXPlayer.play();
            MediaView mediaView = new MediaView(shootFXPlayer);
            pane.getChildren().add(mediaView);
            pt.setOnFinished(event -> {
                pane.getChildren().removeAll(shot.getImageView());
                pane.getChildren().remove(mediaView);
                e.decreaseHP(getDamage());
            });

            pt.play();

        }
    }

    public VBox getInfo() {
        VBox info = new VBox();
        ImageView turret = new ImageView(new Image(fullTurretPath));
        Label label1 = new Label();
        label1.setText("Price: " + this.getScore() + "\nDamage: " + this.getDamage() + "\nRange: " + this.getRange()
                + "\nTimeShoot: " + this.getShootTime());
        label1.setFont(Font.loadFont("file:./src/resources/font/OETZTYP_.TTF", 20));
        info.getChildren().addAll(turret, label1);
        info.setAlignment(Pos.CENTER);
        info.setPrefWidth(256);
        info.setMaxWidth(256);
        info.setPrefHeight(256);
        info.setPrefHeight(256);
        String cssLayout = "-fx-border-color: red;\n" + "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n";

        info.setStyle(cssLayout);
        info.setScaleX(0.8);
        info.setScaleY(0.8);
        return info;
    }
}
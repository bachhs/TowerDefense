package characters;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Nexus implements Health {
    private int health;
    private int score;

    public Nexus() {
        this.health = 100;
        this.score = 50;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return this.score;
    }

    public void decreaseScore(int dec) {
        if (score > dec)
            score -= dec;
        else
            score = 0;
    }

    @Override
    public void decreaseHP(int dec) {
        if (health > dec)
            health -= dec;
        else
            health = 0;
    }

    public boolean isLose() {
        return health == 0;
    }

    public void addScore(int inc) {
        this.score += inc;
    }

    public Text getText() {
        Text text = new Text("Health " + getHealth() + "      " + getScore() + "$");
        text.setFont(Font.loadFont("file:./src/resources/font/CF.TTF", 50));
        text.setFill(Color.DARKRED);
        text.setTranslateX(-370);
        text.setTranslateY(-325);
        return text;
    }
}

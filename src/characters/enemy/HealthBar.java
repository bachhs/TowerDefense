package characters.enemy;

import javafx.scene.control.ProgressBar;

public class HealthBar extends ProgressBar {
    private double healthPercent;
    public void setStyle() {
        if (healthPercent < 0.25)
            super.setStyle("-fx-box-border: goldenrod; -fx-control-inner-background: palegreen;-fx-accent: #db3236;");
        else if (healthPercent < 0.5)
            super.setStyle("-fx-box-border: goldenrod; -fx-control-inner-background: palegreen;-fx-accent: #ff9100;");
        else if (healthPercent < 0.75)
            super.setStyle("-fx-box-border: goldenrod; -fx-control-inner-background: palegreen;-fx-accent: #f4c20d;");
        else
            super.setStyle("-fx-box-border: goldenrod; -fx-control-inner-background: palegreen;-fx-accent: #3cba54;");
    }

    public HealthBar() {
        super(1.0);
        healthPercent = 1.0;
        setStyle();
    }

    public void setProgress(int currentHealth, int maxHealth) {
        this.healthPercent = (double) currentHealth / maxHealth;
        setProgress(healthPercent);
        this.setStyle();
    }
}

package characters;

public class Tile extends Entity {

    public Tile(String imageURL) {
        super(imageURL);
    }

    public void setTranslateXY(double x, double y) {
        this.imageView.setTranslateX(x);
        this.imageView.setTranslateY(y);
    }
}

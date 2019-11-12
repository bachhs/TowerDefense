package characters;

public class Bullet extends Entity {
    public Bullet(String imageURL) {
        super(imageURL);
    }

    public void setTranslateX(double x) {
        imageView.setTranslateX(x);
    }

    public void setTranslateY(double y) {
        imageView.setTranslateY(y);
    }


}

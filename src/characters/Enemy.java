package characters;

public abstract class Enemy implements Entity {
    protected int HP = 0;
    protected int speed = 0;
    protected int armor = 0;
    protected int score = 0;

    String imagePath;

    public boolean isDead() {
        return HP == 0;
    }
}

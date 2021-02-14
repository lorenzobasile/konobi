package konobi;

public class Player {
    private Stone color;

    public Player(Stone color) {
        this.color = color;
    }

    public void setColor(Stone color) {
        this.color = color;
    }

    public Stone getColor() {
        return color;
    }
}

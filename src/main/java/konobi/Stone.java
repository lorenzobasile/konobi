package konobi;

public class Stone {

    private Color color;

    public Stone(Color color) {
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

    public boolean hasSameColorAs(Stone stone) {
        return this.color == stone.getColor();
    }
}

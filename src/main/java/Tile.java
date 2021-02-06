public class Tile {
    private Position position;
    private Color color;


    public Tile(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public boolean isAt(Position position) {
        return this.position.equals(position);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

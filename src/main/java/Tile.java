public class Tile {
    Position position;
    Color color;


    public Tile(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public boolean isAt(Position position) {
        return this.position.equals(position);
    }


}

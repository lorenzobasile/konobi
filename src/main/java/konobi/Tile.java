package konobi;

public class Tile {
    private Position position;
    private Color color;


    public Tile(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public static Tile empty(Position position){
        return new Tile(position, Color.NONE);
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

    private boolean isOnTheSameColumnOf(Tile tile){
        return this.position.getX()== tile.getPosition().getX();
    }

    private boolean isOnTheSameRowOf(Tile tile){
        return this.position.getY()== tile.getPosition().getY();
    }

    public boolean isStronglyConnectedWith(Tile tile){
        return (this.isOnTheSameColumnOf(tile) || this.isOnTheSameRowOf(tile))
                &&
                (this.isVerticallyAdjacentTo(tile) || this.isHorizontallyAdjacentTo(tile));
    }

    private boolean isHorizontallyAdjacentTo(Tile tile) {
        if (Math.abs(this.position.getX() - tile.getPosition().getX()) == 1) return true;
        return false;
    }

    private boolean isVerticallyAdjacentTo(Tile tile) {
        if (Math.abs(this.position.getY() - tile.getPosition().getY()) == 1) return true;
        return false;
    }
}

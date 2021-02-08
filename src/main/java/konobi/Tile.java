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

    public boolean hasSameColorAs(Tile tile) {
        return this.getColor() == tile.getColor();
    }

    private boolean isOnTheSameColumnOf(Tile tile){
        return this.position.getX()== tile.getPosition().getX();
    }

    private boolean isOnTheSameRowOf(Tile tile){
        return this.position.getY()== tile.getPosition().getY();
    }

    public boolean isStronglyConnectedWith(Tile tile) {
        return (this.isHorizontallyAdjacentTo(tile) || this.isVerticallyAdjacentTo(tile)) && this.hasSameColorAs(tile);
    }

    private boolean isHorizontallyAdjacentTo(Tile tile) {
        return this.isOnTheSameRowOf(tile) && this.hasHorizontalDistanceOneFrom(tile);
    }

    private boolean isVerticallyAdjacentTo(Tile tile) {
        return this.isOnTheSameColumnOf(tile) && this.hasVerticalDistanceOneFrom(tile);
    }

    private boolean hasHorizontalDistanceOneFrom(Tile tile) {
        return Math.abs(this.position.getX() - tile.getPosition().getX()) == 1;
    }

    private boolean hasVerticalDistanceOneFrom(Tile tile) {
        return Math.abs(this.position.getY() - tile.getPosition().getY()) == 1;
    }
}

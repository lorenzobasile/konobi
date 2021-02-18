package konobi;

public class Cell {
    private final Position position;
    private boolean isOccupied;
    private Color color;

    public Cell(Position position) {
        this.position = position;
        this.isOccupied = false;
    }
    public void reset(){
        this.color = null;
        this.isOccupied = false;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color stoneColor) {
        this.color = stoneColor;
        isOccupied = true;
    }

    public boolean isAt(Position location){
        return this.position.equals(location);
    }

    public boolean hasSameColorAs(Cell cell) {
        return this.color == cell.color;
    }

    public boolean isOrthogonallyAdjacentTo(Cell cell) {
        return position.squareEuclideanDistanceFrom(cell.position)==1;
    }
    public boolean isDiagonallyAdjacentTo(Cell cell) {
        return position.squareEuclideanDistanceFrom(cell.position)==2;
    }
}
package konobi;

public class Cell {
    private final Position position;
    private boolean isOccupied;
    private Color stone;

    public Cell(Position position) {
        this.position = position;
        this.isOccupied = false;
    }
    public void reset(){
        this.stone = null;
        this.isOccupied = false;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Color getColor() {
        return stone;
    }

    public void setColor(Color stone) {
        this.stone = stone;
        isOccupied = true;
    }

    public boolean isAt(Position position){
        return this.position.equals(position);
    }


    public boolean hasSameColorAs(Cell cell) {
        return this.stone == cell.getColor();
    }


    @Override
    public String toString() {
        return "Cell{" +
                "position=" + position +
                ", isOccupied=" + isOccupied +
                ", currentPiece=" + stone +
                '}';
    }
}
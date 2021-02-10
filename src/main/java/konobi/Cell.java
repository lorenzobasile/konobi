package konobi;

public class Cell {
    private final Position position;
    private boolean isOccupied;
    private Stone stone;

    public Cell(Position position) {
        this.position = position;
        this.isOccupied = false;
    }
    public void reset(){
        this.isOccupied = false;
    }
    public Position getPosition() {
        return position;
    }
    public boolean isOccupied() {
        return isOccupied;
    }
    public Stone getCurrentStone() {
        return stone;
    }
    public void setCurrentStone(Stone currentStone) throws Exception{
        if(this.isOccupied) throw new Exception("Cell already occupied");
        this.stone = currentStone;
        isOccupied = true;
    }
    public boolean isAt(Position position){
        return this.position.equals(position);
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
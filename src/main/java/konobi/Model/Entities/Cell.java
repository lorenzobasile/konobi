package konobi.Model.Entities;

import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<Cell> orthogonalNeighborsIn(Set<Cell> cells) {
        return cells.stream()
                    .filter(this::isOrthogonallyAdjacentTo)
                    .collect(Collectors.toSet());
    }

    public Set<Cell> diagonalNeighborsIn(Set<Cell> cells) {
        return cells.stream()
                    .filter(this::isDiagonallyAdjacentTo)
                    .collect(Collectors.toSet());
    }

    public Set<Cell> commonOrthogonalNeighborsWith(Cell otherCell, Set<Cell> cells){
        Set<Cell> orthogonalNeighborsOfCell = this.orthogonalNeighborsIn(cells);
        Set<Cell> orthogonalNeighborsOfOtherCell = otherCell.orthogonalNeighborsIn(cells);
        orthogonalNeighborsOfCell.retainAll(orthogonalNeighborsOfOtherCell);
        return orthogonalNeighborsOfCell;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "position=" + position +
                ", isOccupied=" + isOccupied +
                ", color=" + color +
                '}';
    }
}
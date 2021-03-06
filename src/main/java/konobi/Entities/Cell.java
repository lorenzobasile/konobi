package konobi.Entities;

import java.util.Set;
import java.util.stream.Collectors;

public class Cell {
    private final Position position;
    private boolean isOccupied;
    private Color color;

    public Cell(Position cellPosition) {
        position = cellPosition;
        isOccupied = false;
    }

    public void removeStone(){
        color = null;
        isOccupied = false;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAt(Position givenPosition){
        return position.equals(givenPosition);
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setColor(Color stoneColor) {
        color = stoneColor;
        isOccupied = true;
    }

    public boolean hasColor(Color color) {
        return this.color == color;
    }

    public boolean hasSameColorAs(Cell cell) {
        return hasColor(cell.color);
    }

    private boolean isOrthogonallyAdjacentTo(Cell otherCell) {
        return this.position.squareEuclideanDistanceFrom(otherCell.position)==1;
    }

    private boolean isDiagonallyAdjacentTo(Cell otherCell) {
        return this.position.squareEuclideanDistanceFrom(otherCell.position)==2;
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

    public boolean isOnStartEdge(int dimension) {
        if(color==Color.WHITE)
            return position.getX()==1;
        else
            return position.getY()==dimension;
    }

    public boolean isOnEndEdge(int dimension) {
        if(color==Color.WHITE)
            return position.getX()==dimension;
        else
            return position.getY()==1;
    }
}
package konobi.Entities;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static konobi.Entities.Position.at;

public class Board extends HashSet<Cell>{

    public Board(int dimension) {
        super();
        Set<Cell> cellSet = IntStream.rangeClosed(1,dimension)
                                     .mapToObj(i -> IntStream.rangeClosed(1,dimension)
                                                             .mapToObj(j -> new Cell(at(i,j))))
                                     .flatMap(Function.identity())
                                     .collect(Collectors.toSet());
        this.addAll(cellSet);
    }

    public int dimension(){
        return (int) Math.sqrt(size());
    }

    public Cell getCell(Position position) {
        return this.stream()
                   .filter(c->c.isAt(position))
                   .findFirst()
                   .orElse(null);
    }

    public void placeStone(Position position, Color color) {
        Cell cellToOccupy = getCell(position);
        cellToOccupy.setColor(color);
    }

    public Set<Cell> strongConnectionsOf(Cell cell) {
        return cell.orthogonalNeighborsIn(this).stream()
                                                    .filter(Cell::isOccupied)
                                                    .filter(c->c.hasSameColorAs(cell))
                                                    .collect(Collectors.toSet());
    }

    public Set<Cell> weakConnectionsOf(Cell cell) {
        return cell.diagonalNeighborsIn(this).stream()
                                                   .filter(Cell::isOccupied)
                                                   .filter(c->c.hasSameColorAs(cell))
                                                   .filter(c->commonStrongConnectionsBetween(cell, c).isEmpty())
                                                   .collect(Collectors.toSet());
    }

    public Set<Cell> connectionsOf(Cell cell) {
        Set<Cell> neighbors = weakConnectionsOf(cell);
        neighbors.addAll(strongConnectionsOf(cell));
        return neighbors;
    }

    private Set<Cell> commonStrongConnectionsBetween(Cell cell, Cell otherCell) {
        Set<Cell> strongNeighborsOfCell = strongConnectionsOf(cell);
        Set<Cell> strongNeighborsOfOtherCell = strongConnectionsOf(otherCell);
        strongNeighborsOfCell.retainAll(strongNeighborsOfOtherCell);
        return strongNeighborsOfCell;
    }

}

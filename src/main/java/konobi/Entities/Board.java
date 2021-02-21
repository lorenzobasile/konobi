package konobi.Entities;


import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static konobi.Entities.Position.at;

public class Board {
    public int dimension;
    public Set<Cell> cells = new HashSet<>();

    public Board(int dimension) {
        this.dimension = dimension;
        Set<Cell> cellSet = IntStream.rangeClosed(1,dimension)
                              .mapToObj(i -> IntStream.rangeClosed(1,dimension)
                                                      .mapToObj(j -> new Cell(at(i,j))))
                              .flatMap(Function.identity())
                              .collect(Collectors.toSet());
        this.cells.addAll(cellSet);
    }

    public Cell getCell(Position position) {
        return cells.stream()
                    .filter(c->c.isAt(position))
                    .findFirst()
                    .orElse(null);
    }

    public void placeStone(Position position, Color color) {
        Cell cellToOccupy = this.getCell(position);
        cellToOccupy.setColor(color);
    }


    public Set<Cell> startEdge(Color color){
        return cells.stream()
                .filter(c->c.hasColor(color))
                .filter(c->c.isOnStartEdge(dimension))
                .collect(Collectors.toSet());
    }



    public Set<Cell> strongConnectionsOf(Cell cell) {
        if(!cell.isOccupied())
            return null;

        return cell.orthogonalNeighborsIn(cells).stream()
                                                .filter(Cell::isOccupied)
                                                .filter(c->c.hasSameColorAs(cell))
                                                .collect(Collectors.toSet());
    }


    public Set<Cell> weakConnectionsOf(Cell cell) {
        if(!cell.isOccupied())
            return null;

        return cell.diagonalNeighborsIn(cells).stream()
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
        return cell.commonOrthogonalNeighborsWith(otherCell, cells).stream()
                                                                   .filter(Cell::isOccupied)
                                                                   .filter(c->c.hasSameColorAs(cell))
                                                                   .filter(c->c.hasSameColorAs(otherCell))
                                                                   .collect(Collectors.toSet());
    }

}

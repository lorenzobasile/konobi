package konobi;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Connections {

    Board board;

    public Connections(Board board) {
        this.board = board;
    }

    public Set<Cell> orthogonalNeighborsOf(Cell cell) {

        return board.cells.stream()
                          .filter(c->c.isOrthogonallyAdjacentTo(cell))
                          .collect(Collectors.toSet());

    }

    public Set<Cell> strongNeighborsOf(Cell cell) {
        if(!cell.isOccupied())
            return new HashSet<>();
        Set<Cell> neighbors = orthogonalNeighborsOf(cell).stream()
                                                         .filter(c->c.isOccupied())
                                                         .filter(c->c.hasSameColorAs(cell))
                                                         .collect(Collectors.toSet());
        return neighbors;
    }


    public Set<Cell> diagonalNeighborsOf(Cell cell) {

        return board.cells.stream()
                          .filter(c->c.isDiagonallyAdjacentTo(cell))
                          .collect(Collectors.toSet());
    }


    public Set<Cell> weakNeighborsOf(Cell cell) {
        if(!cell.isOccupied())
            return new HashSet<>();
        Set<Cell> neighbors = diagonalNeighborsOf(cell).stream()
                                                       .filter(c->c.isOccupied())
                                                       .filter(c->c.hasSameColorAs(cell))
                                                       .filter(c->commonStrongNeighbors(cell, c).isEmpty())
                                                       .collect(Collectors.toSet());
        return neighbors;
    }

    public Set<Cell> neighborsOf(Cell cell) {
        Set<Cell> neighbors = weakNeighborsOf(cell);
        neighbors.addAll(strongNeighborsOf(cell));
        return neighbors;
    }

    public Set<Cell> commonOrthogonalNeighbors(Cell cell, Cell neighbor){
        Set<Cell> orthogonalNeighborsOfCell = orthogonalNeighborsOf(cell);
        Set<Cell> orthogonalNeighborsOfDiagonalCell = orthogonalNeighborsOf(neighbor);
        orthogonalNeighborsOfCell.retainAll(orthogonalNeighborsOfDiagonalCell);
        return orthogonalNeighborsOfCell;
    }

    private Set<Cell> commonStrongNeighbors(Cell cell, Cell neighbor) {
        Set<Cell> strongNeighborsOfCell = commonOrthogonalNeighbors(cell, neighbor).stream()
                                                                                   .filter(c->c.isOccupied())
                                                                                   .filter(c->c.hasSameColorAs(cell))
                                                                                   .filter(c->c.hasSameColorAs(neighbor))
                                                                                   .collect(Collectors.toSet());
        return strongNeighborsOfCell;
    }



}

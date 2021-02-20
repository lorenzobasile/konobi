package konobi.Rules;

import konobi.Entities.Board;
import konobi.Entities.Cell;
import konobi.Entities.Color;

import java.util.Set;

public class WeakConnectionRule implements Rule{

    @Override
    public boolean isValid(Board board, Cell cell, Color stoneColor) {
        board.placeStone(cell.getPosition(), stoneColor);
        Set<Cell> weakNeighbors = board.weakConnectionsOf(cell);
        cell.reset();
        boolean condition = weakNeighbors.stream()
                                         .map(c->c.orthogonalNeighborsIn(board.cells))
                                         .anyMatch(s->s.stream()
                                                       .filter(c->!c.isOccupied())
                                                       .anyMatch(c->checkIfThereAreNoWeakNeighbors(board, c, stoneColor)));
        return !condition;
    }

    private boolean checkIfThereAreNoWeakNeighbors(Board board, Cell cell, Color stoneColor){
        board.placeStone(cell.getPosition(), stoneColor);
        Set<Cell> weakConnectionsOfCell = board.weakConnectionsOf(cell);
        cell.reset();
        return weakConnectionsOfCell.isEmpty();
    }
}
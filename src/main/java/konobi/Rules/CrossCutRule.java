package konobi.Rules;

import konobi.Entities.Board;
import konobi.Entities.Cell;
import konobi.Entities.Color;

import java.util.Set;

public class CrossCutRule implements Rule{
    @Override
    public boolean isValid(Board board, Cell cell, Color stoneColor) {
        board.placeStone(cell.getPosition(), stoneColor);
        Set<Cell> weakNeighbors = board.weakConnectionsOf(cell);
        boolean isThereACrossCut = weakNeighbors.stream()
                                                .map(c->c.commonOrthogonalNeighborsWith(cell, board.cells))
                                                .anyMatch(s->s.stream()
                                                              .allMatch(c->c.isOccupied() && !c.hasSameColorAs(cell)));
        cell.reset();
        return !isThereACrossCut;

    }

}

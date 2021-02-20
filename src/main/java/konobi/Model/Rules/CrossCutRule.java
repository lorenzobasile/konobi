package konobi.Model.Rules;

import konobi.Model.Entities.Board;
import konobi.Model.Entities.Cell;
import konobi.Model.Entities.Color;

import java.util.Set;

public class CrossCutRule implements Rule{
    @Override
    public boolean isValid(Board board, Cell cell, Color stoneColor) {
        Set<Cell> weakNeighbors = board.weakConnectionsOf(cell);
        boolean isThereACrossCut = weakNeighbors.stream()
                                                .map(c->c.commonOrthogonalNeighborsWith(cell, board.cells))
                                                         .anyMatch(s->s.stream()
                                                                       .allMatch(c->c.isOccupied() && c.getColor()==stoneColor.oppositeColor()));
        return !isThereACrossCut;

    }

}

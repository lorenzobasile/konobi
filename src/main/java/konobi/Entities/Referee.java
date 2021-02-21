package konobi.Entities;

import konobi.Rules.CrossCutRule;
import konobi.Rules.WeakConnectionRule;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Referee {
    WeakConnectionRule ruleOne = new WeakConnectionRule();
    CrossCutRule ruleTwo = new CrossCutRule();

    public boolean validateMove(Board board, Cell cell, Color color){
        return ruleOne.isValid(board, cell, color) && ruleTwo.isValid(board, cell, color);
    }

    public Set<Cell> availableCellsFor(Player player, Board board) {
        return board.stream()
                          .filter(c->!c.isOccupied())
                          .filter(c->validateMove(board, c, player.getColor()))
                          .collect(Collectors.toSet());
    }

    public boolean validateChain(Board board, Color color) {
        Set<Position> visitedCells = new HashSet<>();
        return board.startEdge(color).stream()
                                     .filter(Cell::isOccupied)
                                     .filter(c->!visitedCells.contains(c.getPosition()))
                                     .filter(c->c.hasColor(color))
                                     .anyMatch(c->chainSearch(board, c, visitedCells));
    }

    private boolean chainSearch(Board board, Cell source, Set<Position> visitedCells) {
        visitedCells.add(source.getPosition());
        if(source.isOnEndEdge(board.dimension())) return true;
        return board.connectionsOf(source).stream()
                                          .filter(c->!visitedCells.contains(c.getPosition()))
                                          .anyMatch(c->chainSearch(board, c, visitedCells));
    }

}

package konobi.Entities;

import konobi.Rules.CrossCutRule;
import konobi.Rules.WeakConnectionRule;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Referee {
    private final WeakConnectionRule ruleOne = new WeakConnectionRule();
    private final CrossCutRule ruleTwo = new CrossCutRule();
    private final Board board;

    public Referee(Board board){
        this.board = board;
    }

    private boolean validateMove(Cell cell, Color color){
        return ruleOne.isValid(board, cell, color) && ruleTwo.isValid(board, cell, color);
    }

    public Set<Cell> availableMovesFor(Color color) {
        return board.stream()
                    .filter(c->!c.isOccupied())
                    .filter(c->validateMove(c, color))
                    .collect(Collectors.toSet());
    }

    public boolean isThereAWinningChainFor(Color color) {
        Set<Position> visitedCells = new HashSet<>();
        return board.stream()
                    .filter(Cell::isOccupied)
                    .filter(c->c.hasColor(color))
                    .filter(c->c.isOnStartEdge(board.dimension()))
                    .filter(c->!visitedCells.contains(c.getPosition()))
                    .filter(c->c.hasColor(color))
                    .anyMatch(c->chainSearch(c, visitedCells));
    }

    private boolean chainSearch(Cell source, Set<Position> visitedCells) {
        visitedCells.add(source.getPosition());
        if(source.isOnEndEdge(board.dimension())) return true;
        return board.connectionsOf(source).stream()
                                          .filter(c->!visitedCells.contains(c.getPosition()))
                                          .anyMatch(c->chainSearch(c, visitedCells));
    }

}

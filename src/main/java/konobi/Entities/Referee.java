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
        return board.cells.stream()
                          .filter(c->!c.isOccupied())
                          .filter(c->validateMove(board, c, player.getColor()))
                          .collect(Collectors.toSet());
    }


    public boolean validateChain(Board board, Color color) {
        Set<Position> visitedCells = new HashSet<>();
        return board.startEdge(color).stream()
                .filter(c->c.isOccupied())
                .filter(c->!visitedCells.contains(c))
                .filter(c->c.getColor()==color)
                .anyMatch(c->chainSearch(board, c, visitedCells));
    }


    private boolean chainSearch(Board board, Cell source, Set<Position> visitedCells) {
        visitedCells.add(source.getPosition());
        if(board.endEdge(source.getColor()).contains(source)) return true;
        for(Cell cell: board.connectionsOf(source)){
            if(visitedCells.contains(cell.getPosition())) continue;
            if(chainSearch(board, cell, visitedCells)) return true;
        }
        return false;
    }

}

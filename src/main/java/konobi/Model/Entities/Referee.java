package konobi.Model.Entities;

import konobi.Model.Entities.*;
import konobi.Model.Rules.PositionRule;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Referee {

    public boolean validateMove(Board board, Cell cell, Color color){
        board.placeStone(cell.getPosition(), color);
        PositionRule rules = new PositionRule();
        boolean isValidCell = rules.isValid(board, cell, color);
        cell.reset();
        return isValidCell;
    }

    public Set<Cell> availableCellsFor(Player player, Board board) {
        return board.cells.stream()
                          .filter(c->!c.isOccupied())
                          .filter(c->validateMove(board, c, player.getColor()))
                          .collect(Collectors.toSet());
    }

    public boolean validateChain(Board board, Color color) {
        Set<Position> visitedCells = new HashSet<>();
        return board.boardEdge(color, true).stream()
                                                .filter(c->c.isOccupied())
                                                .filter(c->!visitedCells.contains(c))
                                                .filter(c->c.getColor()==color)
                                                .anyMatch(c->chainSearch(board, c, visitedCells));
    }


    private boolean chainSearch(Board board, Cell source, Set<Position> visitedCells) {
        visitedCells.add(source.getPosition());
        if(board.boardEdge(source.getColor(), false).contains(source)) return true;
        for(Cell cell: board.connectionsOf(source)){
            if(visitedCells.contains(cell.getPosition())) continue;
            if(chainSearch(board, cell, visitedCells)) return true;
        }
        return false;
    }

}

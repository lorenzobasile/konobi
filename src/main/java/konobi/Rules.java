package konobi;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Rules {
    Board board;

    public Rules(Board board) {
        this.board = board;
    }

    private boolean isLegalWeakConnectionPlacement(Cell cell) {
        Set<Cell> weakNeighbors = board.weakConnectionsOf(cell);
        Color stoneColor = cell.getColor();
        cell.reset();
        boolean condition = weakNeighbors.stream()
                                         .map(c->c.orthogonalNeighborsIn(board.cells))
                                         .anyMatch(s->s.stream()
                                                       .filter(c->!c.isOccupied())
                                                       .anyMatch(c->checkIfThereAreNoWeakNeighbors(c, stoneColor)));
        board.placeStone(cell.getPosition(), stoneColor);
        return !condition;
    }

    private boolean checkIfThereAreNoWeakNeighbors(Cell cell, Color stoneColor){
        board.placeStone(cell.getPosition(), stoneColor);
        Set<Cell> weakConnectionsOfCell = board.weakConnectionsOf(cell);
        cell.reset();
        return weakConnectionsOfCell.isEmpty();
    }

    private boolean isCrosscutPlacement(Cell cell) {
        Set<Cell> weakNeighbors = board.weakConnectionsOf(cell);
        Color stoneColor = cell.getColor();
        return weakNeighbors.stream()
                            .map(c->c.commonOrthogonalNeighborsWith(cell, board.cells))
                            .anyMatch(s->s.stream()
                                          .allMatch(c->c.isOccupied() && c.getColor()==stoneColor.oppositeColor()));
    }

    public boolean checkTheTwoRules(Cell cell, Color color){
        board.placeStone(cell.getPosition(), color);
        boolean ruleOne = !(isCrosscutPlacement(cell));
        boolean ruleTwo = isLegalWeakConnectionPlacement(cell);
        cell.reset();
        return ruleOne && ruleTwo;
    }

    public boolean checkChain(Color color) {
        Set<Position> visitedCells = new HashSet<>();
        return board.boardEdge(color, true).stream()
                                                .filter(c->c.isOccupied())
                                                .filter(c->!visitedCells.contains(c))
                                                .filter(c->c.getColor()==color)
                                                .anyMatch(c->chainSearch(c, visitedCells));
    }


    private boolean chainSearch(Cell source, Set<Position> visitedCells) {
        visitedCells.add(source.getPosition());
        if(board.boardEdge(source.getColor(), false).contains(source)) return true;
        for(Cell cell: board.connectionsOf(source)){
            if(visitedCells.contains(cell.getPosition())) continue;
            if(chainSearch(cell, visitedCells)) return true;
        }
        return false;
    }

}

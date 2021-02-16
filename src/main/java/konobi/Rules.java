package konobi;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Rules {

    Board board;
    Connections connections;

    public Rules(Board board) {
        this.board = board;
        this.connections = new Connections(board);
    }

    public boolean isLegalWeakConnectionPlacement(Cell cell) {
        Set<Cell> weakNeighbors = connections.weakNeighborsOf(cell);
        Color stoneColor = cell.getColor();
        cell.reset();
        boolean condition = weakNeighbors.stream()
                                         .map(c->connections.orthogonalNeighborsOf(c))
                                         .anyMatch(s->s.stream()
                                         .filter(c->!c.isOccupied())
                                         .anyMatch(c->checkIfThereAreNoWeakNeighbors(c, stoneColor)));
        board.placeStone(cell.getPosition(), stoneColor);
        return !condition;
    }


    private boolean checkIfThereAreNoWeakNeighbors(Cell cell, Color stoneColor){
        board.placeStone(cell.getPosition(), stoneColor);
        boolean weakCondition = connections.weakNeighborsOf(cell).isEmpty();
        cell.reset();
        return weakCondition;
    }

    public boolean isCrosscutPlacement(Cell cell) {
        Set<Cell> weakNeighbors = connections.weakNeighborsOf(cell);
        Color stoneColor = cell.getColor();

        return weakNeighbors.stream()
                            .map(c->connections.commonOrthogonalNeighbors(cell, c))
                            .anyMatch(s->s.stream()
                            .allMatch(c->c.isOccupied() && c.getColor()==stoneColor.oppositeColor()));
    }

    public Set<Cell> legalCellsOf(Color color) {
        return board.cells.stream()
                          .filter(c->!c.isOccupied())
                          .filter(c->checkTheTwoRules(c, color))
                          .collect(Collectors.toSet());
    }

    private boolean checkTheTwoRules(Cell cell, Color color){
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
        for(Cell cell: connections.neighborsOf(source)){
            if(visitedCells.contains(cell.getPosition())) continue;
            if(chainSearch(cell, visitedCells)) return true;
        }
        return false;
    }


}

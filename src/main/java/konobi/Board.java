package konobi;

import java.util.*;
import java.util.stream.Collectors;

import static konobi.Position.at;

public class Board {
    int dimension;
    List<Cell> cells = new ArrayList<>();

    public Board(int dimension) {
        this.dimension = dimension;
        for (int i = 1; i<=dimension; ++i){
            for(int j = 1; j<=dimension; ++j){
                cells.add(new Cell(at(i,j)));
            }
        }
    }



    public Cell getCellAt(Position position) {

        return cells.stream()
                    .filter(c->c.isAt(position))
                    .findFirst()
                    .orElse(null);
    }

    public void placeStoneAt(Stone stone, Position position) {
        Cell cellToOccupy = this.getCellAt(position);
        cellToOccupy.setColor(stone);
    }

    public Set<Cell> orthogonalNeighborsOf(Cell cell) {

        return cells.stream()
                    .filter(c->cell.getPosition().squareEuclideanDistanceFrom(c.getPosition())==1)
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

    private Set<Cell> commonOrthogonalNeighbors(Cell cell, Cell neighbor){
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

    public Set<Cell> diagonalNeighborsOf(Cell cell) {

        return cells.stream()
                    .filter(c->cell.getPosition().squareEuclideanDistanceFrom(c.getPosition())==2)
                    .collect(Collectors.toSet());
    }


    public boolean isLegalWeakConnectionPlacement(Cell cell) {
        Set<Cell> weakNeighbors = weakNeighborsOf(cell);
        Stone stoneColor = cell.getColor();
        cell.reset();
        boolean condition = weakNeighbors.stream()
                                         .map(c->orthogonalNeighborsOf(c))
                                         .anyMatch(s->s.stream()
                                                       .filter(c->!c.isOccupied())
                                                       .anyMatch(c->checkIfThereAreNoWeakNeighbors(c, stoneColor)));
        placeStoneAt(stoneColor, cell.getPosition());
        return !condition;
    }


    private boolean checkIfThereAreNoWeakNeighbors(Cell cell, Stone stoneColor){
        placeStoneAt(stoneColor, cell.getPosition());
        boolean weakCondition = weakNeighborsOf(cell).isEmpty();
        cell.reset();
        return weakCondition;
    }

    public boolean isCrosscutPlacement(Cell cell) {
        Set<Cell> weakNeighbors = weakNeighborsOf(cell);
        Stone stoneColor = cell.getColor();

        return weakNeighbors.stream()
                            .map(c->commonOrthogonalNeighbors(cell, c))
                            .anyMatch(s->s.stream()
                                          .allMatch(c->c.isOccupied() && c.getColor()==stoneColor.oppositeColor()));
    }

    public Set<Cell> legalCellsOf(Stone color) {
        return cells.stream()
                    .filter(c->!c.isOccupied())
                    .filter(c->checkTheTwoRules(c, color))
                    .collect(Collectors.toSet());
    }

    private boolean checkTheTwoRules(Cell cell, Stone color){
        Stone availableStone = color;
        this.placeStoneAt(availableStone, cell.getPosition());
        boolean ruleOne = !(isCrosscutPlacement(cell));
        boolean ruleTwo = isLegalWeakConnectionPlacement(cell);
        cell.reset();
        return ruleOne && ruleTwo;
    }

    public boolean checkChain(Stone color) {
        Set<Position> visitedCells = new HashSet<>();
        if(color== Stone.WHITE) {
            for(int y=1; y<= dimension; y++) {
                if(visitedCells.contains(at(1, y))) continue;
                Cell source = getCellAt(at(1, y));
                if(source.isOccupied() && source.getColor()==color){
                    if(chainSearch(source, visitedCells)) return true;
                }
            }
        }
        else {
            for (int x = 1; x <= dimension; x++) {
                if (visitedCells.contains(at(x, dimension ))) continue;
                Cell source = getCellAt(at(x, dimension));
                if (source.isOccupied() && source.getColor() == color) {
                    if (chainSearch(source, visitedCells)) return true;
                }
            }
        }
        return false;
    }

    private boolean chainSearch(Cell source, Set<Position> visitedCells) {
        visitedCells.add(source.getPosition());
        if(source.getColor() == Stone.WHITE && source.getPosition().getX()==dimension) return true;
        if(source.getColor() == Stone.BLACK && source.getPosition().getY()==1) return true;
        for(Cell cell: neighborsOf(source)){
            if(visitedCells.contains(cell.getPosition())) continue;
            if(chainSearch(cell, visitedCells)) return true;
        }
        return false;
    }
}

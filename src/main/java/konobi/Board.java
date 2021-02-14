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



    public Cell getCellAt(Position position) {  //TODO: make more streamable

        List<Cell> returnedCell = cells.stream()
                                       .filter(c->c.isAt(position))
                                       .collect(Collectors.toList());

        return returnedCell.isEmpty() ? null : returnedCell.get(0);


        /*for (Cell cell : cells){
            if (cell.isAt(position)){
                return cell;
            }
        }
        return null;*/
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


        /*
        for(Cell cellOnBoard : cells) {
            if(cell.getPosition().squareEuclideanDistanceFrom(cellOnBoard.getPosition())==2) {
                neighbors.add(cellOnBoard);
            }

        }
        return neighbors;*/
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
        for (Cell weakNeighbor : weakNeighbors){
            Set<Cell> potentialCrosscut = commonOrthogonalNeighbors(cell, weakNeighbor);
            List<Boolean> conditionOnCrossCell = new ArrayList<>();
            for (Cell crossCell : potentialCrosscut){
                conditionOnCrossCell.add(crossCell.isOccupied() && crossCell.getColor()==stoneColor.oppositeColor());
            }
            if (!Arrays.asList(conditionOnCrossCell).contains(false)) return true;
        }
        return false;

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
        HashMap<Position, Boolean> visitedCells = new HashMap<>();
        for(Cell cell: cells){
            visitedCells.put(cell.getPosition(), false);
        }
        if(color== Stone.WHITE) {
            for(int y=1; y<= dimension; y++) {
                if(visitedCells.get(at(1, y))) continue;
                Cell source = getCellAt(at(1, y));
                if(source.isOccupied() && source.getColor()==color){
                    if(chainSearch(source, visitedCells)) return true;
                }
            }
        }
        else {
            for (int x = 1; x <= dimension; x++) {
                if (visitedCells.get(at(x, dimension ))) continue;
                Cell source = getCellAt(at(x, dimension));
                if (source.isOccupied() && source.getColor() == color) {
                    if (chainSearch(source, visitedCells)) return true;
                }
            }
        }
        return false;
    }

    private boolean chainSearch(Cell source, HashMap<Position, Boolean> visitedCells) {
        visitedCells.put(source.getPosition(), true);
        if(source.getColor() == Stone.WHITE && source.getPosition().getX()==dimension) return true;
        if(source.getColor() == Stone.BLACK && source.getPosition().getY()==1) return true;
        for(Cell cell: neighborsOf(source)){
            if(visitedCells.get(cell.getPosition())) continue;
            if(chainSearch(cell, visitedCells)) return true;
        }
        return false;
    }





/*
    public boolean areWeaklyConnected(Stone stone1, Stone stone2) {
        return (stone1.isDiagonallyAdjacentTo(stone2) && commonStrongNeighborsOf(stone1, stone2).isEmpty());
    }

    public boolean areStronglyConnected(Stone stone1, Stone stone2) {
        return stone1.isStronglyConnectedWith(stone2);
    }

    public List<Stone> commonStrongNeighborsOf(Stone stone1, Stone stone2) {
        List<Stone> commonStrongNeighbors = new ArrayList<>();
        for(Stone t : stones){
            if(areStronglyConnected(stone1,t) && areStronglyConnected(stone2,t))
                commonStrongNeighbors.add(t);
        }
        return commonStrongNeighbors;
    }*/
}

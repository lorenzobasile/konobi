package konobi;

import java.util.*;

import static konobi.Position.at;

public class Board {
    int dimension;
    List<Cell> cells = new ArrayList<>();

    public Board(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i<dimension; ++i){
            for(int j = 0; j<dimension; ++j){
                cells.add(new Cell(at(i,j)));
            }
        }
    }



    public Cell getCellAt(Position position) {

        for (Cell cell : cells){
            if (cell.isAt(position)){
                return cell;
            }
        }
        return null;
    }

    public void placeStoneAt(Stone stone, Position position) {
        Cell cellToOccupy = this.getCellAt(position);
        cellToOccupy.setStone(stone);
    }

    public Set<Cell> orthogonalNeighborsOf(Cell cell) { //TODO: refactor

        Set<Cell> neighbors = new HashSet<>();


        Cell cellAtLeft = getCellAt(cell.getPosition().atLeft());
        if(!(cellAtLeft ==null)){
            neighbors.add(cellAtLeft);
        }

        Cell cellAtRight = getCellAt(cell.getPosition().atRight());
        if(!(cellAtRight ==null)){
            neighbors.add(cellAtRight);
        }

        Cell cellAbove = getCellAt(cell.getPosition().top());
        if(!(cellAbove ==null)){
            neighbors.add(cellAbove);
        }

        Cell cellBelow = getCellAt(cell.getPosition().bottom());
        if(!(cellBelow ==null)){
            neighbors.add(cellBelow);
        }

        return neighbors;
    }

    public Set<Cell> strongNeighborsOf(Cell cell) {   // TODO: stream filter sameColorAs
        if(!cell.isOccupied())
           return new HashSet<>();
        Set<Cell> neighbors = new HashSet<>();
        Stone thisStone = cell.getCurrentStone();

        for(Cell neighbor : orthogonalNeighborsOf(cell)) {
            if (neighbor.isOccupied()) {
                Stone neighborStone = neighbor.getCurrentStone();
                if (neighborStone.hasSameColorAs(thisStone)) {
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    public Set<Cell> weakNeighborsOf(Cell cell) {
        if(!cell.isOccupied())
            return new HashSet<>();
        Set<Cell> neighbors = new HashSet<>();
        Stone thisStone = cell.getCurrentStone();

        for(Cell neighbor : diagonalNeighborsOf(cell)){
            if (neighbor.isOccupied()){
                Stone neighborStone = neighbor.getCurrentStone();
                if (neighborStone.hasSameColorAs(thisStone)){
                    Set<Cell> strongNeighborsOfCell = commonStrongNeighbors(cell, neighbor);
                    if (strongNeighborsOfCell.isEmpty()){
                        neighbors.add(neighbor);
                    }
                }
            }
        }

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

    private Set<Cell> commonStrongNeighbors(Cell cell, Cell neighbor) {   // TODO: call commonOrthogonal and filter same color
        Set<Cell> strongNeighborsOfCell = strongNeighborsOf(cell);
        Set<Cell> strongNeighborsOfDiagonalCell = strongNeighborsOf(neighbor);
        strongNeighborsOfCell.retainAll(strongNeighborsOfDiagonalCell);
        return strongNeighborsOfCell;
    }

    public Set<Cell> diagonalNeighborsOf(Cell cell) {

        Set<Cell> neighbors = new HashSet<>();

        Cell cellAtUpperLeft = getCellAt(cell.getPosition().upperLeft());
        if(!(cellAtUpperLeft==null)){
            neighbors.add(cellAtUpperLeft);
        }

        Cell cellAtUpperRight = getCellAt(cell.getPosition().upperRight());
        if(!(cellAtUpperRight==null)){
            neighbors.add(cellAtUpperRight);
        }

        Cell cellAtLowerLeft = getCellAt(cell.getPosition().lowerLeft());
        if(!(cellAtLowerLeft==null)){
            neighbors.add(cellAtLowerLeft);
        }

        Cell cellAtLowerRight = getCellAt(cell.getPosition().lowerRight());
        if(!(cellAtLowerRight==null)){
            neighbors.add(cellAtLowerRight);
        }

        return neighbors;
    }

    public boolean isLegalWeakConnectionPlacement(Cell cell) {
        Set<Cell> weakNeighbors = weakNeighborsOf(cell);
        Color cellColor = cell.getCurrentStone().getColor();
        cell.reset();
        for (Cell weakNeighbor : weakNeighbors) {
            for (Cell orthogonalNeighborOfWeakNeighbor : orthogonalNeighborsOf(weakNeighbor)) {
                if (!orthogonalNeighborOfWeakNeighbor.isOccupied()) {
                    placeStoneAt(new Stone(cellColor), orthogonalNeighborOfWeakNeighbor.getPosition());
                    boolean weakCondition = weakNeighborsOf(orthogonalNeighborOfWeakNeighbor).isEmpty();
                    orthogonalNeighborOfWeakNeighbor.reset();
                    if(weakCondition) {
                        return false;
                    }
                }
            }
        }
        placeStoneAt(new Stone(cellColor), cell.getPosition());
        return true;
    }

    public boolean isCrosscutPlacement(Cell cell) {
        Set<Cell> weakNeighbors = weakNeighborsOf(cell);
        Color cellColor = cell.getCurrentStone().getColor();
        for (Cell weakNeighbor : weakNeighbors){
            Set<Cell> potentialCrosscut = commonOrthogonalNeighbors(cell, weakNeighbor);
            List<Boolean> conditionOnCrossCell = new ArrayList<>();
            for (Cell crossCell : potentialCrosscut){
                conditionOnCrossCell.add(crossCell.isOccupied() && crossCell.getCurrentStone().getColor()==cellColor.oppositeColor());
            }
            if (!Arrays.asList(conditionOnCrossCell).contains(false)) return true;
        }
        return false;

    }

    public Set<Cell> legalCellsOf(Color color) {
        Set<Cell> setOfLegalCells = new HashSet<>();
        for (Cell cellOnBoard : this.cells){
            if(!cellOnBoard.isOccupied()){
                Stone availableStone = new Stone(color);
                this.placeStoneAt(availableStone, cellOnBoard.getPosition());
                boolean ruleOne = !(isCrosscutPlacement(cellOnBoard));
                boolean ruleTwo = isLegalWeakConnectionPlacement(cellOnBoard);
                if (ruleOne && ruleTwo){
                    setOfLegalCells.add(cellOnBoard);
                }
                cellOnBoard.reset();
            }
        }

        return setOfLegalCells;
    }

    public boolean checkChain(Color color) {
        HashMap<Position, Boolean> visitedCells = new HashMap<>();
        for(Cell cell: cells){
            visitedCells.put(cell.getPosition(), false);
        }
        if(color==Color.WHITE) {
            for(int y=0; y<dimension; y++) {
                if(visitedCells.get(at(0, y))) continue;
                Cell source = getCellAt(at(0, y));
                if(source.isOccupied() && source.getCurrentStone().getColor()==color){
                    if(chainSearch(source, visitedCells)) return true;
                }
            }
        }
        else {
            for (int x = 0; x < dimension; x++) {
                if (visitedCells.get(at(x, dimension - 1))) continue;
                Cell source = getCellAt(at(x, dimension - 1));
                if (source.isOccupied() && source.getCurrentStone().getColor() == color) {
                    if (chainSearch(source, visitedCells)) return true;
                }
            }
        }
        return false;
    }

    private boolean chainSearch(Cell source, HashMap<Position, Boolean> visitedCells) {
        visitedCells.put(source.getPosition(), true);
        if(source.getCurrentStone().getColor()==Color.WHITE && source.getPosition().getX()==dimension-1) return true;
        if(source.getCurrentStone().getColor()==Color.BLACK && source.getPosition().getY()==0) return true;
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

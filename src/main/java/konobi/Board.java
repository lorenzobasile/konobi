package konobi;

import java.util.ArrayList;
import java.util.List;

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

    public Cell getCellAt(Position position) throws Exception{
        for (Cell cell : cells){
            if (cell.isAt(position)){
                return cell;
            }
        }
        throw new Exception("Position outside board");
    }

    public void placeStoneAt(Stone stone, Position position) throws Exception{
        Cell cellToOccupy = this.getCellAt(position);
        cellToOccupy.setCurrentStone(stone);
    }

    public List<Cell> orthogonalNeighborsOf(Cell cell) {

        List<Cell> neighbors = new ArrayList<>();

        try {
            Cell cellAtLeft = getCellAt(cell.getPosition().atLeft());
            neighbors.add(cellAtLeft);
        }
        catch(Exception cellOutOfBoard){}

        try {
            Cell cellAtRight = getCellAt(cell.getPosition().atRight());
            neighbors.add(cellAtRight);
        }
        catch(Exception cellOutOfBoard){}

        try {
            Cell cellAbove = getCellAt(cell.getPosition().top());
            neighbors.add(cellAbove);
        }
        catch(Exception cellOutOfBoard){}

        try {
            Cell cellBelow = getCellAt(cell.getPosition().bottom());
            neighbors.add(cellBelow);
        }
        catch(Exception cellOutOfBoard){}

        return neighbors;
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

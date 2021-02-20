package konobi;


import konobi.Model.Entities.Board;
import konobi.Model.Entities.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static konobi.Model.Entities.Position.at;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testCell {

    @ParameterizedTest
    @CsvSource({"2, 3",
                "4, 2",
                "3, 3"})
    public void verifyOrthogonalNeighborsOfInnerCells(int x, int y) {
        Board board = new Board(7);

        Cell cell = board.getCell(at(x,y));
        Cell right = board.getCell(at(x+1,y));
        Cell left = board.getCell(at(x-1,y));
        Cell top = board.getCell(at(x,y+1));
        Cell bottom = board.getCell(at(x,y-1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(top,left,bottom,right));
        assertEquals(neighborsList,cell.orthogonalNeighborsIn(board.cells));
    }

    @ParameterizedTest
    @CsvSource({"1, 2",
                "1, 4"})
    public void verifyOrthogonalNeighborsOfCellsOnLeftEdge(int x, int y) {
        Board board = new Board(7);
        Cell cell = board.getCell(at(x,y));
        Cell right = board.getCell(at(x+1,y));
        Cell top = board.getCell(at(x,y+1));
        Cell bottom = board.getCell(at(x,y-1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(top,bottom,right));
        assertEquals(neighborsList,cell.orthogonalNeighborsIn(board.cells));
    }

    @Test
    public void verifyOrthogonalNeighborsOfCellsOnTopRightCorner() {
        Board board = new Board(7);
        Cell cell = board.getCell(at(7,7));
        Cell left = board.getCell(at(6,7));
        Cell bottom = board.getCell(at(7,6));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(bottom,left));
        assertEquals(neighborsList,cell.orthogonalNeighborsIn(board.cells));
    }


    @ParameterizedTest
    @CsvSource({"2, 3",
                "4, 2",
                "3, 3"})
    public void verifyDiagonalNeighborsOfInnerCells(int x, int y) {
        Board board = new Board(7);

        Cell cell = board.getCell(at(x,y));
        Cell upperRight = board.getCell(at(x+1,y+1));
        Cell lowerRight = board.getCell(at(x+1,y-1));
        Cell upperLeft = board.getCell(at(x-1,y+1));
        Cell lowerLeft = board.getCell(at(x-1,y-1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(upperRight, lowerRight,upperLeft,lowerLeft));
        assertEquals(neighborsList,cell.diagonalNeighborsIn(board.cells));
    }

    @Test
    public void verifyDiagonalNeighborsOfTopRightCorner() {
        Board board = new Board(7);
        Cell cell = board.getCell(at(7,7));
        Cell lowerLeft = board.getCell(at(6,6));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(lowerLeft));
        assertEquals(neighborsList,cell.diagonalNeighborsIn(board.cells));
    }
}

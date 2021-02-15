package konobi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static konobi.Position.at;

public class testConnections {


    @ParameterizedTest
    @CsvSource({"2, 3",
                "4, 2",
                "3, 3"})
    public void verifyOrthogonalNeighborsOfInnerCells(int x, int y) {
        Board board = new Board(7);
        Connections connections = new Connections(board);
        Cell cell = board.getCell(at(x,y));
        Cell right = board.getCell(at(x+1,y));
        Cell left = board.getCell(at(x-1,y));
        Cell top = board.getCell(at(x,y+1));
        Cell bottom = board.getCell(at(x,y-1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(top,left,bottom,right));
        assertEquals(neighborsList,connections.orthogonalNeighborsOf(cell));
    }

    @ParameterizedTest
    @CsvSource({"1, 2",
                "1, 4"})
    public void verifyOrthogonalNeighborsOfCellsOnLeftEdge(int x, int y) {
        Board board = new Board(7);
        Connections connections = new Connections(board);
        Cell cell = board.getCell(at(x,y));
        Cell right = board.getCell(at(x+1,y));
        Cell top = board.getCell(at(x,y+1));
        Cell bottom = board.getCell(at(x,y-1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(top,bottom,right));
        assertEquals(neighborsList,connections.orthogonalNeighborsOf(cell));
    }

    @Test
    public void verifyOrthogonalNeighborsOfCellsOnTopRightCorner() {
        Board board = new Board(7);
        Connections connections = new Connections(board);
        Cell cell = board.getCell(at(7,7));
        Cell left = board.getCell(at(6,7));
        Cell bottom = board.getCell(at(7,6));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(bottom,left));
        assertEquals(neighborsList,connections.orthogonalNeighborsOf(cell));
    }


    @ParameterizedTest
    @CsvSource({"2, 3",
                "4, 2",
                "3, 3"})
    public void verifyDiagonalNeighborsOfInnerCells(int x, int y) {
        Board board = new Board(7);
        Connections connections = new Connections(board);
        Cell cell = board.getCell(at(x,y));
        Cell upperRight = board.getCell(at(x+1,y+1));
        Cell lowerRight = board.getCell(at(x+1,y-1));
        Cell upperLeft = board.getCell(at(x-1,y+1));
        Cell lowerLeft = board.getCell(at(x-1,y-1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(upperRight, lowerRight,upperLeft,lowerLeft));
        assertEquals(neighborsList,connections.diagonalNeighborsOf(cell));
    }

    @Test
    public void verifyDiagonalNeighborsOfTopRightCorner() {
        Board board = new Board(7);
        Connections connections = new Connections(board);
        Cell cell = board.getCell(at(7,7));
        Cell lowerLeft = board.getCell(at(6,6));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(lowerLeft));
        assertEquals(neighborsList,connections.diagonalNeighborsOf(cell));
    }

    @Test
    public void stoneOfOppositeColorIsNotStronglyConnected() {
        Board board = new Board(3);
        Connections connections = new Connections(board);
        board.placeStone(at(2,2), Color.BLACK);
        board.placeStone(at(2,1), Color.WHITE);
        assertEquals(true,connections.strongNeighborsOf(board.getCell(at(2,2))).isEmpty());
    }

    @Test
    public void stoneOfSameColorAtLeftIsStronglyConnected() {
        Board board = new Board(6);
        Connections connections = new Connections(board);
        board.placeStone(at(2,2), Color.BLACK);
        board.placeStone(at(1,2), Color.BLACK);
        board.placeStone(at(3,2), Color.WHITE);
        Cell strongConnectedStone = board.getCell(at(1,2));
        assertEquals(true,connections.strongNeighborsOf(board.getCell(at(2,2))).contains(strongConnectedStone));
    }

    @Test
    public void twoStonesWithCommonStrongConnectionAreNotWeaklyConnected() {
        Board board = new Board(4);
        Connections connections = new Connections(board);
        board.placeStone(at(2,3), Color.BLACK);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(3,2), Color.BLACK);
        assertEquals(false,connections.weakNeighborsOf(board.getCell(at(2,3))).contains(board.getCell(at(3,2))));
    }

    @Test
    public void stoneHasAllFourWeakConnections() {
        Board board = new Board(5);
        Connections connections = new Connections(board);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(2,2), Color.BLACK);
        board.placeStone(at(2,4), Color.BLACK);
        board.placeStone(at(4,4), Color.BLACK);
        board.placeStone(at(4,2), Color.BLACK);
        assertEquals(4,connections.weakNeighborsOf(board.getCell(at(3,3))).size());
    }

}

package konobi;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.*;

public class testBoard {

    @Test
    public void afterPlacingStoneCellIsOccupied() {
        Board board = new Board(3);
        board.placeStone(at(1,1), Color.BLACK);
        Cell occupiedCell = board.getCell(at(1, 1));
        assertTrue(occupiedCell.isOccupied());
    }

    @Test
    public void stoneOfOppositeColorIsNotStronglyConnected() {
        Board board = new Board(3);
        board.placeStone(at(2,2), Color.BLACK);
        board.placeStone(at(2,1), Color.WHITE);
        assertTrue(board.strongConnectionsOf(board.getCell(at(2, 2))).isEmpty());
    }

    @Test
    public void stoneOfSameColorAtLeftIsStronglyConnected() {
        Board board = new Board(6);
        board.placeStone(at(2,2), Color.BLACK);
        board.placeStone(at(1,2), Color.BLACK);
        board.placeStone(at(3,2), Color.WHITE);
        Cell strongConnectedStone = board.getCell(at(1,2));
        assertTrue(board.strongConnectionsOf(board.getCell(at(2, 2))).contains(strongConnectedStone));
    }

    @Test
    public void twoStonesWithCommonStrongConnectionAreNotWeaklyConnected() {
        Board board = new Board(4);
        board.placeStone(at(2,3), Color.BLACK);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(3,2), Color.BLACK);
        assertFalse(board.weakConnectionsOf(board.getCell(at(2, 3))).contains(board.getCell(at(3, 2))));
    }

    @Test
    public void stoneHasAllFourWeakConnections() {
        Board board = new Board(5);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(2,2), Color.BLACK);
        board.placeStone(at(2,4), Color.BLACK);
        board.placeStone(at(4,4), Color.BLACK);
        board.placeStone(at(4,2), Color.BLACK);
        assertEquals(4,board.weakConnectionsOf(board.getCell(at(3,3))).size());
    }

    @ParameterizedTest
    @CsvSource({"1, 3",
                "2, 3",
                "3, 3"})
    public void blackStonesAtTopAreInStartEdge(int x, int y){
        Board board = new Board(3);
        board.placeStone(at(x, y), Color.BLACK);
        Cell cell  = board.getCell(at(x, y));
        Set<Cell> cellsOnTopEdge = board.boardEdge(Color.BLACK, true);
        assertEquals(true, cellsOnTopEdge.contains(cell));
    }

    @ParameterizedTest
    @CsvSource({"1, 1",
                "1, 2",
                "1, 3"})
    public void whiteStonesAtLeftAreInStartEdge(int x, int y){
        Board board = new Board(3);
        board.placeStone(at(x, y), Color.WHITE);
        Cell cell  = board.getCell(at(x, y));
        Set<Cell> cellsOnTopEdge = board.boardEdge(Color.WHITE, true);
        assertEquals(true, cellsOnTopEdge.contains(cell));
    }

    @ParameterizedTest
    @CsvSource({"1, 1",
                "2, 1",
                "3, 1"})
    public void blackStonesAtLeftAreInEndEdge(int x, int y){
        Board board = new Board(3);
        board.placeStone(at(x, y), Color.BLACK);
        Cell cell  = board.getCell(at(x, y));
        Set<Cell> cellsOnTopEdge = board.boardEdge(Color.BLACK, false);
        assertEquals(true, cellsOnTopEdge.contains(cell));
    }

    @ParameterizedTest
    @CsvSource({"3, 1",
                "3, 2",
                "3, 3"})
    public void whiteStonesAtRightAreInEndEdge(int x, int y){
        Board board = new Board(3);
        board.placeStone(at(x, y), Color.WHITE);
        Cell cell  = board.getCell(at(x, y));
        Set<Cell> cellsOnTopEdge = board.boardEdge(Color.WHITE, false);
        assertEquals(true, cellsOnTopEdge.contains(cell));
    }

}

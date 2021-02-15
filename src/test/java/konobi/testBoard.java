package konobi;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testBoard {

    @Test
    public void placeStoneAndVerifyCellIsOccupied() throws Exception {
        Board board = new Board(3);
        board.placeStoneAt(Color.BLACK, at(1,1));
        Cell occupiedCell = board.getCellAt(at(1, 1));
        assertEquals(true, occupiedCell.isOccupied());
    }

    @ParameterizedTest
    @CsvSource({"1, 3",
                "2, 3",
                "3, 3"})
    public void blackStonesAtTopAreInStartEdge(int x, int y){
        Board board = new Board(3);
        board.placeStoneAt(Color.BLACK, at(x, y));
        Cell cell  = board.getCellAt(at(x, y));
        Set<Cell> cellsOnTopEdge = board.startEdge(Color.BLACK);
        assertEquals(true, cellsOnTopEdge.contains(cell));
    }

    @ParameterizedTest
    @CsvSource({"1, 1",
                "1, 2",
                "1, 3"})
    public void whiteStonesAtLeftAreInStartEdge(int x, int y){
        Board board = new Board(3);
        board.placeStoneAt(Color.WHITE, at(x, y));
        Cell cell  = board.getCellAt(at(x, y));
        Set<Cell> cellsOnTopEdge = board.startEdge(Color.WHITE);
        assertEquals(true, cellsOnTopEdge.contains(cell));
    }

    @ParameterizedTest
    @CsvSource({"1, 1",
                "2, 1",
                "3, 1"})
    public void blackStonesAtLeftAreInEndEdge(int x, int y){
        Board board = new Board(3);
        board.placeStoneAt(Color.BLACK, at(x, y));
        Cell cell  = board.getCellAt(at(x, y));
        Set<Cell> cellsOnTopEdge = board.endEdge(Color.BLACK);
        assertEquals(true, cellsOnTopEdge.contains(cell));
    }

    @ParameterizedTest
    @CsvSource({"3, 1",
                "3, 2",
                "3, 3"})
    public void whiteStonesAtRightAreInEndEdge(int x, int y){
        Board board = new Board(3);
        board.placeStoneAt(Color.WHITE, at(x, y));
        Cell cell  = board.getCellAt(at(x, y));
        Set<Cell> cellsOnTopEdge = board.endEdge(Color.WHITE);
        assertEquals(true, cellsOnTopEdge.contains(cell));
    }

}

package konobi;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testBoard {

    @Test
    public void afterPlacingStoneCellIsOccupied() {
        Board board = new Board(3);
        board.placeStone(at(1,1), Color.BLACK);
        Cell occupiedCell = board.getCell(at(1, 1));
        assertEquals(true, occupiedCell.isOccupied());
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

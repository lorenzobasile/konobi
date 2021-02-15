package konobi;


import org.junit.jupiter.api.Test;

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



/*
    @ParameterizedTest
    @CsvSource({"0, 0, 1, 0", "1, 1, 1, 2"})
    public void areStronglyConnected(int x1, int y1, int x2, int y2) throws Exception {
        Board board = new Board(8);
        Stone stone1 = board.placeTileAt(Color.BLACK, at(x1,y1));
        Stone stone2 = board.placeTileAt(Color.BLACK, at(x2,y2));
        assertEquals(true, stone1.isStronglyConnectedWith(stone2));
    }

    @ParameterizedTest
    @CsvSource({"2, 2, 4, 4", "0, 1, 3, 1"})
    public void areNotStronglyConnected(int x1, int y1, int x2, int y2) throws Exception {
        Board board = new Board(8);
        Stone stone1 = board.placeTileAt(Color.BLACK, at(x1,y1));
        Stone stone2 = board.placeTileAt(Color.BLACK, at(x2,y2));
        assertEquals(false, stone1.isStronglyConnectedWith(stone2));
    }

    @ParameterizedTest
    @CsvSource({"0, 0, 1, 1", "5, 2, 6, 1"})
    public void weakConnectionWithTwoTilesInBoard(int x1, int y1, int x2, int y2) throws Exception {
        Board board = new Board(8);
        Stone stone1 = board.placeTileAt(Color.BLACK, at(x1,y1));
        Stone stone2 = board.placeTileAt(Color.BLACK, at(x2,y2));
        assertEquals(true, board.areWeaklyConnected(stone1, stone2));
    }

    @Test
    public void weakConnectionWithCommonStrongNeighbor() throws Exception {
        Board board = new Board(8);
        Stone stone1 = board.placeTileAt(Color.BLACK, at(0,0));
        Stone stone2 = board.placeTileAt(Color.BLACK, at(1,1));
        Stone stone3 = board.placeTileAt(Color.BLACK, at(1,0));
        assertEquals(false, board.areWeaklyConnected(stone1, stone2));
    }*/

}

package konobi.Entities;

import org.junit.jupiter.api.Test;
import static konobi.Entities.Position.at;
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
    public void stonesOfOppositeColorsAreNotStronglyConnected() {
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

}

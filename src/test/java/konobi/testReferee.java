package konobi;

import konobi.Entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static konobi.Entities.Position.at;
import static org.junit.jupiter.api.Assertions.*;

public class testReferee {
    private Referee referee;
    private Board board;

    @BeforeEach
    public void initialize(){
        board = new Board(5);
        referee = new Referee(board);
    }

    @Test
    public void initiallyAllBlackMovesAreLegal() {
        assertEquals(board, referee.availableCellsFor(Color.BLACK));
    }

    @Test
    public void initiallyAllWhiteMovesAreLegal() {
        assertEquals(board, referee.availableCellsFor(Color.WHITE));
    }

    @Test
    public void checkIfMoveIsLegal() {
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
        Set<Cell> availableCellsForBlack = referee.availableCellsFor(Color.BLACK);
        assertTrue(availableCellsForBlack.contains(cellToVerify));

    }

    @Test
    public void checkIfMoveIsIllegal() {
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
        Set<Cell> availableCellsForWhite = referee.availableCellsFor(Color.WHITE);
        assertFalse(availableCellsForWhite.contains(cellToVerify));

    }

    @Test
    public void blackStonesMakeChain() {
        board.placeStone(at(1,5), Color.BLACK);
        board.placeStone(at(1,4), Color.BLACK);
        board.placeStone(at(2,4), Color.BLACK);
        board.placeStone(at(2,2), Color.BLACK);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(4,4), Color.BLACK);
        board.placeStone(at(4,1), Color.BLACK);
        board.placeStone(at(5,3), Color.BLACK);
        board.placeStone(at(5,2), Color.BLACK);
        board.placeStone(at(1,1), Color.WHITE);
        board.placeStone(at(4,3), Color.WHITE);
        board.placeStone(at(5,1), Color.WHITE);
        board.placeStone(at(3,5), Color.BLACK);
        assertTrue(referee.validateChain(Color.BLACK));
    }

    @Test
    public void blackStonesDoNotMakeChain() {
        board.placeStone(at(3,4), Color.BLACK);
        board.placeStone(at(1,4), Color.BLACK);
        board.placeStone(at(2,4), Color.BLACK);
        board.placeStone(at(2,2), Color.BLACK);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(4,4), Color.BLACK);
        board.placeStone(at(4,1), Color.BLACK);
        board.placeStone(at(5,3), Color.BLACK);
        board.placeStone(at(5,2), Color.BLACK);
        board.placeStone(at(1,1), Color.WHITE);
        board.placeStone(at(4,3), Color.WHITE);
        board.placeStone(at(5,1), Color.WHITE);
        assertFalse(referee.validateChain(Color.BLACK));
    }

    @Test
    public void whiteStonesMakeChain() {
        board.placeStone(at(2,2), Color.WHITE);
        board.placeStone(at(2,3), Color.WHITE);
        board.placeStone(at(2,4), Color.WHITE);
        board.placeStone(at(3,2), Color.WHITE);
        board.placeStone(at(3,4), Color.WHITE);
        board.placeStone(at(4,3), Color.WHITE);
        board.placeStone(at(4,5), Color.WHITE);
        board.placeStone(at(5,1), Color.WHITE);
        board.placeStone(at(5,2), Color.WHITE);
        board.placeStone(at(1,3), Color.WHITE);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(4,1), Color.BLACK);
        board.placeStone(at(5,3), Color.BLACK);
        assertTrue(referee.validateChain(Color.WHITE));
    }

    @Test
    public void whiteStonesDoNotMakeChain() {
        board.placeStone(at(1,3), Color.WHITE);
        board.placeStone(at(2,2), Color.WHITE);
        board.placeStone(at(2,3), Color.WHITE);
        board.placeStone(at(2,4), Color.WHITE);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(3,4), Color.WHITE);
        board.placeStone(at(3,5), Color.WHITE);
        board.placeStone(at(4,4), Color.WHITE);
        board.placeStone(at(1,4), Color.BLACK);
        board.placeStone(at(5,4), Color.BLACK);
        assertFalse(referee.validateChain(Color.WHITE));
    }
}

package konobi;

import konobi.Entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static konobi.Entities.Position.at;
import static org.junit.jupiter.api.Assertions.*;

public class testReferee {

    @Test
    public void initiallyAllBlackMovesAreLegal() {
        Referee referee = new Referee();
        Player player = new Player(Color.BLACK, "player");
        Board board = new Board(10);
        Set<Cell> allCells = new HashSet<Cell>(board.cells);
        assertEquals(allCells, referee.availableCellsFor(player,board));
    }

    @Test
    public void initiallyAllWhiteMovesAreLegal() {
        Referee referee = new Referee();
        Player player = new Player(Color.WHITE, "player");
        Board board = new Board(10);
        Set<Cell> allCells = new HashSet<Cell>(board.cells);
        assertEquals(allCells, referee.availableCellsFor(player,board));
    }

    @Test
    public void checkIfMoveIsLegal() {
        Referee referee = new Referee();
        Player player = new Player(Color.BLACK, "player");
        Board board = new Board(5);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
        Set<Cell> availableCellsForBlack = referee.availableCellsFor(player,board);
        assertTrue(availableCellsForBlack.contains(cellToVerify));

    }

    @Test
    public void checkIfMoveIsIllegal() {
        Referee referee = new Referee();
        Player player = new Player(Color.WHITE, "player");
        Board board = new Board(5);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
        Set<Cell> availableCellsForWhite = referee.availableCellsFor(player,board);
        assertFalse(availableCellsForWhite.contains(cellToVerify));

    }

    @Test
    public void blackStonesMakeChain() {
        Referee referee = new Referee();
        Board board = new Board(5);
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
        assertTrue(referee.validateChain(board, Color.BLACK));
    }

    @Test
    public void blackStonesDoNotMakeChain() {
        Referee referee = new Referee();
        Board board = new Board(5);
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
        assertFalse(referee.validateChain(board, Color.BLACK));
    }

    @Test
    public void whiteStonesMakeChain() {
        Referee referee = new Referee();
        Board board = new Board(5);
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
        assertTrue(referee.validateChain(board, Color.WHITE));
    }

    @Test
    public void whiteStonesDoNotMakeChain() {
        Referee referee = new Referee();
        Board board = new Board(5);
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
        assertFalse(referee.validateChain(board, Color.WHITE));
    }
}

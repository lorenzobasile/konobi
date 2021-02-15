package konobi;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testRules {


    @Test
    public void verifyLegalWeakConnectionPlacement() throws Exception {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.BLACK, at(1,1));
        board.placeStoneAt(Color.WHITE, at(1,2));
        board.placeStoneAt(Color.BLACK, at(3,2));
        board.placeStoneAt(Color.WHITE, at(3,3));

        board.placeStoneAt(Color.BLACK, at(2,2));
        Cell cellToVerify = board.getCellAt(at(2,2));
        assertEquals(true, rules.isLegalWeakConnectionPlacement(cellToVerify));
    }

    @Test
    public void verifyIllegalWeakConnectionPlacement() throws Exception {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.BLACK, at(1,1));
        board.placeStoneAt(Color.WHITE, at(1,2));
        board.placeStoneAt(Color.BLACK, at(3,2));
        board.placeStoneAt(Color.WHITE, at(3,3));

        board.placeStoneAt(Color.BLACK, at(2,1));
        Cell cellToVerify = board.getCellAt(at(2,1));
        assertEquals(false, rules.isLegalWeakConnectionPlacement(cellToVerify));
    }

    @Test
    public void verifyCrosscut() throws Exception {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.BLACK, at(1,1));
        board.placeStoneAt(Color.WHITE, at(1,2));
        board.placeStoneAt(Color.BLACK, at(3,2));
        board.placeStoneAt(Color.WHITE, at(3,3));
        board.placeStoneAt(Color.BLACK, at(2,2));

        board.placeStoneAt(Color.WHITE, at(2, 1));
        Cell cellToVerify = board.getCellAt(at(2,1));
        assertEquals(true, rules.isCrosscutPlacement(cellToVerify));
    }

    @Test
    public void verifyNotCrosscut() throws Exception {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.BLACK, at(1,1));
        board.placeStoneAt(Color.WHITE, at(1,2));
        board.placeStoneAt(Color.BLACK, at(3,2));
        board.placeStoneAt(Color.WHITE, at(3,3));
        board.placeStoneAt(Color.BLACK, at(2,2));

        board.placeStoneAt(Color.BLACK, at(2, 1));
        Cell cellToVerify = board.getCellAt(at(2,1));
        assertEquals(false, rules.isCrosscutPlacement(cellToVerify));
    }

    @Test
    public void checkIfInitiallyAllMovesAreLegal() throws Exception{
        Board board = new Board(10);
        Rules rules = new Rules(board);
        Set<Cell> allCells = new HashSet<Cell>(board.cells);
        assertEquals(allCells, rules.legalCellsOf(Color.BLACK));
    }

    @Test
    public void checkIfMoveIsLegal() throws Exception{
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.BLACK, at(1,1));
        board.placeStoneAt(Color.WHITE, at(1,2));
        board.placeStoneAt(Color.BLACK, at(3,2));
        board.placeStoneAt(Color.WHITE, at(3,3));
        board.placeStoneAt(Color.BLACK, at(2,2));

        Cell cellToVerify = board.getCellAt(at(2,1));
        Set<Cell> availableCellsForBlack = rules.legalCellsOf(Color.BLACK);
        assertEquals(true, availableCellsForBlack.contains(cellToVerify));

    }

    @Test
    public void checkIfMoveIsIllegal() throws Exception{
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.BLACK, at(1,1));
        board.placeStoneAt(Color.WHITE, at(1,2));
        board.placeStoneAt(Color.BLACK, at(3,2));
        board.placeStoneAt(Color.WHITE, at(3,3));
        board.placeStoneAt(Color.BLACK, at(2,2));

        Cell cellToVerify = board.getCellAt(at(2,1));
        Set<Cell> availableCellsForWhite = rules.legalCellsOf(Color.WHITE);
        assertEquals(false, availableCellsForWhite.contains(cellToVerify));

    }

    @Test
    public void checkBlackWin() throws Exception{
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.BLACK, at(1,5));
        board.placeStoneAt(Color.BLACK, at(1,4));
        board.placeStoneAt(Color.BLACK, at(2,4));
        board.placeStoneAt(Color.BLACK, at(2,2));
        board.placeStoneAt(Color.BLACK, at(3,3));
        board.placeStoneAt(Color.BLACK, at(4,4));
        board.placeStoneAt(Color.BLACK, at(4,1));
        board.placeStoneAt(Color.BLACK, at(5,3));
        board.placeStoneAt(Color.BLACK, at(5,2));
        board.placeStoneAt(Color.WHITE, at(1,1));
        board.placeStoneAt(Color.WHITE, at(4,3));
        board.placeStoneAt(Color.WHITE, at(5,1));
        board.placeStoneAt(Color.BLACK, at(3,5));

        assertEquals(true, rules.checkChain(Color.BLACK));
    }

    @Test
    public void checkNotBlackWin() throws Exception{
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.BLACK, at(3,4));
        board.placeStoneAt(Color.BLACK, at(1,4));
        board.placeStoneAt(Color.BLACK, at(2,4));
        board.placeStoneAt(Color.BLACK, at(2,2));
        board.placeStoneAt(Color.BLACK, at(3,3));
        board.placeStoneAt(Color.BLACK, at(4,4));
        board.placeStoneAt(Color.BLACK, at(4,1));
        board.placeStoneAt(Color.BLACK, at(5,3));
        board.placeStoneAt(Color.BLACK, at(5,2));
        board.placeStoneAt(Color.WHITE, at(1,1));
        board.placeStoneAt(Color.WHITE, at(4,3));
        board.placeStoneAt(Color.WHITE, at(5,1));

        assertEquals(false, rules.checkChain(Color.BLACK));
    }

    @Test
    public void checkWhiteWin() throws Exception{
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.WHITE, at(2,2));
        board.placeStoneAt(Color.WHITE, at(2,3));
        board.placeStoneAt(Color.WHITE, at(2,4));
        board.placeStoneAt(Color.WHITE, at(3,2));
        board.placeStoneAt(Color.WHITE, at(3,4));
        board.placeStoneAt(Color.WHITE, at(4,3));
        board.placeStoneAt(Color.WHITE, at(4,5));
        board.placeStoneAt(Color.WHITE, at(5,1));
        board.placeStoneAt(Color.WHITE, at(5,2));
        board.placeStoneAt(Color.WHITE, at(1,3));


        board.placeStoneAt(Color.BLACK, at(3,3));
        board.placeStoneAt(Color.BLACK, at(4,1));
        board.placeStoneAt(Color.BLACK, at(5,3));

        assertEquals(true, rules.checkChain(Color.WHITE));
    }

    @Test
    public void checkNotWhiteWin() throws Exception{
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStoneAt(Color.WHITE, at(1,3));
        board.placeStoneAt(Color.WHITE, at(2,2));
        board.placeStoneAt(Color.WHITE, at(2,3));
        board.placeStoneAt(Color.WHITE, at(2,4));
        board.placeStoneAt(Color.WHITE, at(3,3));
        board.placeStoneAt(Color.WHITE, at(3,4));
        board.placeStoneAt(Color.WHITE, at(3,5));
        board.placeStoneAt(Color.WHITE, at(4,4));

        board.placeStoneAt(Color.BLACK, at(1,4));
        board.placeStoneAt(Color.BLACK, at(5,4));

        assertEquals(false, rules.checkChain(Color.WHITE));
    }

}

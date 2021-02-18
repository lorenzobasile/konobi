package konobi;

import org.junit.jupiter.api.Test;
import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testRules {

    @Test
    public void blackStoneMakesLegalWeakConnectionPlacement() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        Cell cellToVerify = board.getCell(at(2,2));
        assertEquals(true, rules.checkTheTwoRules(cellToVerify, Color.BLACK));
    }

    @Test
    public void whiteStoneMakesLegalWeakConnectionPlacement() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(3,1), Color.WHITE);
        board.placeStone(at(3,2), Color.WHITE);
        board.placeStone(at(4,1), Color.BLACK);
        board.placeStone(at(1,3), Color.WHITE);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(3,4), Color.BLACK);
        board.placeStone(at(5,3), Color.WHITE);
        board.placeStone(at(1,4), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,3));
        assertEquals(true, rules.checkTheTwoRules(cellToVerify, Color.WHITE));
    }

    @Test
    public void blackStoneMakesIllegalWeakConnectionPlacement() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        Cell cellToVerify = board.getCell(at(2,1));
        assertEquals(false, rules.checkTheTwoRules(cellToVerify, Color.BLACK));
    }

    @Test
    public void whiteStoneMakesIllegalWeakConnectionPlacement() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(3,1), Color.WHITE);
        board.placeStone(at(3,2), Color.WHITE);
        board.placeStone(at(4,1), Color.BLACK);
        board.placeStone(at(1,3), Color.WHITE);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(3,4), Color.BLACK);
        board.placeStone(at(5,3), Color.WHITE);
        board.placeStone(at(1,4), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,2));
        assertEquals(false, rules.checkTheTwoRules(cellToVerify, Color.WHITE));
    }

    @Test
    public void whiteStoneMakesCrosscut() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(1,3), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
        assertEquals(false, rules.checkTheTwoRules(cellToVerify, Color.WHITE));
    }


    @Test
    public void blackStoneDoesNotMakeCrosscut()  {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
        assertEquals(true, rules.checkTheTwoRules(cellToVerify, Color.BLACK));
    }

    /*@Test
    public void initiallyAllBlackMovesAreLegal() {
        Board board = new Board(10);
        Rules rules = new Rules(board);
        Set<Cell> allCells = new HashSet<Cell>(board.cells);
        assertEquals(allCells, rules.legalCellsOf(Color.BLACK));
    }

    @Test
    public void initiallyAllWhiteMovesAreLegal() {
        Board board = new Board(10);
        Rules rules = new Rules(board);
        Set<Cell> allCells = new HashSet<Cell>(board.cells);
        assertEquals(allCells, rules.legalCellsOf(Color.WHITE));
    }

    @Test
    public void checkIfMoveIsLegal() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);

        Cell cellToVerify = board.getCell(at(2,1));
        Set<Cell> availableCellsForBlack = rules.legalCellsOf(Color.BLACK);
        assertEquals(true, availableCellsForBlack.contains(cellToVerify));

    }

    @Test
    public void checkIfMoveIsIllegal() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);

        Cell cellToVerify = board.getCell(at(2,1));
        Set<Cell> availableCellsForWhite = rules.legalCellsOf(Color.WHITE);
        assertEquals(false, availableCellsForWhite.contains(cellToVerify));

    }*/

    @Test
    public void blackStonesMakeChain() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
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
        assertEquals(true, rules.checkChain(Color.BLACK));
    }

    @Test
    public void blackStonesDoNotMakeChain() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
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
        assertEquals(false, rules.checkChain(Color.BLACK));
    }

    @Test
    public void whiteStonesMakeChain() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
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
        assertEquals(true, rules.checkChain(Color.WHITE));
    }

    @Test
    public void whiteStonesDoNotMakeChain() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
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
        assertEquals(false, rules.checkChain(Color.WHITE));
    }

}

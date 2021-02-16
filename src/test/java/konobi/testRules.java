package konobi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

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
        board.placeStone(at(2,2), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,2));
        assertEquals(true, rules.isLegalWeakConnectionPlacement(cellToVerify));
    }

    @Test
    public void whiteStoneMakesLegalWeakConnectionPlacement() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(3,1), Color.WHITE);
        board.placeStone(at(3,2), Color.WHITE);
        board.placeStone(at(4,1), Color.BLACK);
        board.placeStone(at(1,3), Color.WHITE);
        board.placeStone(at(2,3), Color.WHITE);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(3,4), Color.BLACK);
        board.placeStone(at(5,3), Color.WHITE);
        board.placeStone(at(1,4), Color.BLACK);

        board.placeStone(at(2,3), Color.WHITE);
        Cell cellToVerify = board.getCell(at(2,3));
        assertEquals(true, rules.isLegalWeakConnectionPlacement(cellToVerify));
    }

    @Test
    public void verifyIllegalWeakConnectionPlacement() {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);

        board.placeStone(at(2,1), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
        assertEquals(false, rules.isLegalWeakConnectionPlacement(cellToVerify));
    }

    @Test
    public void verifyCrosscut() throws Exception {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);

        board.placeStone(at(2, 1), Color.WHITE);
        Cell cellToVerify = board.getCell(at(2,1));
        assertEquals(true, rules.isCrosscutPlacement(cellToVerify));
    }

    @Test
    public void verifyNotCrosscut() throws Exception {
        Board board = new Board(5);
        Rules rules = new Rules(board);
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);

        board.placeStone(at(2, 1), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
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
    public void checkIfMoveIsIllegal() throws Exception{
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

    }

    @Test
    public void checkBlackWin() throws Exception{
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
    public void checkNotBlackWin() throws Exception{
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
    public void checkWhiteWin() throws Exception{
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
    public void checkNotWhiteWin() throws Exception{
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

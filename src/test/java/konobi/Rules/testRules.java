package konobi.Rules;

import konobi.Entities.Board;
import konobi.Entities.Cell;
import konobi.Entities.Color;
import konobi.Rules.CrossCutRule;
import konobi.Rules.WeakConnectionRule;
import org.junit.jupiter.api.Test;
import static konobi.Entities.Position.at;
import static org.junit.jupiter.api.Assertions.*;

public class testRules {


    @Test
    public void blackStoneMakesLegalWeakConnectionPlacement() {
        Board board = new Board(5);
        WeakConnectionRule rule = new WeakConnectionRule();
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        Cell cellToVerify = board.getCell(at(2,2));
        assertTrue(rule.isValid(board, cellToVerify, Color.BLACK));
    }

    @Test
    public void whiteStoneMakesLegalWeakConnectionPlacement() {
        Board board = new Board(5);
        WeakConnectionRule rule = new WeakConnectionRule();
        board.placeStone(at(3,1), Color.WHITE);
        board.placeStone(at(3,2), Color.WHITE);
        board.placeStone(at(4,1), Color.BLACK);
        board.placeStone(at(1,3), Color.WHITE);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(3,4), Color.BLACK);
        board.placeStone(at(5,3), Color.WHITE);
        board.placeStone(at(1,4), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,3));
        assertTrue(rule.isValid(board, cellToVerify, Color.WHITE));
    }


    @Test
    public void blackStoneMakesIllegalWeakConnectionPlacement() {
        Board board = new Board(5);
        WeakConnectionRule rule = new WeakConnectionRule();
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        Cell cellToVerify = board.getCell(at(2,1));
        assertFalse(rule.isValid(board, cellToVerify, Color.BLACK));
    }

    @Test
    public void whiteStoneMakesIllegalWeakConnectionPlacement() {
        Board board = new Board(5);
        WeakConnectionRule rule = new WeakConnectionRule();
        board.placeStone(at(3,1), Color.WHITE);
        board.placeStone(at(3,2), Color.WHITE);
        board.placeStone(at(4,1), Color.BLACK);
        board.placeStone(at(1,3), Color.WHITE);
        board.placeStone(at(3,3), Color.BLACK);
        board.placeStone(at(3,4), Color.BLACK);
        board.placeStone(at(5,3), Color.WHITE);
        board.placeStone(at(1,4), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,2));
        assertFalse(rule.isValid(board, cellToVerify, Color.WHITE));
    }

    @Test
    public void whiteStoneMakesCrosscut() {
        Board board = new Board(5);
        CrossCutRule rule = new CrossCutRule();
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(1,3), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
        assertFalse(rule.isValid(board, cellToVerify, Color.WHITE));
    }


    @Test
    public void blackStoneDoesNotMakeCrosscut()  {
        Board board = new Board(5);
        CrossCutRule rule = new CrossCutRule();
        board.placeStone(at(1,1), Color.BLACK);
        board.placeStone(at(1,2), Color.WHITE);
        board.placeStone(at(3,2), Color.BLACK);
        board.placeStone(at(3,3), Color.WHITE);
        board.placeStone(at(2,2), Color.BLACK);
        Cell cellToVerify = board.getCell(at(2,1));
        assertTrue(rule.isValid(board, cellToVerify, Color.BLACK));
    }

}

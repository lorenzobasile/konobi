package konobi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static konobi.Position.at;
import static org.junit.Assert.assertEquals;


public class testGame {
    private Game game;

    @Before
    public void initialize(){

        game = new Game(10);
    }


    @Test(expected=Exception.class)
    public void shouldNotPlayInOccupiedPosition() throws Exception {
        game.makeMove(at(1,1));
        game.makeMove(at(1,1));
    }

    @Test
    public void afterPieRulePlayer1IsWhite() {
        game.applyPieRule();
        Assert.assertEquals(game.player1.getColor(), Color.WHITE);

    }

    @Test
    public void afterPieRuleCurrentPlayerIsPlayer1() throws Exception{
        game.makeMove(at(2,1));
        game.applyPieRule();
        Assert.assertEquals(game.currentPlayer, game.player1);

    }


}

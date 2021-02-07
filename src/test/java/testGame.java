import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;


public class testGame {
    private Game game;

    @Before
    public void initialize(){
        game = new Game(10);
    }


    @Test(expected=Exception.class)
    public void shouldNotPlayInOccupiedPosition() throws Exception {
        game.makeMove();
        game.makeMove();
    }

    @Test
    public void afterPieRulePlayer1IsWhite() {

        String input = "Yes";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        game.pieRule();
        assertEquals(game.player1.getColor(), Color.WHITE);

    }


}

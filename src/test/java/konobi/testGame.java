package konobi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class testGame {

    private Game game;

    @BeforeEach
    public void initialize(){

        game = new Game(3);
    }


    @Test
    public void afterPieRulePlayer1IsWhite() {
        game.applyPieRule();
        assertEquals(game.player1.getColor(), Color.WHITE);

    }

}

package konobi.InputOutput;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static konobi.Entities.Position.at;

import konobi.Entities.Color;
import konobi.Entities.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class testInputHandler {
    InputHandler inputHandler=new InputHandler(System.in, new Display());

    @Test
    public void dimensionFiveIsReceived() throws Exception{
        String dimension = "5";
        InputStream in = new ByteArrayInputStream(dimension.getBytes());
        inputHandler.setIn(in);
        assertEquals(5, inputHandler.inputDimension());
    }

    @Test
    public void yesAnswerIsReceived() throws Exception{
        String input = "y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        inputHandler.setIn(in);
        assertTrue(inputHandler.playerWantsToApplyPieRule(new Player(Color.BLACK, "player", System.in, System.out)));

    }

    @Test
    public void positionIsReceivedInInput() throws Exception{
        String x = "1\n1";
        InputStream in = new ByteArrayInputStream(x.getBytes());
        System.setIn(in);
        inputHandler.setIn(in);
        assertEquals(at(1, 1), inputHandler.inputMove());

    }

}

package konobi.InputOutput;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static konobi.Entities.Position.at;

import konobi.Entities.Color;
import konobi.Entities.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class testInputTerminal {
    InputTerminal inputTerminal =new InputTerminal(System.in, new Display(System.out));

    @Test
    public void dimensionFiveIsReceived() throws Exception{
        String dimension = "5";
        InputStream in = new ByteArrayInputStream(dimension.getBytes());
        inputTerminal.setIn(in);
        assertEquals(5, inputTerminal.inputDimension());
    }

    @Test
    public void yesAnswerIsReceived() throws Exception{
        String input = "y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        this.inputTerminal.setIn(in);
        assertTrue(this.inputTerminal.playerWantsToApplyPieRule(new Player(Color.BLACK, "player", this.inputTerminal, new Display(System.out))));

    }

    @Test
    public void positionIsReceivedInInput() throws Exception{
        String x = "1\n1";
        InputStream in = new ByteArrayInputStream(x.getBytes());
        System.setIn(in);
        inputTerminal.setIn(in);
        assertEquals(at(1, 1), inputTerminal.inputMove());

    }

}

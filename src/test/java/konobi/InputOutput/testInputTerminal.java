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
    @Test
    public void dimensionFiveIsReceived() {
        String inputDimension = "5";
        InputStream in = new ByteArrayInputStream(inputDimension.getBytes());
        InputTerminal inputTerminal = new InputTerminal(in, new Display(System.out));
        assertEquals(5, inputTerminal.getDimension());
    }

    @Test
    public void yesAnswerIsReceived() {
        String inputAnswer = "y";
        InputStream in = new ByteArrayInputStream(inputAnswer.getBytes());
        InputTerminal inputTerminal = new InputTerminal(in, new Display(System.out));
        assertTrue(inputTerminal.playerWantsToApplyPieRule(new Player(Color.BLACK, "player", inputTerminal, new Display(System.out))));
    }

    @Test
    public void positionIsReceivedInInput() throws Exception{
        String inputPosition = "1\n1";
        InputStream in = new ByteArrayInputStream(inputPosition.getBytes());
        InputTerminal inputTerminal = new InputTerminal(in, new Display(System.out));
        assertEquals(at(1, 1), inputTerminal.inputMove());
    }
}

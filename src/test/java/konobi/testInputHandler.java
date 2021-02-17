package konobi;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static konobi.Position.at;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class testInputHandler {

    @Test
    public void dimensionFiveIsReceived(){
        String dimension = "5";
        InputStream in = new ByteArrayInputStream(dimension.getBytes());
        InputHandler.setStdIn(new Scanner(in));
        assertEquals(5, InputHandler.inputDimension());
    }

    @Test
    public void yesAnswerIsReceived() {
        String input = "y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputHandler.setStdIn(new Scanner(in));
        assertEquals(true, InputHandler.inputPie(new Player(Color.BLACK, "player")));

    }

    @Test
    public void positionIsReceivedInInput() {
        String x = "0\n0";
        InputStream in = new ByteArrayInputStream(x.getBytes());
        //System.setIn(in);
        InputHandler.setStdIn(new Scanner(in));
        assertEquals(at(0, 0), InputHandler.inputMove());

    }

}

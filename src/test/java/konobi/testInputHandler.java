package konobi;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static konobi.Position.at;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class testInputHandler {

    @Test
    public void dimensionFiveIsReceived(){
        InputHandler inputHandler = new InputHandler();
        String dimension = "5";
        InputStream in = new ByteArrayInputStream(dimension.getBytes());
        System.setIn(in);
        assertEquals(5, inputHandler.inputDimension());
    }

    @Test
    public void yesAnswerIsReceived() {
        InputHandler inputhandler = new InputHandler();
        String input = "y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(true, inputhandler.inputPie());

    }

    @Test
    public void positionIsReceivedInInput() {
        InputHandler inputhandler = new InputHandler();
        String x = "0 0";
        InputStream in = new ByteArrayInputStream(x.getBytes());
        System.setIn(in);

        assertEquals(at(0, 0), inputhandler.inputMove());

    }

}
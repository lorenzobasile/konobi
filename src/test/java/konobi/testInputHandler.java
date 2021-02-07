package konobi;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class testInputHandler {

    @Test
    public void YesAnswerIsReceived() {
        InputHandler inputhandler = new InputHandler();
        String input = "y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(true, inputhandler.inputPie());

    }

}

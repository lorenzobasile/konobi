package konobi;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static konobi.Model.Entities.Position.at;

import konobi.Model.Entities.Color;
import konobi.InputOutput.InputHandler;
import konobi.Model.Entities.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class testInputHandler {

    @Test
    public void dimensionFiveIsReceived() throws Exception{
        String dimension = "5";
        InputStream in = new ByteArrayInputStream(dimension.getBytes());
        InputHandler.setStdIn(new Scanner(in));
        assertEquals(5, InputHandler.inputDimension());
    }

    @Test
    public void yesAnswerIsReceived() throws Exception{
        String input = "y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputHandler.setStdIn(new Scanner(in));
        assertEquals(true, InputHandler.inputPie(new Player(Color.BLACK, "player")));

    }

    @Test
    public void positionIsReceivedInInput() throws Exception{
        String x = "1\n1";
        InputStream in = new ByteArrayInputStream(x.getBytes());
        System.setIn(in);
        InputHandler.setStdIn(new Scanner(in));
        assertEquals(at(1, 1), InputHandler.inputMove());

    }

}

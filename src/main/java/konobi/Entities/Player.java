package konobi.Entities;

import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Player {
    private Color color;
    private final String name;
    private InputHandler inputHandler;
    private Display display;

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public Display getDisplay() {
        return display;
    }


    public Player(Color color, String name, InputHandler inputHandler, Display display) {
        this.color = color;
        this.name = name;
        this.inputHandler = inputHandler;
        this.display = display;
    }

    public String getName() {
        return name;
    }


    public Color getColor() {
        return color;
    }

    public void switchColorsWith(Player otherPlayer) {
        Color temp = otherPlayer.color;
        otherPlayer.color=color;
        color=temp;
    }
}

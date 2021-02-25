package konobi.Entities;

import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;

public class Player {
    private Color color;
    private final String name;
    private final InputHandler inputHandler;
    private final Display display;

    public Player(Color color, String name, InputHandler inputHandler, Display display) {
        this.color = color;
        this.name = name;
        this.inputHandler = inputHandler;
        this.display = display;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public Display getDisplay() {
        return display;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void switchColorsWith(Player otherPlayer) {
        Color temp = otherPlayer.color;
        otherPlayer.color = color;
        color = temp;
    }
}

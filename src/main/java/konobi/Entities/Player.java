package konobi.Entities;

import konobi.InputOutput.Display;
import konobi.InputOutput.InputTerminal;

public class Player {
    private Color color;
    private final String name;
    private final InputTerminal inputTerminal;
    private final Display display;

    public Player(Color color, String name, InputTerminal inputTerminal, Display display) {
        this.color = color;
        this.name = name;
        this.inputTerminal = inputTerminal;
        this.display = display;
    }

    public InputTerminal getInputTerminal() {
        return inputTerminal;
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
        otherPlayer.color = this.color;
        this.color = temp;
    }
}

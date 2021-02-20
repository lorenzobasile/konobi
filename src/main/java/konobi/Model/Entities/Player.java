package konobi.Model.Entities;

import konobi.Model.Entities.Color;

public class Player {
    private Color color;

    public String getName() {
        return name;
    }

    private String name;

    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

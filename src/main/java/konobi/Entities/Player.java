package konobi.Entities;

public class Player {
    private Color color;

    public String getName() {
        return name;
    }

    private final String name;

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

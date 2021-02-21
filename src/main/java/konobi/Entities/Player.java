package konobi.Entities;

public class Player {
    private Color color;
    private final String name;

    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
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

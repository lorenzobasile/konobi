package konobi;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position otherPosition) {
        return this.x == otherPosition.x && this.y == otherPosition.y;
    }

    public static Position at(int x, int y){
        return new Position(x,y);
    }
}

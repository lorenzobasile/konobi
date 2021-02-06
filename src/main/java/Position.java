public class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position otherPosition) {
        return this.x == otherPosition.x && this.y == otherPosition.y;
    }

    public static Position At(int x, int y){
        return new Position(x,y);
    }
}

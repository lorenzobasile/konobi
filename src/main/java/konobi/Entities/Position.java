package konobi.Entities;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static Position at(int x, int y){
        return new Position(x,y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int squareEuclideanDistanceFrom(Position other) {
        return (int)Math.pow(x-other.x,2)+(int)Math.pow(y-other.y,2);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

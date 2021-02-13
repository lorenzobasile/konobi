package konobi;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
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

    public Position atRight(){
        return new Position(this.x+1,this.y);
    }

    public Position atLeft(){
        return new Position(this.x-1,this.y);
    }

    public Position top(){
        return new Position(this.x,this.y+1);
    }


    public Position bottom(){
        return new Position(this.x,this.y-1);
    }

    public Position upperLeft() {
        return new Position(this.x-1,this.y+1);
    }

    public Position upperRight() {
        return new Position(this.x+1,this.y+1);
    }

    public Position lowerLeft() {
        return new Position(this.x-1,this.y-1);
    }

    public Position lowerRight() {
        return new Position(this.x+1,this.y-1);
    }

    public int squareEuclideanDistanceFrom(Position other) {
        return (int)Math.pow(this.x-other.getX(),2)+(int)Math.pow(this.y-other.getY(),2);
    }
}

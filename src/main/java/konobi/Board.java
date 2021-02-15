package konobi;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static konobi.Position.at;

public class Board {
    int dimension;
    List<Cell> cells = new ArrayList<>();

    public Board(int dimension) {
        this.dimension = dimension;
        for (int i = 1; i<=dimension; ++i){
            for(int j = 1; j<=dimension; ++j){
                cells.add(new Cell(at(i,j)));
            }
        }
    }

    public Cell getCellAt(Position position) {

        return cells.stream()
                    .filter(c->c.isAt(position))
                    .findFirst()
                    .orElse(null);
    }

    public void placeStoneAt(Color color, Position position) {
        Cell cellToOccupy = this.getCellAt(position);
        cellToOccupy.setColor(color);
    }


    public Set<Cell> startEdge(Color color){
        Predicate<Cell> conditionOnCoordinates;

        if(color==Color.BLACK){
            conditionOnCoordinates = c->c.getPosition().getY()==dimension;
        }
        else{
            conditionOnCoordinates = c->c.getPosition().getX()==1;
        }

        return cells.stream()
                    .filter(conditionOnCoordinates)
                    .collect(Collectors.toSet());
    }

    public Set<Cell> endEdge(Color color){
        Predicate<Cell> conditionOnCoordinates;

        if(color==Color.BLACK){
            conditionOnCoordinates = c->c.getPosition().getY()==1;
        }
        else{
            conditionOnCoordinates = c->c.getPosition().getX()==dimension;
        }

        return cells.stream()
                    .filter(conditionOnCoordinates)
                    .collect(Collectors.toSet());
    }


}

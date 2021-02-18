package konobi;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    public Cell getCell(Position position) {
        return cells.stream()
                    .filter(c->c.isAt(position))
                    .findFirst()
                    .orElse(null);
    }

    public void placeStone(Position position, Color color) {
        Cell cellToOccupy = this.getCell(position);
        cellToOccupy.setColor(color);
    }

    public Set<Cell> boardEdge(Color color, boolean start){
        Predicate<Cell> conditionOnCoordinates;
        if(color == Color.BLACK){
            conditionOnCoordinates = c->c.getPosition().getY() == (start? dimension : 1);
        }
        else{
            conditionOnCoordinates = c->c.getPosition().getX() == (start? 1 : dimension);
        }
        return cells.stream()
                    .filter(conditionOnCoordinates)
                    .collect(Collectors.toSet());
    }

}

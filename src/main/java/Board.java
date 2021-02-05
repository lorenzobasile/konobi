import java.util.ArrayList;
import java.util.List;

public class Board {
    int dimension;
    List<Cell> cells = new ArrayList<>();

    public Board(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i<dimension; ++i){
            for(int j = 0; j<dimension; ++j){
                cells.add(new Cell(new Position(i, j), new Tile(Color.NONE)));
            }
        }
    }
}

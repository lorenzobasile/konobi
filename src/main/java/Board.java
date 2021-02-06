import java.util.ArrayList;
import java.util.List;

public class Board {
    int dimension;
    List<Tile> tiles = new ArrayList<>();

    public Board(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i<dimension; ++i){
            for(int j = 0; j<dimension; ++j){
                tiles.add(new Tile(new Position(i, j), Color.NONE));
            }
        }
    }

    public Tile findTileAt(Position position) {
        for(Tile t : tiles) {
            if(t.isAt(position)) {
                return t;
            }
        }
        return null;
    }

    public void placeTileAt(Position position, Color color) {
        Tile retrievedTile = findTileAt(position);
        retrievedTile.setColor(color);
    }
}

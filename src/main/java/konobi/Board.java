package konobi;

import java.util.ArrayList;
import java.util.List;

import static konobi.Position.at;

public class Board {
    int dimension;
    List<Tile> tiles = new ArrayList<>();

    public Board(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i<dimension; ++i){
            for(int j = 0; j<dimension; ++j){
                tiles.add(Tile.empty(at(i,j)));
            }
        }
    }

    public Tile findTileAt(Position position) throws Exception {
        for(Tile t : tiles) {
            if(t.isAt(position)) {
                return t;
            }
        }
        throw new Exception("konobi.Position outside board");
    }

    public Tile placeTileAt(Color color, Position position) throws Exception {
        Tile retrievedTile = findTileAt(position);
        if(retrievedTile.getColor() != Color.NONE)
            throw new Exception("Already played position");
        retrievedTile.setColor(color);
        return retrievedTile;
    }

    public boolean areWeaklyConnected(Tile tile1, Tile tile2) {
        return (tile1.isDiagonallyAdjacentTo(tile2) && commonStrongNeighborsOf(tile1,tile2).isEmpty());
    }

    public boolean areStronglyConnected(Tile tile1, Tile tile2) {
        return tile1.isStronglyConnectedWith(tile2);
    }

    public List<Tile> commonStrongNeighborsOf(Tile tile1, Tile tile2) {
        List<Tile> commonStrongNeighbors = new ArrayList<>();
        for(Tile t : tiles){
            if(areStronglyConnected(tile1,t) && areStronglyConnected(tile2,t))
                commonStrongNeighbors.add(t);
        }
        return commonStrongNeighbors;
    }
}

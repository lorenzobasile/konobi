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

    public boolean areWeaklyConnected(Tile tile1, Tile tile2) throws Exception {
        if (tile1.getColor()!=tile2.getColor())
            return false;
        if (!tile1.isDiagonallyAdjacentTo(tile2))
            return false;
        Tile potentialCommonStrongNeighbor1 = findTileAt(at(tile1.position.getX(), tile2.position.getY()));
        Tile potentialCommonStrongNeighbor2 = findTileAt(at(tile2.position.getX(), tile1.position.getY()));
        if (potentialCommonStrongNeighbor1.getColor() == tile1.getColor() || potentialCommonStrongNeighbor2.getColor() == tile1.getColor())
            return false;
        else
            return true;
    }
}

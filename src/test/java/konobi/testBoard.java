package konobi;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static konobi.Position.at;

public class testBoard {
    Board board = new Board(3);

    @Test (expected=Exception.class)
    public void positionOutsideBoardNotFound() throws Exception{
        board.findTileAt(at(0,3));
    }

    @Test
    public void placeBlackTileAndVerifyColor() throws Exception {
       board.placeTileAt(Color.BLACK, at(0,0));
       Tile placedTile = board.findTileAt(at(0,0));
       Assert.assertEquals(Color.BLACK,placedTile.getColor());
    }


}

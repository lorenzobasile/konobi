import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class testBoard {
    Board board = new Board(3);

    @Test (expected=Exception.class)
    public void positionOutsideBoardNotFound() throws Exception{
        Tile retrievedTile = board.findTileAt(Position.at(0,3));
    }

    @Test
    public void placeBlackTileAndVerifyColor() throws Exception {
       board.placeTileAt(Color.BLACK, Position.at(0,0));
       Tile placedTile = board.findTileAt(Position.at(0,0));
       assertEquals(Color.BLACK,placedTile.getColor());
    }


}

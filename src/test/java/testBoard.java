import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class testBoard {
    Board board = new Board(3);

    @Test
    public void positionOutsideBoardNotFound(){
        Tile retrievedTile = board.findTileAt(Position.at(0,3));
        assertEquals(null,retrievedTile);
    }

    @Test
    public void placeBlackTileAndVerifyColor(){
       board.placeTileAt(Color.BLACK, Position.at(0,0));
       Tile placedTile = board.findTileAt(Position.at(0,0));
       assertEquals(Color.BLACK,placedTile.getColor());
    }


}

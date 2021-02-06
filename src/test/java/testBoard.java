import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class testBoard {
    Board board = new Board(3);

    @Test
    void positionOutsideBoardNotFound(){
        Tile retrievedTile = board.findTileAt(Position.At(0,3));
        assertEquals(null,retrievedTile);
    }

    @Test
    void placeBlackTileAndVerifyColor(){
       board.placeTileAt(Position.At(0,0), Color.BLACK);
       Tile placedTile = board.findTileAt(Position.At(0,0));
       assertEquals(Color.BLACK,placedTile.getColor());
    }
}

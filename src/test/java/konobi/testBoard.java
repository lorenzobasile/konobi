package konobi;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testBoard {
    Board board = new Board(3);

    @Test
    public void positionOutsideBoardNotFound(){
        assertThrows(Exception.class, ()->
            {board.findTileAt(at(0,3));});
    }

    @Test
    public void placeBlackTileAndVerifyColor() throws Exception {
       board.placeTileAt(Color.BLACK, at(0,0));
       Tile placedTile = board.findTileAt(at(0,0));
       assertEquals(Color.BLACK,placedTile.getColor());
    }


}

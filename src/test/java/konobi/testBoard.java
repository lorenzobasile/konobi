package konobi;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testBoard {


    @Test
    public void positionOutsideBoardNotFound(){
        Board board = new Board(3);
        assertThrows(Exception.class, ()->
            {board.findTileAt(at(0,3));});
    }

    @Test
    public void placeBlackTileAndVerifyColor() throws Exception {
        Board board = new Board(3);
        Tile placedTile = board.placeTileAt(Color.BLACK, at(0,0));
        assertEquals(Color.BLACK,placedTile.getColor());
    }


    @ParameterizedTest
    @CsvSource({"0, 0, 1, 1", "5, 2, 6, 1"})
    public void weakConnectionWithTwoTilesInBoard(int x1, int y1, int x2, int y2) throws Exception {
        Board board = new Board(8);
        Tile tile1 = board.placeTileAt(Color.BLACK, at(x1,y1));
        Tile tile2 = board.placeTileAt(Color.BLACK, at(x2,y2));
        assertEquals(true, board.areWeaklyConnected(tile1, tile2));
    }

    @Test
    public void weakConnectionWithCommonStrongNeighbor() throws Exception {
        Board board = new Board(8);
        Tile tile1 = board.placeTileAt(Color.BLACK, at(0,0));
        Tile tile2 = board.placeTileAt(Color.BLACK, at(1,1));
        Tile tile3 = board.placeTileAt(Color.BLACK, at(1,0));
        assertEquals(false, board.areWeaklyConnected(tile1, tile2));
    }


}

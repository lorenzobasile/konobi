import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class testBoard {
    @Test
    void positionOutsideBoardNotFound(){
        Board board = new Board(3);
        Tile retrievedTile = board.findTileAt(new Position(0,3));
        assertEquals(null,retrievedTile);
    }
}

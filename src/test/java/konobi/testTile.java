package konobi;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static konobi.Position.at;



public class testTile {

    @ParameterizedTest
    @CsvSource({"0, 0, 1, 0", "1, 1, 1, 2"})
    public void twoTilesAreStronglyConnected(int x1, int x2, int y1, int y2) {
        Tile tile1 = new Tile(at(x1, y1), Color.BLACK);
        Tile tile2 = new Tile(at(x2, y2), Color.BLACK);
        assertEquals(true, tile1.isStronglyConnectedWith(tile2));
    }


    public void twoTilesAreNotStronglyConnected() {

    }
}



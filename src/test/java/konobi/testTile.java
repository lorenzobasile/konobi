package konobi;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static konobi.Position.at;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@RunWith(Parameterized.class)
public class testTile {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public testTile(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Parameters
    public static Collection<Object[]> getInputPositions() {
        return Arrays.asList(new Object[][]{
                {0, 0, 1, 0},
                {1, 1, 1, 2},
        });
    }

    @Test
    public void twoTilesAreStronglyConnected() {
        Tile tile1 = new Tile(at(x1, y1), Color.BLACK);
        Tile tile2 = new Tile(at(x2, y2), Color.BLACK);
        assertEquals(true, tile1.isStronglyConnectedWith(tile2));
    }


    public void twoTilesAreNotStronglyConnected() {

    }
}



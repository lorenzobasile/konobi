package konobi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static konobi.Position.at;

public class testConnections {


    @Test
    public void verifyOrthogonalNeighbors() throws Exception{
        Board board = new Board(3);
        Connections connections = new Connections(board);
        Cell cell = board.getCellAt(at(1,2));
        Cell right = board.getCellAt(at(2,2));
        Cell top = board.getCellAt(at(1,3));
        Cell bottom = board.getCellAt(at(1,1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(right,top,bottom));
        assertEquals(neighborsList,connections.orthogonalNeighborsOf(cell));
    }


    @Test
    public void verifyDiagonalNeighbors() throws Exception{
        Board board = new Board(3);
        Connections connections = new Connections(board);
        Cell cell = board.getCellAt(at(1,2));
        Cell upperRight = board.getCellAt(at(2,3));
        Cell lowerRight = board.getCellAt(at(2,1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(upperRight, lowerRight));
        assertEquals(neighborsList,connections.diagonalNeighborsOf(cell));
    }

    @Test
    public void verifyStrongNeighbors() throws Exception{
        Board board = new Board(3);
        Connections connections = new Connections(board);
        board.placeStoneAt(Color.BLACK, at(1,2));

        board.placeStoneAt(Color.BLACK, at(1,1));
        board.placeStoneAt(Color.BLACK, at(2,2));
        board.placeStoneAt(Color.BLACK, at(2,1));
        board.placeStoneAt(Color.WHITE, at(1,3));

        Cell strongNeighborBelow = board.getCellAt(at(1,1));
        Cell strongNeighborRight = board.getCellAt(at(2,2));

        Set<Cell> strongNeighborsList = new HashSet<>(Arrays.asList(strongNeighborRight, strongNeighborBelow));
        assertEquals(strongNeighborsList,connections.strongNeighborsOf(board.getCellAt(at(1,2))));

    }

    @Test
    public void verifyWeakNeighbors() throws Exception{
        Board board = new Board(4);
        Connections connections = new Connections(board);
        board.placeStoneAt(Color.BLACK, at(3,3));
        board.placeStoneAt(Color.BLACK, at(2,3));
        board.placeStoneAt(Color.BLACK, at(2,4));
        board.placeStoneAt(Color.WHITE, at(4,3));
        board.placeStoneAt(Color.BLACK, at(4,4));
        board.placeStoneAt(Color.WHITE, at(4,2));


        Cell weakNeighbor = board.getCellAt(at(4,4));

        Set<Cell> strongNeighborsList = new HashSet<>(Arrays.asList(weakNeighbor));
        assertEquals(strongNeighborsList,connections.weakNeighborsOf(board.getCellAt(at(3,3))));

    }

}

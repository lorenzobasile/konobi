package konobi;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testBoard {


    @Test
    public void positionOutsideBoardNotFound(){
        Board board = new Board(3);
        assertThrows(Exception.class, ()->
            {board.getCellAt(at(0,3));});
    }

    @Test
    public void placeStoneAndVerifyCellIsOccupied() throws Exception {
        Board board = new Board(3);
        board.placeStoneAt(new Stone(Color.BLACK), at(0,0));
        Cell occupiedCell = board.getCellAt(at(0, 0));
        assertEquals(true, occupiedCell.isOccupied());
    }

    @Test
    public void verifyOrthogonalNeighbors() throws Exception{
        Board board = new Board(3);
        Cell cell = board.getCellAt(at(0,1));
        Cell right = board.getCellAt(at(1,1));
        Cell top = board.getCellAt(at(0,2));
        Cell bottom = board.getCellAt(at(0,0));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(right,top,bottom));
        assertEquals(neighborsList,board.orthogonalNeighborsOf(cell));
    }

    @Test
    public void verifyDiagonalNeighbors() throws Exception{
        Board board = new Board(3);
        Cell cell = board.getCellAt(at(0,1));
        Cell upperRight = board.getCellAt(at(1,2));
        Cell lowerRight = board.getCellAt(at(1,0));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(upperRight, lowerRight));
        assertEquals(neighborsList,board.diagonalNeighborsOf(cell));
    }

    @Test
    public void verifyStrongNeighbors() throws Exception{
        Board board = new Board(3);
        board.placeStoneAt(new Stone(Color.BLACK), at(0,1));

        board.placeStoneAt(new Stone(Color.BLACK), at(0,0));
        board.placeStoneAt(new Stone(Color.BLACK), at(1,1));
        board.placeStoneAt(new Stone(Color.BLACK), at(1,0));
        board.placeStoneAt(new Stone(Color.WHITE), at(0,2));

        Cell strongNeighborBelow = board.getCellAt(at(0,0));
        Cell strongNeighborRight = board.getCellAt(at(1,1));

        Set<Cell> strongNeighborsList = new HashSet<>(Arrays.asList(strongNeighborRight, strongNeighborBelow));
        assertEquals(strongNeighborsList,board.strongNeighborsOf(board.getCellAt(at(0,1))));

    }

    @Test
    public void verifyWeakNeighbors() throws Exception{
        Board board = new Board(4);
        board.placeStoneAt(new Stone(Color.BLACK), at(2,2));
        board.placeStoneAt(new Stone(Color.BLACK), at(1,2));
        board.placeStoneAt(new Stone(Color.BLACK), at(1,3));
        board.placeStoneAt(new Stone(Color.WHITE), at(3,2));
        board.placeStoneAt(new Stone(Color.BLACK), at(3,3));
        board.placeStoneAt(new Stone(Color.WHITE), at(3,1));


        Cell weakNeighbor = board.getCellAt(at(3,3));

        Set<Cell> strongNeighborsList = new HashSet<>(Arrays.asList(weakNeighbor));
        assertEquals(strongNeighborsList,board.weakNeighborsOf(board.getCellAt(at(2,2))));

    }

    @Test
    public void verifyLegalWeakConnectionPlacement() throws Exception {
        Board board = new Board(5);
        board.placeStoneAt(new Stone(Color.BLACK), at(0,0));
        board.placeStoneAt(new Stone(Color.WHITE), at(0,1));
        board.placeStoneAt(new Stone(Color.BLACK), at(2,1));
        board.placeStoneAt(new Stone(Color.WHITE), at(2,2));

        board.placeStoneAt(new Stone(Color.BLACK), at(1,1));
        Cell cellToVerify = board.getCellAt(at(1,1));
        assertEquals(true, board.isLegalWeakConnectionPlacement(cellToVerify));
    }

    @Test
    public void verifyIllegalWeakConnectionPlacement() throws Exception {
        Board board = new Board(5);
        board.placeStoneAt(new Stone(Color.BLACK), at(0,0));
        board.placeStoneAt(new Stone(Color.WHITE), at(0,1));
        board.placeStoneAt(new Stone(Color.BLACK), at(2,1));
        board.placeStoneAt(new Stone(Color.WHITE), at(2,2));

        board.placeStoneAt(new Stone(Color.BLACK), at(1,0));
        Cell cellToVerify = board.getCellAt(at(1,0));
        assertEquals(false, board.isLegalWeakConnectionPlacement(cellToVerify));
    }

    @Test
    public void verifyCrosscut() throws Exception {
        Board board = new Board(5);
        board.placeStoneAt(new Stone(Color.BLACK), at(0,0));
        board.placeStoneAt(new Stone(Color.WHITE), at(0,1));
        board.placeStoneAt(new Stone(Color.BLACK), at(2,1));
        board.placeStoneAt(new Stone(Color.WHITE), at(2,2));
        board.placeStoneAt(new Stone(Color.BLACK), at(1,1));

        board.placeStoneAt(new Stone(Color.WHITE), at(1, 0));
        Cell cellToVerify = board.getCellAt(at(1,0));
        assertEquals(true, board.isCrosscutPlacement(cellToVerify));
    }

    @Test
    public void verifyNotCrosscut() throws Exception {
        Board board = new Board(5);
        board.placeStoneAt(new Stone(Color.BLACK), at(0,0));
        board.placeStoneAt(new Stone(Color.WHITE), at(0,1));
        board.placeStoneAt(new Stone(Color.BLACK), at(2,1));
        board.placeStoneAt(new Stone(Color.WHITE), at(2,2));
        board.placeStoneAt(new Stone(Color.BLACK), at(1,1));

        board.placeStoneAt(new Stone(Color.BLACK), at(1, 0));
        Cell cellToVerify = board.getCellAt(at(1,0));
        assertEquals(false, board.isCrosscutPlacement(cellToVerify));
    }

    @Test
    public void checkIfInitiallyAllMovesAreLegal() throws Exception{
        Board board = new Board(10);
        Set<Cell> allCells = new HashSet<Cell>(board.cells);
        assertEquals(allCells, board.legalCellsOf(Color.BLACK));
    }

    @Test
    public void checkIfMoveIsLegal() throws Exception{
        Board board = new Board(5);
        board.placeStoneAt(new Stone(Color.BLACK), at(0,0));
        board.placeStoneAt(new Stone(Color.WHITE), at(0,1));
        board.placeStoneAt(new Stone(Color.BLACK), at(2,1));
        board.placeStoneAt(new Stone(Color.WHITE), at(2,2));
        board.placeStoneAt(new Stone(Color.BLACK), at(1,1));

        Cell cellToVerify = board.getCellAt(at(1,0));
        Set<Cell> availableCellsForBlack = board.legalCellsOf(Color.BLACK);
        assertEquals(true, availableCellsForBlack.contains(cellToVerify));

    }

    @Test
    public void checkIfMoveIsIllegal() throws Exception{
        Board board = new Board(5);
        board.placeStoneAt(new Stone(Color.BLACK), at(0,0));
        board.placeStoneAt(new Stone(Color.WHITE), at(0,1));
        board.placeStoneAt(new Stone(Color.BLACK), at(2,1));
        board.placeStoneAt(new Stone(Color.WHITE), at(2,2));
        board.placeStoneAt(new Stone(Color.BLACK), at(1,1));

        Cell cellToVerify = board.getCellAt(at(1,0));
        Set<Cell> availableCellsForWhite = board.legalCellsOf(Color.WHITE);
        assertEquals(false, availableCellsForWhite.contains(cellToVerify));

    }


/*
    @ParameterizedTest
    @CsvSource({"0, 0, 1, 0", "1, 1, 1, 2"})
    public void areStronglyConnected(int x1, int y1, int x2, int y2) throws Exception {
        Board board = new Board(8);
        Stone stone1 = board.placeTileAt(Color.BLACK, at(x1,y1));
        Stone stone2 = board.placeTileAt(Color.BLACK, at(x2,y2));
        assertEquals(true, stone1.isStronglyConnectedWith(stone2));
    }

    @ParameterizedTest
    @CsvSource({"2, 2, 4, 4", "0, 1, 3, 1"})
    public void areNotStronglyConnected(int x1, int y1, int x2, int y2) throws Exception {
        Board board = new Board(8);
        Stone stone1 = board.placeTileAt(Color.BLACK, at(x1,y1));
        Stone stone2 = board.placeTileAt(Color.BLACK, at(x2,y2));
        assertEquals(false, stone1.isStronglyConnectedWith(stone2));
    }

    @ParameterizedTest
    @CsvSource({"0, 0, 1, 1", "5, 2, 6, 1"})
    public void weakConnectionWithTwoTilesInBoard(int x1, int y1, int x2, int y2) throws Exception {
        Board board = new Board(8);
        Stone stone1 = board.placeTileAt(Color.BLACK, at(x1,y1));
        Stone stone2 = board.placeTileAt(Color.BLACK, at(x2,y2));
        assertEquals(true, board.areWeaklyConnected(stone1, stone2));
    }

    @Test
    public void weakConnectionWithCommonStrongNeighbor() throws Exception {
        Board board = new Board(8);
        Stone stone1 = board.placeTileAt(Color.BLACK, at(0,0));
        Stone stone2 = board.placeTileAt(Color.BLACK, at(1,1));
        Stone stone3 = board.placeTileAt(Color.BLACK, at(1,0));
        assertEquals(false, board.areWeaklyConnected(stone1, stone2));
    }*/

}

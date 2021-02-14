package konobi;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static konobi.Position.at;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testBoard {

    @Test
    public void placeStoneAndVerifyCellIsOccupied() throws Exception {
        Board board = new Board(3);
        board.placeStoneAt(Stone.BLACK, at(1,1));
        Cell occupiedCell = board.getCellAt(at(1, 1));
        assertEquals(true, occupiedCell.isOccupied());
    }

    @Test
    public void verifyOrthogonalNeighbors() throws Exception{
        Board board = new Board(3);
        Cell cell = board.getCellAt(at(1,2));
        Cell right = board.getCellAt(at(2,2));
        Cell top = board.getCellAt(at(1,3));
        Cell bottom = board.getCellAt(at(1,1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(right,top,bottom));
        assertEquals(neighborsList,board.orthogonalNeighborsOf(cell));
    }

    @Test
    public void verifyDiagonalNeighbors() throws Exception{
        Board board = new Board(3);
        Cell cell = board.getCellAt(at(1,2));
        Cell upperRight = board.getCellAt(at(2,3));
        Cell lowerRight = board.getCellAt(at(2,1));
        Set<Cell> neighborsList = new HashSet<>(Arrays.asList(upperRight, lowerRight));
        assertEquals(neighborsList,board.diagonalNeighborsOf(cell));
    }

    @Test
    public void verifyStrongNeighbors() throws Exception{
        Board board = new Board(3);
        board.placeStoneAt(Stone.BLACK, at(1,2));

        board.placeStoneAt(Stone.BLACK, at(1,1));
        board.placeStoneAt(Stone.BLACK, at(2,2));
        board.placeStoneAt(Stone.BLACK, at(2,1));
        board.placeStoneAt(Stone.WHITE, at(1,3));

        Cell strongNeighborBelow = board.getCellAt(at(1,1));
        Cell strongNeighborRight = board.getCellAt(at(2,2));

        Set<Cell> strongNeighborsList = new HashSet<>(Arrays.asList(strongNeighborRight, strongNeighborBelow));
        assertEquals(strongNeighborsList,board.strongNeighborsOf(board.getCellAt(at(1,2))));

    }

    @Test
    public void verifyWeakNeighbors() throws Exception{
        Board board = new Board(4);
        board.placeStoneAt(Stone.BLACK, at(3,3));
        board.placeStoneAt(Stone.BLACK, at(2,3));
        board.placeStoneAt(Stone.BLACK, at(2,4));
        board.placeStoneAt(Stone.WHITE, at(4,3));
        board.placeStoneAt(Stone.BLACK, at(4,4));
        board.placeStoneAt(Stone.WHITE, at(4,2));


        Cell weakNeighbor = board.getCellAt(at(4,4));

        Set<Cell> strongNeighborsList = new HashSet<>(Arrays.asList(weakNeighbor));
        assertEquals(strongNeighborsList,board.weakNeighborsOf(board.getCellAt(at(3,3))));

    }

    @Test
    public void verifyLegalWeakConnectionPlacement() throws Exception {
        Board board = new Board(5);
        board.placeStoneAt(Stone.BLACK, at(1,1));
        board.placeStoneAt(Stone.WHITE, at(1,2));
        board.placeStoneAt(Stone.BLACK, at(3,2));
        board.placeStoneAt(Stone.WHITE, at(3,3));

        board.placeStoneAt(Stone.BLACK, at(2,2));
        Cell cellToVerify = board.getCellAt(at(2,2));
        assertEquals(true, board.isLegalWeakConnectionPlacement(cellToVerify));
    }

    @Test
    public void verifyIllegalWeakConnectionPlacement() throws Exception {
        Board board = new Board(5);
        board.placeStoneAt(Stone.BLACK, at(1,1));
        board.placeStoneAt(Stone.WHITE, at(1,2));
        board.placeStoneAt(Stone.BLACK, at(3,2));
        board.placeStoneAt(Stone.WHITE, at(3,3));

        board.placeStoneAt(Stone.BLACK, at(2,1));
        Cell cellToVerify = board.getCellAt(at(2,1));
        assertEquals(false, board.isLegalWeakConnectionPlacement(cellToVerify));
    }

    @Test
    public void verifyCrosscut() throws Exception {
        Board board = new Board(5);
        board.placeStoneAt(Stone.BLACK, at(1,1));
        board.placeStoneAt(Stone.WHITE, at(1,2));
        board.placeStoneAt(Stone.BLACK, at(3,2));
        board.placeStoneAt(Stone.WHITE, at(3,3));
        board.placeStoneAt(Stone.BLACK, at(2,2));

        board.placeStoneAt(Stone.WHITE, at(2, 1));
        Cell cellToVerify = board.getCellAt(at(2,1));
        assertEquals(true, board.isCrosscutPlacement(cellToVerify));
    }

    @Test
    public void verifyNotCrosscut() throws Exception {
        Board board = new Board(5);
        board.placeStoneAt(Stone.BLACK, at(1,1));
        board.placeStoneAt(Stone.WHITE, at(1,2));
        board.placeStoneAt(Stone.BLACK, at(3,2));
        board.placeStoneAt(Stone.WHITE, at(3,3));
        board.placeStoneAt(Stone.BLACK, at(2,2));

        board.placeStoneAt(Stone.BLACK, at(2, 1));
        Cell cellToVerify = board.getCellAt(at(2,1));
        assertEquals(false, board.isCrosscutPlacement(cellToVerify));
    }

    @Test
    public void checkIfInitiallyAllMovesAreLegal() throws Exception{
        Board board = new Board(10);
        Set<Cell> allCells = new HashSet<Cell>(board.cells);
        assertEquals(allCells, board.legalCellsOf(Stone.BLACK));
    }

    @Test
    public void checkIfMoveIsLegal() throws Exception{
        Board board = new Board(5);
        board.placeStoneAt(Stone.BLACK, at(1,1));
        board.placeStoneAt(Stone.WHITE, at(1,2));
        board.placeStoneAt(Stone.BLACK, at(3,2));
        board.placeStoneAt(Stone.WHITE, at(3,3));
        board.placeStoneAt(Stone.BLACK, at(2,2));

        Cell cellToVerify = board.getCellAt(at(2,1));
        Set<Cell> availableCellsForBlack = board.legalCellsOf(Stone.BLACK);
        assertEquals(true, availableCellsForBlack.contains(cellToVerify));

    }

    @Test
    public void checkIfMoveIsIllegal() throws Exception{
        Board board = new Board(5);
        board.placeStoneAt(Stone.BLACK, at(1,1));
        board.placeStoneAt(Stone.WHITE, at(1,2));
        board.placeStoneAt(Stone.BLACK, at(3,2));
        board.placeStoneAt(Stone.WHITE, at(3,3));
        board.placeStoneAt(Stone.BLACK, at(2,2));

        Cell cellToVerify = board.getCellAt(at(2,1));
        Set<Cell> availableCellsForWhite = board.legalCellsOf(Stone.WHITE);
        assertEquals(false, availableCellsForWhite.contains(cellToVerify));

    }

    @Test
    public void checkBlackWin() throws Exception{
        Board board = new Board(5);
        board.placeStoneAt(Stone.BLACK, at(1,5));
        board.placeStoneAt(Stone.BLACK, at(1,4));
        board.placeStoneAt(Stone.BLACK, at(2,4));
        board.placeStoneAt(Stone.BLACK, at(2,2));
        board.placeStoneAt(Stone.BLACK, at(3,3));
        board.placeStoneAt(Stone.BLACK, at(4,4));
        board.placeStoneAt(Stone.BLACK, at(4,1));
        board.placeStoneAt(Stone.BLACK, at(5,3));
        board.placeStoneAt(Stone.BLACK, at(5,2));
        board.placeStoneAt(Stone.WHITE, at(1,1));
        board.placeStoneAt(Stone.WHITE, at(4,3));
        board.placeStoneAt(Stone.WHITE, at(5,1));
        board.placeStoneAt(Stone.BLACK, at(3,5));

        assertEquals(true, board.checkChain(Stone.BLACK));
    }

    @Test
    public void checkNotBlackWin() throws Exception{
        Board board = new Board(5);
        board.placeStoneAt(Stone.BLACK, at(3,4));
        board.placeStoneAt(Stone.BLACK, at(1,4));
        board.placeStoneAt(Stone.BLACK, at(2,4));
        board.placeStoneAt(Stone.BLACK, at(2,2));
        board.placeStoneAt(Stone.BLACK, at(3,3));
        board.placeStoneAt(Stone.BLACK, at(4,4));
        board.placeStoneAt(Stone.BLACK, at(4,1));
        board.placeStoneAt(Stone.BLACK, at(5,3));
        board.placeStoneAt(Stone.BLACK, at(5,2));
        board.placeStoneAt(Stone.WHITE, at(1,1));
        board.placeStoneAt(Stone.WHITE, at(4,3));
        board.placeStoneAt(Stone.WHITE, at(5,1));

        assertEquals(false, board.checkChain(Stone.BLACK));
    }

    @Test
    public void checkWhiteWin() throws Exception{
        Board board = new Board(5);
        board.placeStoneAt(Stone.WHITE, at(2,2));
        board.placeStoneAt(Stone.WHITE, at(2,3));
        board.placeStoneAt(Stone.WHITE, at(2,4));
        board.placeStoneAt(Stone.WHITE, at(3,2));
        board.placeStoneAt(Stone.WHITE, at(3,4));
        board.placeStoneAt(Stone.WHITE, at(4,3));
        board.placeStoneAt(Stone.WHITE, at(4,5));
        board.placeStoneAt(Stone.WHITE, at(5,1));
        board.placeStoneAt(Stone.WHITE, at(5,2));
        board.placeStoneAt(Stone.WHITE, at(1,3));


        board.placeStoneAt(Stone.BLACK, at(3,3));
        board.placeStoneAt(Stone.BLACK, at(4,1));
        board.placeStoneAt(Stone.BLACK, at(5,3));

        assertEquals(true, board.checkChain(Stone.WHITE));
    }

    @Test
    public void checkNotWhiteWin() throws Exception{
        Board board = new Board(5);
        board.placeStoneAt(Stone.WHITE, at(1,3));
        board.placeStoneAt(Stone.WHITE, at(2,2));
        board.placeStoneAt(Stone.WHITE, at(2,3));
        board.placeStoneAt(Stone.WHITE, at(2,4));
        board.placeStoneAt(Stone.WHITE, at(3,3));
        board.placeStoneAt(Stone.WHITE, at(3,4));
        board.placeStoneAt(Stone.WHITE, at(3,5));
        board.placeStoneAt(Stone.WHITE, at(4,4));

        board.placeStoneAt(Stone.BLACK, at(1,4));
        board.placeStoneAt(Stone.BLACK, at(5,4));

        assertEquals(false, board.checkChain(Stone.WHITE));
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

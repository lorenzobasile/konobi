package konobi.Entities;

import java.util.Set;

public class GameState {
    private final Board board;
    private Color currentColor;
    private final Referee referee;
    private int movesCounter;

    public Board getBoard() {
        return board;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public GameState(int dimension){
        board = new Board(dimension);
        currentColor = Color.BLACK;
        referee = new Referee(board);
        movesCounter = 1;
    }

    public void applyPieRule(){
        movesCounter+=1;
    }

    private void changeCurrentColor() {
        currentColor=currentColor.opposite();
    }

    public void updateBoard(Position inputPosition){
        Color newStone = currentColor;
        board.placeStone(inputPosition, newStone);
        movesCounter+=1;
        changeCurrentColor();
    }

    public boolean outsideBoardMove(Position inputPosition){
        return board.getCell(inputPosition) == null;
    }
    public boolean isInvalidMove(Position inputPosition) {
        Set<Cell> availableCells = referee.availableCellsFor(currentColor);
        return !availableCells.contains(board.getCell(inputPosition));
    }
    public boolean currentPlayerHasToPass() {
        Set<Cell> availableCells = referee.availableCellsFor(currentColor);
        if(availableCells.isEmpty()){
            changeCurrentColor();
            return true;
        }
        return false;
    }
    private Color lastColor(){
        return currentColor.opposite();
    }
    public boolean someoneHasWon(){
        return referee.validateChain(lastColor());
    }
    public boolean isAlreadyOccupied(Position inputPosition) {
        Cell cell = board.getCell(inputPosition);
        return cell.isOccupied();
    }

    public boolean isTurn(int turn) {
        return movesCounter==turn;
    }
}

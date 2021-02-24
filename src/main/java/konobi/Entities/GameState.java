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

    public GameState(int dimension, Color initialColor){
        board = new Board(dimension);
        currentColor = initialColor;
        referee = new Referee(board);
        movesCounter = 1;
    }


    private void switchCurrentColor() {
        currentColor=currentColor.opposite();
    }

    private void incrementCounter() {
        movesCounter += 1;
    }

    public void changeTurn() {
        switchCurrentColor();
        incrementCounter();
    }

    public void updateBoard(Position inputPosition){
        Color newStone = currentColor;
        board.placeStone(inputPosition, newStone);
    }

    public void applyPieRule(){
        switchCurrentColor();
    }

    public boolean currentPlayerCanApplyPieRule() {
        return movesCounter==2;
    }


    public boolean currentPlayerHasToPass() {
        Set<Cell> availableCells = referee.availableCellsFor(currentColor);
        return availableCells.isEmpty();
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

    public boolean outsideBoardMove(Position inputPosition){
        return board.getCell(inputPosition) == null;
    }

    public boolean isInvalidMove(Position inputPosition) {
        Set<Cell> availableCells = referee.availableCellsFor(currentColor);
        return !availableCells.contains(board.getCell(inputPosition));
    }


}

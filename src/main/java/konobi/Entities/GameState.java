package konobi.Entities;

import java.util.Set;

public class GameState {
    private final Board board;
    private Color currentColor;
    private final Referee referee;
    private int movesCounter;

    public GameState(int dimension, Color initialColor){
        board = new Board(dimension);
        currentColor = initialColor;
        referee = new Referee(board);
        movesCounter = 1;
    }

    public Board getBoard() {
        return board;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    private Color getLastColor(){
        return currentColor.getOpposite();
    }

    private void switchCurrentColor() {
        currentColor = currentColor.getOpposite();
    }

    private void incrementCounter() {
        movesCounter += 1;
    }

    public void changeTurn() {
        switchCurrentColor();
        incrementCounter();
    }

    public boolean canPieRuleBeApplied() {
        return movesCounter == 2;
    }

    public void applyPieRule(){
        switchCurrentColor();
    }

    public boolean isPassMandatory() {
        Set<Cell> availableCells = referee.availableMovesFor(currentColor);
        return availableCells.isEmpty();
    }

    public boolean hasTheLastPlayerWon(){
        return referee.isThereAWinningChainFor(getLastColor());
    }

    public boolean isAlreadyOccupied(Position inputPosition) {
        Cell cell = board.getCell(inputPosition);
        return cell.isOccupied();
    }

    public boolean isMoveOutsideBoard(Position inputPosition){
        return board.getCell(inputPosition) == null;
    }

    public boolean isMoveInvalid(Position inputPosition) {
        Set<Cell> availableCells = referee.availableMovesFor(currentColor);
        return !availableCells.contains(board.getCell(inputPosition));
    }

    public void updateBoard(Position inputPosition){
        Color newStone = currentColor;
        board.placeStone(inputPosition, newStone);
    }
}

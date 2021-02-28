package konobi.Entities;

import konobi.InputOutput.Display;
import konobi.InputOutput.Exceptions.NegativeNumberException;
import konobi.InputOutput.InputHandler;

public abstract class Game {

    protected final GameState gameState;
    protected final Player player1;
    protected final Player player2;

    public Game(int dimension, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        gameState = new GameState(dimension, player1.getColor());
    }

    public void play() {
        do {
            singleTurn();
        } while (!checkWin());
    }

    public InputHandler currentInputHandler() {
        return getCurrentPlayer().getInputHandler();
    }

    public Display currentDisplay() {
        return getCurrentPlayer().getDisplay();
    }

    public Display otherDisplay() {
        return getOtherPlayer().getDisplay();
    }

    private void applyAndNotifyPieRule() {
        gameState.applyPieRule();
        player1.switchColorsWith(player2);
        notifyPieRule();
    }

    protected abstract void notifyPieRule();

    protected Player getCurrentPlayer(){
        if(gameState.getCurrentColor() == player1.getColor())
            return player1;
        else
            return player2;
    }

    protected Player getOtherPlayer(){
        if(getCurrentPlayer() == player1)
            return player2;
        else
            return player1;
    }

    public void singleTurn() {
        currentDisplay().currentPlayerTurnMessage(getCurrentPlayer());
        if(gameState.pieRuleCanBeApplied() && currentInputHandler().playerWantsToApplyPieRule(getCurrentPlayer())) {
            applyAndNotifyPieRule();
        } else {
            regularMove();
            printBoard(gameState.getBoard());
        }
        gameState.changeTurn();
    }

    protected abstract void printBoard(Board board);

    private void regularMove() {
        if(gameState.passIsMandatory()) {
            currentDisplay().passMessage(getOtherPlayer());
        }
        else {
            Position inputPosition = chooseNextMove();
            gameState.updateBoard(inputPosition);
        }
    }

    private Position chooseNextMove() {
        Position inputPosition;
        try{
            inputPosition = currentInputHandler().inputMove();
        } catch (NumberFormatException notANumber){
            currentDisplay().notANumberMessage();
            return chooseNextMove();
        } catch (NegativeNumberException wrongInput){
            currentDisplay().printExceptionCause(wrongInput);
            return chooseNextMove();
        }
        if(gameState.outsideBoardMove(inputPosition)){
            currentDisplay().positionOutsideBoardMessage();
            return chooseNextMove();
        }
        else if(gameState.isAlreadyOccupied(inputPosition)){
            currentDisplay().alreadyPlayedPositionMessage();
            return chooseNextMove();
        }
        else if(gameState.isInvalidMove(inputPosition)){
            currentDisplay().invalidMoveMessage();
            return chooseNextMove();
        }
        return inputPosition;
    }

    public boolean checkWin() {
        if(gameState.someoneHasWon()) {
            notifyEndOfMatch();
            return true;
        }
        return false;
    }

    protected abstract void notifyEndOfMatch();

}

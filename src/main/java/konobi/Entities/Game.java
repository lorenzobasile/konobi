package konobi.Entities;

import konobi.InputOutput.Display;
import konobi.InputOutput.Exceptions.NegativeNumberException;
import konobi.InputOutput.InputTerminal;

public abstract class Game {

    protected final GameState gameState;
    protected final Player player1;
    protected final Player player2;

    public Game(int dimension, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameState = new GameState(dimension, player1.getColor());
    }

    public void play() {
        do {
            singleTurn();
        } while (!checkAndNotifyWin());
    }

    private InputTerminal getCurrentInputTerminal() {
        return getCurrentPlayer().getInputTerminal();
    }

    protected Display getCurrentDisplay() {
        return getCurrentPlayer().getDisplay();
    }

    protected Display getOtherDisplay() {
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

    private void singleTurn() {
        getCurrentDisplay().currentPlayerTurnMessage(getCurrentPlayer());
        if(gameState.canPieRuleBeApplied() && getCurrentInputTerminal().playerWantsToApplyPieRule(getCurrentPlayer())) {
            applyAndNotifyPieRule();
        } else {
            regularMove();
            printBoard(gameState.getBoard());
        }
        gameState.changeTurn();
    }

    protected abstract void printBoard(Board board);

    private void regularMove() {
        if(gameState.isPassMandatory()) {
            notifyMandatoryPass();
        }
        else {
            Position inputPosition = chooseNextMove();
            gameState.updateBoard(inputPosition);
        }
    }

    protected abstract void notifyMandatoryPass();

    private Position chooseNextMove() {
        Position inputPosition;
        try{
            inputPosition = getCurrentInputTerminal().inputMove();
        } catch (NumberFormatException notANumber){
            getCurrentDisplay().notANumberMessage();
            return chooseNextMove();
        } catch (NegativeNumberException wrongInput){
            getCurrentDisplay().printExceptionCause(wrongInput);
            return chooseNextMove();
        }
        if(gameState.isMoveOutsideBoard(inputPosition)){
            getCurrentDisplay().positionOutsideBoardMessage();
            return chooseNextMove();
        }
        else if(gameState.isAlreadyOccupied(inputPosition)){
            getCurrentDisplay().alreadyPlayedPositionMessage();
            return chooseNextMove();
        }
        else if(gameState.isMoveInvalid(inputPosition)){
            getCurrentDisplay().invalidMoveMessage();
            return chooseNextMove();
        }
        return inputPosition;
    }

    private boolean checkAndNotifyWin() {
        if(gameState.hasTheLastPlayerWon()) {
            notifyEndOfMatch();
            return true;
        }
        return false;
    }

    protected abstract void notifyEndOfMatch();

}

package konobi.ConsoleVersion;

import konobi.Entities.*;
import konobi.InputOutput.Display;
import konobi.InputOutput.Exceptions.NegativeNumberException;
import konobi.InputOutput.InputHandler;

import java.io.IOException;


public class Match {

    protected final GameState gameState;
    protected final Player player1;
    protected final Player player2;
    
    public InputHandler currentInputHandler(){
        return getCurrentPlayer().getInputHandler();
    }
    public Display currentDisplay(){
        return getCurrentPlayer().getDisplay();
    }

    public Display otherDisplay(){
        return getOtherPlayer().getDisplay();
    }

    public static Match init() throws IOException {
        Display commonDisplay=new Display();
        InputHandler commonInputHandler=new InputHandler(System.in, commonDisplay);
        commonDisplay.welcomeMessage();
        int dimension = commonInputHandler.getDimension();
        String player1Name = commonInputHandler.inputPlayerName(1);
        String player2Name = commonInputHandler.inputPlayerName(2);
        Player player1 = new Player(Color.BLACK, player1Name, commonInputHandler, commonDisplay);
        Player player2 = new Player(Color.WHITE, player2Name, commonInputHandler, commonDisplay);
        Match match = new Match(dimension, player1, player2);
        commonDisplay.playerColorsMessage(player1, player2);
        return match;
    }

    public Match(int dimension, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        gameState = new GameState(dimension, player1.getColor());
    }

    protected void applyPieRule() {
        gameState.applyPieRule();
        player1.switchColorsWith(player2);
        notifyPieRule();
    }

    protected void notifyPieRule() {

        currentDisplay().playerColorsMessage(player1, player2);
    }

    protected Player getCurrentPlayer(){
        if(gameState.getCurrentColor()==player1.getColor())
            return player1;
        else
            return player2;
    }

    protected Player getOtherPlayer(){
        if(getCurrentPlayer()==player1)
            return player2;
        else
            return player1;
    }

    public void singleTurn() throws IOException {

        currentDisplay().currentPlayerTurnMessage(getCurrentPlayer());
        if(gameState.currentPlayerCanApplyPieRule() && currentInputHandler().playerWantsToApplyPieRule(getCurrentPlayer())) {
            applyPieRule();
        } else {
            regularMove();
            printBoard(gameState.getBoard());
        }
        gameState.changeTurn();
    }

    protected void printBoard(Board board){
        currentDisplay().printBoard(board);
    }

    protected void regularMove() throws IOException {
        if(gameState.currentPlayerHasToPass()) {
            currentDisplay().passMessage(getOtherPlayer());
        }
        else {
            Position inputPosition = chooseNextMove();
            gameState.updateBoard(inputPosition);
        }
    }

    protected Position chooseNextMove() throws IOException{
        Position inputPosition;
        try{
            inputPosition = currentInputHandler().inputMove();
        } catch(NumberFormatException | NegativeNumberException wrongInput){
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

    protected void notifyEndOfMatch() {
        otherDisplay().winMessage(getOtherPlayer());
    }

}

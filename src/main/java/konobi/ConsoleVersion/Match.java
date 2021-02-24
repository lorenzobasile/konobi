package konobi.ConsoleVersion;

import konobi.Entities.*;
import konobi.InputOutput.Display;
import konobi.InputOutput.Exceptions.NegativeNumberException;
import konobi.InputOutput.Exceptions.WrongAnswerException;
import konobi.InputOutput.InputHandler;

import java.io.IOException;


public class Match {

    protected final GameState gameState;
    private final Player player1;
    private final Player player2;
    protected InputHandler inputHandler;
    protected Display display;

    public static Match init() throws IOException {
        Display display=new Display();
        InputHandler inputHandler=new InputHandler(display);
        display.welcomeMessage();
        int dimension = inputHandler.getDimension();
        String player1Name = inputHandler.inputPlayerName(1);
        String player2Name = inputHandler.inputPlayerName(2);
        Player player1 = new Player(Color.BLACK, player1Name);
        Player player2 = new Player(Color.WHITE, player2Name);
        Match match = new Match(dimension, player1, player2, inputHandler, display);
        display.playerColorsMessage(player1, player2);
        return match;
    }

    public Match(int dimension, Player player1, Player player2, InputHandler inputHandler, Display display) {
        this.player1 = player1;
        this.player2 = player2;
        this.inputHandler = inputHandler;
        this.display = display;
        gameState = new GameState(dimension, player1.getColor());
    }

    protected void checkAndApplyPieRule() throws IOException{
        try {
            if (inputHandler.inputPie(getCurrentPlayer())) {
                gameState.applyPieRule();
                player1.switchColorsWith(player2);
                display.playerColorsMessage(player1, player2);
            }
        } catch(WrongAnswerException wrongInput){
            display.printExceptionCause(wrongInput);
            checkAndApplyPieRule();
        }
    }

    protected Player getCurrentPlayer(){
        if(gameState.getCurrentColor()==player1.getColor())
            return player1;
        else
            return player2;
    }

    protected Player getLastPlayer(){
        if(getCurrentPlayer()==player1)
            return player2;
        else
            return player1;
    }

    public void singleTurn() throws IOException {
        if(gameState.currentPlayerCanApplyPieRule()) {
            checkAndApplyPieRule();
        }
        display.currentPlayerMessage(getCurrentPlayer());
        if(gameState.currentPlayerHasToPass()){
            gameState.applyPass();
            display.passMessage(getLastPlayer());
        }
        else{
            Position inputPosition = chooseNextMove();
            gameState.updateBoard(inputPosition);

        }
        display.printBoard(gameState.getBoard());
    }

    protected Position chooseNextMove() throws IOException{
        Position inputPosition;
        try{
            inputPosition = inputHandler.inputMove();
        } catch(NumberFormatException notANumber){
            display.notAnIntegerMessage();
            return chooseNextMove();
        } catch(NegativeNumberException negativeCoordinate){
            display.printExceptionCause(negativeCoordinate);
            return chooseNextMove();
        }
        if(gameState.outsideBoardMove(inputPosition)){
            display.positionOutsideBoardMessage();
            return chooseNextMove();
        }
        else if(gameState.isAlreadyOccupied(inputPosition)){
            display.alreadyPlayedPositionMessage();
            return chooseNextMove();
        }
        else if(gameState.isInvalidMove(inputPosition)){
            display.invalidMoveMessage();
            return chooseNextMove();
        }
        return inputPosition;
    }

    public boolean checkWin() {
        if(gameState.someoneHasWon()) {
            display.winMessage(getLastPlayer());
            return true;
        }
        return false;
    }

}

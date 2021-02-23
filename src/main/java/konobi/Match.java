package konobi;

import konobi.Entities.*;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;

import java.io.IOException;
import java.net.Socket;


public class Match {

    private final GameState gameState;
    private final Player player1;
    private final Player player2;
    private InputHandler inputHandler;
    private Display display;


    public static Match init(Socket client1Socket, Socket client2Socket) throws IOException {
        Display display=new Display();
        InputHandler inputHandler=new InputHandler(System.in, display);
        display.setOut(client1Socket.getOutputStream());
        inputHandler.setIn(client1Socket.getInputStream());
        display.welcomeMessage();
        int dimension = inputHandler.getDimension();
        String player1Name = inputHandler.inputPlayerName(1);
        display.setOut(client2Socket.getOutputStream());
        inputHandler.setIn(client2Socket.getInputStream());
        String player2Name = inputHandler.inputPlayerName(2);
        Player player1 = new Player(Color.BLACK, player1Name, client1Socket.getInputStream(), client1Socket.getOutputStream());
        Player player2 = new Player(Color.WHITE, player2Name, client2Socket.getInputStream(), client2Socket.getOutputStream());
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

    private void checkAndApplyPieRule() {
        try {
            if (inputHandler.inputPie(getCurrentPlayer())) {
                gameState.applyPieRule();
                player1.switchColorsWith(player2);
                display.playerColorsMessage(player1, player2);
            }
        } catch(Exception wrongInput){
            display.printExceptionCause(wrongInput);
            checkAndApplyPieRule();
        }
    }

    private Player getCurrentPlayer(){
        if(gameState.getCurrentColor()==player1.getColor())
            return player1;
        else
            return player2;
    }

    private Player getLastPlayer(){
        if(getCurrentPlayer()==player1)
            return player2;
        else
            return player1;
    }

    public void singleTurn() throws IOException {
        inputHandler.setIn(getCurrentPlayer().getInputStream());
        display.setOut(getCurrentPlayer().getOutputStream());
        display.printBoard(gameState.getBoard());
        if(gameState.currentPlayerCanApplyPieRule()) {
            checkAndApplyPieRule();
            inputHandler.setIn(getCurrentPlayer().getInputStream());
            display.setOut(getCurrentPlayer().getOutputStream());
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

    private Position chooseNextMove() {
        Position inputPosition;
        try{
            inputPosition = inputHandler.inputMove();
        } catch(NumberFormatException notANumber){
            display.notAnIntegerMessage();
            return chooseNextMove();
        } catch(Exception negativeCoordinate){
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

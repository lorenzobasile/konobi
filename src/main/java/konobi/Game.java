package konobi;

import konobi.Entities.*;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;


public class Game {

    private final GameState state;
    private final Player player1;
    private final Player player2;

    public static Game init() {
        Display.welcomeMessage();
        int dimension = InputHandler.getDimension();
        String player1Name = InputHandler.inputPlayerName(1);
        String player2Name = InputHandler.inputPlayerName(2);
        Game game = new Game(dimension, player1Name, player2Name);
        Display.playerColorsMessage(game.player1, game.player2);
        return game;
    }

    public Game(int dimension, String player1Name, String player2Name) {
        player1 = new Player(Color.BLACK, player1Name);
        player2 = new Player(Color.WHITE, player2Name);
        state = new GameState(dimension);
    }

    private void checkAndApplyPieRule() {
        try {
            if (InputHandler.inputPie(getCurrentPlayer())) {
                state.applyPieRule();
                player1.switchColorsWith(player2);
                Display.playerColorsMessage(player1, player2);
                Display.printBoard(state.getBoard());
            }
        } catch(Exception wrongInput){
            Display.printExceptionCause(wrongInput);
            checkAndApplyPieRule();
        }
    }

    private Player getCurrentPlayer(){
        if(state.getCurrentColor()==player1.getColor()) return player1;
        return player2;
    }

    private Player getLastPlayer(){
        if(state.getCurrentColor()==player2.getColor()) return player1;
        return player2;
    }

    public void singleTurn() {

        if(state.isTurn(1)) {
            Display.printBoard(state.getBoard());
        }
        if(state.isTurn(2)) {
            checkAndApplyPieRule();
        }
        Display.currentPlayerMessage(getCurrentPlayer());
        if(state.currentPlayerHasToPass()){
            Display.passMessage(getCurrentPlayer());
        }
        else{
            Position inputPosition = chooseNextMove();
            state.updateBoard(inputPosition);
            Display.printBoard(state.getBoard());
        }

    }

    private Position chooseNextMove() {
        Position inputPosition;
        try{
            inputPosition = InputHandler.inputMove();
        } catch(NumberFormatException notANumber){
            Display.notAnIntegerMessage();
            return chooseNextMove();
        } catch(Exception negativeCoordinate){
            Display.printExceptionCause(negativeCoordinate);
            return chooseNextMove();
        }
        if(state.outsideBoardMove(inputPosition)){
            Display.positionOutsideBoardMessage();
            return chooseNextMove();
        }
        else if(state.isAlreadyOccupied(inputPosition)){
            Display.alreadyPlayedPositionMessage();
            return chooseNextMove();
        }
        else if(state.isInvalidMove(inputPosition)){
            Display.invalidMoveMessage();
            return chooseNextMove();
        }
        return inputPosition;
    }

    public boolean checkWin() {
        if(state.someoneHasWon()) {
            Display.winMessage(getLastPlayer());
            return true;
        }
        return false;
    }

}

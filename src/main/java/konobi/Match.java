package konobi;

import konobi.Entities.*;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;


public class Match {

    private final Game game;
    private final Player player1;
    private final Player player2;

    public static Match init() {
        Display.welcomeMessage();
        int dimension = InputHandler.getDimension();
        String player1Name = InputHandler.inputPlayerName(1);
        String player2Name = InputHandler.inputPlayerName(2);
        Player player1 = new Player(Color.BLACK, player1Name);
        Player player2 = new Player(Color.WHITE, player2Name);
        Match match = new Match(dimension, player1, player2);
        Display.playerColorsMessage(player1, player2);
        return match;
    }

    public Match(int dimension, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        game = new Game(dimension, player1.getColor());
    }

    private void checkAndApplyPieRule() {
        try {
            if (InputHandler.inputPie(getCurrentPlayer())) {
                game.applyPieRule();
                player1.switchColorsWith(player2);
                Display.playerColorsMessage(player1, player2);
            }
        } catch(Exception wrongInput){
            Display.printExceptionCause(wrongInput);
            checkAndApplyPieRule();
        }
    }

    private Player getCurrentPlayer(){
        if(game.getCurrentColor()==player1.getColor())
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

    public void singleTurn() {
        if(game.currentPlayerCanApplyPieRule()) {
            checkAndApplyPieRule();
        }
        Display.currentPlayerMessage(getCurrentPlayer());
        if(game.currentPlayerHasToPass()){
            game.applyPass();
            Display.passMessage(getLastPlayer());
        }
        else{
            Position inputPosition = chooseNextMove();
            game.updateBoard(inputPosition);

        }
        Display.printBoard(game.getBoard());
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
        if(game.outsideBoardMove(inputPosition)){
            Display.positionOutsideBoardMessage();
            return chooseNextMove();
        }
        else if(game.isAlreadyOccupied(inputPosition)){
            Display.alreadyPlayedPositionMessage();
            return chooseNextMove();
        }
        else if(game.isInvalidMove(inputPosition)){
            Display.invalidMoveMessage();
            return chooseNextMove();
        }
        return inputPosition;
    }

    public boolean checkWin() {
        if(game.someoneHasWon()) {
            Display.winMessage(getLastPlayer());
            return true;
        }
        return false;
    }

}

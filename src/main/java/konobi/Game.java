package konobi;

import konobi.Entities.*;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;

import java.util.Set;

public class Game {

    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Referee supervisor;
    private int movesCounter;



    public static Game init() {
        Display.welcomeMessage();
        int dimension = InputHandler.getDimension();
        String player1Name = InputHandler.inputPlayerName(1);
        String player2Name = InputHandler.inputPlayerName(2);
        return new Game(dimension, player1Name, player2Name);
    }

    public Game(int dimension, String player1Name, String player2Name) {
        board = new Board(dimension);
        player1  = new Player(Color.BLACK, player1Name);
        player2  = new Player(Color.WHITE, player2Name);
        currentPlayer = player1;
        supervisor = new Referee();
        movesCounter = 0;
    }

    private void checkAndApplyPieRule() {
        try {
            if (InputHandler.inputPie(currentPlayer)) {
                player1.switchColorsWith(player2);
                changeTurn();
                Display.playerColorsMessage(player1, player2);
                Display.printBoard(board);
            }
        } catch(Exception wrongInput){
            Display.printExceptionCause(wrongInput);
            checkAndApplyPieRule();
        }
    }

    private void changeTurn() {
        if (currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
    }


    public void singleTurn() {
        movesCounter += 1;
        if(movesCounter==1) {
            Display.playerColorsMessage(player1, player2);
            Display.printBoard(board);
        }
        else{
            changeTurn();
        }
        if(movesCounter==2) {
            checkAndApplyPieRule();
        }
        Display.currentPlayerMessage(currentPlayer);
        Set<Cell> availableCells = supervisor.availableCellsFor(currentPlayer, board);
        checkMandatoryPass(availableCells);
        Position inputPosition = chooseNextMove(availableCells);

        Color newStone = currentPlayer.getColor();
        board.placeStone(inputPosition, newStone);
        Display.printBoard(board);

    }

    private void checkMandatoryPass(Set<Cell> availableCells) {
        if (availableCells.isEmpty()) {
            Display.passMessage(currentPlayer);
            changeTurn();
        }
    }


    private Position chooseNextMove(Set<Cell> availableCells) {
        Cell inputCell;
        Position inputPosition;
        try{
            inputPosition = InputHandler.inputMove();
        } catch(NumberFormatException notANumber){
            Display.notAnIntegerMessage();
            return chooseNextMove(availableCells);
        } catch(Exception negativeCoordinate){
            Display.printExceptionCause(negativeCoordinate);
            return chooseNextMove(availableCells);
        }
        inputCell = board.getCell(inputPosition);
        if(inputCell==null){
            Display.positionOutsideBoardMessage();
            return chooseNextMove(availableCells);
        }
        else if(inputCell.isOccupied()){
            Display.alreadyPlayedPositionMessage();
            return chooseNextMove(availableCells);
        }
        else if(!availableCells.contains(inputCell)){
            Display.invalidMoveMessage();
            return chooseNextMove(availableCells);
        }
        return inputPosition;
    }

    public boolean checkWin() {
        boolean someoneHasWon = supervisor.validateChain(board, currentPlayer.getColor());
        if(someoneHasWon) {
            Display.winMessage(currentPlayer);
        }
        return someoneHasWon;
    }

}

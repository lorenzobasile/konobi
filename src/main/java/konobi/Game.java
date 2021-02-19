package konobi;

import konobi.Exceptions.NegativeNumberException;
import konobi.Exceptions.WrongAnswerException;

import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    Rules rules;
    int movesCounter;

    public static int getDimension(){
        int dimension;
        try {
            dimension = InputHandler.inputDimension();
        } catch(NumberFormatException notANumber){
            Display.notAnIntegerMessage();
            return getDimension();
        } catch(NegativeNumberException negativeDimension){
            Display.printExceptionCause(negativeDimension);
            return getDimension();
        } catch(Exception otherException){
            Display.printExceptionCause(otherException);
            return getDimension();
        }
        return dimension;
    }

    public static Game init() {
        Display.welcomeMessage();
        int dimension = getDimension();
        String player1Name = InputHandler.inputPlayerName(1);
        String player2Name = InputHandler.inputPlayerName(2);
        return new Game(dimension, player1Name, player2Name);
    }

    public Game(int dimension, String player1Name, String player2Name) {
        this.board = new Board(dimension);
        this.player1  = new Player(Color.BLACK, player1Name);
        this.player2  = new Player(Color.WHITE, player2Name);
        this.currentPlayer = player1;
        this.rules = new Rules(board);
        this.movesCounter = 0;
    }

    private void checkAndApplyPieRule() {
        try {
            if (InputHandler.inputPie(currentPlayer)) {
                switchColors();
                changeTurn();
                Display.playerColorsMessage(player1, player2);
                Display.printBoard(board);
            }
        } catch(WrongAnswerException wrongInput){
            Display.printExceptionCause(wrongInput);
            checkAndApplyPieRule();
        } catch(Exception otherException){
            Display.printExceptionCause(otherException);
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

    private void switchColors() {
        Color temp = player2.getColor();
        player2.setColor(player1.getColor());
        player1.setColor(temp);
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
        Display.currentPlayerMessage(currentPlayer);
        if(movesCounter==2) {
            checkAndApplyPieRule();
        }
        Set<Cell> availableCells = availableCellsForCurrentPlayer();
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

    public Set<Cell> availableCellsForCurrentPlayer() {
        return board.cells.stream()
                          .filter(c->!c.isOccupied())
                          .filter(c->rules.checkTheTwoRules(c, currentPlayer.getColor()))
                          .collect(Collectors.toSet());
    }

    private Position chooseNextMove(Set<Cell> availableCells) {
        Cell inputCell;
        Position inputPosition;
        try{
            inputPosition = InputHandler.inputMove();
        } catch(NumberFormatException notANumber){
            Display.notAnIntegerMessage();
            return chooseNextMove(availableCells);
        } catch(NegativeNumberException negativeCoordinate){
            Display.printExceptionCause(negativeCoordinate);
            return chooseNextMove(availableCells);
        } catch(Exception otherException){
            Display.printExceptionCause(otherException);
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
        boolean someoneHasWon = rules.checkChain(currentPlayer.getColor());
        if(someoneHasWon) {
            Display.winMessage(currentPlayer);
        }
        return someoneHasWon;
    }

}

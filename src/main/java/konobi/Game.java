package konobi;

import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    Rules rules;
    int movesCounter;

    public static Game init() {
        Displayer.welcomeMessage();
        int dimension = InputHandler.inputDimension();
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
            if(InputHandler.inputPie(currentPlayer)) {
                switchColors();
                changeTurn();
                Displayer.playerColorsMessage(player1, player2);
                Displayer.printBoard(board);
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
            Displayer.playerColorsMessage(player1, player2);
            Displayer.printBoard(board);
        }
        else{
            changeTurn();
        }
        if(movesCounter==2) {
            checkAndApplyPieRule();
        }
        Set<Cell> availableCells = availableCellsForCurrentPlayer();
        checkMandatoryPass(availableCells);
        Position inputPosition = chooseNextMove(availableCells);

        Color newStone = currentPlayer.getColor();
        board.placeStone(inputPosition, newStone);
        Displayer.printBoard(board);

    }

    private void checkMandatoryPass(Set<Cell> availableCells) {
        if (availableCells.isEmpty()) {
            Displayer.passMessage(currentPlayer);
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
        boolean accepted;
        do {
            accepted = true;
            Displayer.currentPlayerMessage(currentPlayer);
            inputPosition = InputHandler.inputMove();
            inputCell = board.getCell(inputPosition);
            if(inputCell == null){
                accepted = false;
                Displayer.positionOutsideBoardMessage();
            }
            else if(inputCell.isOccupied()){
                accepted = false;
                Displayer.alreadyPlayedPositionMessage();
            }
            else if(!availableCells.contains(inputCell)){
                accepted = false;
                Displayer.invalidMoveMessage();
            }
        } while (!accepted);
        return inputPosition;
    }

    public boolean checkWin() {
        boolean someoneHasWon = rules.checkChain(currentPlayer.getColor());
        if(someoneHasWon) {
            Displayer.winMessage(currentPlayer);
        }
        return someoneHasWon;
    }

}

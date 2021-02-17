package konobi;

import java.util.Set;

public class Game {

    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    Rules rules;
    int movesCounter;


    public static Game init() {
        Displayer.welcomeMessage();
        int dimension = IoHandler.inputDimension();
        String player1Name = IoHandler.inputPlayerName(1);
        String player2Name = IoHandler.inputPlayerName(2);
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

    public void checkAndApplyPieRule() {
            if(IoHandler.inputPie(currentPlayer)) {
                switchColors();
                changeTurn();
                Displayer.playerColorsMessage(player1, player2);
                showGameBoard();
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
            showGameBoard();
        }
        else{
            changeTurn();
        }
        if(movesCounter==2) {
            checkAndApplyPieRule();
        }
        Set<Cell> availableCells = rules.legalCellsOf(currentPlayer.getColor());
        checkMandatoryPass(availableCells);
        Position inputPosition=chooseNextMove(availableCells);

        Color newStone = currentPlayer.getColor();
        board.placeStone(inputPosition, newStone);
        showGameBoard();

    }

    private void checkMandatoryPass(Set<Cell> availableCells) {
        if (availableCells.isEmpty()) {
            Displayer.passMessage(currentPlayer);
            changeTurn();
        }
    }

    private Position chooseNextMove(Set<Cell> availableCells) {
        Cell inputCell;
        Position inputPosition;
        boolean accepted;
        do {
            accepted=true;
            Displayer.currentPlayerMessage(currentPlayer);
            inputPosition = IoHandler.inputMove();
            inputCell = board.getCell(inputPosition);
            if(inputCell==null){
                accepted=false;
                Displayer.positionOutsideBoardMessage();
            }
            else if(!availableCells.contains(inputCell)){
                accepted=false;
                Displayer.invalidMoveMessage();
            }
        } while (!accepted);
        return inputPosition;
    }

    public void showGameBoard(){
        Displayer.printBoard(this.board);
    }

    public boolean checkWin() {
        boolean someoneHasWon = rules.checkChain(currentPlayer.getColor());
        if(someoneHasWon) {
            Displayer.winMessage(currentPlayer);
        }
        return someoneHasWon;
    }



}

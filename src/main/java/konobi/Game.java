package konobi;

import java.util.Set;

public class Game {

    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    static IoHandler ioHandler = new IoHandler();
    Rules rules;
    int movesCounter;

    public static void welcomeMessage(){
        ioHandler.welcomeMessage();
    }

    public Game() {
        this.board = new Board(ioHandler.inputDimension());
        this.player1  = new Player(Color.BLACK, ioHandler.inputPlayerName(1));
        this.player2  = new Player(Color.WHITE, ioHandler.inputPlayerName(2));
        this.currentPlayer = player1;
        this.rules = new Rules(board);
        this.movesCounter = 0;
    }

    public void checkAndApplyPieRule() {
            if(ioHandler.inputPie(currentPlayer)) {
                switchColors();
                changeTurn();
                ioHandler.showPlayerColors(player1, player2);
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
            ioHandler.showPlayerColors(player1, player2);
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
            ioHandler.mustPass(currentPlayer);
            changeTurn();
        }
    }

    private Position chooseNextMove(Set<Cell> availableCells) {
        Cell inputCell;
        Position inputPosition;
        boolean accepted;
        do {
            accepted=true;
            ioHandler.printCurrentPlayer(currentPlayer);
            inputPosition = ioHandler.inputMove();
            inputCell = board.getCell(inputPosition);
            if(inputCell==null){
                accepted=false;
                ioHandler.positionOutsideBoard();
            }
            else if(!availableCells.contains(inputCell)){
                accepted=false;
                ioHandler.invalidMove();
            }
        } while (!accepted);
        return inputPosition;
    }

    public void showGameBoard(){
        ioHandler.printBoard(this.board);
    }

    public boolean checkWin() {
        boolean someoneHasWon = rules.checkChain(currentPlayer.getColor());
        if(someoneHasWon) {
            ioHandler.winMessage(currentPlayer);
        }
        return someoneHasWon;
    }



}

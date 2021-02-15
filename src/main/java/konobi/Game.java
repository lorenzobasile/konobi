package konobi;

import java.util.Set;

public class Game {


    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    static IoHandler ioHandler = new IoHandler();
    Rules rules;

    public Game() {
        ioHandler.welcomeMessage();
        int inputDimension = ioHandler.inputDimension();
        this.board = new Board(inputDimension);
        this.player1  = new Player(Color.BLACK, ioHandler.inputPlayerName(1));
        this.player2  = new Player(Color.WHITE, ioHandler.inputPlayerName(2));
        ioHandler.showPlayerColors(player1, player2);
        this.currentPlayer = player1;
        this.rules = new Rules(board);
    }

    public void checkAndApplyPieRule() {
            if(ioHandler.inputPie(currentPlayer)) {
                switchColors();
                changeTurn();
                ioHandler.showPlayerColors(player1, player2);
                showGameBoard();
            }
            else singleTurn();
    }

    public void changeTurn() {
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
        Set<Cell> availableCells = rules.legalCellsOf(currentPlayer.getColor());
        if (availableCells.isEmpty()) {
            changeTurn();
        }
        Cell inputCell;
        Position inputPosition;

        do {
            ioHandler.printCurrentPlayer(currentPlayer);
            inputPosition = ioHandler.inputMove();
            inputCell = board.getCellAt(inputPosition);

        } while (!availableCells.contains(inputCell));

        Color newStone = currentPlayer.getColor();
        board.placeStoneAt(newStone, inputPosition);
        changeTurn();
        showGameBoard();
    }

    public void showGameBoard(){
        ioHandler.printBoard(this.board);
    }

    public boolean checkWin() {
        return rules.checkChain(currentPlayer.getColor());
    }



}

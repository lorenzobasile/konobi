package konobi;

import java.util.Set;

public class Game {


    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    IoHandler ioHandler;
    Rules rules;



    public Game(int inputDimension) {
        this.board = new Board(inputDimension);
        this.player1  = new Player(Color.BLACK);
        this.player2  = new Player(Color.WHITE);
        this.currentPlayer = player1;
        this.ioHandler =  new IoHandler();
    }

    /*
    public void play() throws Exception{
        makeMove();
        pieRule();
        //while(true){
            //makeMove();
        //}
    }*/


    public void makeMove(Position position) {

        board.placeStoneAt(currentPlayer.getColor(), position);

        changeTurn();
    }


    public void applyPieRule() {
            switchColors();
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
    }

    public void showGameBoard(){
        ioHandler.printBoard(this.board);
    }

    public boolean checkWin() throws Exception {
        return rules.checkChain(currentPlayer.getColor());
    }



}

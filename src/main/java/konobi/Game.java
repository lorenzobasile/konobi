package konobi;

import java.util.HashSet;
import java.util.Set;

public class Game {


    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    InputHandler inputHandler;

    public Game(int inputDimension) {
        this.board = new Board(inputDimension);
        this.player1  = new Player(Color.BLACK);
        this.player2  = new Player(Color.WHITE);
        this.currentPlayer = player1;
        this.inputHandler =  new InputHandler();
    }

    /*
    public void play() throws Exception{
        makeMove();
        pieRule();
        //while(true){
            //makeMove();
        //}
    }*/

    public void makeMove(Position position) throws Exception{

        board.placeStoneAt(new Stone(currentPlayer.getColor()), position);

        changeTurn();
    }


    public void applyPieRule() {
            switchColors();
            changeTurn();
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

    public void singleTurn() throws Exception {
        Set<Cell> availableCells = board.legalCellsOf(currentPlayer.getColor());
        if (availableCells.isEmpty()) {
            changeTurn();
            return;
        }
        Cell inputCell;
        Position inputPosition;
        do {
            inputPosition = inputHandler.inputMove();
            inputCell = board.getCellAt(inputPosition);
        } while (!availableCells.contains(inputCell));
        Stone newStone = new Stone(currentPlayer.getColor());
        board.placeStoneAt(newStone, inputPosition);
        changeTurn();
    }
}

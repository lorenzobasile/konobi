package konobi;

import java.util.HashSet;
import java.util.Set;

public class Game {


    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;

    public Game(int inputDimension) {
        this.board = new Board(inputDimension);
        this.player1  = new Player(Color.BLACK);
        this.player2  = new Player(Color.WHITE);
        this.currentPlayer = player1;
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

    public Set<Cell> legalCellsOfCurrentPlayer() throws Exception {
        Color colorOfCurrentPlayer = currentPlayer.getColor();
        Set<Cell> setOfLegalCells = new HashSet<>();
        for (Cell cellOnBoard : this.board.cells){
            if(cellOnBoard.isOccupied()){
               continue;
            }
            else{
                Stone availableStone = new Stone(colorOfCurrentPlayer);
                this.board.placeStoneAt(availableStone, cellOnBoard.getPosition());
                boolean ruleOne = !(board.isCrosscutPlacement(cellOnBoard));
                boolean ruleTwo = board.isLegalWeakConnectionPlacement(cellOnBoard);
                if (ruleOne && ruleTwo){
                    setOfLegalCells.add(cellOnBoard);
                }
                cellOnBoard.reset();
            }
        }

        return setOfLegalCells;
    }
}

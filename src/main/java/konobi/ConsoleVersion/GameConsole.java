package konobi.ConsoleVersion;

import konobi.Entities.Board;
import konobi.Entities.Game;
import konobi.Entities.Player;

public class GameConsole extends Game {

    public GameConsole(int dimension, Player player1, Player player2) {
        super(dimension, player1, player2);
    }

    @Override
    protected void notifyPieRule() {
        getCurrentDisplay().playerColorsMessage(player1, player2);
    }

    @Override
    protected void notifyMandatoryPass(){
        getCurrentDisplay().passMessage(getCurrentPlayer());
    }

    @Override
    protected void printBoard(Board board){
        getCurrentDisplay().printBoard(board);
    }

    @Override
    protected void notifyEndOfMatch() {
        getOtherDisplay().winMessage(getOtherPlayer());
    }

}

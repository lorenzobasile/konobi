package konobi.ConsoleVersion;

import konobi.Entities.Board;
import konobi.Entities.Game;
import konobi.Entities.Player;

public class GameConsole extends Game {

    public GameConsole(int dimension, Player player1, Player player2) { super(dimension, player1, player2); }

    protected void notifyPieRule() {
        currentDisplay().playerColorsMessage(player1, player2);
    }

    protected void printBoard(Board board){
        currentDisplay().printBoard(board);
    }

    protected void notifyEndOfMatch() {
        otherDisplay().winMessage(getOtherPlayer());
    }

}

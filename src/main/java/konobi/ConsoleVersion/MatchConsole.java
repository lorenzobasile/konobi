package konobi.ConsoleVersion;

import konobi.Entities.Board;
import konobi.Entities.Player;
import konobi.Entities.Match;

public class MatchConsole extends Match {

    public MatchConsole(int dimension, Player player1, Player player2) { super(dimension, player1, player2); }

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

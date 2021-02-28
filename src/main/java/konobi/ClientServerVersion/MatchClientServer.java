
package konobi.ClientServerVersion;

import konobi.Entities.Board;
import konobi.Entities.Player;
import konobi.Entities.Match;

public class MatchClientServer extends Match {

    public MatchClientServer(int dimension, Player player1, Player player2) {
        super(dimension, player1, player2);
    }

    public void notifyPieRule() {
        currentDisplay().playerColorsMessage(player1, player2);
        otherDisplay().pieRuleHasBeenAppliedMessage();
        otherDisplay().playerColorsMessage(player1, player2);
    }

    public void printBoard(Board board){
        currentDisplay().printBoard(board);
        otherDisplay().otherPlayerHasMadeMoveMessage();
        otherDisplay().printBoard(board);
    }

    public void notifyEndOfMatch() {
        otherDisplay().winMessage(getOtherPlayer());
        currentDisplay().lossMessage(getCurrentPlayer());
    }

}

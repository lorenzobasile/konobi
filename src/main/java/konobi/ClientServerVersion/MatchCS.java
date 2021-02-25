
package konobi.ClientServerVersion;

import konobi.Entities.Board;
import konobi.Entities.Color;
import konobi.Entities.Player;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;
import konobi.ConsoleVersion.Match;

public class MatchCS extends Match {

    public static MatchCS init(InputHandler client1InputHandler, InputHandler client2InputHandler, Display client1Display, Display client2Display) {
        client1Display.welcomeMessage();
        client2Display.welcomeMessage();
        int dimension = client1InputHandler.getDimension();
        String player1Name = client1InputHandler.inputPlayerName(1);
        String player2Name = client2InputHandler.inputPlayerName(2);
        Player player1 = new Player(Color.BLACK, player1Name, client1InputHandler, client1Display);
        Player player2 = new Player(Color.WHITE, player2Name, client2InputHandler, client2Display);
        MatchCS match = new MatchCS(dimension, player1, player2);
        client1Display.playerColorsMessage(player1, player2);
        client2Display.playerColorsMessage(player1, player2);
        return match;
    }

    public MatchCS(int dimension, Player player1, Player player2) {
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

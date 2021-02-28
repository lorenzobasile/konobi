package konobi.ConsoleVersion;

import konobi.Entities.Player;
import konobi.Entities.MatchInitializer;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;


public class MatchInitializerConsole extends MatchInitializer {
    public MatchInitializerConsole() {
        Display commonDisplay = new Display(System.out);
        InputHandler commonInputHandler = new InputHandler(System.in, commonDisplay);

        player1Display = commonDisplay;
        player2Display = commonDisplay;
        player1InputHandler = commonInputHandler;
        player2InputHandler = commonInputHandler;
    }

    protected MatchConsole constructMatch(int dimension, Player player1, Player player2){
        return new MatchConsole(dimension, player1, player2);
    }

    protected void welcome() {
        player1Display.welcomeMessage();
    }

    protected void playerRolesMessage(Player player1, Player player2) {
        player1Display.playerColorsMessage(player1, player2);
    }

}

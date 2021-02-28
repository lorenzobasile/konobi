package konobi.ConsoleVersion;

import konobi.Entities.Player;
import konobi.Entities.GameInitializer;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;


public class GameInitializerConsole extends GameInitializer {
    public GameInitializerConsole() {
        Display commonDisplay = new Display(System.out);
        InputHandler commonInputHandler = new InputHandler(System.in, commonDisplay);

        player1Display = commonDisplay;
        player2Display = commonDisplay;
        player1InputHandler = commonInputHandler;
        player2InputHandler = commonInputHandler;
    }

    protected GameConsole constructMatch(int dimension, Player player1, Player player2){
        return new GameConsole(dimension, player1, player2);
    }

    protected void welcome() {
        player1Display.welcomeMessage();
    }

    protected void playerRolesMessage(Player player1, Player player2) {
        player1Display.playerColorsMessage(player1, player2);
    }

}

package konobi.ConsoleVersion;

import konobi.Entities.Player;
import konobi.Entities.GameInitializer;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputTerminal;


public class GameInitializerConsole extends GameInitializer {
    public GameInitializerConsole() {
        Display commonDisplay = new Display(System.out);
        InputTerminal commonInputTerminal = new InputTerminal(System.in, commonDisplay);
        player1Display = commonDisplay;
        player2Display = commonDisplay;
        player1InputTerminal = commonInputTerminal;
        player2InputTerminal = commonInputTerminal;
    }

    protected GameConsole constructGame(int dimension, Player player1, Player player2){
        return new GameConsole(dimension, player1, player2);
    }

    protected void welcome() {
        player1Display.welcomeMessage();
    }

    protected void showPlayerColors(Player player1, Player player2) {
        player1Display.playerColorsMessage(player1, player2);
    }

}

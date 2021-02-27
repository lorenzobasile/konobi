package konobi.ConsoleVersion;

import konobi.Entities.Color;
import konobi.Entities.Player;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;

public class GameRunner {
    protected InputHandler player1InputHandler, player2InputHandler;
    protected Display player1Display, player2Display;

    public GameRunner() {
        Display commonDisplay = new Display(System.out);
        InputHandler commonInputHandler = new InputHandler(System.in, commonDisplay);

        player1Display = commonDisplay;
        player2Display = commonDisplay;
        player1InputHandler = commonInputHandler;
        player2InputHandler = commonInputHandler;
    }

    public Match init() {
        welcome();
        int dimension = player1InputHandler.getDimension();
        String player1Name = player1InputHandler.inputPlayerName(1);
        String player2Name = player2InputHandler.inputPlayerName(2);
        Player player1 = new Player(Color.BLACK, player1Name, player1InputHandler, player1Display);
        Player player2 = new Player(Color.WHITE, player2Name, player2InputHandler, player2Display);
        Match match = constructMatch(dimension, player1, player2);
        playerRolesMessage(player1,player2);
        return match;
    }

    protected Match constructMatch(int dimension, Player player1, Player player2){
        return new Match(dimension, player1, player2);
    }

    protected void welcome() {
        player1Display.welcomeMessage();
    }

    protected void playerRolesMessage(Player player1, Player player2) {
        player1Display.playerColorsMessage(player1, player2);
    }
}
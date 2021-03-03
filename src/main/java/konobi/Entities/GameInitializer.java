package konobi.Entities;

import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;

public abstract class GameInitializer {
    protected InputHandler player1InputHandler, player2InputHandler;
    protected Display player1Display, player2Display;

    public Game init() {
        welcome();
        int dimension = player1InputHandler.getDimension();
        String player1Name = player1InputHandler.inputPlayerName(1);
        String player2Name = player2InputHandler.inputPlayerName(2);
        Player player1 = new Player(Color.BLACK, player1Name, player1InputHandler, player1Display);
        Player player2 = new Player(Color.WHITE, player2Name, player2InputHandler, player2Display);
        Game game = constructGame(dimension, player1, player2);
        showPlayerColors(player1,player2);
        return game;
    }

    protected abstract Game constructGame(int dimension, Player player1, Player player2);

    protected abstract void welcome();

    protected abstract void showPlayerColors(Player player1, Player player2);
}
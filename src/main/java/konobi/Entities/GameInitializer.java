package konobi.Entities;

import konobi.InputOutput.Display;
import konobi.InputOutput.InputTerminal;

public abstract class GameInitializer {
    protected InputTerminal player1InputTerminal, player2InputTerminal;
    protected Display player1Display, player2Display;

    public Game prepareAndConstructGame() {
        welcome();
        int dimension = player1InputTerminal.getDimension();
        String player1Name = player1InputTerminal.inputPlayerName(1);
        String player2Name = player2InputTerminal.inputPlayerName(2);
        Player player1 = new Player(Color.BLACK, player1Name, player1InputTerminal, player1Display);
        Player player2 = new Player(Color.WHITE, player2Name, player2InputTerminal, player2Display);
        Game game = constructGame(dimension, player1, player2);
        showPlayerColors(player1,player2);
        return game;
    }

    protected abstract Game constructGame(int dimension, Player player1, Player player2);

    protected abstract void welcome();

    protected abstract void showPlayerColors(Player player1, Player player2);
}
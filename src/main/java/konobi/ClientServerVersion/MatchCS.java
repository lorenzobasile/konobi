
package konobi.ClientServerVersion;

import konobi.Entities.Color;
import konobi.Entities.Player;
import konobi.Entities.Position;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;
import konobi.ConsoleVersion.Match;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MatchCS extends Match {

    public static MatchCS init(InputStream client1InputStream, InputStream client2InputStream, OutputStream client1OutputStream, OutputStream client2OutputStream) throws IOException {
        Display display=new Display(client1OutputStream);
        InputHandler inputHandler=new InputHandler(client1InputStream, display);
        display.welcomeMessage();
        int dimension = inputHandler.getDimension();
        String player1Name = inputHandler.inputPlayerName(1);
        display.setOut(client2OutputStream);
        inputHandler.setIn(client2InputStream);
        String player2Name = inputHandler.inputPlayerName(2);
        Player player1 = new Player(Color.BLACK, player1Name, client1InputStream, client1OutputStream);
        Player player2 = new Player(Color.WHITE, player2Name, client2InputStream, client2OutputStream);
        MatchCS match = new MatchCS(dimension, player1, player2, inputHandler, display);
        display.playerColorsMessage(player1, player2);
        return match;
    }

    public MatchCS(int dimension, Player player1, Player player2, InputHandler inputHandler, Display display) {
        super(dimension, player1, player2, inputHandler, display);
    }

    public void singleTurn() throws IOException {
        setIO();
        display.printBoard(gameState.getBoard());
        display.currentPlayerMessage(getCurrentPlayer());
        if(gameState.currentPlayerCanApplyPieRule() && inputHandler.playerWantsToApplyPieRule(getCurrentPlayer())) {
            applyPieRule();
        } else {
            regularMove();
        }
        display.printBoard(gameState.getBoard());
        gameState.changeTurn();
    }



    private void setIO() {
        inputHandler.setIn(getCurrentInputStream());
        display.setOut(getCurrentOutputStream());
    }

    private OutputStream getCurrentOutputStream() {
        return getCurrentPlayer().getOutputStream();
    }

    private InputStream getCurrentInputStream() {
        return getCurrentPlayer().getInputStream();
    }
}

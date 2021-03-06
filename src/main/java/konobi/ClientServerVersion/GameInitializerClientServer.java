
package konobi.ClientServerVersion;

import konobi.Entities.GameInitializer;
import konobi.Entities.Player;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputTerminal;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameInitializerClientServer extends GameInitializer {

    public GameInitializerClientServer(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket client1Socket = serverSocket.accept();
        player1Display = new Display(client1Socket.getOutputStream());
        player1InputTerminal = new InputTerminal(client1Socket.getInputStream(), player1Display);
        player1Display.waitingForOtherPlayerMessage();
        Socket client2Socket = serverSocket.accept();
        player2Display = new Display(client2Socket.getOutputStream());
        player2InputTerminal = new InputTerminal(client2Socket.getInputStream(), player2Display);
    }

    protected GameClientServer constructGame(int dimension, Player player1, Player player2){
        return new GameClientServer(dimension, player1, player2);
    }

    protected void welcome() {
        player1Display.welcomeMessage();
        player2Display.welcomeMessage();
    }

    protected void showPlayerColors(Player player1, Player player2) {
        player1Display.playerColorsMessage(player1, player2);
        player2Display.playerColorsMessage(player1, player2);
    }

}

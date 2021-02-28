
package konobi.ClientServerVersion;

import konobi.Entities.MatchInitializer;
import konobi.Entities.Player;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MatchInitializerClientServer extends MatchInitializer {

    public MatchInitializerClientServer(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket client1Socket = serverSocket.accept();
        player1Display = new Display(client1Socket.getOutputStream());
        player1InputHandler = new InputHandler(client1Socket.getInputStream(), player1Display);
        player1Display.waitingForOtherPlayerMessage();
        Socket client2Socket = serverSocket.accept();
        player2Display = new Display(client2Socket.getOutputStream());
        player2InputHandler = new InputHandler(client2Socket.getInputStream(), player2Display);
    }

    protected MatchClientServer constructMatch(int dimension, Player player1, Player player2){
        return new MatchClientServer(dimension, player1, player2);
    }

    @Override
    protected void welcome() {
        player1Display.welcomeMessage();
        player2Display.welcomeMessage();
    }

    @Override
    protected void playerRolesMessage(Player player1, Player player2) {
        player1Display.playerColorsMessage(player1, player2);
        player2Display.playerColorsMessage(player1, player2);
    }

}

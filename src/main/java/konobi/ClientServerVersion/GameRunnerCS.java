
package konobi.ClientServerVersion;

import konobi.ConsoleVersion.GameRunner;
import konobi.ConsoleVersion.Match;
import konobi.Entities.Player;
import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameRunnerCS extends GameRunner {

    public GameRunnerCS(int portNumber) throws IOException {
        System.out.println(portNumber);
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("created server");
        Socket client1Socket = serverSocket.accept();
        player1Display = new Display(client1Socket.getOutputStream());
        player1InputHandler = new InputHandler(client1Socket.getInputStream(), player1Display);
        player1Display.waitingForOtherPlayerMessage();
        Socket client2Socket = serverSocket.accept();
        player2Display = new Display(client2Socket.getOutputStream());
        player2InputHandler = new InputHandler(client2Socket.getInputStream(), player2Display);
    }

    protected MatchCS constructMatch(int dimension, Player player1, Player player2){
        return new MatchCS(dimension, player1, player2);
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

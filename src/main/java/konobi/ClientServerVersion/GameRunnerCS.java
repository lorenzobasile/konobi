
package konobi.ClientServerVersion;

import konobi.InputOutput.Display;
import konobi.InputOutput.InputHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameRunnerCS {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            Socket client1Socket = serverSocket.accept();
            Display client1Display=new Display(client1Socket.getOutputStream());
            InputHandler client1InputHandler=new InputHandler(client1Socket.getInputStream(), client1Display);
            client1Display.waitingForOtherPlayerMessage();
            Socket client2Socket = serverSocket.accept();
            Display client2Display=new Display(client2Socket.getOutputStream());
            InputHandler client2InputHandler=new InputHandler(client2Socket.getInputStream(), client2Display);
            MatchCS match = MatchCS.init(client1InputHandler, client2InputHandler, client1Display, client2Display);
            do {
                match.singleTurn();
            } while (!match.checkWin());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

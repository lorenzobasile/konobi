package konobi.ClientServerVersion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameRunnerCS {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            Socket client1Socket = serverSocket.accept();
            Socket client2Socket = serverSocket.accept();
            MatchCS match = MatchCS.init(client1Socket.getInputStream(), client2Socket.getInputStream(), client1Socket.getOutputStream(), client2Socket.getOutputStream());
            do {
                match.singleTurn();
            } while (!match.checkWin());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
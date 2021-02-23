package konobi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameRunner {

    public static void main(String[] args){

        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            Socket client1Socket = serverSocket.accept();
            Socket client2Socket = serverSocket.accept();
            Match match = Match.init(client1Socket.getInputStream(), client2Socket.getInputStream(), client1Socket.getOutputStream(), client2Socket.getOutputStream());
            do {
                match.singleTurn();
            } while (!match.checkWin());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
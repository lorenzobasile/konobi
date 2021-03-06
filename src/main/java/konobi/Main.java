package konobi;

import konobi.ClientServerVersion.GameInitializerClientServer;
import konobi.ConsoleVersion.GameInitializerConsole;
import konobi.Entities.Game;
import konobi.Entities.GameInitializer;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int portNumber;
        GameInitializer gameInitializer;
        if (args.length > 1) {
            System.out.println("Error: too many arguments");
            return;
        }
        else if (args.length == 1) {
            try {
                portNumber = Integer.parseInt(args[0]);
                gameInitializer = new GameInitializerClientServer(portNumber);
            } catch(NumberFormatException invalidPortNumber){
                System.out.println("Invalid port number");
                return;
            } catch(IOException ioException){
                System.out.println("I/O error: not able to establish client-server connection");
                return;
            }
        }
        else{
            gameInitializer = new GameInitializerConsole();
        }
        Game game = gameInitializer.prepareAndConstructGame();
        game.play();
    }
}

package konobi;

import konobi.ClientServerVersion.GameInitializerClientServer;
import konobi.ConsoleVersion.GameInitializerConsole;
import konobi.Entities.Game;
import konobi.Entities.GameInitializer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int portNumber;
        GameInitializer gameInitializer = null;
        if (args != null && args.length > 1) {
            System.err.println("error: too many arguments");
            System.exit(1);
        }
        else if (args != null && args.length == 1) {
            try {
                portNumber = Integer.parseInt(args[0]);
                gameInitializer = new GameInitializerClientServer(portNumber);
            } catch(NumberFormatException invalidPortNumber){
                System.err.println("Invalid port number");
                System.exit(1);
            } catch(IOException ioException){
                System.err.println("I/O error: not able to establish client-server connection");
                System.exit(1);
            }
        }
        else{
            gameInitializer = new GameInitializerConsole();
        }
        Game game = gameInitializer.init();
        game.play();
    }



}

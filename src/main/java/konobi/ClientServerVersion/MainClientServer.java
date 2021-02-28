package konobi.ClientServerVersion;

import konobi.Entities.Game;

import java.io.IOException;

public class MainClientServer {
    public static void main(String[] args) {
        int portNumber = 4444;

        if (args.length > 1) {
            System.err.println("error: too many arguments");
            System.exit(1);
        }
        if (args.length == 1) {
            portNumber = Integer.parseInt(args[0]);
        }

        try {
            GameInitializerClientServer matchInitializer = new GameInitializerClientServer(portNumber);
            Game game = matchInitializer.init();
            game.play();
        } catch (IOException e) {
            System.err.println("I/O error: not able to establish client-server connection");
            System.exit(1);
        }
    }


}

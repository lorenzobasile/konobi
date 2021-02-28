package konobi;

import konobi.ClientServerVersion.GameRunnerCS;
import konobi.ClientServerVersion.MatchCS;

import java.io.IOException;

public class MainCS {
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
            GameRunnerCS gameRunner = new GameRunnerCS(portNumber);
            MatchCS match = (MatchCS)gameRunner.init();
            match.play();
        } catch (IOException e) {
            System.err.println("I/O error: not able to establish client-server connection");
            System.exit(1);
        }
    }


}

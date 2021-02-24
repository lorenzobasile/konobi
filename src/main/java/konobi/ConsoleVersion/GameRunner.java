package konobi.ConsoleVersion;

import java.io.IOException;

public class GameRunner {
    public static void main(String[] args){

        try {
            Match match = Match.init();
            do {
                match.singleTurn();
            } while (!match.checkWin());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
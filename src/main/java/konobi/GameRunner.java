package konobi;

import java.io.IOException;

public class GameRunner {

    public static void main(String[] args){
        Match match = null;
        try {
            match = Match.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            match.singleTurn();
        } while (!match.checkWin());
    }
}
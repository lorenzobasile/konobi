package konobi;

public class GameRunner {

    public static void main(String[] args){
        Match match = Match.init();
        do {
            match.singleTurn();
        } while (!match.checkWin());
    }
}
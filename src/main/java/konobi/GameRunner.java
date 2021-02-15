package konobi;

public class GameRunner {

    public static void main(String[] args) {
            Game game = new Game();
            game.singleTurn();
            game.checkAndApplyPieRule();
            do {
                game.singleTurn();
            } while (!game.checkWin());
    }
}
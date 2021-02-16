package konobi;

public class GameRunner {

    public static void main(String[] args) {
            Game.welcomeMessage();
            Game game = new Game();
            do {
                game.singleTurn();

            } while (!game.checkWin());
    }
}
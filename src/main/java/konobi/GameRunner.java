package konobi;

public class GameRunner {

    public static void main(String[] args){
        System.out.println("hello");
        Game game = Game.init();
        do {
            game.singleTurn();
        } while (!game.checkWin());
    }
}
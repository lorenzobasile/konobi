package konobi;

import konobi.Model.Game;

public class GameRunner {

    public static void main(String[] args){
        Game game = Game.init();
        do {
            game.singleTurn();
        } while (!game.checkWin());
    }
}
package konobi.ConsoleVersion;

import konobi.Entities.Game;

public class MainConsole {

    public static void main(String[] args){
        GameInitializerConsole matchInitializer = new GameInitializerConsole();
        Game game = matchInitializer.init();
        game.play();

    }


}

package konobi.ConsoleVersion;

import konobi.Entities.Game;

public class MainConsole {

    public static void main(String[] args){
        GameInitializerConsole gameInitializer = new GameInitializerConsole();
        Game game = gameInitializer.init();
        game.play();

    }


}

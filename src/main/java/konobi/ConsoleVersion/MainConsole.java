package konobi.ConsoleVersion;

import konobi.Entities.Match;

public class MainConsole {

    public static void main(String[] args){
        MatchInitializerConsole matchInitializer = new MatchInitializerConsole();
        Match match = matchInitializer.init();
        match.play();

    }


}

package konobi.ConsoleVersion;

public class Main {

    public static void main(String[] args){
        // sto rimuovendo la code publication fra CS e non CS, ma non quella fra player1 e player2
        GameRunner gameRunner = new GameRunner();
        Match match = gameRunner.init();
        match.play();

    }


}

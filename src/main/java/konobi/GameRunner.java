package konobi;

public class GameRunner {

    public static void main(String[] args) {
        Game game = new Game(10);
        try {
            game.singleTurn();
        }
        catch(Exception e){}
        game.ioHandler.printBoard(game.board);
    }

    /*
    public void play() throws Exception{
        makeMove();
        pieRule();
        //while(true){
            //makeMove();
        //}
    }*/
}

import org.junit.Test;


public class testGame {
    Game game = new Game(10);

    @Test(expected=Exception.class)
    public void blackPlaysFirst() throws Exception{
        game.play(Color.WHITE, Position.at(0,4));
    }

    @Test(expected=Exception.class)
    public void whiteShouldPlayAfterBlack() throws Exception {
        game.play(Color.BLACK, Position.at(0,0));
        game.play(Color.BLACK, Position.at(0,0));
    }
}

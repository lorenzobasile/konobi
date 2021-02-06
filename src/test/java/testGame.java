import org.junit.Test;


public class testGame {

    @Test(expected=Exception.class)
    public void blackPlaysFirst() throws Exception{
        Game game = new Game(10);
        game.play(Color.WHITE, Position.at(0,4));
    }
}

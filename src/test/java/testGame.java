import org.junit.Test;


public class testGame {
    Game game = new Game(10);

    @Test(expected=Exception.class)
    public void shouldNotPlayInOccupiedPosition() throws Exception {
        game.play(Position.at(0,0));
        game.play(Position.at(0,0));
    }
}

package konobi.Entities;

import konobi.testMainConsole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static konobi.Entities.Position.at;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testGameState {
    private GameState gameState;

    @BeforeEach
    public void initialize(){
        gameState = new GameState(5, Color.BLACK);
    }

    @Test
    public void afterAStoneIsPlacedCellIsOccupied() {
        gameState.updateBoard(at(1,1));
        assertTrue(gameState.isAlreadyOccupied(at(1,1)));
    }

    @Test
    public void moveOutsideBoard(){
        assertTrue(gameState.isMoveOutsideBoard(at(6, 6)));
    }

    @Test
    public void invalidMoveDueToWeakConnection() {
        gameState.updateBoard(at(1,1));
        gameState.changeTurn();
        gameState.updateBoard(at(2,1));
        gameState.changeTurn();
        assertTrue(gameState.isMoveInvalid(at(2,2)));
    }

    @Test
    public void whitePlayerHasToPass() throws URISyntaxException, IOException {
        URL filePath = testMainConsole.class.getClassLoader().getResource("pass_test.txt");
        assert filePath != null;
        List<String> fileLines = Files.readAllLines(Paths.get(filePath.toURI()));
        for (int i = 0; i<fileLines.size(); i+=2){
            int x = Integer.parseInt(fileLines.get(i));
            int y = Integer.parseInt(fileLines.get(i+1));
            gameState.updateBoard(at(x, y));
            gameState.changeTurn();
        }
        assertTrue(gameState.isPassMandatory());
    }

}
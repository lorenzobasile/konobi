package konobi.Entities;

import konobi.InputOutput.Display;
import konobi.testGameRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static konobi.Entities.Position.at;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class testGameState {

    private GameState state;

    @BeforeEach
    public void initialize(){
        state = new GameState(5, Color.BLACK);
    }

    @Test
    public void afterAStoneIsPlacedItsCellIsOccupied() {
        state.updateBoard(at(1,1));
        assertTrue(state.isAlreadyOccupied(at(1,1)));
    }

    @Test
    public void moveOutsideBoard(){
        assertTrue(state.outsideBoardMove(at(6, 6)));
    }

    @Test
    public void nextMoveIsInvalid() {
        state.updateBoard(at(1,1));
        state.updateBoard(at(2,1));
        assertTrue(state.isInvalidMove(at(2,2)));
    }

    @Test
    public void whitePlayerHasToPass() throws URISyntaxException, IOException {

        URL filePath = testGameRunner.class.getClassLoader().getResource("pass_test.txt");
        assert filePath != null;
        List<String> fileLines = Files.readAllLines(Paths.get(filePath.toURI()));

        for (int i = 0; i<fileLines.size(); i+=2){
            int x = Integer.parseInt(fileLines.get(i));
            int y = Integer.parseInt(fileLines.get(i+1));
            state.updateBoard(at(x, y));
        }

        assertTrue(state.currentPlayerHasToPass());
    }


}
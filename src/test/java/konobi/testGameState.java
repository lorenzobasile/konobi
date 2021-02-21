package konobi;

import konobi.Entities.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static konobi.Entities.Position.at;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class testGameState {

    private GameState state;

    @BeforeEach
    public void initialize(){
        state = new GameState(5);
    }

    @Test
    public void afterAStoneIsPlacedItsPositionIsOccupied() {
        state.updateBoard(at(1,1));
        assertTrue(state.isAlreadyOccupied(at(1,1)));
    }

    @Test
    public void nextMoveIsInvalid() {
        state.updateBoard(at(1,1));
        state.updateBoard(at(2,1));
        assertTrue(state.isInvalidMove(at(2,2)));
    }

}
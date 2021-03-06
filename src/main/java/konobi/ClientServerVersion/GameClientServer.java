package konobi.ClientServerVersion;

import konobi.Entities.Board;
import konobi.Entities.Game;
import konobi.Entities.Player;

public class GameClientServer extends Game {

    public GameClientServer(int dimension, Player player1, Player player2) {
        super(dimension, player1, player2);
    }

    @Override
    public void notifyPieRule() {
        getCurrentDisplay().playerColorsMessage(player1, player2);
        getOtherDisplay().pieRuleHasBeenAppliedMessage();
        getOtherDisplay().playerColorsMessage(player1, player2);
    }

    @Override
    protected void notifyMandatoryPass(){
        getCurrentDisplay().passMessage(getCurrentPlayer());
        getOtherDisplay().passMessage(getCurrentPlayer());
    }

    @Override
    public void printBoard(Board board){
        getCurrentDisplay().printBoard(board);
        getOtherDisplay().otherPlayerHasMadeMoveMessage();
        getOtherDisplay().printBoard(board);
    }

    @Override
    public void notifyEndOfMatch() {
        getOtherDisplay().winMessage(getOtherPlayer());
        getCurrentDisplay().lossMessage(getCurrentPlayer());
    }

}

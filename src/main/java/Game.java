public class Game {

    Board board;
    Color lastTurn;

    public Game(int inputDimension) {
        this.board = new Board(inputDimension);
        this.lastTurn = Color.NONE;
    }

    public void play(Color color, Position position) throws Exception{
        if(lastTurn == Color.NONE && color == Color.WHITE){
            throw new Exception("invalid first player");
        }
        if(lastTurn == color) {
            throw new Exception("invalid next player");
        }

        board.placeTileAt(color, position);
        lastTurn = color;
    }
}

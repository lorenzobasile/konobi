public class Game {

    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;

    public Game(int inputDimension) {
        this.board = new Board(inputDimension);
        this.player1  = new Player(Color.BLACK);
        this.player2  = new Player(Color.WHITE);
        this.currentPlayer = player1;
    }


    public void play(Position position) throws Exception{

        board.placeTileAt(currentPlayer.getColor(), position);

        changeTurn();
    }

    private void changeTurn() {
        if (currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
    }
}

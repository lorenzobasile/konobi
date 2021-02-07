public class Game {

    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    InputHandler inputhandler;

    public Game(int inputDimension) {
        this.board = new Board(inputDimension);
        this.player1  = new Player(Color.BLACK);
        this.player2  = new Player(Color.WHITE);
        this.currentPlayer = player1;
        this.inputhandler = new InputHandler();
    }

    public void play() throws Exception{
        makeMove();
        pieRule();
        //while(true){
            //makeMove();
        //}
    }

    public void makeMove() throws Exception{

        Position position = inputhandler.inputMove();
        board.placeTileAt(currentPlayer.getColor(), position);

        changeTurn();
    }

    public void pieRule() {
        boolean answer = inputhandler.inputPie();
        if(answer) {
            switchColors();
            changeTurn();
        }

    }

    private void changeTurn() {
        if (currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
    }

    private void switchColors() {
        Color temp = player2.getColor();
        player2.setColor(player1.getColor());
        player1.setColor(temp);
    }
}

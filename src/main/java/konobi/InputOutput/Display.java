package konobi.InputOutput;

import konobi.Entities.*;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.IntStream;

import static konobi.Entities.Position.at;


public class Display {
    PrintWriter out;

    private static final String KONOBI_LOGO = " __  ___   ______   .__   __.   ______   .______    __ \n" +
            "|  |/  /  /  __  \\  |  \\ |  |  /  __  \\  |   _  \\  |  |\n" +
            "|  '  /  |  |  |  | |   \\|  | |  |  |  | |  |_)  | |  |\n" +
            "|    <   |  |  |  | |  . `  | |  |  |  | |   _  <  |  |\n" +
            "|  .  \\  |  `--'  | |  |\\   | |  `--'  | |  |_)  | |  |\n" +
            "|__|\\__\\  \\______/  |__| \\__|  \\______/  |______/  |__|\n" +
            "                                                       ";
    private static final String RULES_PAGE = "https://boardgamegeek.com/boardgame/123213/konobi";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BROWN = "\u001b[48;5;179m";
    public static final String ANSI_BEIGE = "\u001b[48;5;130m";
    public static final String ANSI_BLACK_CIRCLE = "\u26AB";
    public static final String ANSI_WHITE_CIRCLE = "\u26AA";

    public Display(OutputStream out) {
        this.out = new PrintWriter(out, true);
    }

    public Display(){
        out = new PrintWriter(System.out, true);
    }

    public void printEmptyLine(){
        out.println();
    }

    public void welcomeMessage(){
        out.println(KONOBI_LOGO);
        out.println("Welcome to Konobi!");
        out.println("Official rules and documentation: "+RULES_PAGE);
        printEmptyLine();
    }

    public void inputBoardDimensionMessage() {
        out.print("Please, insert the dimension of the board: ");
        out.flush();
    }

    public void inputXCoordinateMessage() {
        out.print("Enter x coordinate: ");
        out.flush();
    }

    public void inputYCoordinateMessage() {
        out.print("Enter y coordinate: ");
        out.flush();
    }

    public void askPieRuleMessage(String name) {
        out.print(name + ", do you want to apply the pie rule? (y/n) ");
        out.flush();
    }

    public void pieRuleHasBeenAppliedMessage() {
        out.println("Pie rule has been applied: now you are WHITE");
    }

    public void playerColorsMessage(Player player1, Player player2){
        printEmptyLine();
        out.println(player1.getName()+" is "+player1.getColor()+", "+player2.getName()+" is "+player2.getColor());
    }

    public void currentPlayerTurnMessage(Player currentPlayer) {
        printEmptyLine();
        out.println(currentPlayer.getName()+", it's your turn!");
    }

    public void playerNameMessage(int whichPlayer) {
        out.print("Player " + whichPlayer + ": what's your name? ");
        out.flush();
    }

    public void playerNameMessage() {
        out.print("What's your name? ");
        out.flush();
    }

    public void winMessage(Player currentPlayer) {
        out.println("Congratulations " + currentPlayer.getName() + ", you won!");
    }

    public void positionOutsideBoardMessage() {
        out.println("The position you inserted is outside the board, please reinsert");
    }

    public void invalidMoveMessage() {
        out.println("This move is illegal, please reinsert");
    }

    public void alreadyPlayedPositionMessage() {
        out.println("This position is already occupied, please reinsert");
    }

    public void passMessage(Player currentPlayer) {
        out.println("No available moves for "+currentPlayer.getName()+", who must pass");
    }

    public void printExceptionCause(Exception e) {
        out.println(e.getMessage());
    }

    private BiConsumer<Position, Board> printConsumer = (position, board) -> printPosition(position, board);

    public void printBoard(Board board) {
        out.println();
        IntStream.range(0, board.dimension())
                .map(i -> board.dimension() - i)
                .mapToObj(i -> IntStream.rangeClosed(1, board.dimension())
                                        .mapToObj(j -> at(j,i)))
                                        .flatMap(Function.identity())
                                        .forEachOrdered(p-> printConsumer.accept(p, board));

        /*
        for (int i = board.dimension(); i>0; i--){
            for(int j = 1; j<=board.dimension(); j++){
                Position position = at(j, i);
                drawSquareAt(position);
                ifPresentDrawStoneAt(position, board);
            }
            out.println();
        }*/
    }

    private void printPosition(Position position, Board board){
        drawSquareAt(position);
        ifPresentDrawStoneAt(position, board);
        if(position.getX()==board.dimension()) out.println();
    }

    private void ifPresentDrawStoneAt(Position position, Board board) {
        Cell cell = board.getCell(position);
        if (board.getCell(position).isOccupied()){
            if (cell.hasColor(Color.BLACK)){
                out.print(ANSI_BLACK_CIRCLE + ANSI_RESET);
            }
            else{
                out.print(ANSI_WHITE_CIRCLE + ANSI_RESET);
            }
        } else {
            out.print("  " + ANSI_RESET);
        }
    }

    private void drawSquareAt(Position position) {
        String squareRepresentation = ((position.getX()+ position.getY()) % 2 == 0) ? ANSI_BROWN : ANSI_BEIGE;
        out.print(squareRepresentation);
    }

    public void lossMessage(Player currentPlayer) {
        out.println(currentPlayer.getName()+" you lost!");
    }

    public void otherPlayerHasMadeMoveMessage() {
        printEmptyLine();
        out.println("The other player has played");
    }

    public void waitingForOtherPlayerMessage() {
        out.println("Waiting for the other player...");
    }
}
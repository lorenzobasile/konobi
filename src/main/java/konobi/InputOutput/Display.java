package konobi.InputOutput;

import konobi.Entities.*;
import java.io.OutputStream;
import java.io.PrintWriter;
import static konobi.Entities.Position.at;

public class Display {

    private final PrintWriter out;

    private static final String KONOBI_LOGO = " __  ___   ______   .__   __.   ______   .______    __ \n" +
            "|  |/  /  /  __  \\  |  \\ |  |  /  __  \\  |   _  \\  |  |\n" +
            "|  '  /  |  |  |  | |   \\|  | |  |  |  | |  |_)  | |  |\n" +
            "|    <   |  |  |  | |  . `  | |  |  |  | |   _  <  |  |\n" +
            "|  .  \\  |  `--'  | |  |\\   | |  `--'  | |  |_)  | |  |\n" +
            "|__|\\__\\  \\______/  |__| \\__|  \\______/  |______/  |__|\n" +
            "                                                       ";
    private static final String RULES_PAGE = "https://boardgamegeek.com/boardgame/123213/konobi";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BROWN = "\u001b[48;5;179m";
    private static final String ANSI_BEIGE = "\u001b[48;5;130m";
    private static final String ANSI_BLACK_CIRCLE = "\u26AB";
    private static final String ANSI_WHITE_CIRCLE = "\u26AA";
    private static final String CROSS_SYMBOL = "X";
    private static final String CIRCLE_SYMBOL = "O";
    private static final String CELL_CROSS_SYMBOL = "|X|";
    private static final String CELL_CIRCLE_SYMBOL = "|O|";
    private static final String CELL_EMPTY_SYMBOL = "|_|";


    public Display(OutputStream out) {
        this.out = new PrintWriter(out, true);
    }

    public void welcomeMessage(){
        out.println(KONOBI_LOGO);
        out.println("Welcome to Konobi!");
        out.println("Official rules and documentation: " + RULES_PAGE);
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
        out.println(player1.getName() + " is "+ playerSymbol(player1.getColor()) + ", " + player2.getName() + " is " + playerSymbol(player2.getColor()) );
    }

    private String playerSymbol(Color playerColor){
        if(isWindows()){
            return playerColor == Color.BLACK ? CROSS_SYMBOL : CIRCLE_SYMBOL;
        }
        else{
            return playerColor.toString();
        }
    }


    public void currentPlayerTurnMessage(Player currentPlayer) {
        printEmptyLine();
        out.println(currentPlayer.getName() + ", it's your turn!");
    }

    public void playerNameMessage(int whichPlayer) {
        out.print("Player " + whichPlayer + ": what's your name? ");
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
        out.println("No available moves for " + currentPlayer.getName() + ", who must pass");
    }

    public void notANumberMessage() {
        out.println("Not an integer, please reinsert");
    }

    public void printExceptionCause(Exception e) {
        out.println(e.getMessage());
    }

    public void printBoard(Board board) {
        printEmptyLine();
        for (int i = board.dimension(); i>0; i--){
            for(int j = 1; j <= board.dimension(); j++){
                Position position = at(j, i);
                drawSquareAt(position);
                ifPresentDrawStoneAt(position, board);
            }
            printEmptyLine();
        }
    }

    private void ifPresentDrawStoneAt(Position position, Board board) {
        Cell cell = board.getCell(position);
        if (board.getCell(position).isOccupied()){
            if (cell.hasColor(Color.BLACK)){
                String blackCircleRepresentation = isWindows() ? CELL_CROSS_SYMBOL : ANSI_BLACK_CIRCLE + ANSI_RESET;
                out.print(blackCircleRepresentation);
            }
            else{
                String whiteColorRepresentation = isWindows() ? CELL_CIRCLE_SYMBOL : ANSI_WHITE_CIRCLE + ANSI_RESET;
                out.print(whiteColorRepresentation);
            }
        } else {
            String emptyCellRepresentation = isWindows() ? CELL_EMPTY_SYMBOL : "  " + ANSI_RESET;
            out.print(emptyCellRepresentation);
        }
    }

    private void drawSquareAt(Position position) {
        if (isWindows())
            return;
        String squareRepresentation = ((position.getX()+ position.getY()) % 2 == 0) ? ANSI_BROWN : ANSI_BEIGE;
        out.print(squareRepresentation);
    }

    private boolean isWindows(){
        String OS = System.getProperty("os.name").toLowerCase();
        return OS.contains("windows");
    }

    public void lossMessage(Player currentPlayer) {
        out.println(currentPlayer.getName() + " you lost!");
    }

    public void otherPlayerHasMadeMoveMessage() {
        printEmptyLine();
        out.println("The other player has played");
    }

    public void waitingForOtherPlayerMessage() {
        out.println("Waiting for the other player...");
    }

    public void printEmptyLine(){
        out.println();
    }
}
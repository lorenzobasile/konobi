package konobi;

import static konobi.Position.at;

public class Displayer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BROWN = "\u001b[48;5;94m";
    public static final String ANSI_BEIGE = "\u001b[48;5;172m";

    public static void welcomeMessage(){
        System.out.println("Welcome to Konobi!");
    }

    public static void inputBoardDimensionMessage() {
        System.out.print("Please, insert the dimension of the board: ");
    }

    public static void notAnIntegerMessage() {
        System.out.println("Not an integer: reinsert value");
    }

    public static void negativeDimensionMessage() {
        System.out.println("Negative dimension: reinsert");
    }

    public static void inputXCoordinateMessage() {
        System.out.print("Enter x coordinate: ");
    }

    public static void inputYCoordinateMessage() {
        System.out.print("Enter y coordinate: ");
    }

    public static void askPieRuleMessage(String name) {
        System.out.println(name + ", do you want to apply the pie rule? (y/n) ");
    }

    public static void playerColorsMessage(Player player1, Player player2){
        System.out.println();
        System.out.println(player1.getName()+" is "+player1.getColor()+", "+player2.getName()+" is "+player2.getColor());
    }

    public static void currentPlayerMessage(Player currentPlayer) {
        System.out.println(currentPlayer.getName()+", it's your turn!");
        System.out.println();
    }

    public static void playerNameMessage(int whichPlayer) {
        System.out.print("Player " + whichPlayer + ": what's your name? ");
    }


    public static void winMessage(Player currentPlayer) {
        System.out.println("Congratulations " + currentPlayer.getName() + ", you won!");
    }

    public static void positionOutsideBoardMessage() {
        System.out.println("The position you inserted is outside the board, please reinsert");
    }

    public static void invalidMoveMessage() {
        System.out.println("This move is illegal, please insert a new position");
    }

    public static void passMessage(Player currentPlayer) {
        System.out.println("No available moves for "+currentPlayer.getName()+", who must pass");
    }


    public static void printBoard(Board board) {
        for (int i = board.dimension; i>0; i--){
            for(int j = 1; j<=board.dimension; j++){
                if((i+j) % 2 == 0){
                    System.out.print(ANSI_BROWN);
                }
                else{
                    System.out.print(ANSI_BEIGE);
                }
                if (board.getCell(at(j, i)).isOccupied()){
                    Color stone = board.getCell(at(j, i)).getColor();
                    if (stone== Color.BLACK){
                        System.out.print(ANSI_BLACK + "\u26AB" + ANSI_RESET);
                    }
                    else{
                        System.out.print(ANSI_WHITE + "\u26AA" + ANSI_RESET);
                    }
                } else {
                    System.out.print("  " + ANSI_RESET);
                }
            }
            System.out.println();
        }
    }
}

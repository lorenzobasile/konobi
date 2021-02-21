package konobi.InputOutput;

import konobi.Entities.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static konobi.Entities.Position.at;
import java.util.function.Function;



public class Display {

    private static final String KONOBI_LOGO = " __  ___   ______   .__   __.   ______   .______    __ \n" +
            "|  |/  /  /  __  \\  |  \\ |  |  /  __  \\  |   _  \\  |  |\n" +
            "|  '  /  |  |  |  | |   \\|  | |  |  |  | |  |_)  | |  |\n" +
            "|    <   |  |  |  | |  . `  | |  |  |  | |   _  <  |  |\n" +
            "|  .  \\  |  `--'  | |  |\\   | |  `--'  | |  |_)  | |  |\n" +
            "|__|\\__\\  \\______/  |__| \\__|  \\______/  |______/  |__|\n" +
            "                                                       ";
    private static final String RULES_PAGE = "https://boardgamegeek.com/boardgame/123213/konobi";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BROWN = "\u001b[48;5;179m";
    public static final String ANSI_BEIGE = "\u001b[48;5;130m";

    public static void welcomeMessage(){
        System.out.println(KONOBI_LOGO);
        System.out.println("Welcome to Konobi!");
        System.out.println("Official rules and documentation: "+RULES_PAGE);
        System.out.println();
    }

    public static void printEmptyLine(){
        System.out.println();
    }

    public static void inputBoardDimensionMessage() {
        System.out.print("Please, insert the dimension of the board: ");
    }

    public static void notAnIntegerMessage() {
        System.out.println("Not an integer: reinsert");
    }

    public static void inputXCoordinateMessage() {
        System.out.print("Enter x coordinate: ");
    }

    public static void inputYCoordinateMessage() {
        System.out.print("Enter y coordinate: ");
    }

    public static void askPieRuleMessage(String name) {
        System.out.print(name + ", do you want to apply the pie rule? (y/n) ");
    }

    public static void playerColorsMessage(Player player1, Player player2){
        System.out.println();
        System.out.println(player1.getName()+" is "+player1.getColor()+", "+player2.getName()+" is "+player2.getColor());
    }

    public static void currentPlayerMessage(Player currentPlayer) {
        System.out.println();
        System.out.println(currentPlayer.getName()+", it's your turn!");
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
        System.out.println("This move is illegal, please reinsert");
    }

    public static void alreadyPlayedPositionMessage() {
        System.out.println("This position is already occupied, please reinsert");
    }

    public static void passMessage(Player currentPlayer) {
        System.out.println("No available moves for "+currentPlayer.getName()+", who must pass");
    }

    public static void printExceptionCause(Exception e) {
        System.out.println(e.getMessage());
    }

    //private static BiConsumer<Position, Board> printConsumer = (position, board) -> displayPosition(position, board);

    public static void printBoard(Board board) {
        System.out.println();
        /*IntStream.range(0, board.dimension())
                .map(i -> board.dimension() - i)
                .mapToObj(i -> IntStream.rangeClosed(1, board.dimension())
                                        .mapToObj(j -> at(j,i)))
                                        .flatMap(Function.identity())
                                        .forEachOrdered(p-> printConsumer.accept(p, board));*/


        for (int i = board.dimension(); i>0; i--){
            for(int j = 1; j<=board.dimension(); j++){
                if((i+j) % 2 == 0){
                    System.out.print(ANSI_BROWN);
                }
                else{
                    System.out.print(ANSI_BEIGE);
                }
                if (board.getCell(at(j, i)).isOccupied()){
                    Cell cell = board.getCell(at(j, i));
                    if (cell.hasColor(Color.BLACK)){
                        System.out.print(ANSI_BLACK + "\u26AB"  + ANSI_RESET);
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

/*
public static void displayPosition(Position position, Board board) {
    if ((position.getX() + position.getY()) % 2 == 0) {
        System.out.print(ANSI_BROWN);
    } else {
        System.out.print(ANSI_BEIGE);
    }
    if (board.getCell(position).isOccupied()) {
        Cell cell = board.getCell(position);
        if (cell.hasColor(Color.BLACK)) {
            System.out.print(ANSI_BLACK + "\u26AB" + ANSI_RESET);
        } else {
            System.out.print(ANSI_WHITE + "\u26AA" + ANSI_RESET);
        }
    } else {
        System.out.print("  " + ANSI_RESET);
    }
    if(position.getX()==board.dimension()) System.out.println();
}*/
}
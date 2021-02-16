package konobi;

import java.util.Scanner;

import static konobi.Position.at;

public class IoHandler {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BROWN = "\u001b[48;5;94m";
    public static final String ANSI_BEIGE = "\u001b[48;5;172m";


    public void welcomeMessage(){
        System.out.println("Welcome to Konobi!");
    }

    public int inputDimension(){
        Scanner stdIn = new Scanner(System.in);
        System.out.print("Please, insert the dimension of the board: ");
        int dimension;
        if(stdIn.hasNextInt()) {
            dimension = stdIn.nextInt();
        }
        else{
            System.out.println("Not an integer: reinsert dimension");
            return inputDimension();
        }
        if(dimension<0) {
            System.out.println("Negative dimension: reinsert");
            return inputDimension();
        }
        System.out.println();
        return dimension;
    }


    public Position inputMove() {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("Enter x coordinate: ");
        int x, y;
        if(stdIn.hasNextInt()) {
            x = stdIn.nextInt();
        }
        else{
            System.out.println("Not an integer: reinsert coordinates");
            return inputMove();
        }
        System.out.print("Enter y coordinate: ");
        if(stdIn.hasNextInt()) {
            y = stdIn.nextInt();
        }
        else{
            System.out.println("Not an integer: reinsert coordinates");
            return inputMove();
        }
        return at(x,y);
    }

    public boolean inputPie(Player currentPlayer){
        Scanner stdIn = new Scanner(System.in);
        System.out.println(currentPlayer.getName()+", do you want to apply the pie rule? (y/n) ");
        String answer = stdIn.nextLine();
        if(answer.equals("y"))
            return true;
        else if(answer.equals("n"))
            return false;
        else
            return inputPie(currentPlayer);
    }

    public void showPlayerColors(Player player1, Player player2){
        System.out.println();
        System.out.println(player1.getName()+" is "+player1.getColor()+", "+player2.getName()+" is "+player2.getColor());
    }

    public void printBoard(Board board) {
        String c = Character.toString((char)7);
        for (int i = board.dimension; i>0; i--){
            for(int j = 1; j<=board.dimension; j++){
                if((i+j) % 2 == 0){
                    System.out.print(ANSI_BROWN);
                }
                else{
                    System.out.print(ANSI_BEIGE);
                }
                if (board.getCell(at(i, j)).isOccupied()){
                    Color stone = board.getCell(at(i, j)).getColor();
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


    public void printCurrentPlayer(Player currentPlayer) {
        System.out.println(currentPlayer.getName()+", it's your turn!");
        System.out.println();
    }

    public String inputPlayerName(int i) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("Player "+i+": what's your name? ");
        String name = stdIn.nextLine();
        return name;
    }

    public void winMessage(Player currentPlayer) {
        System.out.println("Congratulations " + currentPlayer.getName() + ", you won!");
    }

    public void positionOutsideBoard() {
        System.out.println("The position you inserted is outside the board, please reinsert");
    }

    public void invalidMove() {
        System.out.println("This move is illegal, please insert a new position");
    }

    public void mustPass(Player currentPlayer) {
        System.out.println("No available moves for "+currentPlayer.getName()+", who must pass");
    }
}

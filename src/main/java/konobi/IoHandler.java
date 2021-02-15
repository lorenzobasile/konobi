package konobi;

import java.util.Scanner;

public class IoHandler {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";


    public void welcomeMessage(){
        System.out.println("Welcome to Konobi!");
    }

    public int inputDimension(){
        Scanner stdIn = new Scanner(System.in);
        System.out.print("Please, insert the dimension of the board: ");
        int dimension = stdIn.nextInt();
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
        return Position.at(x,y);
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
        System.out.println(player1.getName()+" is "+player1.getColor()+", "+player2.getName()+" is "+player2.getColor());
    }

    public void printBoard(Board board) {

        String line = new String(new char[2*board.dimension]).replace('\0', '-');
        System.out.println(line);
        for (int i = board.dimension-1; i>=0; i--){
            for(int j = 0; j<board.dimension; ++j){
                if (board.cells.get(i+j*board.dimension).isOccupied()){
                    Color stone = board.cells.get(i+j*board.dimension).getColor();
                    if (stone== Color.BLACK){
                        System.out.print("X ");
                    }
                    else{
                        System.out.print("O ");
                    }
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println(line);
    }


    public void printCurrentPlayer(Player currentPlayer) {
        System.out.println(currentPlayer.getName()+", it's your turn!");
    }

    public String inputPlayerName(int i) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("Player "+i+": what's your name? ");
        String name = stdIn.nextLine();
        return name;
    }
}

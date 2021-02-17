package konobi;

import java.util.Scanner;

import static konobi.Position.at;

public class InputHandler {

    public static void setStdIn(Scanner stdIn) {
        InputHandler.stdIn = stdIn;
    }

    private static Scanner stdIn = new Scanner(System.in);

    public static int inputDimension(){
        Displayer.inputBoardDimensionMessage();
        String stringDimension = stdIn.nextLine();
        int dimension = 0;
        try {
            dimension = Integer.parseInt(stringDimension);
        }
        catch (NumberFormatException notANumber) {
            Displayer.notAnIntegerMessage();
            return inputDimension();
        }

        if(dimension<2) {
            Displayer.negativeDimensionMessage();
            return inputDimension();
        }
        System.out.println();
        return dimension;
    }


    public static Position inputMove() {
        Displayer.inputXCoordinateMessage();
        int x, y;
        if(stdIn.hasNextInt()) {
            x = Integer.parseInt(stdIn.nextLine());
        }
        else{
            Displayer.notAnIntegerMessage();
            return inputMove();
        }
        Displayer.inputYCoordinateMessage();
        if(stdIn.hasNextInt()) {
            y = Integer.parseInt(stdIn.nextLine());
        }
        else{
            Displayer.notAnIntegerMessage();
            return inputMove();
        }
        return at(x,y);
    }

    public static boolean inputPie(Player currentPlayer){
        Displayer.askPieRuleMessage(currentPlayer.getName());
        String answer = stdIn.nextLine();
        if(answer.equals("y"))
            return true;
        else if(answer.equals("n"))
            return false;
        else
            return inputPie(currentPlayer);
    }


    public static String inputPlayerName(int whichPlayer) {
        Displayer.playerNameMessage(whichPlayer);
        String name = stdIn.nextLine();
        System.out.println();
        return name;
    }


}

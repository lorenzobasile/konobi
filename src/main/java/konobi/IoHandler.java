package konobi;

import java.util.Scanner;

import static konobi.Position.at;

public class IoHandler {

    public static int inputDimension(){
        Scanner stdIn = new Scanner(System.in);
        Displayer.inputBoardDimensionMessage();
        int dimension;
        if(stdIn.hasNextInt()) {
            dimension = stdIn.nextInt();
        }
        else{
            Displayer.notAnIntegerMessage();
            return inputDimension();
        }
        if(dimension<0) {
            Displayer.negativeDimensionMessage();
            return inputDimension();
        }
        System.out.println();
        return dimension;
    }


    public static Position inputMove() {
        Scanner stdIn = new Scanner(System.in);
        Displayer.inputXCoordinateMessage();
        int x, y;
        if(stdIn.hasNextInt()) {
            x = stdIn.nextInt();
        }
        else{
            Displayer.notAnIntegerMessage();
            return inputMove();
        }
        Displayer.inputYCoordinateMessage();
        if(stdIn.hasNextInt()) {
            y = stdIn.nextInt();
        }
        else{
            Displayer.notAnIntegerMessage();
            return inputMove();
        }
        return at(x,y);
    }

    public static boolean inputPie(Player currentPlayer){
        Scanner stdIn = new Scanner(System.in);
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
        Scanner stdIn = new Scanner(System.in);
        Displayer.playerNameMessage(whichPlayer);
        String name = stdIn.nextLine();
        return name;
    }


}

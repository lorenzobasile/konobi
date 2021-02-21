package konobi.InputOutput;

import konobi.Entities.Position;
import konobi.InputOutput.Exceptions.NegativeNumberException;
import konobi.InputOutput.Exceptions.WrongAnswerException;
import konobi.Entities.Player;

import java.util.Scanner;

import static konobi.Entities.Position.at;

public class InputHandler {

    public static void setStdIn(Scanner stdIn) {
        InputHandler.stdIn = stdIn;
    }

    private static Scanner stdIn = new Scanner(System.in);

    public static int getDimension(){
        int dimension;
        try {
            dimension = inputDimension();
        } catch(NumberFormatException notANumber){
            Display.notAnIntegerMessage();
            return getDimension();
        } catch(Exception negativeDimension){
            Display.printExceptionCause(negativeDimension);
            return getDimension();
        }
        return dimension;
    }

    public static int inputDimension() throws Exception{
        Display.inputBoardDimensionMessage();
        String stringDimension = stdIn.nextLine();
        int dimension = Integer.parseInt(stringDimension);
        if (dimension < 1) {
            throw new NegativeNumberException("Negative dimension: please reinsert");
        }
        Display.printEmptyLine();
        return dimension;
    }


    public static Position inputMove() throws Exception{
        Display.inputXCoordinateMessage();
        String stringInput = stdIn.nextLine();
        int x = Integer.parseInt(stringInput);
        Display.inputYCoordinateMessage();
        stringInput = stdIn.nextLine();
        int y = Integer.parseInt(stringInput);
        if (x<1 || y<1) {
            throw new NegativeNumberException("The position you inserted is outside the board, please reinsert");
        }
        return at(x,y);
    }

    public static boolean inputPie(Player currentPlayer) throws Exception{
        Display.askPieRuleMessage(currentPlayer.getName());
        String answer = stdIn.nextLine();
        if(answer.equals("y"))
            return true;
        else if(answer.equals("n"))
            return false;
        else
            throw new WrongAnswerException("Invalid answer: please reinsert");
    }


    public static String inputPlayerName(int whichPlayer) {
        Display.playerNameMessage(whichPlayer);
        return stdIn.nextLine();
    }


}

package konobi.InputOutput;

import konobi.Entities.Position;
import konobi.InputOutput.Exceptions.NegativeNumberException;
import konobi.InputOutput.Exceptions.WrongAnswerException;
import konobi.Entities.Player;
import java.io.InputStream;
import java.util.Scanner;

import static konobi.Entities.Position.at;

public class InputHandler {

    private Scanner in;
    private final Display display;

    public InputHandler(InputStream in, Display display){
        this.in = new Scanner(in);
        this.display = display;
    }

    public void setIn(InputStream in) {
        this.in = new Scanner(in);
    }
    
    public int getDimension() {
        int dimension;
        try {
            dimension = inputDimension();
        } catch(NumberFormatException | NegativeNumberException wrongInput){
            display.printExceptionCause(wrongInput);
            return getDimension();
        }
        return dimension;
    }

    public int inputDimension() throws NegativeNumberException, NumberFormatException{
        display.inputBoardDimensionMessage();
        String stringDimension = in.nextLine();
        int dimension = Integer.parseInt(stringDimension);
        if (dimension < 1) {
            throw new NegativeNumberException("Negative dimension: please reinsert");
        }
        display.printEmptyLine();
        return dimension;
    }
    
    public Position inputMove() throws NegativeNumberException, NumberFormatException{
        display.inputXCoordinateMessage();
        String stringInput = in.nextLine();
        int x = Integer.parseInt(stringInput);
        display.inputYCoordinateMessage();
        stringInput = in.nextLine();
        int y = Integer.parseInt(stringInput);
        if (x<1 || y<1) {
            throw new NegativeNumberException("The position you inserted is outside the board, please reinsert");
        }
        return at(x,y);
    }

    public boolean getAnswerForPieRule(Player currentPlayer) throws WrongAnswerException {
        display.askPieRuleMessage(currentPlayer.getName());
        String answer = in.nextLine();
        if(answer.equals("y"))
            return true;
        else if(answer.equals("n"))
            return false;
        else
            throw new WrongAnswerException("Invalid answer: please reinsert");
    }

    public boolean playerWantsToApplyPieRule(Player currentPlayer) {
        boolean answer;
        try {
            answer = getAnswerForPieRule(currentPlayer);
        }
        catch(WrongAnswerException exception) {
            display.printExceptionCause(exception);
            return playerWantsToApplyPieRule(currentPlayer);
        }
        return answer;
    }

    public String inputPlayerName(int whichPlayer) {
        display.playerNameMessage(whichPlayer);
        return in.nextLine();
    }

}

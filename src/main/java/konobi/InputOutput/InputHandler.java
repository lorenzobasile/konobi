package konobi.InputOutput;

import konobi.Entities.Position;
import konobi.InputOutput.Exceptions.NegativeNumberException;
import konobi.InputOutput.Exceptions.WrongAnswerException;
import konobi.Entities.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static konobi.Entities.Position.at;

public class InputHandler {

    public void setIn(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
    }

    private BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    private Display display;

    public InputHandler(InputStream in, Display display){
        this.in = new BufferedReader(new InputStreamReader(in));
        this.display=display;
    }

    public InputHandler(Display display){
        this.display=display;
    }

    public int getDimension() throws IOException{
        int dimension;
        try {
            dimension = inputDimension();
        } catch(NumberFormatException notANumber){
            display.notAnIntegerMessage();
            return getDimension();
        } catch(NegativeNumberException negativeDimension){
            display.printExceptionCause(negativeDimension);
            return getDimension();
        }
        return dimension;
    }

    public int inputDimension() throws IOException, NegativeNumberException, NumberFormatException{
        display.inputBoardDimensionMessage();
        String stringDimension = in.readLine();
        int dimension = Integer.parseInt(stringDimension);
        if (dimension < 1) {
            throw new NegativeNumberException("Negative dimension: please reinsert");
        }
        display.printEmptyLine();
        return dimension;
    }


    public Position inputMove() throws NegativeNumberException, IOException, NumberFormatException{
        display.inputXCoordinateMessage();
        String stringInput = in.readLine();
        int x = Integer.parseInt(stringInput);
        display.inputYCoordinateMessage();
        stringInput = in.readLine();
        int y = Integer.parseInt(stringInput);
        if (x<1 || y<1) {
            throw new NegativeNumberException("The position you inserted is outside the board, please reinsert");
        }
        return at(x,y);
    }

    public boolean getAnswerForPieRule(Player currentPlayer) throws WrongAnswerException, IOException {
        display.askPieRuleMessage(currentPlayer.getName());
        String answer = in.readLine();
        if(answer.equals("y"))
            return true;
        else if(answer.equals("n"))
            return false;
        else
            throw new WrongAnswerException("Invalid answer: please reinsert");
    }

    public boolean playerWantsToApplyPieRule(Player currentPlayer) throws IOException {
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


    public String inputPlayerName(int whichPlayer) throws IOException {
        display.playerNameMessage(whichPlayer);
        return in.readLine();
    }

    public String inputPlayerName() throws IOException {
        display.playerNameMessage();
        return in.readLine();
    }


}

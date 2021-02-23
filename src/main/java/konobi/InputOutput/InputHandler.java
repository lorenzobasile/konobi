package konobi.InputOutput;

import konobi.Entities.Position;
import konobi.InputOutput.Exceptions.NegativeNumberException;
import konobi.InputOutput.Exceptions.WrongAnswerException;
import konobi.Entities.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import static konobi.Entities.Position.at;

public class InputHandler {

    public void setIn(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
    }

    private BufferedReader in;
    private Display display;

    public InputHandler(InputStream in, Display display){
        this.in = new BufferedReader(new InputStreamReader(in));
        this.display=display;
    }
    public int getDimension(){
        int dimension;
        try {
            dimension = inputDimension();
        } catch(NumberFormatException notANumber){
            display.notAnIntegerMessage();
            return getDimension();
        } catch(NegativeNumberException negativeDimension){
            display.printExceptionCause(negativeDimension);
            return getDimension();
        } catch(Exception generalException){
            display.printExceptionCause(generalException);
            return getDimension();
        }
        return dimension;
    }

    public int inputDimension() throws Exception{
        display.inputBoardDimensionMessage();
        String stringDimension = in.readLine();
        int dimension = Integer.parseInt(stringDimension);
        if (dimension < 1) {
            throw new NegativeNumberException("Negative dimension: please reinsert");
        }
        display.printEmptyLine();
        return dimension;
    }


    public Position inputMove() throws Exception{
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

    public boolean inputPie(Player currentPlayer) throws Exception{
        display.askPieRuleMessage(currentPlayer.getName());
        String answer = in.readLine();
        if(answer.equals("y"))
            return true;
        else if(answer.equals("n"))
            return false;
        else
            throw new WrongAnswerException("Invalid answer: please reinsert");
    }


    public String inputPlayerName(int whichPlayer) throws IOException {
        display.playerNameMessage(whichPlayer);
        return in.readLine();
    }


}

package konobi;

import java.io.InputStream;
import java.util.Scanner;

public class InputHandler {

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
        System.out.println("Enter x coordinate: ");
        int x = stdIn.nextInt();
        System.out.println("Enter y coordinate: ");
        int y = stdIn.nextInt();
        return Position.at(x,y);
    }

    public boolean inputPie(){
        Scanner stdIn = new Scanner(System.in);
        System.out.println("Do you want to apply the pie rule? (y/n) ");
        String answer = stdIn.nextLine();
        if(answer.equals("y"))
            return true;
        else if(answer.equals("n"))
            return false;
        else
            return inputPie();
    }


}

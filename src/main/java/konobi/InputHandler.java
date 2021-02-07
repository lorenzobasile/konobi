package konobi;

import java.util.Scanner;

public class InputHandler {


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

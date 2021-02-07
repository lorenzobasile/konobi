import java.util.Scanner;


public class InputHandler {



    public Position inputMove() {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter x coordinate: ");

        int x = myObj.nextInt();

        System.out.println("Enter y coordinate: ");

        int y = myObj.nextInt();

        return Position.at(x,y);
    }

    public boolean inputPie(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Do you want to apply the pie rule? : ");
        String answer = myObj.nextLine();
        return answer.equals("Yes");
    }
}

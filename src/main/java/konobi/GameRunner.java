package konobi;

public class GameRunner {

    public static void main(String[] args) {
        try {
            IoHandler ioHandler = new IoHandler();
            ioHandler.welcomeMessage();
            int inputDimension = ioHandler.inputDimension();
            Game game = new Game(inputDimension);
            game.singleTurn();
            game.changeTurn();
            game.showGameBoard();
            boolean answerPieRule = ioHandler.inputPie();
            if (answerPieRule) {
                game.applyPieRule();
            }
            else {
                game.singleTurn();
            }
            game.showGameBoard();
            do {
                game.changeTurn();
                game.singleTurn();
                game.showGameBoard();
            } while (!game.checkWin());
        } catch (Exception e) {};


        /*ììtry {
            game.singleTurn();

            do {
                game.singleTurn();
            } while (!game.checkWin());

        }
        catch(Exception e){}
        game.ioHandler.printBoard(game.board);
         */
        }
    }

    /*
    public void play() throws Exception{
        makeMove();
        pieRule();
        //while(true){
            //makeMove();
        //}
    }*/


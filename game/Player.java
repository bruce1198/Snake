package game;

public class Player {

    Snake[] snakes;
    int numberOfSnack;
    int numberOfPoint;
    Player(int number) {
        numberOfPoint = 0;
        numberOfSnack = number;
        snakes = new Snake[number];
        /*for(int i=0; i<number; i++) {
            snakes[i] = new Snake();
        }*/
        snakes[number-1] = new Snake();
    }
    public Snake getSnake() throws NotEnoughSnakeException{
        //System.out.println(numberOfSnack);
        if(numberOfSnack==0)
            throw new NotEnoughSnakeException();
        else {
            return snakes[numberOfSnack-1];
        }
    }
    public void getPoint() {
        numberOfPoint++;
    }
}
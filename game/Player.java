package game;

public class Player {

    Snake[] snakes;
    int numberOfSnack;
    int numberOfPoint;
    Player(int number) {
        numberOfPoint = 0;
        numberOfSnack = number;
        snakes = new Snake[number];
        snakes[number-1] = new Snake(0, 0, 0);
    }
    Player(int number, int id) {
        numberOfPoint = 0;
        numberOfSnack = number;
        snakes = new Snake[number];
        snakes[number-1] = new Snake(0, 580, 1);
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